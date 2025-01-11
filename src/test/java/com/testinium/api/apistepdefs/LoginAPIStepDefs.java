package com.testinium.api.apistepdefs;

import com.testinium.api.services.AuthServices;
import io.cucumber.java.en.Given;

public class LoginAPIStepDefs {
    private static final AuthServices authServices = new AuthServices();
    @Given("Login with username {string} and password {string} with API call")
    public void login(String username, String password) {
        authServices.login(username, password);

    }
}
