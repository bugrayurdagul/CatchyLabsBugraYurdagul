package com.testinium.api.services;

import com.testinium.utils.Resources;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;

import java.io.PrintStream;
import java.util.Arrays;

public class BaseApiTest {

    RequestSpecification spec;

    public BaseApiTest() {
        PrintStream logStream = new PrintStream(System.out);

        spec = new RequestSpecBuilder()
                .setBaseUri(Resources.getConfiguration().get("api-url").getAsString())
               // .addFilters(Arrays.asList(new RequestLoggingFilter(), new ResponseLoggingFilter()))
                .build();

    }
}
