package com.testinium.api.app;


import com.testinium.api.models.Auth;
import com.testinium.api.models.Calculator;
import com.testinium.api.services.BaseApiTest;
import com.testinium.utils.Methods;
import io.restassured.response.Response;

import static com.testinium.api.services.AuthServices.auth;
import static io.restassured.RestAssured.given;

public class Calculations extends BaseApiTest {


    public void calculate(String operator, String number1, String number2, int response) {
        Calculator calculator = new Calculator(number1,number2);
        Response response1 = given(spec)
                .contentType("application/json")
                .header(auth.headerMaker())
                .body(calculator)
                .when()
                .post("/calculators/" + operator);

        response1.then().assertThat().statusCode(response);

    }

    public void login(String user, int response) {
        Calculator calculator = new Calculator("1","2");

        auth = Auth.fromConfig(Methods.getUserFromJson(user +".username"), Methods.getUserFromJson(user + ".password"));
        Response response1 = given(spec)
                .contentType("application/json")
                .header(auth.headerMaker())
                .body(calculator)
                .when()
                .post("/calculators/" + "adds");

        response1.then().assertThat().statusCode(response);

    }
}
