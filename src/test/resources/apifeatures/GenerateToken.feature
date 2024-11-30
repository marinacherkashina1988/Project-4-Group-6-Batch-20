Feature: Generate Authentication Token

  Background: User has registered with valid credentials

  @generateToken @errorMessage @generateTokenErrorMessage @api @invalidCredentials @group6
  Scenario: Generating token with any required fields missing and validating an error
    Given request to generate token with email "admin_access@google.com" and missing password is prepared
    When POST request to generate token is called
    Then response status code is 400
    Then error message "Please fill all inputs" is displayed in response body
    When request to generate token with missing email and password "Password123" is prepared
    And POST request to generate token is called
    Then response status code is 400
    Then error message "Please fill all inputs" is displayed in response body

  @generateToken @errorMessage @generateTokenErrorMessage @api @invalidCredentials @group6
  Scenario: Generating token by providing incorrect credentials
    Given request to generate token with mismatching email "lulu@gmail.com" and correct password "Password123" is prepared
    When POST request to generate token is called
    Then response status code is 400
    Then error message "Email or Password is incorrect." is displayed in response body
    When request to generate token with correct email "admin_access@google.com" and mismatching password "Wrong111" is prepared
    And POST request to generate token is called
    Then response status code is 400
    Then error message "Email or Password is incorrect." is displayed in response body

  @generateToken @errorMessage @api @validCredentials @group6
  Scenario: Generating token by providing valid credentials
    Given request to generate token with valid email "admin_access@google.com" and valid password "Password123" is prepared
    When POST request to generate token is called
    Then response status code is 200
    Then token has to match JWT format