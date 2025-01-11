package com.testinium;

import com.testinium.pages.CalculatorPage;
import com.testinium.pages.LoginPage;


public class Pages {


    private CalculatorPage calculatorPage;
    private LoginPage loginPage;



    public CalculatorPage calculatorPage() {
        if (calculatorPage == null) calculatorPage = new CalculatorPage();
        return calculatorPage;
    }
    public LoginPage loginPage() {
        if (loginPage == null) loginPage = new LoginPage();
        return loginPage;
    }


}
