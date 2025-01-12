package com.testinium.utils;

import com.sun.istack.logging.Logger;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import java.util.logging.Level;

public class Hook {

    double startTime;
    double duration;
    public static Scenario currentScenario;

    @Before("@ui")
    public void setup(Scenario scenario) {
        currentScenario = scenario;
        System.out.println("BEFORE");
        startTime = System.nanoTime();
        System.out.println("window size from hook -> " + Driver.getDriver().manage().window().getSize());
        WaitUtils.resetDriverImplicitTimeout();
        System.out.println("Browser: " + Resources.getConfiguration().get("browser").getAsString());
        Logger.getLogger(Hook.class).log(Level.INFO, "BASE URL : " + Resources.getConfiguration().get("url").getAsString());
        Driver.getDriver().get(Resources.getConfiguration().get("url").getAsString());
    }
    @Before("@api")
    public void setupApi(Scenario scenario) {
        currentScenario = scenario;
        System.out.println("BEFORE");
        startTime = System.nanoTime();
        Logger.getLogger(Hook.class).log(Level.INFO, "API BASE URL : " + Resources.getConfiguration().get("api-url").getAsString());
    }

    @After("@ui")
    public void teardown(Scenario scenario) {
        currentScenario = null;
        if (scenario.isFailed()) {
            TakesScreenshot takesScreenshot = (TakesScreenshot) Driver.getDriver();
            byte[] image = takesScreenshot.getScreenshotAs(OutputType.BYTES);
            scenario.attach(image, "image/png", "Fail Screenshot");
        }
        Methods.userVariables.clear();
        Methods.userVariablesCompare.clear();
        System.out.println("AFTER");
        duration = (System.nanoTime() - startTime) / 1000000000;
        System.out.println(duration);
        Driver.closeDriver();
    }
}
