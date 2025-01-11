package com.testinium.stepdefs;

import com.testinium.Pages;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.ParameterType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class CalculatorPageStepDefs {
    Pages pages = new Pages();

    @ParameterType("year|month")
    public String yearOrMonth(String yearOrMonth) {
        return yearOrMonth;
    }


    @Given("Calculate interest rate with {string} year, {string} rate and {string} TL and save it to {string} user variable")
    public void calculateInterestRate(String year, String rate, String amount, String userVariable) {
        pages.calculatorPage().calculateInterestRate(year, rate, amount, userVariable);
    }

    @Then("Calculate budget with the following values and save it to user variable:")
    public void calculateBudget(DataTable dataTable) {
        pages.calculatorPage().calculateBudget(dataTable);

    }

    @Then("Calculate PMT with following rate and payment periods then save the minimum amount to pay")
    public void calculateLoans(DataTable dataTable) {
        pages.calculatorPage().calculateLoans(dataTable);

    }

    @Then("Calculate investment with {string} {yearOrMonth}, {string} rate and {string} TL and save it to {string} user variable")
    public void calculateInvestment(String time, String yearOrMonth, String rate, String amount, String userVariable) {
        pages.calculatorPage().calculateInvestment(time, yearOrMonth, rate, amount, userVariable);

    }


    @Then("Calculate daily expenses with {string} TL meal, {string} TL transportation and {string} TL coffee and save it to {string} user variable")
    public void calculateDailyExpenses(String meal, String trasportation, String coffee, String userVariable) {
        pages.calculatorPage().calculateDailyExpenses(meal, trasportation, coffee, userVariable);
    }

    @Then("Calculate exchange rate with {string} TL to {string} and save it to {string} user variable")
    public void calculateExchangeRate(String lira, String exchange, String userVariable) {
        pages.calculatorPage().calculateExchangeRate(lira, exchange, userVariable);
    }
}
