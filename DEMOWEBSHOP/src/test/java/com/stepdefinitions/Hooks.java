package com.stepdefinitions;

import com.utilities.HelperClass;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;

public class Hooks {

    
    public static WebDriver getDriver() {
        return HelperClass.getDriver();
    }

    @Before
    public void setUp(Scenario scenario) {
        System.out.println("====== Starting Scenario: " + scenario.getName() + " ======");
        HelperClass.setUpDriver();
    }

    @After
    public void tearDown(Scenario scenario) {
        System.out.println("====== Ending Scenario: " + scenario.getName()
                + " | Status: " + scenario.getStatus() + " ======");

        if (scenario.isFailed()) {
            byte[] screenshot = HelperClass.captureScreenshot();
            if (screenshot != null) {
                scenario.attach(screenshot, "image/png", "Failure Screenshot");
                System.out.println("Screenshot captured for failed scenario.");
            }
        }

        HelperClass.tearDown();
    }
}