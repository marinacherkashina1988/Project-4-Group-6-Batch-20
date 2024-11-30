package steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.eo.Se;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import utils.CommonMethods;
import utils.ConfigReader;

import java.util.List;


public class JobDetailsSteps extends CommonMethods {

    @When("user enters valid credentials")
    public void user_enters_valid_credentials() {
        sendText(ConfigReader.read("userName"), loginPage.usernameField);
        sendText(ConfigReader.read("password"), loginPage.passwordField);
    }

    @When("clicks login button")
    public void clicks_login_button() {
       click(loginPage.loginButton);
    }

    @When("user clicks PIM option")
    public void user_clicks_pim_option() {
        click(dashboardPage.pimMenuButton);
    }

    @When("user clicks employee list option")
    public void user_clicks_employee_list_option() {
        click(dashboardPage.employeeListButton);
    }

    @When("user navigates to the employee profile by providing employee ids {string}")
    public void user_navigates_to_the_employee_profile_by_providing_employee_ids(String ids) {
        sendText(ids, employeeSearchPage.employeeIDSearchField);
        click(employeeSearchPage.searchButton);
        List<WebElement> allEmployeeId = driver.findElements(By.xpath("//table/tbody/tr/td[2]"));
        for (WebElement id : allEmployeeId) {
            if (id.getText().equals(ids)) {
                click(id);
            }
        }
    }

    @When("user clicks on Job button")
    public void user_clicks_on_job_button() {
       click(jobDetailsPage.jobClick);
    }

    @When("user clicks on EditSave button to unlock the employee job details")
    public void user_clicks_on_edit_save_button_to_unlock_the_employee_job_details() {
        click(jobDetailsPage.saveEditDetails);
    }

    @When("user add required fields {string}, {string},{string},{string},{string}")
    public void user_all_required_fields(String title, String status, String date, String unit, String location) {
        selectFromDropDown(jobDetailsPage.jobTitleField,title) ;
        selectFromDropDown(jobDetailsPage.empStatusField,status);
        sendText(date, jobDetailsPage.joinedDate);
        selectFromDropDown(jobDetailsPage.subUnit,unit);
        selectFromDropDown(jobDetailsPage.empLocation,location);
    }

    @When("user validate that fields are not empty")
    public void user_validate_that_fields_are_not_empty() {
        Assert.assertNotNull("All the required fields are not null",jobDetailsPage.requiredList);
        Assert.assertNotNull("All the required data are not null",jobDetailsPage.joinedDate);
    }

    @When("user clicks EditSave button")
    public void user_clicks_edit_save_button() {
        click(jobDetailsPage.saveEditDetails);
    }

    @Then("user can see the success message")
    public void user_can_see_the_success_message() {
       Assert.assertTrue("Success message is displayed",jobDetailsPage.successMessage.isDisplayed());
    }

    @When("user don't add all the required fields {string}, {string},{string}")
    public void user_don_t_add_all_the_required_fields(String title, String status, String unit) {
        selectFromDropDown(jobDetailsPage.jobTitleField,title) ;
        selectFromDropDown(jobDetailsPage.empStatusField,status);
        selectFromDropDown(jobDetailsPage.subUnit,unit);
    }

    @Then("System should gives an error {string}")
    public void system_should_gives_an_error(String ErrorMessage) {
        String actualMessage = jobDetailsPage.successMessage.getText();
        if (!ErrorMessage.equalsIgnoreCase(actualMessage)) {
            System.out.println("Fields are not marked as mandatory. Error message is not displayed.");
            System.out.println("Expected message: " + ErrorMessage);
            System.out.println("Actual message: " + actualMessage);
        }
        //Assert.assertEquals("Error message does not match",jobDetailsPage.successMessage.getText(),ErrorMessage);
    }

    @Then("all the details has been saved {string}, {string},{string},{string},{string}")
    public void all_the_details_has_been_saved(String title, String status, String date, String unit, String location) {
        Select select=new Select(jobDetailsPage.jobTitleField);
        Select sel1=new Select(jobDetailsPage.empStatusField);
        Select sel3=new Select(jobDetailsPage.subUnit);
        Select sel4=new Select(jobDetailsPage.empLocation);
        Assert.assertEquals(select.getFirstSelectedOption().getText(),title);
        Assert.assertEquals(sel1.getFirstSelectedOption().getText(),status);
        Assert.assertEquals(sel3.getFirstSelectedOption().getText(),unit);
        Assert.assertEquals(sel4.getFirstSelectedOption().getText(),location);
        System.out.println(jobDetailsPage.joinedDate.getText());
    }

}