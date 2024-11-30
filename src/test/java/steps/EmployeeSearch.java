package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.EmployeeSearchPage;
import utils.CommonMethods;
import utils.ConfigReader;

import java.util.List;
import java.util.Map;

public class EmployeeSearch extends CommonMethods {
    public String fullName;
    public String partialName;
    public String validID;
    Actions actions = new Actions(driver);

    @When("user enters {string}")
    public void user_enters(String fullName) {
        getWait().until(ExpectedConditions.textToBePresentInElementValue(employeeSearchPage.emloyeeNameSearchField, "Type for hints..."));
        sendText(fullName, employeeSearchPage.emloyeeNameSearchField);
        this.fullName = fullName;
        actions.moveToElement(employeeSearchPage.emloyeeNameSearchField).sendKeys("\uE007").perform();
    }

    @When("user clicks Search button")
    public void user_clicks_search_button() {
        click(employeeSearchPage.searchButton);
    }

    @Then("user is able to see the employee")
    public void user_is_able_to_see_the_employee() {
        String firstName = "";
        String lastName = "";
        int spaceIndex = fullName.indexOf(" ");
        if (spaceIndex == -1) {
            firstName = fullName.trim();
        } else {
            firstName = fullName.substring(0, spaceIndex).trim();
            lastName = fullName.substring(spaceIndex + 1).trim();
        }
        boolean employeeFound = false;
        for (int i = 0; i < employeeSearchPage.allFirstNames.size(); i++) {
            String pageFirstName = employeeSearchPage.allFirstNames.get(i).getText().trim();
            String pageLastName = employeeSearchPage.allLastNames.get(i).getText().trim();
            if (spaceIndex == -1) {
                if (pageFirstName.equals(firstName) || pageLastName.equals(firstName)) {
                    employeeFound = true;
                    break;
                }
            } else {
                if (pageFirstName.equals(firstName) && pageLastName.equals(lastName)) {
                    employeeFound = true;
                    break;
                }
            }
        }
        Assert.assertTrue(employeeFound);
    }
    @When("user enters {string} regarding case in Employee Name field")
    public void user_enters_regarding_case_in_employee_name_field(String partialName) {
        getWait().until(ExpectedConditions.textToBePresentInElementValue(employeeSearchPage.emloyeeNameSearchField, "Type for hints..."));
        sendText(partialName, employeeSearchPage.emloyeeNameSearchField);
        this.partialName = partialName;
        actions.moveToElement(employeeSearchPage.emloyeeNameSearchField).sendKeys("\uE007").perform();
    }
    @Then("user is able to see some results")
    public void user_is_able_to_see_many_matches_as_results() {
        boolean employeeFound = false;
        for (int i = 0; i < employeeSearchPage.allFirstNames.size(); i++) {
            String pageFirstName = employeeSearchPage.allFirstNames.get(i).getText().trim();
            String pageLastName = employeeSearchPage.allLastNames.get(i).getText().trim();
             if (pageFirstName.toLowerCase().contains(partialName.toLowerCase()) || pageLastName.toLowerCase().contains(partialName.toLowerCase())) {
                    employeeFound = true;
                    break;
            }
        }
        Assert.assertTrue(employeeFound);
    }

    @When("user enters valid employee ID {string} in the ID field")
    public void userEntersValidEmployeeIDInTheIDField(String validID) {
        sendText(validID, employeeSearchPage.employeeIDSearchField);
        this.validID=validID;
    }

    @Then("user is able to see the unique employee")
    public void userIsAbleToSeeTheUniqueEmployee() {
        Assert.assertEquals(employeeSearchPage.allIDs.size(),1);
        Assert.assertEquals(employeeSearchPage.allIDs.get(0).getText(),validID);
    }

    @When("user enters invalid employee ID {string} in the ID field")
    public void userEntersInvalidEmployeeIDInTheIDField(String invalidID) {
        sendText(invalidID, employeeSearchPage.employeeIDSearchField);
    }

    @Then("user is able to see an error message {string}")
    public void userIsAbleToSeeAnErrorMessage(String message) {
        Assert.assertEquals(employeeSearchPage.noFoundMessage.getText(),message);
    }
    @When("user has a list of partial names to check and he search for them one by one")
    public void user_has_a_list_of_partial_names_to_check_and_he_search_for_them_one_by_one(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> partialNames=dataTable.asMaps();
        for (Map<String, String> name:partialNames) {
            getWait().until(ExpectedConditions.textToBePresentInElementValue(employeeSearchPage.emloyeeNameSearchField, "Type for hints..."));
            sendText(name.get("name"), employeeSearchPage.emloyeeNameSearchField);
            this.partialName = name.get("name");
            actions.moveToElement(employeeSearchPage.emloyeeNameSearchField).sendKeys(Keys.ESCAPE).perform();
            click(employeeSearchPage.searchButton);
            boolean employeeFound = false;
            for (int i = 0; i < employeeSearchPage.allFirstNames.size(); i++) {
                String pageFirstName = employeeSearchPage.allFirstNames.get(i).getText().trim();
                String pageLastName = employeeSearchPage.allLastNames.get(i).getText().trim();
                if (pageFirstName.toLowerCase().contains(partialName.toLowerCase()) || pageLastName.toLowerCase().contains(partialName.toLowerCase())) {
                    employeeFound = true;
                    break;
                }

            }
            Assert.assertTrue(employeeFound);
            click(employeeSearchPage.employeeListBtn);
        }

    }

    @And("user clicks on Employee List button")
    public void userClicksOnEmployeeListButton() {
        click(employeeSearchPage.employeeListBtn);
    }
}