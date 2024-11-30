package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.CommonMethods;
import utils.ExcelReader;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class PictureUploadSteps extends CommonMethods {

    @When("user clicks on employee list option")
    public void user_clicks_on_employee_list_option() {
        click(dashboardPage.employeeListButton);
    }

    @When("user navigates to the employee profile by providing employee id {string}")
    public void user_navigates_to_the_employee_profile_by_providing_employee_id(String employee_id) {
        sendText(employee_id, employeeSearchPage.employeeIDSearchField);
        click(employeeSearchPage.searchButton);
        List<WebElement> allEmployeeId = driver.findElements(By.xpath("//table/tbody/tr/td[2]"));
        for (WebElement id : allEmployeeId) {
            if (id.getText().equals(employee_id)) {
                click(id);
                System.out.println(id);
            }
        }
    }

    @When("user selects a file with a .bmp or .tiff extension")
    public void userSelectsAFileWithABmpOrTiffExtension() {
        click(employeeProfilePage.photoField);
        try {
            List<Map<String, String>> employeeDetails = ExcelReader.read();
            for (Map<String, String> employee : employeeDetails) {
                sendText(employee.get("Unsupported File Format"), employeeProfilePage.chooseFileButton);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        click(employeeProfilePage.uploadButton);
    }

    @Then("error message for saving photo {string} is displayed")
    public void errorMessageForSavingPhotoIsDisplayed(String errorMessage) {
        String actualMessage = employeeProfilePage.errorMessage.getText().substring(0, 37);
        Assert.assertEquals(errorMessage, actualMessage);
    }

    @And("user selects a file that exceeds permitted size in MB")
    public void userSelectsAFileThatExceedsPermittedSizeInMB() {
        click(employeeProfilePage.photoField);
        try {
            List<Map<String, String>> employeeDetails = ExcelReader.read();
            for (Map<String, String> employee : employeeDetails) {
                sendText(employee.get("Exceeded 1MB"), employeeProfilePage.chooseFileButton);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        click(employeeProfilePage.uploadButton);
    }

    @Then("error message for saving photo {string} should be displayed")
    public void errorMessageForSavingPhotoShouldBeDisplayed(String errorMessage) {
        String actualMessage = employeeProfilePage.errorMessage.getText().substring(0, 34);
        Assert.assertEquals(errorMessage, actualMessage);
    }

    @And("user selects an image file with incorrect dimensions")
    public void UserSelectsAnImageFileWithIncorrectDimensions() {
        click(employeeProfilePage.photoField);
        try {
            List<Map<String, String>> employeeDetails = ExcelReader.read();
            for (Map<String, String> employee : employeeDetails) {
                sendText(employee.get("Incorrect Dimensions"), employeeProfilePage.chooseFileButton);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        click(employeeProfilePage.uploadButton);
    }

    @Then("dimensions {int} px x {int} px are verified")
    public void dimensionsPxXPxAreVerified(int width, int height) {
        int actualWidth = Integer.valueOf(employeeProfilePage.photoField.getAttribute("width"));
        int actualHeight = Integer.valueOf(employeeProfilePage.photoField.getAttribute("height"));
        if (width != actualWidth && height != actualHeight) {
            Assert.assertTrue(employeeProfilePage.recommendationMessage.isDisplayed());
        }
    }

    @And("recommended message {string} is displayed")
    public void recommendedMessageIsDisplayed(String message) {
        String actualMessage = employeeProfilePage.recommendationMessage.getText();
        Assert.assertEquals(message, actualMessage);
    }


    @When("user selects a file with a .jpg, .png, or .gif extension under 1MB and dimensions are 200px x 200px")
    public void user_selects_a_file_with_a_jpg_png_or_gif_extension_under_1mb_and_dimensions_are_200px_x_200px() {
        click(employeeProfilePage.photoField);
        try {
            List<Map<String, String>> newEmployeeDetails = ExcelReader.read();
            for (Map<String, String> employee : newEmployeeDetails) {
                sendText(employee.get("Profile Photo"), employeeProfilePage.chooseFileButton);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        click(employeeProfilePage.uploadButton);
    }

    @Then("profile picture is successfully uploaded and message {string} is displayed")
    public void profilePictureIsSuccessfullyUploadedAndMessageIsDisplayed(String message) {
        String actualMessage = employeeProfilePage.successfulUploadMessage.getText().substring(0, 21);
        Assert.assertEquals(message, actualMessage);
    }



}