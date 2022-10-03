package com.e2e.pages.authentication;

import com.e2e.utilities.SeleniumUtilities;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegistrationPage {

    public static String ERR_MESSAGE_XPATH = "//div[contains(text(),'%s')]";

    WebDriver driver;
    int waitTime = 0;

    @FindBy(id="firstname")
    private WebElement inputFirstname;

    @FindBy(id="lastname")
    private WebElement inputLastname;

    @FindBy(id="email_address")
    private WebElement inputEmail;

    @FindBy(id="password")
    private WebElement inputPassword;

    @FindBy(id="password-confirmation")
    private WebElement inputPasswordConfirmation;

    @FindBy(xpath = "//button[contains(@class,'submit')]")
    private WebElement btnRegister;

    public RegistrationPage(WebDriver driver, int waitTime){
        this.driver = driver;
        this.waitTime = waitTime;
        PageFactory.initElements(driver, this);
    }

    public void enterFirstName(String firstName){
        inputFirstname.sendKeys(firstName);
    }

    public void enterLastName(String lastName){
        inputLastname.sendKeys(lastName);
    }

    public void enterPassword(String password){
        inputPassword.sendKeys(password);
    }


    public void confirmPassword(String password){
        inputPasswordConfirmation.sendKeys(password);
    }

    public void enterEmail(String email){
        inputEmail.sendKeys(email);
    }


    public void clickRegisterBtn(){
        btnRegister.click();
    }

    public boolean isUserRegistered(){
        WebElement successMsg = driver.findElement(By.xpath("//div[contains(@class,'message-success success message')]"));
        return successMsg.isDisplayed();
    }

    public boolean isErrorMessageShown(String message){
        WebElement error = driver.findElement(By.xpath(String.format(ERR_MESSAGE_XPATH, message)));
        SeleniumUtilities.highlightControl(error, driver);
        return error.isDisplayed();
    }

}
