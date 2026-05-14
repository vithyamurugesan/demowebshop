package com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegisterPage {

    WebDriver driver;
    WebDriverWait wait;

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//a[@class='ico-register']")
    public WebElement registerLink;

    @FindBy(id = "FirstName")
    public WebElement firstNameField;

    @FindBy(id = "LastName")
    public WebElement lastNameField;

    @FindBy(id = "Email")
    public WebElement emailField;

    @FindBy(id = "Password")
    public WebElement passwordField;

    @FindBy(id = "ConfirmPassword")
    public WebElement confirmPasswordField;

    @FindBy(id = "register-button")
    public WebElement registerButton;

    @FindBy(xpath = "//div[@class='result']")
    public WebElement registrationSuccessMessage;

    // Shown for existing email (server-side error)
    @FindBy(xpath = "//div[contains(@class,'message-error')]//li")
    public WebElement serverErrorMessage;

    public void clickRegisterLink() {
        wait.until(ExpectedConditions.elementToBeClickable(registerLink));
        registerLink.click();
    }

    public void enterFirstName(String firstName) {
        wait.until(ExpectedConditions.visibilityOf(firstNameField));
        firstNameField.clear();
        firstNameField.sendKeys(firstName);
    }

    public void enterLastName(String lastName) {
        wait.until(ExpectedConditions.visibilityOf(lastNameField));
        lastNameField.clear();
        lastNameField.sendKeys(lastName);
    }

    public void enterEmail(String email) {
        wait.until(ExpectedConditions.visibilityOf(emailField));
        emailField.clear();
        emailField.sendKeys(email);
    }

    public void enterPassword(String password) {
        wait.until(ExpectedConditions.visibilityOf(passwordField));
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    public void enterConfirmPassword(String confirmPassword) {
        wait.until(ExpectedConditions.visibilityOf(confirmPasswordField));
        confirmPasswordField.clear();
        confirmPasswordField.sendKeys(confirmPassword);
    }

    public void clickRegisterButton() {
        wait.until(ExpectedConditions.elementToBeClickable(registerButton));
        registerButton.click();
    }

    public boolean isSuccessMessageDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOf(registrationSuccessMessage));
            return registrationSuccessMessage.isDisplayed();
        } catch (Exception e) {
            System.out.println("Success message not found: " + e.getMessage());
            return false;
        }
    }

    public String getRegistrationSuccessMessage() {
        try {
            wait.until(ExpectedConditions.visibilityOf(registrationSuccessMessage));
            return registrationSuccessMessage.getText();
        } catch (Exception e) {
            return "";
        }
    }

    public boolean isExistingEmailErrorDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOf(serverErrorMessage));
            return serverErrorMessage.isDisplayed();
        } catch (Exception e) {
            System.out.println("Existing email error not found: " + e.getMessage());
            return false;
        }
    }

    public String getExistingEmailError() {
        try {
            wait.until(ExpectedConditions.visibilityOf(serverErrorMessage));
            return serverErrorMessage.getText();
        } catch (Exception e) {
            return "";
        }
    }
}