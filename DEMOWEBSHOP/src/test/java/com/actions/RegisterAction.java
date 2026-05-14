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


    private String generateUniqueEmail() {
        String timestamp = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        return "vithya_" + timestamp + "@testmail.com";
    }

    public void navigateToRegisterPage() {
        registerPage.clickRegisterLink();
    }

 
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
        registerPage.enterEmail(email);         
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