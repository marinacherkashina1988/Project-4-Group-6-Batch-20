package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import utils.CommonMethods;
import utils.ConfigReader;

public class LoginDetailsSteps extends CommonMethods {

    @Given("user logs in with valid credentials")
    public void user_logs_in_with_valid_credentials() {
        sendText(ConfigReader.read("userName"), loginPage.usernameField);
        sendText(ConfigReader.read("password"), loginPage.passwordField);
        click(loginPage.loginButton);
    }

    @And("user navigates to the dashboard page")
    public void user_navigates_to_the_dashboard_page() {
        Assert.assertTrue(dashboardPage.welcomeMessage.getText().contains("Welcome"));
        Assert.assertTrue(dashboardPage.welcomeMessage.isDisplayed());
    }

    @Then("user clicks on add employee option")
    public void user_clicks_on_add_employee_option() {
        click(addEmployeePage.addEmployee);
    }

    @When("user selects the checkbox {string}")
    public void user_selects_the_checkbox(String checkBoxName) {
        switch (checkBoxName) {
            case "Create login details":
                waitForVisibility(addEmployeePage.createLoginCheckbox);
                click(addEmployeePage.createLoginCheckbox);

                break;
            default:
                throw new RuntimeException("Invalid checkbox name: " + checkBoxName);
        }
    }

    @Then("username,password,confirm password, and status field should be enabled")
    public void username_password_confirm_password_and_status_field_should_be_enabled() {
        WebElement[] fields = {addEmployeePage.usernameField, addEmployeePage.passwordField,
                addEmployeePage.confirmPasswordField, addEmployeePage.statusDropdown};

        for (WebElement field : fields) {
            if (!field.isEnabled()) {
                throw new AssertionError("Field " + field.getAttribute("id") +
                        " is not enabled");
            }
        }
    }

    @When("user selects the checkbox {string} in the Add Employee page")
    public void user_selects_the_checkbox_in_the_Add_Employee_page(String checkBoxName) {
        switch (checkBoxName) {
            case "Create login details":
                waitForVisibility(addEmployeePage.createLoginCheckbox);
                click(addEmployeePage.createLoginCheckbox);
                break;
            default:
                throw new RuntimeException("Invalid checkbox name:" + checkBoxName);
        }
    }

    @Then("username, password fields, and status field are enabled")
    public void username_password_fields_and_status_field_are_enabled() {
        Assert.assertTrue("Username field is not enabled",
                addEmployeePage.passwordField.isEnabled());
        Assert.assertTrue("Password field is not enabled",
                addEmployeePage.confirmPasswordField.isEnabled());
        Assert.assertTrue("Confirm password field is not enabled",
                addEmployeePage.usernameField.isEnabled());
        Assert.assertTrue("Status dropdown is not enabled",
                addEmployeePage.statusDropdown.isEnabled());
    }


    @When("user enters {string} as password")
    public void user_enters_as_password(String password) {
        sendText(password, addEmployeePage.passwordField);
    }

    @And("user confirms the password {string}")
    public void user_confirms_the_password(String confirmPassword) {
        sendText(confirmPassword, addEmployeePage.confirmPasswordField);
    }

    @Given("user selects {string} as the status from the dropdown")
    public void user_selects_as_the_status_from_the_dropdown(String status) {
        selectFromDropDown(status, addEmployeePage.statusDropdown);
    }

    @Then("there should be no error messages under password and confirm password fields")
    public void there_should_be_no_error_messages_under_password_and_confirm_password_fields() {
        try {
            Assert.assertFalse("Error message is displayed under the password field",
                    addEmployeePage.passwordError.isDisplayed());
        } catch (NoSuchElementException e) {
            System.out.println("No error message under field as expected");
        }
        try {
            Assert.assertFalse("Error message is displayed under the confirm password field",
                    addEmployeePage.confirmPasswordError.isDisplayed());
        } catch (NoSuchElementException e) {
            System.out.println("No error message under confirm password field as expected");
        }
    }

    @Then("status dropdown should display {string}")
    public void status_dropdown_should_display(String expectedStatus) {
        String actualStatus = getSelectedOption(addEmployeePage.statusDropdown);

        Assert.assertEquals("Status dropdown value is incorrect",
                expectedStatus, actualStatus);
    }

    @When("user clicks {string}")
    public void user_clicks(String buttonName) {
        switch (buttonName) {
            case "Save":
                click(addEmployeePage.saveButton);
                break;
            default:
                throw new RuntimeException("Invalid button name: " + buttonName);
        }
    }

    @Then("user should be navigated to the Human Management System page")
    public void userShouldBeNavigatedToTheHumanManagementSystemPage() {
        String actualTitle = driver.getCurrentUrl();
        Assert.assertNotEquals(actualTitle, "http://hrm.syntaxtechs.net/humanresources/symfony/web/index.php/pim/addEmployee");
    }

    @When("user fills mandatory fields {string} and {string} for First Name and Last Name")
    public void user_fills_mandatory_fields_and_for_first_name_and_last_name(
            String firstName, String lastName) {
        sendText(firstName, addEmployeePage.firstNameField);
        sendText(lastName, addEmployeePage.lastNameField);
    }

    @When("user selects the {string}")
    public void user_selects_the(String option) {
        switch (option.toLowerCase()) {
            case "create login details":
                click(addEmployeePage.createLoginCheckbox);
                break;
            case "enabled":
                selectFromDropDown(addEmployeePage.statusDropdown, "Enabled");
                break;
            default:
                throw new RuntimeException("Invalid option provided: " + option);
        }
    }

    @When("user sets {string} as username")
    public void user_sets_as_username(String username) {
        sendText(username, addEmployeePage.usernameField);
    }

    @When("user enters {string} as the password")
    public void user_enters_as_the_password(String password) {
        sendText(password, addEmployeePage.passwordField);
    }

    @When("user confirms password as {string}")
    public void user_confirms_password_as(String confirmPassword) {
        sendText(confirmPassword, addEmployeePage.confirmPasswordField);
    }

    @When("user clicks the {string} button")
    public void user_clicks_the_button(String buttonSave) {
        switch (buttonSave.toLowerCase()) {
            case "save":
                click(addEmployeePage.saveButton);
                break;
            default:
                throw new RuntimeException("Invalid button name: " + buttonSave);
        }
    }

    @Then("error message {string} should be visible")
    public void error_message_should_be_visible(String expectedMessage) {
        waitForVisibility(addEmployeePage.confirmPasswordError);

        String actualMessage = addEmployeePage.confirmPasswordError.getText();
        System.out.println("Actual error message: " + actualMessage);
        Assert.assertEquals("Error message is incorrect", expectedMessage, actualMessage);
    }

    @When("user logins with valid credentials as {string} and {string}")
    public void userLoginsWithValidCredentialsAsAnd(String userName, String password) {
        sendText(userName, loginPage.usernameField);
        sendText(password, loginPage.passwordField);
        click(loginPage.loginButton);
    }

    @When("user fills mandatory fields {string},{string} for First Name,Last Name, and Employee ID")
    public void userFillsMandatoryFieldsForFirstNameLastNameAndEmployeeID(String firstName, String lastName) {
        sendText(firstName, addEmployeePage.firstNameField);
        sendText(lastName, addEmployeePage.lastNameField);
    }

    @When("username is generated based on ID")
    public void usernameIsGeneratedBasedOnID() {
        String username = "user" + addEmployeePage.employeeIDField.getAttribute("value");
        sendText(username, addEmployeePage.usernameField);
        System.out.println(username);
    }
}


