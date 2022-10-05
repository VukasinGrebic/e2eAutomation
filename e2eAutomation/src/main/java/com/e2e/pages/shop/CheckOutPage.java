package com.e2e.pages.shop;

import com.e2e.utilities.SeleniumUtilities;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;

public class CheckOutPage {
    public static String MESSAGE_XPATH = "//p[contains(text(),'%s')]";
    public static String ERR_MESSAGE_XPATH = "//span[contains(text(),'%s')]";

    WebDriver driver;

    WebDriverWait wait;
    int waitTime = 5;

    @FindBy(xpath = "//li[contains(@class, 'item')]/button[contains(@class,'action primary checkout')]")
    public WebElement proceedToCheckoutButton;

    @FindBy(xpath = "//button[contains(@class,'button action continue primary')]")
    private WebElement nextButton;
    @FindBy(xpath = "//button[contains(@title,'Add to Cart')]")
    private WebElement addToCartButton;

    @FindBy(name = "firstname")
    public WebElement firstName;


    @FindBy(name = "lastname")
    private WebElement lastName;

    @FindBy(name = "street[0]")
    private WebElement streetAddress;

    @FindBy(name = "city")
    private WebElement city;

    @FindBy(name = "region_id")
    private WebElement state;

    @FindBy(name = "postcode")
    private WebElement zip;

    @FindBy(name = "telephone")
    private WebElement phone;

    @FindBy(xpath = "//button[contains(@title, 'Place Order')]")
    private WebElement placeOrder;

    @FindBy(xpath = "//div[@class='product-add-form']//div[@class='swatch-option text']")
    private WebElement size;

    @FindBy(xpath = "//div[contains(@class, 'shipping-address-item selected-item')]")
    public WebElement addressPresent;

    public WebElement getAddItemButton(int index) {
        ArrayList<WebElement> addItem = new ArrayList<WebElement>(driver.findElements(By.xpath("//button[contains(@title, 'Add to Cart')]")));
        return addItem.get(index);
    }

    public WebElement getSize(int index) {
        ArrayList<WebElement> chooseSize = new ArrayList<WebElement>(driver.findElements(By.xpath("//div[@class='product-add-form']//div[@class='swatch-option text']")));
        return chooseSize.get(index);
    }

    public WebElement getColor(int index) {
        ArrayList<WebElement> chooseColor = new ArrayList<WebElement>(driver.findElements(By.xpath("//div[@class='swatch-option color']")));
        return chooseColor.get(index);
    }


    public WebElement getShippingMethods (int index) {
        ArrayList<WebElement> chooseShippingMethod = new ArrayList<WebElement>(driver.findElements(By.xpath("//input[contains(@type,'radio')]")));
        return chooseShippingMethod.get(index);
    }

    public CheckOutPage(WebDriver driver, WebDriverWait wait, int waitTime) {
        this.driver = driver;
        this.wait = wait;
        this.waitTime = waitTime;
        PageFactory.initElements(driver, this);
    }

    public void addItem (int index) {
        getAddItemButton(index).click();
    }

    public void selectSize (int index) {
        wait.until(ExpectedConditions.elementToBeClickable(getSize(index)));
        getSize(index).click();
    }

    public void selectSize () {
        wait.until(ExpectedConditions.elementToBeClickable(size));
        size.click();
    }

    public void selectColor (int index) {
        getColor(index).click();
    }

    public void clickProceedToCheckoutButton () {
        wait.until(ExpectedConditions.elementToBeClickable(proceedToCheckoutButton));
        proceedToCheckoutButton.click();
    }

    public void clickAddToCartButton () {
        addToCartButton.click();
    }

    public void enterFirstName (String fName) {
        firstName.clear();
        firstName.sendKeys(fName);
    }

    public void enterLastName (String lName) {
        lastName.clear();
        lastName.sendKeys(lName);
    }

    public void enterStreet (String streetA) {
        streetAddress.clear();
        streetAddress.sendKeys(streetA);
    }

    public void enterCity (String homeCity) {
        city.clear();
        city.sendKeys(homeCity);
    }

    public void selectState(String state) {
        Select dropdownCountry = new Select(driver.findElement(By.name("region_id")));
        dropdownCountry.selectByVisibleText(state);
    }

    public void enterZip (String zipCode) {
        zip.clear();
        zip.sendKeys(zipCode);
    }

    public void selectCountry(String country) {
        Select dropdownCountry = new Select(driver.findElement(By.name("country_id")));
        dropdownCountry.selectByVisibleText(country);
    }

    public void selectCountryBlank(int index) {
        Select dropdownCountry = new Select(driver.findElement(By.name("country_id")));
        dropdownCountry.selectByIndex(index);
    }

    public void enterPhone (String phoneNumber) {
        phone.clear();
        phone.sendKeys(phoneNumber);
    }

    public void clickNext(){
        nextButton.click();
    }

    public void clickPlaceOrder () {
        wait.until(ExpectedConditions.elementToBeClickable(placeOrder));
        placeOrder.click();
    }

    public void selectShippingMethod (int index) {
        getShippingMethods(index).click();
    }

    public boolean isOrdered(String message){
        WebElement error = driver.findElement(By.xpath(String.format(MESSAGE_XPATH, message)));
        return error.isDisplayed();
    }

    public boolean isErrorMessageShown(String message){
        WebElement error = driver.findElement(By.xpath(String.format(ERR_MESSAGE_XPATH, message)));
        SeleniumUtilities.highlightControl(error, driver);
        return error.isDisplayed();
    }
}
