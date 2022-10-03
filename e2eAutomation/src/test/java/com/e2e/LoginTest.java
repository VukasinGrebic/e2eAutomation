package com.e2e;

import com.e2e.pages.authentication.LoginPage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTest extends TestBase {

    LoginPage loginPage;
    String loginUrl = "https://magento.softwaretestingboard.com/customer/account/login/referer/aHR0cHM6Ly9tYWdlbnRvLnNvZnR3YXJldGVzdGluZ2JvYXJkLmNvbS9jdXN0b21lci9hY2NvdW50L2NyZWF0ZS8%2C/";

    @Test(priority = 2,
            enabled = true,
            description = "Successful basic login with valid credentials")
    public void testLogin_Success()  {
        // --- Arrange ---
        RegistrationTest registrationTest = new RegistrationTest();
        String username = registrationTest.getEmail();
        String password = registrationTest.getPassword();

        loginPage = new LoginPage(driver, waitTime);
        driver.get(loginUrl);

        // --- ACT ---
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        // For demo purposes, slow down here to see results on page
        if (DEMO) {
            try {
                Thread.sleep(demoWait);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        loginPage.clickLoginBtn();
        if (DEMO) {
            try {
                Thread.sleep(demoWait);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // --- Assert ---
        Assert.assertTrue(loginPage.isUserLogged());
    }

    @Test(priority = 1,
            enabled = true,
            dataProvider = "invalidLoginData",
            description = "Unsuccessful login with invalid data provided")
    public void testLogin_Failed(String description, String username, String password, String errMessage) throws InterruptedException {
        // --- Arrange ---
        loginPage = new LoginPage(driver, waitTime);
        driver.get(loginUrl);

        // --- ACT ---
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        // For demo purposes, slow down here to see results on page
        if (DEMO) {
            try {
                Thread.sleep(demoWait);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        loginPage.clickLoginBtn();

        // --- Assert ---
        boolean error = loginPage.isErrorMessageShown(errMessage);
        Assert.assertTrue(error);
    }

    @DataProvider(name = "invalidLoginData")
    public Object[][] invalidLoginData() {
        Object[][] data = {
                {"Missing username", "", "passSord92389", "A login and a password are required."},
                {"Missing password", "trainer@example.com", "", "This is a required field."}
        };
        return data;
    }

}
