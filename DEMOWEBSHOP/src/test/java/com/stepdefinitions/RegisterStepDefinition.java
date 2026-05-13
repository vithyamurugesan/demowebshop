package com.stepdefinitions;

import com.actions.RegisterAction;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import org.testng.Assert;

import java.util.List;
import java.util.Map;

public class RegisterStepDefinition {

    RegisterAction registerAction;

    public RegisterStepDefinition() {
        registerAction = new RegisterAction(Hooks.getDriver());
    }

  

    @Given("User is on Demo Web Shop application")
    public void user_is_on_demo_web_shop_application() {
        String title = Hooks.getDriver().getTitle();
        Assert.assertNotNull(title, "Browser did not load the Demo Web Shop application.");
        System.out.println("Application loaded. Page title: " + title);
    }

 

    @Given("User navigates to the Register page")
    public void user_navigates_to_register_page() {
        registerAction.navigateToRegisterPage();
    }

    @When("User enters valid registration details")
    public void user_enters_valid_registration_details(DataTable dataTable) {
        List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);
        Map<String, String> row = data.get(0);
        registerAction.enterValidRegistrationDetails(
                row.get("firstName"), row.get("lastName"),
                row.get("email"), row.get("password"), row.get("confirmPassword"));
    }

    @When("User clicks on Register button")
    public void user_clicks_on_register_button() {
        registerAction.clickRegisterButton();
    }

    @Then("User should be registered successfully")
    public void user_should_be_registered_successfully() {
        Assert.assertTrue(registerAction.isRegistrationSuccessful(),
                "Registration was not successful. Success message not displayed.");
    }

    @Then("User should see registration confirmation message")
    public void user_should_see_registration_confirmation_message() {
        String msg = registerAction.getSuccessMessage();
        Assert.assertTrue(msg.contains("Your registration completed"),
                "Expected confirmation message not found. Actual: " + msg);
    }

  

    @When("User enters registration details with an already registered email")
    public void user_enters_registration_details_with_existing_email(DataTable dataTable) {
        List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);
        Map<String, String> row = data.get(0);
        registerAction.enterRegistrationWithExistingEmail(
                row.get("firstName"), row.get("lastName"),
                row.get("email"), row.get("password"), row.get("confirmPassword"));
    }

    @Then("User should see existing email error message")
    public void user_should_see_existing_email_error_message() {
        Assert.assertTrue(registerAction.isExistingEmailErrorDisplayed(),
                "Existing email error message is not displayed.");
        String errorMsg = registerAction.getExistingEmailErrorMessage();
        Assert.assertTrue(errorMsg.contains("already") || errorMsg.contains("registered")
                        || errorMsg.contains("exists"),
                "Unexpected existing email error message: " + errorMsg);
    }
}