Feature: Admin Login to the HRMS website
Given website address and valid credentials in config.properties

  @login @artem @group6
  Scenario: Successful admin login
    When user logs in with valid credentials
    Then user navigates to the dashboard page
