Feature:

  Background:
    Given employee logs in with valid username "JackJohn88" and password "Isabella_Carter@123!"
    When employee clicks on My Info button

  @EditableAndDisplayed @Full @group6
  Scenario: Validate
    When employee clicks on Membership button
    And employee click on Add Top Button
    Then employee can see all the required fields are displayed and editable

  @CheckRequiredFields @Full @group6
  Scenario: Check The required Fields
    When employee clicks on Membership button
    And employee click on Add Top Button
    When employee selects "Membership1" from dropdown
    And employee selects paid by "Company"
    And employee enters subscription amount "500"
    And employee selects "Euro" from the currency dropdown menu
    And empployee selects "2012-02-12" as commence date
    And employee selects "2024-12-11" as renewal date
    Then employee clicks on Save membership button
    And message "Successfully Saved" is displayed

  @EditAndCheck @Full @group6
  Scenario: Edit employee details and check
    When employee clicks on Membership button
    When employee click on membership "Membership1"
    When employee selects "Elsayed Masoud" from dropdown
    And employee selects paid by "Individual"
    And employee enters subscription amount "300"
    And employee selects "Albanian Lek" from the currency dropdown menu
    And empployee selects "2010-02-22" as commence date
    And employee selects "2010-12-11" as renewal date
    Then employee clicks on Save membership button
    And message "Successfully Saved" is displayed
    Then all the saved details are checked "Elsayed Masoud","Individual","300.00","ALL","2010-02-22","2010-12-11"

  @Delete @Full @group6
  Scenario:Delete the membership as an employee
    When employee clicks on Membership button
    When employee select "Membership1"
    And employee clicks on Delete button
    Then message for deleted membership "Successfully Deleted" is displayed