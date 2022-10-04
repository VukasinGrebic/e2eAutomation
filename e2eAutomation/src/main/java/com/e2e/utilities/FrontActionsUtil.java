package com.e2e.utilities;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * The Actions API utility class - common domain situations which requires action API to be performed
 */
public class FrontActionsUtil {

    protected WebDriver driver;
    protected WebDriverWait wait;

    /**
     * Click on control using Actions API
     * @param driver    Running instance of a driver
     * @param element   Element on which action will be performed
     */
    public static void actionClick(WebDriver driver, WebElement element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element).click().build().perform();
    }

    /**
     * Click on control using Actions API in two distinct steps: (1)  move to control (2) click on it
     * @param driver    Running instance of a driver
     * @param element   Element on which action will be performed
     */
    public static void move2ElementAndClick(WebDriver driver, WebElement element){
        Actions actions = new Actions(driver);
        actions.moveToElement(element).build().perform();
        actions.click(element).build().perform();
    }

    public static void actionScroll2ElementAndClick(WebDriver driver, WebElement element){
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        actionClick(driver, element);
    }

    /**
     * Send text to control using Actions API
     * @param driver    Running instance of a driver
     * @param element   Element on which action will be performed
     * @param keys      Sequence of character to be sent to the control/element
     * @param clickEnter if true - simulate click on `Enter` key
     */
    public static void actionSendKeys(WebDriver driver, WebElement element, String keys, boolean clickEnter) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
        actions.click().sendKeys(keys).perform();
        if(clickEnter){ actions.sendKeys(Keys.ENTER).perform(); }
    }

    public static void actionScroll2ElementSendKeys(WebDriver driver, WebElement element, String keys, boolean clickEnter){
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        actionSendKeys(driver, element, keys, clickEnter);
    }

    public static void actionScroll2Element(WebDriver driver, WebElement element){
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public static void actionSwitchToElement(WebDriver driver, WebElement element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element).build().perform();
    }

    public static void clickOnElementByJS(WebDriver driver, WebElement element){
        ((JavascriptExecutor) driver).executeScript("arguments[0].click(true);", element);
    }

    public void waitForElement(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

}
