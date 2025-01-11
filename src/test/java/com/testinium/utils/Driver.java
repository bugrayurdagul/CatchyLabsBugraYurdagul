package com.testinium.utils;

import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.CapabilityType;

import java.util.HashMap;
import java.util.Map;

public class Driver {
	private static WebDriver driver;

	private Driver(){
	}

	public synchronized static WebDriver getDriver(String browser){
		if(driver == null) {
			browser = browser == null ? Resources.getConfiguration().get("browser").getAsString() : browser;
			System.out.println("driver = " + driver);
			LogUtils.log("Creating the driver for: %s", browser);
			switch (browser) {
				case "chrome":
					ChromeOptions options = new ChromeOptions();
					Map<String,Object> prefs = new HashMap<>();
					prefs.put("safebrowsing.enabled", "false");
					options.setExperimentalOption("prefs", prefs);
					options.addArguments("--start-maximized");
					options.setCapability(CapabilityType.UNHANDLED_PROMPT_BEHAVIOUR, UnexpectedAlertBehaviour.ACCEPT);
					driver = new ChromeDriver(options);
					break;
				case "chromeHeadless":
					ChromeOptions headlessOptions = new ChromeOptions();
					Map<String,Object> headlessPrefs = new HashMap<>();
					headlessPrefs.put("safebrowsing.enabled", "false");
					headlessOptions.setExperimentalOption("prefs", headlessPrefs);
					headlessOptions.addArguments("--start-maximized");
					headlessOptions.addArguments("--headless=new");
					driver = new ChromeDriver(headlessOptions);
					break;
				case "firefox":
					FirefoxOptions ffOptions = new FirefoxOptions();
					ffOptions.addPreference("browser.safebrowsing.enabled", false);
					driver = new FirefoxDriver(ffOptions);
					driver.manage().window().maximize();
					break;
				case "firefoxHeadless":
					FirefoxOptions ffHeadlessOptions = new FirefoxOptions();
					ffHeadlessOptions.addArguments("--headless");
					ffHeadlessOptions.addPreference("browser.safebrowsing.enabled", false);
					driver = new FirefoxDriver(ffHeadlessOptions);
					break;

				default:
					throw new RuntimeException("Illegal browser type!");
			}
		}
		return driver;
	}

	public static WebDriver getDriver(){
		return getDriver(null);
	}

	public static void closeDriver(){
		if(driver != null)
			driver.quit();
		driver = null;
	}
}