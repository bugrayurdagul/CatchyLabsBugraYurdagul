package com.testinium.api.services;

import com.testinium.api.models.Auth;

import com.testinium.utils.Methods;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class AuthServices extends BaseApiTest{

    public static Auth auth;


    public void login(String username, String password) {
        auth = Auth.fromConfig(Methods.getUserFromJson(username), Methods.getUserFromJson(password));
        Response response = given(spec)
                .contentType("application/json")
                .when()
                .body(auth)
                .post("/login");

        response.then().statusCode(200);

        auth.setRefresh_token(response.jsonPath().getString("refresh_token"));
        auth.setAccess_token(response.jsonPath().getString("access_token"));
    }

    public void refreshToken(){

        Response response = given(spec)
                .contentType("application/json")
                .header(auth.headerMaker())
                .when()
                .post("/refresh-token");

        response.then().statusCode(200);

        auth.setRefresh_token(response.jsonPath().getString("refresh_token"));
        auth.setAccess_token(response.jsonPath().getString("access_token"));

    }


}
