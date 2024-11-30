Feature: Get the created employee

  Background:
    Given request to generate token with valid email "admin_access@google.com" and valid password "Password123" is prepared
    When POST request to generate token is called
    Then response status code is 200
    Then token has to match JWT format

  @api @artem @valid @group6
  Scenario: Get the created employee by valid ID
    Given a request is prepared to get the created the employee by valid ID "115714A"
    When a GET call is made to get the employee
    Then the status for get call is 200
    And the data coming from the GET call should be
      | emp_firstname | emp_lastname | emp_middle_name | emp_gender | emp_birthday | emp_status | emp_job_title |
      | Matthew       | James        | Perry           | Male       | 2000-11-06   | employed   | QA lead       |

  @api @artem @invalid @group6
  Scenario: Get the created employee by invalid ID
    Given a request is prepared to get the created the employee by invalid ID "115601Y"
    When a GET call is made to get the employee
    Then the error status for get call is 400
    And the error message is "Employee does not exist or you have provided invalid employee_id"