package com.e2e;

import com.e2e.model.Student;
import com.e2e.pages.student.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class ManageStudentTest extends TestBase {

    StudentPage studentPage;
    StudentCreatePage studentCreatePage;
    StudentDetailsPage studentReadPage;
    StudentEditPage studentEditPage;
    StudentDeletePage studentDeletePage;

    DateTimeFormatter newPattern = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    DateTimeFormatter oldPattern = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private String firstNameStudent = "Demo003";
    private String lastNameStudent = "QA103";
    private String newNameStudent = "QA103-EDIT";
    private String enrollmentDateStudent = "01/01/2022";

    String studentsUrl = "http://wagner.wang/ContosoUniversity/Students";

    @Test(priority = 1,
            enabled = true,
            description = "Successful student creation")
    public void testStudentCreate(){

        driver.get(studentsUrl);
        // You can provide input parameters like this
        // String firstName = "QA1001";
        // String lastName = "Demo1";
        // String enrollDate = "01/01/2022";
        // OR, for complex entities, just use model class
        Student student = new Student(lastNameStudent,firstNameStudent, enrollmentDateStudent); //last, first, enrollment

        studentPage = new StudentPage(driver, waitTime);
        studentCreatePage = studentPage.createNewStudent();
        studentCreatePage.enterNewStudentData(student);  //studentCreatePage.enterNewStudentData(student.getFirstName(), student.getLastName(), student.getEnrollmentDate());
        if(DEMO){ try { Thread.sleep(demoWait); } catch (InterruptedException e) { e.printStackTrace(); } }
        studentCreatePage.createNewStudent();

        Assert.assertTrue(studentPage.isStudentCreated(student.getFirstName()));
    }

    @Test(priority = 2,
            enabled = true,
            description = "Successful student record reading")
    public void testStudentRead(){

        driver.get(studentsUrl);
        Student student = new Student(lastNameStudent,firstNameStudent, enrollmentDateStudent);
        //Student student = new Student("Petrovic","Djordje", "31/12/2012");

        studentPage = new StudentPage(driver, waitTime);
        studentReadPage = studentPage.readExistingStudent(student.getFirstName(), student.getLastName());

        Assert.assertTrue(studentReadPage.checkFirstName(student.getFirstName()));
        Assert.assertTrue(studentReadPage.checkLastName(student.getLastName()));

        // First, parse OLD date pattern and convert it into new date pattern
        LocalDate eDate = LocalDate.parse(student.getEnrollmentDate(), oldPattern);
        student.setEnrollmentDate(eDate.format(newPattern));
        Assert.assertTrue(studentReadPage.checkEnrollmentDate(student.getEnrollmentDate()));

    }

    @Test(priority = 3,
            enabled = true,
            description = "Successful student record editing")
    public void testStudentEdit(){

        driver.get(studentsUrl);
        Student student = new Student(lastNameStudent,firstNameStudent, enrollmentDateStudent); //last, first, enrollment

        studentPage = new StudentPage(driver, waitTime);
        studentEditPage = studentPage.editExistingStudent(student.getFirstName(), student.getLastName());
        student.setFirstName(newNameStudent); // NEW student name
        studentEditPage.editStudentsData(student);
        studentEditPage.saveEditedStudentsData();

        // Confirm changed name
        Assert.assertTrue(studentPage.isStudentCreated(student.getFirstName()));

    }

    @Test(priority = 4,
            enabled = true,
            description = "Successful student record deletion")
    public void testStudentDelete(){

        driver.get(studentsUrl);
        Student student = new Student(lastNameStudent,newNameStudent, enrollmentDateStudent); //last, first, enrollment

        studentPage = new StudentPage(driver, waitTime);
        studentDeletePage = studentPage.deleteExistingStudent(student.getFirstName(), student.getLastName());
        studentDeletePage.deleteExistingStudent(student);

        // assert todo
        Assert.assertTrue(studentPage.isStudentDeleted(student.getFirstName()));

    }


}
