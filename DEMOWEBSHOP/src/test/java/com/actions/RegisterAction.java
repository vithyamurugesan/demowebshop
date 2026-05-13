package com.actions;

import com.pages.RegisterPage;
import org.openqa.selenium.WebDriver;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class RegisterAction {

    WebDriver driver;
    RegisterPage registerPage;

    public RegisterAction(WebDriver driver) {
        this.driver = driver;
        registerPage = new RegisterPage(driver);
    }

    /**
     * Generates a unique email using current timestamp.
     * Format: vithya_20260514_120530@testmail.com
     * Guaranteed unique on every run — no manual change needed.
     */
    private String generateUniqueEmail() {
        String timestamp = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        return "vithya_" + timestamp + "@testmail.com";
    }

    public void navigateToRegisterPage() {
        registerPage.clickRegisterLink();
    }

    /**
     * Overrides the email from the feature file with a fresh unique email every run.
     */
    public void enterValidRegistrationDetails(String firstName, String lastName,
                                               String email, String password,
                                               String confirmPassword) {
        String uniqueEmail = generateUniqueEmail();
        System.out.println("Using generated email: " + uniqueEmail);
        registerPage.enterFirstName(firstName);
        registerPage.enterLastName(lastName);
        registerPage.enterEmail(uniqueEmail);   // always fresh
        registerPage.enterPassword(password);
        registerPage.enterConfirmPassword(confirmPassword);
    }

    public void enterRegistrationWithExistingEmail(String firstName, String lastName,
                                                    String email, String password,
                                                    String confirmPassword) {
        registerPage.enterFirstName(firstName);
        registerPage.enterLastName(lastName);
        registerPage.enterEmail(email);         // uses fixed already-registered email
        registerPage.enterPassword(password);
        registerPage.enterConfirmPassword(confirmPassword);
    }

    public void clickRegisterButton() {
        registerPage.clickRegisterButton();
    }

    public boolean isRegistrationSuccessful() {
        return registerPage.isSuccessMessageDisplayed();
    }

    public String getSuccessMessage() {
        return registerPage.getRegistrationSuccessMessage();
    }

    public boolean isExistingEmailErrorDisplayed() {
        return registerPage.isExistingEmailErrorDisplayed();
    }

    public String getExistingEmailErrorMessage() {
        return registerPage.getExistingEmailError();
    }
}