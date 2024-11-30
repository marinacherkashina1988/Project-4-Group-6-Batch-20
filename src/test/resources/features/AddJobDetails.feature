Feature:Job Details

  Background:
    When user enters valid credentials
    And clicks login button
    And user clicks PIM option
    And user clicks employee list option
    And user navigates to the employee profile by providing employee ids "42843534"
    When user clicks on Job button

  @SaveAndValidate @group6
  Scenario: Validate and save the mandatory fields
    And user clicks on EditSave button to unlock the employee job details
    When user add required fields "advisor", "active","2015-12-15","IT Support","Big Office"
    And user validate that fields are not empty
    And user clicks EditSave button
    Then user can see the success message
    And all the details has been saved "advisor", "active","2015-12-15","IT Support","Big Office"

  @checkMandatoryFields @group6
  Scenario: Checking Mandatory fields
    And user clicks on EditSave button to unlock the employee job details
    When user don't add all the required fields "advisor", "active","IT Support"
    And user clicks EditSave button
    Then System should gives an error "Missing Fields"
