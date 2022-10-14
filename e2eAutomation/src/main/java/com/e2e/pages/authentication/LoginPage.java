package com.e2e.pages.authentication;

import com.e2e.utilities.SeleniumUtilities;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    public static String ERR_MESSAGE_XPATH = "//div[contains(text(),'%s')]";

    WebDriver driver;
    WebDriverWait wait;
    int waitTime = 5000;

    @FindBy(id = "email")
    private WebElement loc_txtEmail;

    @FindBy(id = "pass")
    private WebElement loc_txtPassword;

    @FindBy(xpath = "//button[contains(@class,'action login primary')]")
    private WebElement loc_btnLogin;

    public LoginPage(WebDriver driver, int waitTime){
        this.driver = driver;
        this.waitTime = waitTime;
        PageFactory.initElements(driver, this);

    }


    public void enterUsername(String email){
        loc_txtEmail.sendKeys(email);
    }

    public void enterPassword(String password){
        loc_txtPassword.sendKeys(password);
    }


    public void clickLoginBtn(){
        loc_btnLogin.click();
    }

    public boolean isUserLogged(){
        WebElement logOut = driver.findElement(By.xpath("//span[contains(@class,'logged-in')]"));
        return logOut.isDisplayed();
    }

    public boolean isErrorMessageShown(String message){
        WebElement error = driver.findElement(By.xpath(String.format(ERR_MESSAGE_XPATH, message)));
        SeleniumUtilities.highlightControl(error, driver);
        return error.isDisplayed();
    }

}
