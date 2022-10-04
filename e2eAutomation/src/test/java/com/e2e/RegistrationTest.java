package com.e2e;

import com.e2e.model.TestUser;
import com.e2e.pages.authentication.RegistrationPage;
import com.e2e.utilities.FrontActionsUtil;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class RegistrationTest extends TestBase {

    RegistrationPage registerPage;

    String registrationUrl = "https://magento.softwaretestingboard.com/customer/account/create/";
    public static TestUser testUser;

    String firstName = "Namek";

    String lastName = "LNamek";

    String email = "test333@gmail.com";
    String password = "Pw82k38sd";




    @Test(priority = 2,
            enabled = true,
            description = "Successful user account registration")
    public void testRegister_Success()  {

        testUser = new TestUser(firstName,lastName,email,password);
        registerPage = new RegistrationPage(driver, waitTime);

        driver.get(registrationUrl);

        registerPage.enterFirstName(testUser.getFirstName());
        registerPage.enterLastName(testUser.getLastName());
        registerPage.enterEmail(testUser.getEmail());
        registerPage.enterPassword(testUser.getPassword());
        registerPage.confirmPassword(testUser.getPassword());
        registerPage.clickRegisterBtn();

        Assert.assertTrue(registerPage.isUserRegistered());
    }

    @Test(priority = 1,
            enabled = true,
            dataProvider = "invalidRegistrationData",
            description = "Unsuccessful user account registration")
    public void testRegister_Failed(String description, String firstName, String lastName, String email, String password, String errMessage){

        registerPage = new RegistrationPage(driver, waitTime);
        driver.get(registrationUrl);

        registerPage.enterFirstName(firstName);
        registerPage.enterLastName(lastName);
        registerPage.enterEmail(email);
        registerPage.enterPassword(password);
        registerPage.confirmPassword(password);
//         For demo purposes, slow down here to see results on page
        if(DEMO){ try { Thread.sleep(demoWait); } catch (InterruptedException e) { e.printStackTrace(); } }
        registerPage.clickRegisterBtn();

        boolean error = registerPage.isErrorMessageShown(errMessage);
        Assert.assertTrue(error);
    }


    @DataProvider(name = "invalidRegistrationData")
    public Object[][] invalidRegistrationData(){
        Object[][] data = {
                {"Missing firstName", "", "LNames", "test90@mail.com", "ksajdka2", "This is a required field."},
                {"Missing lastName", "Fnames", "", "teskl@mail.com", "jkasjdkn", "This is a required field."},
                {"Missing email", "Fname", "Lnamess", "", "jksajdkn29", "This is a required field."},
                {"Missing password", "Fnamesd", "Lnamom", "testm@mail.com", "", "This is a required field."}
        };
        return data;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
