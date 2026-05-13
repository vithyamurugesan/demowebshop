package com.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
 
@CucumberOptions(
        features = "src/test/resources/Features",
        glue = "com.stepdefinitions",
        plugin = {
                "pretty",
                "html:target/cucumber-reports.html",
                "json:target/cucumber.json",
        },
        monochrome = true)
public class TestNGRunner extends AbstractTestNGCucumberTests {
 
}
 