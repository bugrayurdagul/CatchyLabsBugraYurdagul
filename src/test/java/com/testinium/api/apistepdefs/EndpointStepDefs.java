package com.testinium.api.apistepdefs;

import com.testinium.api.app.Calculations;
import com.testinium.api.app.MicroServices;
import io.cucumber.java.ParameterType;
import io.cucumber.java.en.Then;

public class EndpointStepDefs {

    Calculations calculations = new Calculations();

    @ParameterType("Add|Subtract|Multiply|Divide|.*")
    public String calculation(String operator) {
        switch (operator) {
            case "Add":
                operator = "adds";
                break;
            case "Subtract":
                operator = "subtracts";
                break;
            case "Multiply":
                operator = "multiplies";
                break;
            case "Divide":
                operator = "divides";
                break;
            default:
                break;
        }
        return operator;
    }

    @Then("{calculation} {string} and {string} and make sure response is {int}")
    public void calculation(String operator, String number1, String number2, int response) {
        calculations.calculate(operator, number1, number2,response);

    }


    @Then("Login with {string} user and make sure response is {int}")
    public void loginWithUserAndMakeSureResponseIs(String user, int response) {
        calculations.login(user, response);
    }
}
