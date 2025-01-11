package com.testinium.uistepdefs;

import com.sun.istack.logging.Logger;
import com.testinium.utils.Methods;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.junit.Assert;

public class BaseStepDefs extends Methods {

    @And("Compare these two {string} and {string} values that saved by user")
    public void compareTheseTwoValuesAndSavedByUser(String firstValue, String secondValue) {

    }

    @Then("Verify that {string} is equal to {string}")
    public void verifyThatIsEqualTo(String valueUI, String valueAPI) {
        String firstValueFromUser = getTheVariable(valueUI.replace("$", ""));
        String secondValueFromUser = getCompareVariables().get(valueAPI.replace("$", "").replace("API", ""));
        Assert.assertEquals("Results did not match", firstValueFromUser, secondValueFromUser);
        Logger.getLogger(BaseStepDefs.class).info("User variables matched.");
    }

    @Then("Compare two lists that saved by user")
    public void compareTwoListsThatSavedByUser() {
        Assert.assertEquals("User variables did not match", getUserVariables(), getCompareVariables());
    }
}
