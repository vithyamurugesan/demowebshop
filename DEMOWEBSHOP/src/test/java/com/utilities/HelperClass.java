package com.utilities;

import java.time.Duration;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class HelperClass {

    private static final String BASE_URL = "https://demowebshop.tricentis.com/";

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static void setUpDriver() {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");

        WebDriver webDriver = new ChromeDriver(options);
        driver.set(webDriver);

        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        webDriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        webDriver.manage().window().maximize();

        webDriver.get(BASE_URL);
        System.out.println("Browser launched and navigated to: " + BASE_URL);
    }

   
    public static WebDriver getDriver() {
        return driver.get();
    }

  
    public static void openPage(String url) {
        getDriver().get(url);
        System.out.println("Navigated to: " + url);
    }

  
    public static byte[] captureScreenshot() {
        try {
            return ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);
        } catch (Exception e) {
            System.out.println("Screenshot capture failed: " + e.getMessage());
            return null;
        }
    }

    public static void tearDown() {
        if (getDriver() != null) {
            getDriver().quit();
            driver.remove();
            System.out.println("Browser closed and driver removed from ThreadLocal.");
        }
    }
}