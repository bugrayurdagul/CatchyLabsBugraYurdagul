package com.testinium.api.services;

import com.testinium.utils.Resources;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

import java.io.PrintStream;

public class BaseApiTest {

    public RequestSpecification spec;

    public BaseApiTest() {
        spec = new RequestSpecBuilder()
                .setBaseUri(Resources.getConfiguration().get("api-url").getAsString())
               // .addFilters(Arrays.asList(new RequestLoggingFilter(), new ResponseLoggingFilter()))
                .build();

    }
}
