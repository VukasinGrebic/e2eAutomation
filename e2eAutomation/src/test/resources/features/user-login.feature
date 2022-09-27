# Since login is pre-requisites for all other scenario MUST be implemented first
Feature: Login into application
  Login as a default user
  -------------------
  As a registered user
  I want to login into application
  So that I can use all functionalities of the application
  -------------------
  Business rules:
  (1) Password policy...

  # Pre-conditions for all the scenarios
  Background:
    Given I am on the login page

  Scenario: Successful basic login with valid credentials
    When I click on "Login" link
    And I type "username@somedomain.com" in the email field
    And I type "Contoso!1234" in the password field
    And I press the "Log in" button
    Then I should be redirected to main dashboard page

  Scenario Outline: Unsuccessful login with invalid data provided
    When I click on "Login" link
    And I type <username> in the email field
    And I type <password> in the password field
    And I press the "Log in" button
    Then log in will fail with appropriate error message <err-message>

    Examples:
      | Description         | username              | password  | err-message                     |
      | Missing username    |                       | Demo!1234 | The Email field is required.    |
      | Missing password    | trainer@example.com |           | The Password field is required. |
