package com.testinium.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {
                "pretty",
                "json:target/cucumber.json",
                "html:target/site/cucumber-pretty"
        },
        features = {"src/test/resources/features"},
        glue = {"com.testinium.utils", "com.testinium.uistepdefs", "com.testinium.api.apistepdefs"}
)
public class FullRunner {

}
