package com.testinium.utils;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.function.Supplier;

public class WaitUtils {

    public static final int ITERATION_COUNT_MAX = 20;
    public static final int WAIT_AMOUNT_MILLISECOND = 100;
    private static final Logger logger = LogManager.getLogger();
    public static final int SHORT_WAIT = Resources.getConfiguration().get("short-wait").getAsInt();
    public static final int TIMEOUT = 10;
    /**
     * Gets a WebDriverWait object. Waits for a maximum of the specified seconds
     *
     * @param seconds Maximum seconds to wait
     * @return WebDriverWait object
     */
    public static WebDriverWait getWaiter(long seconds) {
        return new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(seconds));
    }


    /**
     * Gets a WebDriverWait object. Waits for a maximum of 10 seconds
     *
     * @return WebDriverWait object
     */
    public static WebDriverWait getWaiter() {
        return new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(TIMEOUT));
    }


    /**
     * Waits for the specified amount of milliseconds
     *
     * @param milliseconds Milliseconds to wait
     */
    public static void waitByMilliSeconds(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Performs a pause
     *
     * @param seconds Second to wait
     */
    public static void waitFor(int seconds) {
        waitByMilliSeconds(Duration.ofSeconds(seconds).toMillis());
    }


    /**
     * Waits for the provided element to be visible on the page
     *
     * @param element
     * @return
     */
    public static WebElement waitForVisibility(WebElement element) {
        return getWaiter(4 * 60).until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * Waits for element matching the locator to be visible on the page
     *
     * @param locator Locator of the element
     * @return Element itself
     */
    public static WebElement waitForVisibility(By locator) {
        return getWaiter().until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * Waits for element matching the locator to be invisible on the page
     *
     * @param locator Locator of the element
     * @return Element itself
     */
    public static void waitForInvisibility(By locator) {
        WaitUtils.waitUntilCondition(() -> !Methods.doesElementExist(locator));
        logger.info("Waited for element to disappear.");
    }

    /**
     * Tries a SendKey to the element until it is successfully sendable. Acts as a "Wait until we can send keys"
     *
     * @param element Element to wait
     * @return element itself for chain reference
     */
    public static WebElement waitElementInteractableWithSendKeys(WebElement element) {
        int loopCount = 0;
        String message = "";
        while (loopCount < ITERATION_COUNT_MAX) {
            try {
                element.sendKeys("");
                return element;
            } catch (WebDriverException e) {
                message = e.getMessage();
            }
            loopCount++;
            waitByMilliSeconds(WAIT_AMOUNT_MILLISECOND);
        }
        Assert.fail("Element: '" + element + "' is NOT interactable with " + message);
        throw new RuntimeException();
    }


    /**
     * Tries to click the element with multiple tries. Click-or-fail situation
     *
     * @param element Element to click
     * @return element itself for chain reference
     */
    public static WebElement waitElementInteractableWithClicking(WebElement element) {
        int loopCount = 0;
        String message = "";
        while (loopCount < ITERATION_COUNT_MAX) {
            try {
                element.click();
                return element;
            } catch (WebDriverException e) {
                message = e.getMessage();
            }
            loopCount++;
            waitByMilliSeconds(WAIT_AMOUNT_MILLISECOND);
        }
        Assert.fail("Element: '" + element + "' is NOT interactable with " + message);
        return null;
    }

    /**
     * Resets the waiter timeout. There are some places where we make a quick check, via changing the implicit timeout.
     * This functions resets it back to the defaults.
     */
    public static void resetDriverImplicitTimeout() {
        Driver.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(SHORT_WAIT));
    }

    /**
     * Waits until a specific condition is True. A Supplier is sent because we need to try it continuously.
     *
     * @param function lambda function to supply. We cannot use boolean itself because that will be render the result without waiting.
     *                 Instead, we make a lambda function, to make the Java retry that solution.
     *                 usage: {@code ()->variable.equals(expected)} or if it's a function that returns boolean, use {@code this::function}
     */
    public static void waitUntilCondition(Supplier<Boolean> function) {
        int timeout = TIMEOUT;

        while (!function.get() && timeout > 0) {
            WaitUtils.waitByMilliSeconds(250);
            timeout--;
        }

        if (timeout <= 0 && !function.get())
            Assert.fail("Condition failed");

    }
}
