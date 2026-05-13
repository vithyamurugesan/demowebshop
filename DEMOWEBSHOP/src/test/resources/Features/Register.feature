Feature: Vithya_13MAY2026_DEMOWEBSHOP_FeatureFileforUser Registration Functionality
  Description:
     This feature is used to verify user registration functions
     like registering with valid details and existing email.

  Background:
    Given User is on Demo Web Shop application

  @ValidRegistration
  Scenario: Verify user registration with valid details
    Given User navigates to the Register page
    When User enters valid registration details
      | firstName | lastName | email                  | password   | confirmPassword |
      | John      | Doe      | placeholder@testmail.com | Test@1234  | Test@1234       |
    And User clicks on Register button
    Then User should be registered successfully
    And User should see registration confirmation message

  @ExistingEmail
  Scenario: Verify registration with existing email
    Given User navigates to the Register page
    When User enters registration details with an already registered email
      | firstName | lastName | email                    | password   | confirmPassword |
      | Alice     | Brown    | johndoe123@testmail.com  | Test@1234  | Test@1234       |
    And User clicks on Register button
    Then User should see existing email error message
