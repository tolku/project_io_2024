package com.fodapi;

//TODO remove in the future - should be replaced by UserEntityDTO
public class User {

    private String firstName;
    private String secondName;
    private String email;
    private String passwordHash;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    @Override
    public String toString() {
        return "imie: " + firstName + " " + "nazwisko: " + this.secondName + " " + "email: " + this.email + " " + "haslo: " + this.passwordHash;
    }
}
