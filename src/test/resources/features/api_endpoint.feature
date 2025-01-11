@api_endpoints_test @api
Feature: API Basic Functionalities

  @api_add_success
  Scenario: Api add success test
    * Login with username "admin.username" and password "admin.password" with API call
    * Add "2" and "3" and make sure response is 200

  @api_sub_success
  Scenario: Api subtract success test
    * Login with username "admin.username" and password "admin.password" with API call
    * Subtract "3" and "3" and make sure response is 200

  @api_mul_success
  Scenario: Api multiply success test
    * Login with username "admin.username" and password "admin.password" with API call
    * Multiply "3" and "3" and make sure response is 200

  @api_div_success
  Scenario: Api divide success test
    * Login with username "admin.username" and password "admin.password" with API call
    * Divide "3" and "3" and make sure response is 200

  @api_add_500
  Scenario: Api add 500 test
    * Login with username "admin.username" and password "admin.password" with API call
    * Add "2,1" and "3" and make sure response is 500

  @api_sub_500
  Scenario: Api subtract 500 test
    * Login with username "admin.username" and password "admin.password" with API call
    * Subtract "2,1" and "3" and make sure response is 500

  @api_mul_500
  Scenario: Api multiply 500 test
    * Login with username "admin.username" and password "admin.password" with API call
    * Multiply "2,1" and "3" and make sure response is 500

  @api_div_500
  Scenario: Api divide 500 test
    * Login with username "admin.username" and password "admin.password" with API call
    * Divide "2,1" and "3" and make sure response is 500

  @api_wrong_path_test
  Scenario: Api wrong path test
    * Login with username "admin.username" and password "admin.password" with API call
    * Adds "2" and "3" and make sure response is 404

  @api_unauthorized_test
  Scenario: Api unauthorized test
    Then Login with "unauthorized" user and make sure response is 403

