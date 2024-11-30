Feature: Change personal details

  Background:
    When user logs in with valid credentials
    And user clicks on PIM option
    And user clicks on employee list option
    When user enters valid employee ID "42843534" in the ID field
    When user clicks Search button
    Then user is able to see the unique employee

    @edit @artem @group6
    Scenario: Edit personal details
      When user clicks on unique employee
      Then the new page with user profile is opened
      When user clicks on Edit-Save button
      When textbox with employee firstname is displayed and editable
      Then user enters a new first name "Maria"
      When textbox with employee middlename is displayed and editable
      Then user enters a new middle name "Isabella"
      When textbox with employee lastname is displayed and editable
      Then user enters a new last name "Ocasio-Carter"
      When gender radio button is displayed and clickable
      And options "Male" and "Female" are presented
      Then user clicks on "Female" button
      When a dropdown menu Nationality is displayed
      Then user selects their nationality "British"
      When a dropdown menu Marital Status is displayed
      And options "Single", "Married" and "Other" are presented
      Then user selects marital status "Married"
      When user clicks on Edit-Save button
      Then user sees a message "Successfully Saved"
      Then user is able to see that the firstname is updated
      And user is able to see that the middle name is updated
      And user is able to see that the lastname is updated
      And the employee gender is updated
      And the employee nationality is updated
      And the marital status is updated
      And the data is presented in database