Feature: API User Creation

  @UserCreation @api
  Scenario: Valid User Creation
    Given request is prepared by providing name "Adam", email "dynamic" and password "Password123"
    When Post call is made
    Then the status code for this is 201
    Then and confirmation message appears "User Created"

  @Duplicate @api
  Scenario Outline: Validate User Creation With Incorrect Or Missing Fields
    Given request is prepared with name "<name>", email "<email>" and password "<password>"
    When Post call is made
    Then the status for this is <status_code>
    And the error message appears "<error_message>"
    Examples:
      | name | email                 | password | status_code | error_message |
      | Adam | Stegar@gdfdsfmail.com | Password123 | 200 | The email address you have entered is already registered |
      | Adam | Stegargdfdsfmail.com  | Password123 | 400 |  Invalid Email  |
      | Adam | Stegar@gdfdsfmail.com |             | 400 |  Please fill all inputs |
      |      | Stegar@gdfdsfmail.com | Password123 | 400 | Please fill all inputs  |