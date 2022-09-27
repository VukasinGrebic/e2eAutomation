package com.e2e.pages.student;

import com.e2e.model.Student;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class StudentCreatePage {
    WebDriver driver;
    int waitTime = 0;

    @FindBy(id="FirstMidName")
    private WebElement inputFirstName;

    @FindBy(id="LastName")
    private WebElement inputLastName;

    @FindBy(id="EnrollmentDate")
    private WebElement inputEnrollDate;

    @FindBy(xpath = "//input[@type='submit']")
    private WebElement btnCreate;

    public StudentCreatePage(WebDriver driver, int waitTime){
        this.driver = driver;
        this.waitTime = waitTime;
        PageFactory.initElements(driver, this);
    }

    public void enterNewStudentData(String firstName, String lastName, String enrollDate){
        inputLastName.sendKeys(lastName);
        inputFirstName.sendKeys(firstName);
        Actions actions = new Actions(driver);
        actions.click(inputEnrollDate).sendKeys(enrollDate).build().perform();
    }

    public void enterNewStudentData(Student student){
        inputLastName.sendKeys(student.getLastName());
        inputFirstName.sendKeys(student.getFirstName());
        Actions actions = new Actions(driver);
        actions.click(inputEnrollDate).sendKeys(student.getEnrollmentDate()).build().perform();
    }

    public void createNewStudent(){
        btnCreate.click();
    }

}
