package com.testinium.uistepdefs;

import com.testinium.Pages;
import io.cucumber.java.en.Given;

public class LoginPageStepDefs {

    public final Pages pages = new Pages();

    @Given("Login with {string} username and {string} password")
    public void login(String username, String password) {
        pages.loginPage().login(username, password);
    }


}
