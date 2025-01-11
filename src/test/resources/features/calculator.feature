@ui
Feature: Calculator Feature
  Background:
  * Login with username "admin.username" and password "admin.password" with API call


  @user_calculates_interest_rate_on_the_calculator_and_verifies_the_result_with_api_call
  Scenario: User calculates interest rate on the calculator and verifies the result with api call
    * Calculate interest rate with API with "1" year, "0.05" rate and "100" TL and save it to "$calculatedInterestAPI" user variable
    Given Login with "admin.username" username and "admin.password" password
    Then Calculate interest rate with "1" year, "0,05" rate and "100" TL and save it to "$calculatedInterest" user variable
    And Verify that "$calculatedInterest" is equal to "$calculatedInterestAPI"

  @user_calculates_budget_with_monthly_income_and_expenses_on_the_calculator_and_verifies_the_result_with_api_call
  Scenario: User calculates budget with monthly income and expenses on the calculator and verifies the result with api call
    * Calculate budget with API with the following values and save it to user variable:
      | Income | Expense |
      | 1000   | 800     |
      | 1000   | 1000    |
      | 1000   | 1200    |
      | 2000   | 1000    |
      | 1500   | 500     |
      | 500    | 500     |
    Given Login with "admin.username" username and "admin.password" password
    Then Calculate budget with the following values and save it to user variable:
      | Income | Expense |
      | 1000   | 800     |
      | 1000   | 1000    |
      | 1000   | 1200    |
      | 2000   | 1000    |
      | 1500   | 500     |
      | 500    | 500     |
    And Compare two lists that saved by user


  @user_compares_bank_loans_on_the_calculator_and_verifies_the_result_with_api_call
  Scenario: User compares bank loans on the calculator and verifies the result with api call
    * Calculate PMT with API with following rate and payment periods then save the minimum amount to pay
      | Principal Amount | Rate  | Period |
      | 1200             | 0.01  | 12     |
      | 1600             | 0.005 | 6      |
      | 1300             | 0.02  | 6      |
    Given Login with "admin.username" username and "admin.password" password
    Then Calculate PMT with following rate and payment periods then save the minimum amount to pay
      | Principal Amount | Rate  | Period |
      | 1200             | 0,01  | 12     |
      | 1600             | 0,005 | 6      |
      | 1300             | 0,02  | 6      |


  @user_calculates_short_term_investment_on_the_calculator_and_verifies_the_result_with_api_call
  Scenario: User calculates short term investment on the calculator and verifies the result with api call
    * Calculate investment with API with "6" month, "%3" rate and "500" TL and save it to "$calculatedInvestmentAPI" user variable
    Given Login with "admin.username" username and "admin.password" password
    Then Calculate investment with "6" month, "%3" rate and "500" TL and save it to "$calculatedInvestment" user variable
    And Verify that "$calculatedInvestment" is equal to "$calculatedInvestmentAPI"

  @user_calculates_daily_expenses_on_the_calculator_and_verifies_the_result_with_api_call
  Scenario: User calculates daily expenses on the calculator and verifies the result with api call
    * Calculate daily expenses with API with "50" TL meal, "20" TL transportation and "10" TL coffee and save it to "$calculatedDailyExpensesAPI" user variable
    Given Login with "admin.username" username and "admin.password" password
    Then Calculate daily expenses with "50" TL meal, "20" TL transportation and "10" TL coffee and save it to "$calculatedDailyExpenses" user variable
    And Verify that "$calculatedDailyExpenses" is equal to "$calculatedDailyExpensesAPI"

  @user_calculates_long_term_investment_on_the_calculator_and_verifies_the_result_with_api_call
  Scenario: User calculates long term investment on the calculator and verifies the result with api call
    * Calculate investment with API with "1" year, "%4" rate and "200" TL and save it to "$calculatedInvestmentAPI" user variable
    Given Login with "admin.username" username and "admin.password" password
    Then Calculate investment with "1" year, "%4" rate and "200" TL and save it to "$calculatedInvestment" user variable
    And Verify that "$calculatedInvestment" is equal to "$calculatedInvestmentAPI"

  @user_calculates_exchange_rate_on_the_calculator_and_verifies_the_result_with_api_call
  Scenario: User calculates exchange rate on the calculator and verifies the result with api call
    * Calculate exchange rate with API with "36000" TL to "USD" and save it to "$calculatedExchangeRateUSD" user variable
    * Calculate exchange rate with API with "36000" TL to "EUR" and save it to "$calculatedExchangeRateEUR" user variable
    Given Login with "admin.username" username and "admin.password" password
    Then Calculate exchange rate with "36000" TL to "USD" and save it to "$calculatedExchangeRateUSD" user variable
    And Calculate exchange rate with "36000" TL to "EUR" and save it to "$calculatedExchangeRateEUR" user variable
    And Compare two lists that saved by user

