# CatchyLabs Calculator App Web UI And API Test Automation

---
**Attention:** Before reading this document, first go at the end of this document and read the `IMPORTANT NOTE`.

This project is a test automation framework for the CatchyLabs Calculator App using Cucumber and Java. It's running on `chromeHeadless` browser. You can change it with following instructions on Installation under Create Test Environment's 3rd step.

---
## Features

* API testing using Cucumber and RestAssured
* UI testing using Cucumber and Selenium WebDriver
* Support for multiple browsers (Chrome, Firefox, Edge)
* Reporting using Cucumber HTML reports
* Adaptable for new UI and API tests


---
## Installation

To install and run the project, follow these steps:

### Prerequisites

* Java 21 or later
* Maven 3.6 or later
* Cucumber 4.8.0 or later
* Selenium 4.20.0 or later
* Google Chrome, Mozilla Firefox, or Microsoft Edge browser

### Step 1: Clone the Repository

Clone the Testinium project repository using the following command:
`git clone https://github.com/bugrayurdagul/CatchyLabsBugraYurdagul.git`

### Step 2: Install Dependencies

No need for additional dependencies. 

Basically install the project dependencies using the following command:
`mvn clean install` or go to the pom.xml file and run click `m` button up right side of the page.

If project build already installed dependencies, you can skip this step.

### Step 3: Create Test Environment

1. Create a credentials file named `credentials.json` in the `src/test/resources/test_data/` directory.

2. Add the following structure to the `credentials.json` file:
```json
{
  "admin": {
    "username": "change_this_with_yourusername",
    "password": "change_this_with_yourpassword"
  },
  "unauthorized": {
    "username": "wrong_username",
    "password": "wrong_password"
  }
}
```
_**Important:**_ Replace `change_this_with_yourusername` and `change_this_with_yourpassword` with your actual username and password and don't delete `unauthorized` section.

3. Change the browser if you want. It comes with `chromeHeadless` default option. You can change it in the file named `configration.json` in the `src/test/resources/test_data/` directory.
   Change the `"chromeHeadless"` option to `"chrome" or "firefox" or "firefoxHeadles" or "edge" or "edgeHeadless"`.
---
## Test Runs

1. Run this command if you want to run Full test:
`mvn -P Full clean verify`
2. Run this command if you want to run Tag test Don't forget to change tags in `src/test/java/com/testinium/runner/TagRunner.java` file:
`mvn -P Tag clean verify`

### API Testing

API tests are located in `src/test/resources/features/api_endpoint.feature`. To run API tests, use the following command:
`mvn test -Dcucumber.options="--tags @api"`


### UI Testing

UI tests are located in `src/test/resources/features/basic_functions_ui.feature`. To run UI tests, use the following command:
`mvn test -Dcucumber.options="--tags @ui"`

---

## Test Reports

After the tests are completed, an HTML report will be generated in the `target/cucumber-html-reports` directory.

To view the report, open the `owerview-features.html` file in a web browser.

---
## Contributing

Contributions are welcome! Please submit a pull request with your changes.

## License

This project is licensed under the MIT License.

# IMPORTANT NOTE:
* Please do not forget to create the credentials file structure.
* If you don't want to skip alert popups, please comment out the lines have this `setCapability(CapabilityType.UNHANDLED_PROMPT_BEHAVIOUR, UnexpectedAlertBehaviour.ACCEPT);` in `src/test/java/com/testinium/utils/Driver.java`
* There're multiple bugs in this website and api calls. This project's aim is catching them.
