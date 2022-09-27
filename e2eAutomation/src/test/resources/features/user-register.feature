Feature: Register as a new user
  As i guest you want to register new account in order to be able to access application functionalities
  ----------------------
  As a guest user
  I want to register new account
  So that I am able to login and ise applications functionalities
  ----------------------
  Business rules:
  (1) User name constraint aka valid email address
  (2) Password policy...

  Scenario: Successful user account registration
    Given I am on the registration page "http://wagner.wang/Identity/Account/Register"
    When I type "username@somedomain.com" in the email field
    And I type "Contoso!1234" in the password field
    And I type "Contoso!1234" in the confirm password field
    And I type "Intership guy" in the full name field
    And I enter valid date "mm/dd/yyyy" in the birth date field
    And I press the "Register" button
    Then I should be logged and redirected to main page
    And my username should appear on the page header in upper right corner

  Scenario Outline: Unsuccessful user account registration
    Given I am on the registration page "http://wagner.wang/Identity/Account/Register"
    When I type <email>  in the email field
    And I type <password> in the password field
    And I type <password> in the confirm password field
    And I type <full-name> in the full name field
    And I type <birth-date> in the birth date field
    And I press the "Register" button
    Then registration process will fail with <err-message> for invalid inputs


    Examples:
      | Description        | email                 | password  | full-name | birth-date | err-message                       |
      | Missing email      |                       | Demo!1234 | Demo      | 01/01/1999 | The Email field is required.      |
      | Missing password   | trainer@example.com |           | Demo      | 01/01/1999 | The Password field is required.   |
      | Missing full name  | trainer@example.com | Demo!1234 |           | 01/01/1999 | The Full name field is required.  |
      | Missing birth date | trainer@example.com | Demo!1234 | Demo      |            | The Birth Date field is required. |