package com.e2e.pages.student;

import com.e2e.model.Student;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class StudentDeletePage {

    WebDriver driver;
    int waitTime = 0;

    private static String CTRL_XPATH = "//dt[contains(text(),'%s')]/following-sibling::dd[contains(text(),'%s')]";
    private static String FIRST_NAME = "First Name";
    private static String LAST_NAME = "Last Name";
    private static String ENROLL_DATE = "Enrollment Date";

    private WebElement inputLastName;
    private WebElement inputFirstName;
    private WebElement inputEnrollDate;

    @FindBy(xpath = "//input[@type='submit']")
    private WebElement btnDelete;

    public StudentDeletePage(WebDriver driver, int waitTime){
        this.driver = driver;
        this.waitTime = waitTime;
        PageFactory.initElements(driver, this);
    }

    public void deleteExistingStudent(Student student){
        // todo: check first is it required student

        String xpath = String.format(CTRL_XPATH, FIRST_NAME, student.getFirstName());
        inputFirstName = (new WebDriverWait(driver, Duration.ofSeconds(waitTime))).
                until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));

        xpath = String.format(CTRL_XPATH, LAST_NAME, student.getLastName());
        inputLastName = (new WebDriverWait(driver, Duration.ofSeconds(waitTime))).
                until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));

        if(inputFirstName.isDisplayed() && inputLastName.isDisplayed()){
            btnDelete.click();
        }
    }

}
