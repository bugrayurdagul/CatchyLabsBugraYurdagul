package com.testinium.pages;

import com.testinium.utils.Driver;
import com.testinium.utils.Methods;
import com.testinium.utils.WaitUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends Methods {

    @FindBy(xpath = "//input[@placeholder='Username']")
    public WebElement div_LoginUsername_HomePage;
    @FindBy(xpath = "//input[@placeholder='Password']")
    public WebElement div_LoginPassword_HomePage;

    Methods methods;

    public LoginPage() {
        PageFactory.initElements(Driver.getDriver(), this);
        methods = new Methods();
    }

    /**
     * Logs in to the application.
     * @param username the username to use
     * @param password the password to use
     */
    public void login(String username, String password) {
        sendKeysTo(div_LoginUsername_HomePage, getUserFromJson(username));
        sendKeysTo(div_LoginPassword_HomePage, getUserFromJson(password));
        clickOnButton("Login");
        WaitUtils.waitElementInteractableWithClicking(findInputLabelledBy("Open Calculator"));
        WaitUtils.waitForVisibility(findInputLabelledBy("AC"));
    }



}
