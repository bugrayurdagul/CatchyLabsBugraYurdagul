package com.testinium.pages;

import com.testinium.utils.Methods;
import com.testinium.utils.Driver;
import io.cucumber.datatable.DataTable;
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
            System.out.println(result);
            saveTheVariable("budget" + i, result);
            i++;
        }
        System.out.println(userVariables);


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
            }
            numerator = getResult("*", rate, tempResult3);
            denominator = getResult("-", tempResult3, "1");
            result = getResult("*", principalAmount, getResult("/", numerator, denominator));
            System.out.println(result);
            saveTheVariable("Loan " + i, result);
            i++;
        }
        System.out.println(userVariables);
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
        saveTheVariable(userVariable + "Correct", result);
        System.out.println(userVariables);

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
        System.out.println(result);

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
        System.out.println(result);
        saveTheVariable(exchange + "Value", result);
    }
}
