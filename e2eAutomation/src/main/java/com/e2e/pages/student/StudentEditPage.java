package com.e2e.pages.student;

import com.e2e.model.Student;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class StudentEditPage {

    WebDriver driver;
    int waitTime = 0;

    @FindBy(id="FirstMidName")
    private WebElement inputFirstName;

    @FindBy(id="LastName")
    private WebElement inputLastName;

    @FindBy(id="EnrollmentDate")
    private WebElement inputEnrollDate;

    @FindBy(xpath = "//input[@type='submit']")
    private WebElement btnSave;

    public StudentEditPage(WebDriver driver, int waitTime){
        this.driver = driver;
        this.waitTime = waitTime;
        PageFactory.initElements(driver, this);
    }

    public void editStudentsData(Student student){
        inputLastName.clear();
        inputLastName.sendKeys(student.getLastName());

        inputFirstName.clear();
        inputFirstName.sendKeys(student.getFirstName());

        Actions actions = new Actions(driver);
        actions.click(inputEnrollDate).sendKeys(student.getEnrollmentDate()).build().perform();

    }

    public void saveEditedStudentsData(){
        btnSave.click();
    }



}
