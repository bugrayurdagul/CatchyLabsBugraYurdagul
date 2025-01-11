package com.testinium.api.apistepdefs;

import com.testinium.api.app.MicroServices;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class CalculatorAPIStepDefs {

    private static final MicroServices microServices = new MicroServices();


    @Given("Calculate interest rate with API with {string} year, {string} rate and {string} TL and save it to {string} user variable")
    public void calculateInterestRate(String year, String rate, String amount, String userVariable) {
        microServices.calculateInterestRateWithAPI(year, rate, amount, userVariable);
    }

    @Then("Calculate budget with API with the following values and save it to user variable:")
    public void calculateBudgetWithAPI(DataTable dataTable) {
        microServices.calculateBudgetWithAPI(dataTable);
    }

    @Then("Calculate PMT with API with following rate and payment periods then save the minimum amount to pay")
    public void calculatePMTWithAPI(DataTable dataTable) {
        microServices.calculatePMTWithAPI(dataTable);
    }

    @Then("Calculate investment with API with {string} {yearOrMonth}, {string} rate and {string} TL and save it to {string} user variable")
    public void calculateInvestment(String time, String yearOrMonth, String rate, String amount, String userVariable) {
        microServices.calculateInvestmentWithAPI(time, yearOrMonth, rate, amount, userVariable);}

    @Then("Calculate daily expenses with API with {string} TL meal, {string} TL transportation and {string} TL coffee and save it to {string} user variable")
    public void calculateDailyExpenses(String meal, String transportation, String coffee, String userVariable) {
        microServices.calculateDailyExpensesWithAPI(meal, transportation, coffee, userVariable);
    }

    @Then("Calculate exchange rate with API with {string} TL to {string} and save it to {string} user variable")
    public void calculateExchangeRate(String lira, String exchange, String userVariable) {
        microServices.calculateExchangeRateWithAPI(lira, exchange, userVariable);
    }
}
