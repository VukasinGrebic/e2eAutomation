package com.e2e.pages.student;

import com.e2e.config.ConfigurationManager;
import com.e2e.utilities.SeleniumUtilities;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class StudentDetailsPage {

    private static String CTRL_XPATH = "//dt[contains(text(),'%s')]/following-sibling::dd[contains(text(),'%s')]";
    private static String FIRST_NAME = "First Name";
    private static String LAST_NAME = "Last Name";
    private static String ENROLL_DATE = "Enrollment Date";

    WebDriver driver;
    int waitTime = 0;

    private WebElement inputLastName;
    private WebElement inputFirstName;
    private WebElement inputEnrollDate;

    public StudentDetailsPage(WebDriver driver, int waitTime) {
        this.driver = driver;
        this.waitTime = waitTime;
        PageFactory.initElements(driver, this);
    }

    public boolean checkFirstName(String firstName) {
        String xpath = String.format(CTRL_XPATH, FIRST_NAME, firstName);

        inputFirstName = (new WebDriverWait(driver, Duration.ofSeconds(waitTime))).
                until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
        SeleniumUtilities.highlightControl(inputFirstName, driver);

        return inputFirstName.isDisplayed();
    }

    public boolean checkLastName(String lastName) {
        String xpath = String.format(CTRL_XPATH, LAST_NAME, lastName);

        inputLastName = (new WebDriverWait(driver, Duration.ofSeconds(waitTime))).
                until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
        SeleniumUtilities.highlightControl(inputLastName, driver);

        return inputLastName.isDisplayed();
    }

    public boolean checkEnrollmentDate(String eDate) {
        String xpath = String.format(CTRL_XPATH, ENROLL_DATE, eDate);

        inputEnrollDate = (new WebDriverWait(driver, Duration.ofSeconds(waitTime))).
                until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));

        if (ConfigurationManager.configuration().ctrlHighLight()) {
            SeleniumUtilities.highlightControl(inputEnrollDate, driver);
        }

        return inputEnrollDate.isDisplayed();
    }


}
