package com.e2e.model;

public class Student {

    private String lastName = "";
    private String firstName = "";
    private String enrollmentDate = "";

    public Student(){

    }

    public Student(String lastName, String firstName, String enrollmentDate){
        this.lastName = lastName;
        this.firstName = firstName;
        this.enrollmentDate = enrollmentDate;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(String enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }
}
