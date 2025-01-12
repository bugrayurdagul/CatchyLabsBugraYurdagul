package com.testinium.pages;

import com.testinium.utils.Methods;
import com.testinium.utils.Driver;
import com.testinium.utils.WaitUtils;
import io.cucumber.datatable.DataTable;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.Map;

public class CalculatorPage extends Methods {

    public CalculatorPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }


    /**
     * Calculates the interest rate.
     *
     * @param year      the year
     * @param rate      the rate
     * @param money     the money
     * @param userVariable the user variable
     */
    public void calculateInterestRate(String year, String rate, String money, String userVariable) {
        String result = getResult("*", getResult("+", getResult("*", year, rate), year), money);
        saveTheVariable(userVariable, result);
        logger.info("UI Calculator Result: " + userVariables.get(userVariable));
    }

    /**
     * Calculates the budget.
     *
     * @param dataTable the data table
     */
    public void calculateBudget(DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        String result;
        int i = 0;
        for (Map<String, String> columns : rows) {
            result = getResult("-", columns.get("Income"), columns.get("Expense"));
            logger.info("Budget "+(i+1)+":"+result);
            saveTheVariable("Budget" + (i+1), result);
            i++;
        }
        logger.info(userVariables);


    }

    /**
     * Calculates the loan.
     *
     * @param dataTable the data table
     */
    public void calculateLoans(DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        String principalAmount;
        String rate;
        String period;
        String numerator;
        String denominator;
        String result;
        int i = 0;
        for (Map<String, String> columns : rows) {
            period = columns.get("Period");
            rate = columns.get("Rate");
            principalAmount = columns.get("Principal Amount");
            String tempResult1 = getResult("+", "1", rate);
            String tempResult2 = getResult("*", getResult("+", "1", rate), tempResult1);
            String tempResult3 = tempResult2;
            for (int j = 0; j < Integer.parseInt(period) - 3; j++) {
                tempResult3 = getResult("*", tempResult2, tempResult1);
                tempResult2 = tempResult3;
                clickOnButton("AC");
                WaitUtils.waitForInvisibility(By.xpath("//span[contains(.,normalize-space('='))]"));

            }
            numerator = getResult("*", rate, tempResult3);
            denominator = getResult("-", tempResult3, "1");
            result = getResult("*", principalAmount, getResult("/", numerator, denominator));
            logger.info("PMT" + i + ":" + result);
            saveTheVariable("Loan " + i, result);
            i++;
        }
        logger.info(userVariables);
    }

    /**
     * Calculates the investment.
     *
     * @param time       the time
     * @param yearOrMonth the year or month
     * @param rate       the rate
     * @param amount     the amount
     * @param userVariable the user variable
     */
    public void calculateInvestment(String time, String yearOrMonth, String rate, String amount, String userVariable) {
        if (yearOrMonth.equals("month"))
            time = getResult("/", time, "12");
        rate = getResult("/", rate.replace("%", ""), "100");

        String result = getResult("*", amount, getResult("+", "1", getResult("*", time, rate)));
        saveTheVariable(userVariable, result);
        logger.info(userVariables);
    }

    /**
     * Calculates the daily expenses.
     *
     * @param meal       the meal
     * @param transportation the transportation
     * @param coffee     the coffee
     * @param userVariable the user variable
     */
    public void calculateDailyExpenses(String meal, String transportation, String coffee, String userVariable) {
        String result = getResult("+", meal, transportation, coffee);
        saveTheVariable(userVariable, result);
        logger.info(userVariables);

    }

    /**
     * Calculates the exchange rate.
     *
     * @param lira       the lira
     * @param exchange   the exchange
     * @param userVariable the user variable
     */
    public void calculateExchangeRate(String lira, String exchange, String userVariable) {
        String fee;
        String rate;
        if (exchange.equals("USD")) {
            fee = "0,002";
            rate = "33,10";
        } else {
            fee = "0,001";
            rate = "36,50";
        }
        String result = (getResult("*", getResult("/", lira, rate), getResult("-", "1", fee)));
        saveTheVariable(userVariable, result);
        logger.info(userVariables);
    }

    public void makeSureResultIs(String operator, String number1, String number2, String result) {
        String result1 = getResult(operator, number1, number2);
        Assert.assertEquals("Calculation was wrong. Expected: " + result + " Actual: " + result1,result,result1);
    }

    public void divideZero(String operator, String number1, String number2, String message) {
        turnNumberToDigitsAndClick(operator, number1, number2);
        if (!doesElementExist(By.xpath("//div[text()='Not a Number']")))
            Assert.fail("Bad calculation. Zero cannot be divided. This message should be shown: "+message);
    }
}
