package com.e2e;

import com.e2e.pages.authentication.LoginPage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTest extends TestBase {

    LoginPage loginPage;
    String loginUrl = "http://wagner.wang/Identity/Account/Login";

    @Test(priority = 2,
            enabled = true,
            description = "Successful basic login with valid credentials")
    public void testLogin_Success() {
        // --- Arrange ---
        String username = RegistrationTest.testUser.getUsername();
        String password = RegistrationTest.testUser.getPassword();
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
            enabled = false,
            dataProvider = "invalidLoginData",
            description = "Unsuccessful login with invalid data provided")
    public void testLogin_Failed(String description, String username, String password, String errMessage) {
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
                {"Missing username", "", "Demo!1234", "The Email field is required."},
                {"Missing password", "trainer@example.com", "", "The Password field is required."}
        };
        return data;
    }

}
