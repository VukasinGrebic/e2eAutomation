package com.e2e.pages.shop;

import com.e2e.utilities.FrontActionsUtil;
import com.e2e.utilities.SeleniumUtilities;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;

public class CheckOutPage {
    public static String MESSAGE_XPATH = "//p[contains(text(),'%s')]";
    public static String ERR_MESSAGE_XPATH = "//span[contains(text(),'%s')]";

    WebDriver driver;

    WebDriverWait wait;
    int waitTime = 5;

    @FindBy(xpath = "//li[contains(@class, 'item')]/button[contains(@class,'action primary checkout')]")
    public WebElement loc_btnProceedToCheckout;

    @FindBy(xpath = "//button[contains(@class,'button action continue primary')]")
    private WebElement loc_btnNext;
    @FindBy(xpath = "//button[contains(@title,'Add to Cart')]")
    private WebElement loc_btnAddToCart;

    @FindBy(name = "firstname")
    public WebElement loc_txtFirstName;


    @FindBy(name = "lastname")
    private WebElement loc_txtLastName;

    @FindBy(name = "street[0]")
    private WebElement loc_txtStreetAddress;

    @FindBy(name = "city")
    private WebElement loc_txtCity;

    @FindBy(name = "region_id")
    private WebElement loc_txtState;

    @FindBy(name = "postcode")
    private WebElement loc_txtZip;

    @FindBy(name = "telephone")
    private WebElement loc_txtPhone;

    @FindBy(name = "region_id")
    private WebElement loc_ddState;

    @FindBy(name = "country_id")
    private WebElement loc_ddCountry;

    @FindBy(xpath = "//button[contains(@title, 'Place Order')]")
    private WebElement loc_btnPlaceOrder;

    @FindBy(xpath = "//div[@class='product-add-form']//div[@class='swatch-option text']")
    private WebElement loc_rbtnSize;

    @FindBy(xpath = "//div[contains(@class, 'shipping-address-item selected-item')]")
    public WebElement loc_lbAddressPresent;

    public WebElement loc_btnAddItem(int index) {
        ArrayList<WebElement> addItem = new ArrayList<WebElement>(driver.findElements(By.xpath("//button[contains(@title, 'Add to Cart')]")));
        return addItem.get(index);
    }

    public WebElement loc_rbtnSize(int index) {
        ArrayList<WebElement> chooseSize = new ArrayList<WebElement>(driver.findElements(By.xpath("//div[@class='product-add-form']//div[@class='swatch-option text']")));
        return chooseSize.get(index);
    }

    public WebElement loc_rbtnColor(int index) {
        ArrayList<WebElement> chooseColor = new ArrayList<WebElement>(driver.findElements(By.xpath("//div[@class='swatch-option color']")));
        return chooseColor.get(index);
    }


    public WebElement loc_rbtnShippingMethods(int index) {
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
        loc_btnAddItem(index).click();
    }

    public void selectSize (int index) {
        FrontActionsUtil.waitForElement(loc_rbtnSize(index), driver, waitTime);
        loc_rbtnSize(index).click();
    }

    public void selectSize () {
        FrontActionsUtil.waitForElement(loc_rbtnSize, driver, waitTime);
        loc_rbtnSize.click();
    }

    public void selectColor (int index) {
        loc_rbtnColor(index).click();
    }

    public void clickProceedToCheckoutButton () {
        FrontActionsUtil.waitForElement(loc_btnProceedToCheckout, driver, waitTime);
        loc_btnProceedToCheckout.click();
    }

    public void clickAddToCartButton () {
        loc_btnAddToCart.click();
    }

    public void enterFirstName (String fName) {
        loc_txtFirstName.clear();
        loc_txtFirstName.sendKeys(fName);
    }

    public void enterLastName (String lName) {
        loc_txtLastName.clear();
        loc_txtLastName.sendKeys(lName);
    }

    public void enterStreet (String streetA) {
        loc_txtStreetAddress.clear();
        loc_txtStreetAddress.sendKeys(streetA);
    }

    public void enterCity (String homeCity) {
        loc_txtCity.clear();
        loc_txtCity.sendKeys(homeCity);
    }

    public void selectState(String state) {
        FrontActionsUtil.selectElementByVisibleText(loc_ddState,state);
    }

    public void enterZip (String zipCode) {
        loc_txtZip.clear();
        loc_txtZip.sendKeys(zipCode);
    }

    public void selectCountry(String country) {
        FrontActionsUtil.selectElementByVisibleText(loc_ddCountry,country);
    }

    public void selectCountryBlank(int index) {
        FrontActionsUtil.selectElementByIndex(loc_ddCountry,index);
    }

    public void enterPhone (String phoneNumber) {
        loc_txtPhone.clear();
        loc_txtPhone.sendKeys(phoneNumber);
    }

    public void clickNext(){
        loc_btnNext.click();
    }

    public void clickPlaceOrder () {
        FrontActionsUtil.waitForElement(loc_btnPlaceOrder, driver, waitTime);
        loc_btnPlaceOrder.click();
    }

    public void selectShippingMethod (int index) {
        loc_rbtnShippingMethods(index).click();
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
