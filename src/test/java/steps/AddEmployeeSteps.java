package steps;

import io.cucumber.java.en.And;
import utils.DBReader;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import pages.DashboardPage;
import utils.*;

import java.io.IOException;
import java.sql.*;
import java.util.List;
import java.util.Map;

public class AddEmployeeSteps extends CommonMethods {

    public static String employee_ID;
    public static String expectedFirstName;
    public static String expectedMiddleName;
    public static String expectedLastName;

    @And("user clicks on PIM option")
    public void userClicksOnPIMOption() {
        click(dashboardPage.pimMenuButton);
    }

    @When("user clicks on Add Employee button")
    public void user_clicks_on_add_employee_button() {
        click(dashboardPage.addEmployeeButton);
    }

    @When("user enters an employee full name")
    public void user_enters_an_employee_full_name() {
        try {
            List<Map<String, String>> newEmployeeDetails = ExcelReader.read();
            for (Map<String, String> employee : newEmployeeDetails) {
                sendText(employee.get("First Name"), addEmployeePage.firstNameField);
                sendText(employee.get("Middle Name"), addEmployeePage.middleNameField);
                sendText(employee.get("Last Name"), addEmployeePage.lastNameField);
                expectedFirstName = employee.get("First Name");
                expectedMiddleName = employee.get("Middle Name");
                expectedLastName = employee.get("Last Name");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        employee_ID = addEmployeePage.employeeIDField.getAttribute("value");
    }

    @When("user clicks on Save button")
    public void user_clicks_on_save_button() {
        click(addEmployeePage.saveButton);
    }

    @Then("user is navigated to the new employee profile page")
    public void user_is_navigated_to_the_new_employee_profile_page() {
        Assert.assertTrue(addEmployeePage.personalDetails.isDisplayed());
    }

    @When("user enters an employee full name and a unique employee ID")
    public void user_enters_an_employee_full_name_and_a_unique_employee_id() {
        int employeeID = generateNumbers();
        try {
            List<Map<String, String>> newEmployeeDetails = ExcelReader.read();
            for (Map<String, String> employee : newEmployeeDetails) {
                sendText(employee.get("First Name"), addEmployeePage.firstNameField);
                sendText(employee.get("Middle Name"), addEmployeePage.middleNameField);
                sendText(employee.get("Last Name"), addEmployeePage.lastNameField);
                sendText(String.valueOf(employeeID), addEmployeePage.employeeIDField);
                expectedFirstName = employee.get("First Name");
                expectedMiddleName = employee.get("Middle Name");
                expectedLastName = employee.get("Last Name");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        employee_ID = addEmployeePage.employeeIDField.getAttribute("value");
    }

    @When("user enters middlename")
    public void user_enters_middlename() {
        sendText("Middle Name", addEmployeePage.middleNameField);
    }

    @Then("clear error message or prompts is displayed for firstname and lastname fields")
    public void clear_error_message_or_prompts_is_displayed_for_firstname_and_lastname_fields() {
        Assert.assertTrue(addEmployeePage.empFirstNameErrorMsg.isDisplayed());
        Assert.assertTrue(addEmployeePage.empLastNameErrorMsg.isDisplayed());
    }

    @When("user enters an employee full name and existing ID {string}")
    public void user_enters_an_employee_full_name_and_existing_id(String existingId) {
        try {
            List<Map<String, String>> newEmployeeDetails = ExcelReader.read();
            for (Map<String, String> employee : newEmployeeDetails) {
                sendText(employee.get("First Name"), addEmployeePage.firstNameField);
                sendText(employee.get("Middle Name"), addEmployeePage.middleNameField);
                sendText(employee.get("Last Name"), addEmployeePage.lastNameField);
                sendText(existingId, addEmployeePage.employeeIDField);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Then("clear error message is displayed")
    public void clear_error_message_is_displayed() {
        Assert.assertTrue(addEmployeePage.employeeIdErrorMessage.isDisplayed());
    }

    @Then("query {string} is executed to fetch the employee details from the database")
    public void queryIsExecutedToFetchTheEmployeeDetailsFromTheDatabase(String query) {
        List<Map<String, String>> actualEmployee = DBReader.fetch(query + employee_ID);
        String actualFirstname = actualEmployee.get(0).get("emp_firstname");
        String actualMiddleName = actualEmployee.get(0).get("emp_middle_name");
        String actualLastName = actualEmployee.get(0).get("emp_lastname");
        Assert.assertEquals(expectedFirstName, actualFirstname);
        Assert.assertEquals(expectedMiddleName, actualMiddleName);
        Assert.assertEquals(expectedLastName, actualLastName);
    }
}