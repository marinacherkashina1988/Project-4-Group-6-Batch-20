Feature: Profile Photo Upload

  Background:
    When user logs in with valid credentials
    And user clicks on PIM option
    And user clicks on employee list option
    And user navigates to the employee profile by providing employee id "42843534"

  @profilePhotoUpload @unsupportedFileType @passed @group6
  Scenario: File upload with unsupported file types
    When user selects a file with a .bmp or .tiff extension
    Then error message for saving photo "Failed to Save: File Type Not Allowed" is displayed

  @profilePhotoUpload @oversizedFile @passed @group6
  Scenario: File upload with oversized file
    And user selects a file that exceeds permitted size in MB
    Then error message for saving photo "Failed to Save: File Size Exceeded" should be displayed

  @profilePhotoUpload @incorrectDimensions @passed @group6
  Scenario: File upload with incorrect dimensions
    And user selects an image file with incorrect dimensions
    Then dimensions 200 px x 200 px are verified
    And recommended message "Accepts jpg, .png, .gif up to 1MB. Recommended dimensions: 200px X 200px" is displayed

  @profilePhotoUpload @validProfilePhoto @passed @group6
  Scenario: Uploading a photo on an employee's profile
    When user selects a file with a .jpg, .png, or .gif extension under 1MB and dimensions are 200px x 200px
    Then profile picture is successfully uploaded and message "Successfully Uploaded" is displayed