package com.e2e.pages.authentication;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    public static String ERR_MESSAGE_XPATH = "//li[contains(text(),'%s')]";

    WebDriver driver;
    int waitTime = 0;

    @FindBy(id = "Input_Email")
    private WebElement inputEmail;

    @FindBy(id = "Input_Password")
    private WebElement inputPassword;

    @FindBy(xpath = "//button[contains(text(),'Log in')]")
    private WebElement loginButton;

    public LoginPage(WebDriver driver, int waitTime){
        this.driver = driver;
        this.waitTime = waitTime;
        PageFactory.initElements(driver, this);

    }


    public void enterUsername(String email){
        inputEmail.sendKeys(email);
    }

    public void enterPassword(String password){
        inputPassword.sendKeys(password);
    }


    public void clickLoginBtn(){
        loginButton.click();
    }

    public boolean isUserLogged(){
        WebElement logOut = driver.findElement(By.id("logout"));
        return logOut.isDisplayed();
    }

    public boolean isErrorMessageShown(String message){
        WebElement error = driver.findElement(By.xpath(String.format(ERR_MESSAGE_XPATH, message)));
        return error.isDisplayed();
    }

}
