package com.testinium.utils;

import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.CapabilityType;

import java.util.HashMap;
import java.util.List;
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
					headlessOptions.setCapability(CapabilityType.UNHANDLED_PROMPT_BEHAVIOUR, UnexpectedAlertBehaviour.ACCEPT);

					driver = new ChromeDriver(headlessOptions);
					break;
				case "firefox":
					FirefoxOptions ffOptions = new FirefoxOptions();
					ffOptions.addPreference("browser.safebrowsing.enabled", false);
					ffOptions.setCapability(CapabilityType.UNHANDLED_PROMPT_BEHAVIOUR, UnexpectedAlertBehaviour.ACCEPT);
					driver = new FirefoxDriver(ffOptions);
					driver.manage().window().maximize();
					break;
				case "firefoxHeadless":
					FirefoxOptions ffHeadlessOptions = new FirefoxOptions();
					ffHeadlessOptions.addArguments("--headless");
					ffHeadlessOptions.addPreference("browser.safebrowsing.enabled", false);
					ffHeadlessOptions.setCapability(CapabilityType.UNHANDLED_PROMPT_BEHAVIOUR, UnexpectedAlertBehaviour.ACCEPT);
					driver = new FirefoxDriver(ffHeadlessOptions);
					break;
				case "edge":
					EdgeOptions edgeOptions = new EdgeOptions();
					edgeOptions.setExperimentalOption("excludeSwitches", List.of("disable-popup-blocking"));
					edgeOptions.setExperimentalOption("prefs", Map.of("safebrowsing.enabled", false));
					edgeOptions.addArguments("----disable-notifications");
					edgeOptions.addArguments("--start-maximized");
					edgeOptions.setCapability(CapabilityType.UNHANDLED_PROMPT_BEHAVIOUR, UnexpectedAlertBehaviour.ACCEPT);
					driver = new EdgeDriver(edgeOptions);
					break;
				case "edgeHeadless":
					EdgeOptions edgeHeadlessOptions = new EdgeOptions();
					edgeHeadlessOptions.setExperimentalOption("excludeSwitches", List.of("disable-popup-blocking"));
					edgeHeadlessOptions.setExperimentalOption("prefs", Map.of("safebrowsing.enabled", false));
					edgeHeadlessOptions.addArguments("----disable-notifications");
					edgeHeadlessOptions.addArguments("--start-maximized");
					edgeHeadlessOptions.addArguments("--headless=new");
					edgeHeadlessOptions.setCapability(CapabilityType.UNHANDLED_PROMPT_BEHAVIOUR, UnexpectedAlertBehaviour.ACCEPT);
					driver = new EdgeDriver(edgeHeadlessOptions);
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