package com.testinium.api.services;

import com.testinium.api.models.Auth;
import com.testinium.api.models.Calculator;
import com.testinium.utils.Methods;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class CalculatorServices extends BaseApiTest {
    public static Auth auth;
    public static Calculator calculator;
    public static Response response;

    public CalculatorServices() {
        auth = AuthServices.auth;
    }
    public Methods methods = new Methods();

    public String calculateWithAPI(String operator, String number1, String number2) {

        calculator = new Calculator(number1, number2);
         response = given(spec)
                .contentType("application/json")
                .header(auth.headerMaker())
                .when()
                .body(calculator)
                .post("/calculators/"+operator);
        response.then().statusCode(200);
        System.out.println("API Calculator Result: "+number1 + " " + operator + " " + number2 + " = "
                + response.getBody().jsonPath().getString("result"));
        return response.jsonPath().getString("result");
    }



}
