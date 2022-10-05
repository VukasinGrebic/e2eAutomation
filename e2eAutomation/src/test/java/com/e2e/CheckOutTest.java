package com.e2e;
import com.e2e.pages.authentication.LoginPage;
import com.e2e.pages.shop.CheckOutPage;
import com.e2e.utilities.MouseHover;
import com.e2e.utilities.FrontActionsUtil;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CheckOutTest extends TestBase {

    CheckOutPage checkoutPage;
    LoginPage loginPage;

    FrontActionsUtil frontActionsUtil;

    MouseHover mouseHover;

    String checkOutUrl = "https://magento.softwaretestingboard.com/";
    String loginUrl = "https://magento.softwaretestingboard.com/customer/account/login/referer/aHR0cHM6Ly9tYWdlbnRvLnNvZnR3YXJldGVzdGluZ2JvYXJkLmNvbS9jdXN0b21lci9hY2NvdW50L2NyZWF0ZS8%2C/";

    String cartUrl = "https://magento.softwaretestingboard.com/checkout/cart/";

    String fName = "FNames";

    String lName = "LNames";

    String street = "Kom osa 2/2";

    String city = "Atlanta";

    String state = "Alabama";

    String zip = "12345";

    String country = "United States";

    String phone = "909023902";

    String msg = "Your order number is";


    @Test(priority = 2,
            enabled = true,
            description = "Successful adding to cart and ordering with logged in account")
    public void testCheckOut_Success() throws InterruptedException {

        RegistrationTest registrationTest = new RegistrationTest();
        String username = registrationTest.getEmail();
        String password = registrationTest.getPassword();

        loginPage = new LoginPage(driver, waitTime);
        checkoutPage = new CheckOutPage(driver, wait, waitTime);
        mouseHover = new MouseHover(driver, wait);
        frontActionsUtil = new FrontActionsUtil();

        driver.get(loginUrl);

        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLoginBtn();

        driver.get(checkOutUrl);
        mouseHover.mouseHoverAddItem(1);
        checkoutPage.addItem(1);
        checkoutPage.selectSize();
        checkoutPage.selectColor(1);
        checkoutPage.clickAddToCartButton();

        driver.get(cartUrl);
        checkoutPage.clickProceedToCheckoutButton();
         if (!checkoutPage.addressPresent.isDisplayed()) {
            checkoutPage.enterFirstName(fName);
            checkoutPage.enterLastName(lName);
            checkoutPage.enterStreet(street);
            checkoutPage.enterCity(city);
            checkoutPage.selectState(state);
            checkoutPage.enterZip(zip);
            checkoutPage.selectCountry(country);
            checkoutPage.enterPhone(phone);
        }

        checkoutPage.selectShippingMethod(1);
        checkoutPage.clickNext();
        Thread.sleep(5000);

        if (DEMO) {
            try {
                Thread.sleep(demoWait);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        checkoutPage.clickPlaceOrder();
        Assert.assertTrue(checkoutPage.isOrdered(msg));

    }
    @Test(priority = 2,
            enabled = true,
            dataProvider = "checkOutFailed",
            description = "Unsuccessful info when ordering with logged in account")
    public void testShopping_Failed(String description,
                                    String fName, String lName, String street, String city, String state,
                                    String zip, String country, String phone, String errMessage)throws InterruptedException {

        RegistrationTest registrationTest = new RegistrationTest();
        String username = registrationTest.getEmail1();
        String password = registrationTest.getPassword1();

        loginPage = new LoginPage(driver, waitTime);
        checkoutPage = new CheckOutPage(driver, wait, waitTime);
        mouseHover = new MouseHover(driver, wait);
        frontActionsUtil = new FrontActionsUtil();
        if(description.equals("Missing first name")){
            driver.get(loginUrl);
            loginPage.enterUsername(username);
            loginPage.enterPassword(password);
            loginPage.clickLoginBtn();
        }

        driver.get(checkOutUrl);
        mouseHover.mouseHoverAddItem(1);
        checkoutPage.addItem(1);
        checkoutPage.selectSize();
        checkoutPage.selectColor(1);
        checkoutPage.clickAddToCartButton();

        driver.get(cartUrl);
        checkoutPage.clickProceedToCheckoutButton();
        checkoutPage.enterFirstName(fName);
        checkoutPage.enterLastName(lName);
        checkoutPage.enterStreet(street);
        checkoutPage.enterCity(city);
        checkoutPage.selectState(state);
        checkoutPage.enterZip(zip);
        if (description.equals("Missing country")){
            checkoutPage.selectCountryBlank(0);
        }
        checkoutPage.selectCountry(country);
        checkoutPage.enterPhone(phone);
        checkoutPage.selectShippingMethod(1);
        if (DEMO) {
            try {
                Thread.sleep(demoWait);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        checkoutPage.clickNext();

        boolean error = checkoutPage.isErrorMessageShown(errMessage);
        Assert.assertTrue(error);

    }

    @DataProvider(name = "checkOutFailed")
    public Object[][] shoppingFailed() {
        Object[][] data = {
                {"Missing first name","", "Lname", "Street", "City", "Alabama", "12345",
                        "United States", "0601234512", "This is a required field."},
                {"Missing last name","Fname", "", "Street", "City", "Alabama", "12345",
                        "United States", "92813918329", "This is a required field."},
                {"Missing street address","Fname", "Lname", "", "City", "Alabama", "12345",
                        "United States", "92813918329", "This is a required field."},
                {"Missing city","Fname", "Lname", "Street", "", "Alabama", "12345",
                        "United States", "92813918329", "This is a required field."},
                {"Missing state","Fname", "Lname", "Street", "City", "Please select a region, state or province.", "12345",
                        "United States", "92813918329", "This is a required field."},
                {"Missing zip","Fname", "Lname", "Street", "City", "Alabama", "",
                        "United States", "92813918329", "This is a required field."},
                {"Missing country","Fname", "Lname", "Street", "City", "Alabama", "12345",
                        "", "92813918329", "This is a required field."},
                {"Missing phone","Fname", "Lname", "Street", "City", "Alabama", "12345",
                        "United States", "", "This is a required field."},


        };
        return data;
    }

}
