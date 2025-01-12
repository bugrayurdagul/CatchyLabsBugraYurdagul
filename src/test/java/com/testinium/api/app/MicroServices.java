package com.testinium.api.app;

import com.testinium.api.services.CalculatorServices;
import com.testinium.utils.Methods;
import io.cucumber.datatable.DataTable;

import java.util.List;
import java.util.Map;

public class MicroServices extends CalculatorServices {
    public void calculateInterestRateWithAPI(String year, String rate, String money, String userVariable) {
        String result = calculateWithAPI("multiplies", year, rate);
        result = calculateWithAPI("adds", result, year);
        result = calculateWithAPI("multiplies", result, money).replace(".0","");
        methods.saveTheVariable(userVariable, result);
        Methods.logger.info("API Result: " + methods.getTheVariable(userVariable));

    }

    public void calculateBudgetWithAPI(DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        String result;
        int i = 0;
        for (Map<String, String> columns : rows) {
            result = calculateWithAPI("subtracts", columns.get("Income"), columns.get("Expense")).replace(".0","");
            Methods.logger.info(result);
            methods.saveTheVariable("BudgetAPI" + (i+1), result);
            methods.getTheVariable("BudgetAPI" + (i+1));
            i++;
        }
        Methods.logger.info(methods.getCompareVariables());
    }

    public void calculatePMTWithAPI(DataTable dataTable) {
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
            String tempResult1 = calculateWithAPI("adds", "1", rate);
            String tempResult2 = calculateWithAPI("multiplies", calculateWithAPI("adds", "1", rate), tempResult1);
            String tempResult3 = tempResult2;
            for (int j = 0; j < Integer.parseInt(period) - 3; j++) {
                tempResult3 = calculateWithAPI("multiplies", tempResult2, tempResult1);
                tempResult2 = tempResult3;
            }
            numerator = calculateWithAPI("multiplies", rate, tempResult3);
            denominator = calculateWithAPI("subtracts", tempResult3, "1");
            result = calculateWithAPI("multiplies", principalAmount, calculateWithAPI("divides", numerator, denominator)).replace(".0","");
            Methods.logger.info(result);
            methods.saveTheVariable("LoanAPI " + i, result);
            i++;
        }
        Methods.logger.info(methods.getCompareVariables());

    }

    public void calculateInvestmentWithAPI(String time, String yearOrMonth, String rate, String amount, String userVariable) {
        if (yearOrMonth.equals("month"))
            time = calculateWithAPI("divides", time, "12");
        rate = calculateWithAPI("divides", rate.replace("%", ""), "100");

        String result = calculateWithAPI("multiplies", amount, calculateWithAPI("adds", "1", calculateWithAPI("multiplies", time, rate)));
        methods.saveTheVariable(userVariable, result.replace(".0",""));
        Methods.logger.info(methods.getCompareVariables());

    }

    public void calculateDailyExpensesWithAPI(String meal, String transportation, String coffee, String userVariable) {
        String result = calculateWithAPI("adds", calculateWithAPI("adds", meal, transportation), coffee);
        methods.saveTheVariable(userVariable, result.replace(".0",""));
        Methods.logger.info(methods.getCompareVariables());
    }

    public void calculateExchangeRateWithAPI(String lira, String exchange, String userVariable) {
        String fee;
        String rate;
        if (exchange.equals("USD")) {
            fee = "0.002";
            rate = "33.10";
        } else {
            fee = "0.001";
            rate = "36.50";
        }
        String result = calculateWithAPI("subtracts", calculateWithAPI("divides", lira, rate), calculateWithAPI("subtracts", "1", fee));
        methods.saveTheVariable(userVariable, result.replace(".0",""));
        Methods.logger.info(methods.getCompareVariables());
    }


}
