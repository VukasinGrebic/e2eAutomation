package com.e2e.pages.authentication;

import com.e2e.utilities.SeleniumUtilities;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegistrationPage {

    public static String ERR_MESSAGE_XPATH = "//li[contains(text(),'%s')]";

    WebDriver driver;
    int waitTime = 0;

    @FindBy(id="Input_Email")
    private WebElement inputEmail;

    @FindBy(id="Input_Password")
    private WebElement inputPassword;

    @FindBy(id="Input_ConfirmPassword")
    private WebElement inputConfirmPassword;

    @FindBy(id="Input_Name")
    private WebElement inputName;

    @FindBy(id="Input_DOB")
    private WebElement inputDate;

    @FindBy(xpath = "//button[contains(text(),'Register')]")
    private WebElement btnRegister;

    public RegistrationPage(WebDriver driver, int waitTime){
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

    public void confirmPassword(String password){
        inputConfirmPassword.sendKeys(password);
    }

    public void enterFullName(String name){
        inputName.sendKeys(name);
    }

    public void enterBirthDate(String date){
        //inputDate.sendKeys(date);
        Actions actions = new Actions(driver);
        actions.click(inputDate).sendKeys(date).build().perform();
    }

    public void clickRegisterBtn(){
        btnRegister.click();
    }

    public boolean isUserRegistered(){
        WebElement logOut = driver.findElement(By.id("logout"));
        return logOut.isDisplayed();
    }

    public boolean isErrorMessageShown(String message){
        WebElement error = driver.findElement(By.xpath(String.format(ERR_MESSAGE_XPATH, message)));
        SeleniumUtilities.highlightControl(error, driver);
        return error.isDisplayed();
    }

}
