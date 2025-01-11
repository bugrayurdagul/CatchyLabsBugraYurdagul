package com.testinium.utils;

import com.google.gson.JsonElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.*;

import java.time.Duration;
import java.util.*;
import java.util.regex.Pattern;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class Methods {
    private static final Logger logger = LogManager.getLogger();
    public static Map<String, String> userVariables = new HashMap<>();
    public static Map<String, String> userVariablesCompare = new HashMap<>();

    /**
     * Check if an element is present on the page.
     * @param byLocator the By locator to use
     * @return true if the element is present, false otherwise
     */
    public static boolean doesElementExist(By byLocator) {
        Driver.getDriver().manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        List<WebElement> list = Driver.getDriver().findElements(byLocator);
        WaitUtils.resetDriverImplicitTimeout();
        return list.size() > 0;
    }

    /**
     * Send keys to a text box
     * @param tbToSendKeys the text box to send keys to
     * @param keysToSend the keys to send
     */
    public static void sendKeysTo(WebElement tbToSendKeys, String keysToSend) {
        WaitUtils.waitElementInteractableWithSendKeys(tbToSendKeys);
        tbToSendKeys.clear();
        tbToSendKeys.sendKeys(keysToSend);
    }

    /**
     * Click a button
     * @param buttonName the name of the button to click
     */
    public static void clickOnButton(String buttonName) {
            WaitUtils.waitElementInteractableWithClicking(findInputLabelledBy(buttonName));
    }

    /**
     * Find an element with a label
     * @param labelText the text of the label
     * @return the element with the label
     */
    public static WebElement findInputLabelledBy(String labelText) {
        String matchingElementXpathString = "//div[./div[text()=normalize-space('%s')] and @class='%s']";
        String classNameXpathString = "//div[./div[text()=normalize-space('=')]]";
        WebElement element = null;
        if (doesElementExist(By.xpath(classNameXpathString))) {
            element = findElementByXpath(String.format(matchingElementXpathString, labelText, findElementByXpath(classNameXpathString)
                    .getAttribute("class")));
            return element;
        } else if (element == null) {
            element = findElementByXpath("//div[./div[text()=normalize-space('" + labelText + "')]]");
            return element;
        } else
            return element;
    }

    /**
     * Find an element by XPath
     * @param xpath the XPath to use
     * @return the element found
     */
    public static WebElement findElementByXpath(String xpath) {
        return By.xpath(xpath).findElement(Driver.getDriver());
    }


    /**
     * Get a value from the credentials JSON
     * @param key the key to get the value from
     * @return the value associated with the key
     */
    public static String getUserFromJson(String key) {
        String delimiter = Pattern.quote(".");
        JsonElement jsonElement;
        var objectNames = List.of(key.split(delimiter));
        try {
            jsonElement = Resources.credentials().get(objectNames.get(0));
        } catch (NoSuchElementException e) {
            return null;
        }
        return jsonElement.getAsJsonObject().get(objectNames.get(1)).getAsString();
    }

    /**
     * Save a value to the user variables map
     * @param key the key to save the value to
     * @param userVariable the value to save
     */
    public void saveTheVariable(String key, String userVariable) {
        key = key.replace("$", "");
        if (key.contains("API"))
            userVariablesCompare.put(key.replace("API", ""), userVariable);
        else
            userVariables.put(key, userVariable);
    }

    /**
     * Get a value from the user variables map
     * @param key the key to get the value from
     * @return the value associated with the key
     */
    public String getTheVariable(String key) {
        key = key.replace("$", "");
        if (key.contains("API"))
            return userVariablesCompare.get(key.replace("API", ""));
        return userVariables.get(key);
    }

    public Map<String, String> getCompareVariables() { return userVariablesCompare; }
    public Map<String, String> getUserVariables() { return userVariables; }

    /**
     * Get the text from the result
     * @param operator the operator to use
     * @param numbers the numbers to use
     * @return the result of the calculation
     */
    public String getTextFromResult(String operator, String... numbers) {
        String resultXpath = "//span[contains(.,normalize-space(\"=\"))]";
        return findElementByXpath(resultXpath).getText().replace("=", "").replace(" ", "");

    }

    /**
     * Calculate the result of a calculation with a string operator
     * @param operator the operator to use
     * @param numbers the numbers to use
     * @return the result of the calculation
     */
    private String calculateWithStringOperator(String operator, String... numbers) {
        String result = "";
        switch (operator) {
            case "+":
                result = String.valueOf(Double.parseDouble(numbers[0].replace(",", "."))
                        + Double.parseDouble(numbers[1].replace(",", ".")));
                break;
            case "-":
                result = String.valueOf(Double.parseDouble(numbers[0].replace(",", "."))
                        - Double.parseDouble(numbers[1].replace(",", ".")));
                break;
            case "*":
                result = String.valueOf(Double.parseDouble(numbers[0].replace(",", "."))
                        * Double.parseDouble(numbers[1].replace(",", ".")));
                break;
            case "/":
                result = String.valueOf(Double.parseDouble(numbers[0].replace(",", "."))
                        / Double.parseDouble(numbers[1].replace(",", ".")));
                break;
        }
        return result;
    }

    /**
     * Turn a number to digits and click on them
     * @param operator the operator to use
     * @param numbers the numbers to use
     */
    public void turnNumberToDigitsAndClick(String operator, String... numbers) {
        List<String> list = Arrays.asList(numbers);
        String elementControl = "//span[contains(.,normalize-space('='))]";
/*        int count = 0;
		if (numbers.length > 1) {
			numberToDigits(numbers[0]);
            count++;
            clickOperator(operator);
            numberToDigits(numbers[1]);
            count++;
            System.out.println("numbers[0] -> " + numbers[0]);
            System.out.println("numbers[1] -> " + numbers[1]);
		}
        if (numbers.length == 1 || doesElementExist(By.xpath(elementControl)) || count == 2) {
			clickOperator(operator);
			numberToDigits(numbers[count]);
            count++;
		}*/
        for (int i = 0; i < list.size(); i++) {
            if (list.size() == 1 || i != 0) {
                clickOperator(operator);
                numberToDigits(list.get(i));
                clickOperator("=");
            } else
                numberToDigits(list.get(i));

        }

    }

    /**
     * Click an operator
     * @param operator the operator to click
     */
    private void clickOperator(String operator) {
        switch (operator) {
            case "+":
                clickOnButton("+");
                break;
            case "-":
                clickOnButton("-");
                break;
            case "*":
                clickOnButton("*");
                break;
            case "/":
                clickOnButton("/");
                break;
            case "=": {
                clickOnButton("=");
                try {
                    WaitUtils.waitForVisibility(By.xpath("//span[contains(.,normalize-space('='))]"));
                }catch (TimeoutException e){
                    fail("Equals clicked but result won't show up. "+e.getMessage());
                }
            }
            break;
        }

    }

    /**
     * Turn a number to digits and click on them
     * @param number the number to turn to digits
     */
    private void numberToDigits(String number) {
        for (int i = 0; i < number.length(); i++) {
            clickOnButton(String.valueOf(number.charAt(i)));
        }
    }

    /**
     * Calculate the result of a calculation with a string operator
     * @param operator the operator to use
     * @param numbers the numbers to use
     * @return the result of the calculation
     */
    protected String getResult(String operator, String... numbers) {
        if (numbers.length < 2 && !doesElementExist(By.xpath("//span[contains(.,normalize-space(\"=\"))]")))
            Assertions.fail("You didn't click any number on page.");
        turnNumberToDigitsAndClick(operator, numbers);
        System.out.print("UI Calculator Result: ");
        for (int i = 0; i< numbers.length; i++) {
            if (i == numbers.length - 1)
                operator = "=";
            System.out.print(numbers[i] + " " + operator + " ");
        }
        String result = getTextFromResult(operator, numbers);
        System.out.println(result);
        return result;
    }

}