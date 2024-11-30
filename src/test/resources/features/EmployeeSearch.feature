Feature: Search for employees by their name or employee ID

  Background:
    When user logs in with valid credentials
    And user clicks on PIM option
    And user clicks on Employee List button

  @artem @search @group6
  Scenario: Search for employee by full name
    When user enters "Boris Grebenschikov"
    And user clicks Search button
    Then user is able to see the employee

  @artem @search @group6
  Scenario Outline: Search for employee by partial and ignore case name
    When user enters "<name>" regarding case in Employee Name field
    And user clicks Search button
    Then user is able to see some results
    Examples:
      | name |
      | boRi |

  @artem @search @multi @group6
  Scenario: Search for employee by partial and ignore case name (different variations)
    When user has a list of partial names to check and he search for them one by one
      | name   |
      | Boris  |
      | boris  |
      | Bori   |
      | boriS  |
      | greben |
      | gReBeN |

  @artem @search @group6
  Scenario: Search for employee by id
    When user enters valid employee ID "115714A" in the ID field
    And user clicks Search button
    Then user is able to see the unique employee

  @artem @search @group6
  Scenario: Search for employee by invalid id
    When user enters invalid employee ID "115601Y" in the ID field
    And user clicks Search button
    Then user is able to see an error message "No Records Found"