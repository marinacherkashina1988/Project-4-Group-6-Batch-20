Feature: Validate Employee Creation API
  As a tester
  I want to validate the API for creating a new employee record
  So that employee details are stored correctly and error handling works for invalid or incomplete data

  Background:
    Given request to generate token with valid email "admin_access@google.com" and valid password "Password123" is prepared
    When POST request to generate token is called
    Then response status code is 200
    Then token has to match JWT format

  @employeeCreation @api
  Scenario: Validate successful employee creation with valid data
    Given  a valid payload is prepared with following details:
      |emp_firstname|emp_lastname|emp_middle_name|emp_gender|emp_birthday|emp_status|emp_job_title|
      |Mark         |Scott       |JJ             |M         |1995-11-06  |Trainee  |QA manager    |
    When the post request is sent to create an employee
    Then the status code should be 201
    And  the response message should be "Employee Created"

  @invalidGender @api
  Scenario: Validate API with invalid gender
    Given a payload is prepared with employee details and invalid gender "X"
      |emp_firstname|emp_lastname|emp_middle_name|emp_gender|emp_birthday|emp_status|emp_job_title|
      |Matthew      |James       |Perry          |X         |2000-11-06  |employed  |QA manager   |
    When the post request is sent to create an employee
    Then the status code should be 400
    And the error message should be "enter M for male enter F for female"

  @invalidDoBformat @api
  Scenario: Validate API with invalid birth date format
    Given a payload is prepared with employee details and invalid birthday format:
      |emp_firstname|emp_lastname|emp_middle_name|emp_gender|emp_birthday|emp_status|emp_job_title|
      |Mark         |Scott       |JJ             |M         |1995/11/06  |Trainee  |QA manager    |
    When the post request is sent to create an employee
    Then the status code should be 400
    And the error message should be displayed


  @missingFields @api @group6
  Scenario: Validate missing fields in payload
    Given a payload is prepared with employee details and missing fields
      |emp_firstname|emp_lastname|emp_middle_name|emp_gender|emp_birthday|emp_status|emp_job_title|
      |             |Scott       |JJ             |M         |1995-11-06  |Trainee  |QA manager    |
    When the post request is sent to create an employee
    Then the status code should be 400
    Then the response should indicate that the payload is incomplete

  @invalidStatus @api @group6
  Scenario: Validate API with invalid status
    Given a payload is prepared with employee details and invalid status
      |emp_firstname|emp_lastname|emp_middle_name|emp_gender|emp_birthday|emp_status|emp_job_title|
      |Mark         |Scott       |JJ             |M         |1995-11-06  |Status0   |QA manager    |
    When the post request is sent to create an employee
    Then the response should indicate that the status value is incorrect
    But the API created the employee record with invalid status