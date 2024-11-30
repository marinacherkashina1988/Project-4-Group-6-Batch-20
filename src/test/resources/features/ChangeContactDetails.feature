Feature: Changing contact details as an employee

  Background:
    Given employee logs in with valid username "JackJohn88" and password "Isabella_Carter@123!"
    When employee clicks on My Info button
    Then employee is navigated to the employee profile "Jack John Beans"
    And employee clicks on Contact Details button
    And employee clicks on Edit contact details button

  @changeContactDetails @validateContactDetailsFields @failed @group6
  Scenario: Validate fields on the contact information page
    Then following fields are displayed: "Address Street 1", "Address Street 1", "City", "State", "Zip Code", "Country", "Home Phone", "Mobile Phone", "Work Phone", "Work Email", "Other Email"
    And textboxes and dropdown are displayed and editable

  @changeContactDetails @invalidContactDetails @passed @group6
  Scenario: Update the contact details with invalid details
    When employee updates the following fields with invalid data type
      | Home Telephone | Mobile          | Work Telephone | Work Email   | Other Email   |
      | *home number*  | *mobile number* | *work number*  | *work email* | *other email* |
    And employee clicks on Save contact details button
    Then Error message "Allows numbers and only + - / ( )" is displayed next to the Home Telephone textbox
    And Error message "Allows numbers and only + - / ( )" is displayed next to the Mobile textbox
    And Error message "Allows numbers and only + - / ( )" is displayed next to the Work Telephone textbox
    And Error message "Expected format: admin@example.com" is displayed next to the Work Email textbox
    And Error message "Expected format: admin@example.com" is displayed next to the Other Email textbox

  @changeContactDetails @validContactDetails @passed @group6
  Scenario: Update the contact details with valid details
    When employee corrects the following fields with valid details
      | Address Street 1 | Address Street 2 | City       | State/Province | Zip/Postal Code | Country       | Home Telephone | Mobile         | Work Telephone | Work Email             | Other Email          |
      | 123 Main Street  | Apt 1            | Metropolis | Alaska         | 90210           | United States | (217) 555-1234 | (217) 555-5678 | (217) 555-9012 | JackJohn88@company.com | JackJohn88@gmail.com |
    And employee clicks on Save contact details button
    Then employee contact details are successfully updated and match the details provided
      | Address Street 1 | Address Street 2 | City       | State/Province | Zip/Postal Code | Country       | Home Telephone | Mobile         | Work Telephone | Work Email             | Other Email          |
      | 123 Main Street  | Apt 1            | Metropolis | Alaska         | 90210           | United States | (217) 555-1234 | (217) 555-5678 | (217) 555-9012 | JackJohn88@company.com | JackJohn88@gmail.com |
