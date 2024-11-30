Feature: Add language proficiency details on the employee profile

  Background:
    Given employee logs in with valid username "JackJohn88" and password "Isabella_Carter@123!"
    When employee clicks on My Info button
    Then employee is navigated to the employee profile "Jack John Beans"
    And employee clicks on Qualifications button

  @addLanguage @validationError @passed @group6
  Scenario: Validating error
    When employee clicks on Add language button
    Then dropdown field is displayed and editable
    And employee selects "Arabic" language from dropdown
    And employee selects the language fluency level "Intermediate" from dropdown
    And employee selects the language competency level "Speaking" from dropdown
    And employee enters a comment longer than 100 characters "Language fluency level and language competency level were not selected due to incorrect dropdown options"
    Then comment validation error "Should be less than 100 characters" is displayed
    And employee clicks on Save button
    Then validation error "Required" is displayed

  @addLanguage @validateDropDown @validateMandatoryFields @failed @group6
    Scenario: Validate dropdown of the mandatory fields
    Given employee clicks on Add language button
    When employee clicks on Select language button
    And language dropdown menu is enabled
    Then standard languages options "Chinese", "French", "German", "Indian", "Indo", "Polish", "Urdu" are displayed
    When employee clicks on Select fluency button
    And fluency dropdown menu is enabled
    Then fluency levels "Basic", "Intermediate", "Advanced", "Fluent" are displayed
    When employee clicks on Select competency button
    And competency dropdown menu is enabled
    Then competency levels "Speaking", "Reading", "Writing" are displayed
    When employee clicks on Comment text box
    Then employee is able to enter a comment "Validating Comment Text Box"