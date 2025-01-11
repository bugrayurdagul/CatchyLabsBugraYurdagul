package com.testinium.stepdefs;

import com.testinium.Pages;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class LoginPageStepDefs {

    public final Pages pages = new Pages();

    @Given("Login with {string} username and {string} password")
    public void login(String username, String password) {
        pages.loginPage().login(username, password);
    }


}
