package com.e2e.model;

public class TestUser {

    private String username = "";
    private String password = "";
    private String name = "";
    private String birthDate = "";

    public TestUser(){}

    public TestUser(String username, String password, String name, String birthDate){
        this.username = username;
        this.password = password;
        this.name = name;
        this.birthDate = birthDate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }
}
