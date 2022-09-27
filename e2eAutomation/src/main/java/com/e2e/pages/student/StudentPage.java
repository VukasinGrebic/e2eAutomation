package com.e2e.pages.student;

import com.e2e.utilities.SeleniumUtilities;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class StudentPage {

    public static String CRUD_OPTION_XPATH = "//td[contains(text(),'%s')]/following-sibling::td[contains(text(),'%s')]/..//a[contains(text(),'%s')]";
    public static String READ_OPTION = "Details";
    public static String EDIT_OPTION = "Edit";
    public static String DELETE_OPTION = "Delete";

    WebDriver driver;
    int waitTime = 0;

    @FindBy(xpath = "//a[contains(text(),'Create New')]")
    private WebElement lnkCreateNew;

    @FindBy(xpath = "//input[@type='text']")
    private WebElement inputFind;

    @FindBy(xpath = "//input[@type='submit']")
    private WebElement btnSearch;

    @FindBy(xpath = "//a[contains(text(),'Details')]")
    private WebElement lnkDetails;

    @FindBy(xpath = "//a[contains(text(),'Edit')]")
    private WebElement lnkEdit;

    // Xpath: //td[contains(text(),'%s')]/following-sibling::td[contains(text(),'%s')]/..//a[contains(text(),'Delete')]
    //  First parameter=LastName, Second parameter = FirstName
    @FindBy(xpath = "//a[contains(text(),'Delete')]")
    private  WebElement lnkDelete;


    public StudentPage(WebDriver driver, int waitTime){
        this.driver = driver;
        this.waitTime = waitTime;
        PageFactory.initElements(driver, this);
    }

    public StudentCreatePage createNewStudent(){
        lnkCreateNew.click();
        StudentCreatePage createStudent = new StudentCreatePage(driver, waitTime);
        return createStudent;
    }

    public StudentDetailsPage readExistingStudent(String firstName, String lastName){
        inputFind.sendKeys(firstName);
        btnSearch.click();

        String xpath = String.format(CRUD_OPTION_XPATH, lastName, firstName, READ_OPTION);
        lnkDetails =  (new WebDriverWait(driver, Duration.ofSeconds(waitTime))).
                until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));

        lnkDetails.click();
        StudentDetailsPage readStudent = new StudentDetailsPage(driver, waitTime);
        return readStudent;
    }

    public StudentEditPage editExistingStudent(String firstName, String lastName){

        inputFind.sendKeys(firstName);
        btnSearch.click();

        // NOTE: Re-find control dynamically
        String xpath = String.format(CRUD_OPTION_XPATH, lastName, firstName, EDIT_OPTION);
        lnkEdit =  (new WebDriverWait(driver, Duration.ofSeconds(waitTime))).
                until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));;

        lnkEdit.click();
        StudentEditPage editStudent = new StudentEditPage(driver, waitTime);
        return  editStudent;
    }

    public StudentDeletePage deleteExistingStudent(String firstName, String lastName){

        inputFind.sendKeys(firstName);
        btnSearch.click();

        String xpath = String.format(CRUD_OPTION_XPATH, lastName, firstName, DELETE_OPTION);
        lnkDelete =  (new WebDriverWait(driver, Duration.ofSeconds(waitTime))).
                until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));;

        lnkDelete.click();
        StudentDeletePage deleteStudent = new StudentDeletePage(driver, waitTime);

        return deleteStudent;
    }

    public boolean isStudentCreated(String firstName){
        inputFind.sendKeys(firstName);
        btnSearch.click();

        String xpath = String.format("//td[contains(text(),'%s')]", firstName);
        WebElement student = (new WebDriverWait(driver, Duration.ofSeconds(waitTime))).
                until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
        SeleniumUtilities.highlightControl(student, driver);

        return student.isDisplayed();
    }

    public boolean isStudentDeleted(String firstName){
        inputFind.sendKeys(firstName);
        btnSearch.click();
        int deleteWait = 5;

        WebElement student = null;
        String xpath = String.format("//td[contains(text(),'%s')]", firstName);
        try{
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(deleteWait));
            student = (new WebDriverWait(driver, Duration.ofSeconds(deleteWait))).
                    until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
        } catch (Exception ex){
            //Log something here...
        }

        return (student == null ? true : false); // If student is null it means student is deleted
    }


}
