package com.e2e.utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;

public class MouseHover {

    WebDriver driver;
    WebDriverWait wait;

    public WebElement getAddItemButton(int index) {
        ArrayList<WebElement> addItem = new ArrayList<WebElement>(driver.findElements(By.xpath("//div[contains(@class, 'product-item-info')]")));
        return addItem.get(index);
    }

    public MouseHover(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void mouseHoverAddItem(int index) {
        Actions action = new Actions(driver);
        action.moveToElement(getAddItemButton(index)).perform();
    }
}
