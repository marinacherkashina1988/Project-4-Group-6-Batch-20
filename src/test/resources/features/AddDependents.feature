@addDependents
Feature: Add Dependents to Employee Profile

  Background:
    When user enters admin username and admin password
    And user clicks on login button
    Then user is navigated to dashboard page
    When the user navigates to the Employee List
    And the user searches for the employee "Oliverica" with ID "115585A"
    Then user clicks search button
    And the employee details should be displayed
    Then the user selects the employee
    And the user is on the Personal Details Page
    Then the user clicks on Dependent tab


  @getTheUser @Olivera @group6
  Scenario Outline: Add a dependent with a valid data
    And the user clicks on Add button to add dependent
    When the user enters dependent name as "<name>"
    And the user selects relationship as "<relationship>"
    And the user selects date of birth as "<dateOfBirth>"
    Then the user clicks on "SAVE" button
    And the dependent "<name>" should be added successfully
    Examples:
      |name          |relationship|dateOfBirth|
      |Michael Lee   |Child       |2000-01-11 |
      |Michael Jordan|Parent     |1960-09-11 |


  @missingOrInvalidFields @Olivera @group6
  Scenario Outline: Add a dependent with with missing or invalid fields
    And the user clicks on Add button to add dependent
    When the user sends a request to add a dependent with a following data: a dependent with "<name>",and "<relationship>"
    Then the response should return "<expectedMessage>"
    Examples:
      |name          |relationship |expectedMessage |
      |Mike          |             |Required        |
      |Michael Lee   |Child        |Required        |
      |              |Parent       |Required        |

  @addMultiple @Olivera @group6
  Scenario: Add multiple dependents for an employee
    When the user sends a request to add a dependent with a following data:
      |name          |relationship|dateOfBirth|
      |Michael Smith |Spouse      |1985-01-11 |
      |Michael Lee   |Child       |2000-01-11 |
    Then the response should confirm all dependents were added successfully

  @edit @Olivera @group6
  Scenario Outline: Display dependents in the HRMS system and make sure they are editable
    When the user views  the dependent list for an employee
    Then the dependent list should display all added dependents
    When the user selects a dependent to edit with name "<currentName>"
    And the user updates the dependent's information as "<newName>"
    Then the dependent information should be updated successfully
    Examples:
      |currentName|newName|
      |Michael Lee|Michael Musk |

  @delete @Olivera @group6
  Scenario Outline: Verify successful deletion
    When the user clicks on Dependents tab
    And the user selects a dependent to delete "<newName>"
    And the user clicks on the "Delete" button
    Then the dependent should be removed from the list
    Examples:
      |newName|
      |Jack Miller|