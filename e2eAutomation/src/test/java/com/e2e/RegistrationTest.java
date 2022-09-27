package com.e2e;

import com.e2e.model.TestUser;
import com.e2e.pages.authentication.RegistrationPage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class RegistrationTest extends TestBase {

    RegistrationPage registerPage;
    String registrationUrl = "http://wagner.wang/Identity/Account/Register";
    public static TestUser testUser;
    String username = "test003@example.com";
    String password = "Demo!1234";
    String name = "Demo";
    String birthDate = "01/01/1999";

    @Test(priority = 2,
            enabled = true,
            description = "Successful user account registration")
    public void testRegister_Success(){

        testUser = new TestUser(username,password, name, birthDate);
        registerPage = new RegistrationPage(driver, waitTime);
        driver.get(registrationUrl);

        registerPage.enterUsername(testUser.getUsername());
        registerPage.enterPassword(testUser.getPassword());
        registerPage.confirmPassword(testUser.getPassword());
        registerPage.enterFullName(testUser.getName());
        registerPage.enterBirthDate(testUser.getBirthDate());
        registerPage.clickRegisterBtn();

        Assert.assertTrue(registerPage.isUserRegistered());
    }

    @Test(priority = 1,
            enabled = true,
            dataProvider = "invalidRegistrationData",
            description = "Unsuccessful user account registration")
    public void testRegister_Failed(String description, String username, String password, String fullName, String birthDate, String errMessage){

        registerPage = new RegistrationPage(driver, waitTime);
        driver.get(registrationUrl);

        registerPage.enterUsername(username);
        registerPage.enterPassword(password);
        registerPage.confirmPassword(password);
        registerPage.enterFullName(fullName);
        registerPage.enterBirthDate(birthDate);
        // For demo purposes, slow down here to see results on page
        if(DEMO){ try { Thread.sleep(demoWait); } catch (InterruptedException e) { e.printStackTrace(); } }
        registerPage.clickRegisterBtn();

        boolean error = registerPage.isErrorMessageShown(errMessage);
        Assert.assertTrue(error);
    }


    @DataProvider(name = "invalidRegistrationData")
    public Object[][] invalidRegistrationData(){
        Object[][] data = {
                {"Missing email", "", "Demo!1234", "Demo", "01/01/1999", "The Email field is required."},
                {"Missing password", "trainer@example.com", "", "Demo", "01/01/1999", "The Password field is required."},
                {"Missing full name", "trainer@example.com", "Demo!1234", "", "01/01/1999", "The Full name field is required."},
                {"Missing birth date", "trainer@example.com", "Demo!1234", "Demo", "", "The Birth Date field is required."}
        };
        return data;
    }
}
