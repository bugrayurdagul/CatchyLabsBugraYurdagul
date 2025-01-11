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
        tags = "@user_calculates_daily_expenses",
        features = {"src/test/resources/features"},
        glue = {"com.testinium.utils", "com.testinium.stepdefs", "com.testinium.api.steps"}
)
public class LocalRunner {

}
