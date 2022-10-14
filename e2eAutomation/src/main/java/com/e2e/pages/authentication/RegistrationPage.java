package com.e2e.pages.authentication;

import com.e2e.utilities.SeleniumUtilities;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegistrationPage {

    public static String ERR_MESSAGE_XPATH = "//div[contains(text(),'%s')]";

    WebDriver driver;
    int waitTime = 0;

    @FindBy(id="firstname")
    private WebElement loc_txtFirstname;

    @FindBy(id="lastname")
    private WebElement loc_txtLastname;

    @FindBy(id="email_address")
    private WebElement loc_txtEmail;

    @FindBy(id="password")
    private WebElement loc_txtPassword;

    @FindBy(id="password-confirmation")
    private WebElement loc_txtPasswordConfirmation;

    @FindBy(xpath = "//button[contains(@class,'submit')]")
    private WebElement loc_btnRegister;

    public RegistrationPage(WebDriver driver, int waitTime){
        this.driver = driver;
        this.waitTime = waitTime;
        PageFactory.initElements(driver, this);
    }

    public void enterFirstName(String firstName){
        loc_txtFirstname.sendKeys(firstName);
    }

    public void enterLastName(String lastName){
        loc_txtLastname.sendKeys(lastName);
    }

    public void enterPassword(String password){
        loc_txtPassword.sendKeys(password);
    }


    public void confirmPassword(String password){
        loc_txtPasswordConfirmation.sendKeys(password);
    }

    public void enterEmail(String email){
        loc_txtEmail.sendKeys(email);
    }


    public void clickRegisterBtn(){
        loc_btnRegister.click();
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
