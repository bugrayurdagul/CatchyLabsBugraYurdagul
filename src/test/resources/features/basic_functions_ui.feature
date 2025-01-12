@ui @basic_functions
Feature: Basic Functionalities of UI


  @basic_add_calculation
  Scenario: Basic add calculation
    Given Login with "admin.username" username and "admin.password" password
    And Add "2" and "3" and make sure result is "5"

  @basic_subtract_calculation
  Scenario: Basic subtract calculation
    Given Login with "admin.username" username and "admin.password" password
    And Subtract "3" and "3" and make sure result is "0"

  @basic_multiply_calculation
  Scenario: Basic multiply calculation
    Given Login with "admin.username" username and "admin.password" password
    And Multiply "3" and "3" and make sure result is "9"

  @basic_divide_calculation
  Scenario: Basic divide calculation
    Given Login with "admin.username" username and "admin.password" password
    And Divide "3" and "3" and make sure result is "1"

  @basic_add_calculation_with_minus
  Scenario: Basic add calculation with minus
    Given Login with "admin.username" username and "admin.password" password
    And Add "-2" and "-3" and make sure result is "-5"

  @basic_add_calculation_with_decimal
  Scenario: Basic add calculation with decimal
    Given Login with "admin.username" username and "admin.password" password
    And Add "2,5" and "3,5" and make sure result is "6"

  @basic_divide_calculation_with_zero
  Scenario: Basic divide calculation with zero
    Given Login with "admin.username" username and "admin.password" password
    And Divide "3" and "0" and divide by zero and make sure result is "Not a Number"


