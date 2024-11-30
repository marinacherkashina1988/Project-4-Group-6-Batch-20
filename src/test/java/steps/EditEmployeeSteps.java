package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import utils.CommonMethods;
import utils.DBReader;

import java.util.List;
import java.util.Map;

public class EditEmployeeSteps extends CommonMethods {
    public String searchPageURL;
    public String empFirstName;
    public String empMiddleMame;
    public String empLastName;
    public String empGender;
    public String empMaritalStatus;
    public String empNationality;
    public String empID;
    public String empGenderCode;
    public String empNationCode;
    public String genderOption1;
    public String genderOption2;

    @When("user clicks on unique employee")
    public void user_clicks_on_unique_employee() {
        searchPageURL=driver.getCurrentUrl();
        empID=employeeSearchPage.allIDs.get(0).getText();
        click(employeeSearchPage.allIDs.get(0));
    }
    @Then("the new page with user profile is opened")
    public void the_new_page_with_user_profile_is_opened() {
        Assert.assertNotEquals(driver.getCurrentUrl(),searchPageURL);
    }
    @When("textbox with employee firstname is displayed and editable")
    public void textbox_with_employee_firstname_is_displayed_and_editable() {
        Assert.assertTrue(editEmployeePage.empFirstName.isDisplayed());
        Assert.assertTrue(editEmployeePage.empFirstName.isEnabled());
    }
    @Then("user enters a new first name {string}")
    public void user_enters_a_new_first_name(String empFirstName) {
        sendText(empFirstName, editEmployeePage.empFirstName);
        this.empFirstName=empFirstName;

    }
    @When("textbox with employee middlename is displayed and editable")
    public void textbox_with_employee_middlename_is_displayed_and_editable() {
        Assert.assertTrue(editEmployeePage.empMiddleName.isDisplayed());
        Assert.assertTrue(editEmployeePage.empMiddleName.isEnabled());
    }

    @Then("user enters a new middle name {string}")
    public void userEntersANewMiddleName(String empMiddleName) {
        sendText(empMiddleName, editEmployeePage.empMiddleName);
        this.empMiddleMame=empMiddleName;
    }
    @When("textbox with employee lastname is displayed and editable")
    public void textbox_with_employee_lastname_is_displayed_and_editable() {
        Assert.assertTrue(editEmployeePage.empLastName.isDisplayed());
        Assert.assertTrue(editEmployeePage.empLastName.isEnabled());
    }
    @Then("user enters a new last name {string}")
    public void userEntersANewLastName(String empLastName) {
        sendText(empLastName, editEmployeePage.empLastName);
        this.empLastName=empLastName;
    }
    @When("gender radio button is displayed and clickable")
    public void gender_radio_button_is_displayed_and_clickable() {
        for (WebElement button: editEmployeePage.genderButtons){
            Assert.assertTrue(button.isDisplayed());
            Assert.assertTrue(button.isEnabled());
            }
    }
    @When("options {string} and {string} are presented")
    public void options_and_are_presented(String btnMale, String btnFemale) {
        Assert.assertEquals(editEmployeePage.maleBtn.getText(),btnMale);
        genderOption1=btnMale;
        Assert.assertEquals(editEmployeePage.femaleBtn.getText(),btnFemale);
        genderOption2=btnFemale;

    }
    @Then("user clicks on {string} button")
    public void user_clicks_on_button(String option) {
        if (editEmployeePage.maleBtn.getText().equals(option)){
               click(editEmployeePage.maleBtn);
            }
        if (editEmployeePage.femaleBtn.getText().equals(option)){
            click(editEmployeePage.femaleBtn);
        }
        this.empGender=option;
    }
    @When("a dropdown menu Nationality is displayed")
    public void a_dropdown_menu_nationality_is_displayed() {
        Assert.assertTrue(editEmployeePage.nationalitySelect.isEnabled());

    }
    @Then("user selects their nationality {string}")
    public void user_selects_their_nationality(String nationality) {
      Select sel=new Select (editEmployeePage.nationalitySelect);
       sel.selectByVisibleText(nationality);
        this.empNationality=nationality;
    }
    @When("a dropdown menu Marital Status is displayed")
    public void a_dropdown_menu_marital_status_is_displayed() {
        Assert.assertTrue(editEmployeePage.maritalStatus.isEnabled());
    }
    @When("options {string}, {string} and {string} are presented")
    public void options_and_are_presented(String opt1, String opt2, String opt3) {
        String[] expectedOptions = {opt1,opt2,opt3};
        Select sel2=new Select (editEmployeePage.maritalStatus);
        List<WebElement> options = sel2.getOptions();
        for (String expectedOption : expectedOptions) {
            boolean optionPresent = false;
            for (WebElement option : options) {
                if (option.getText().equals(expectedOption)) {
                    optionPresent = true;
                    break;
                }
            }
            Assert.assertTrue(optionPresent);
        }

    }
    @Then("user selects marital status {string}")
    public void user_selects_marital_status(String empMaritalStatus) {
        Select sel2=new Select (editEmployeePage.maritalStatus);
        sel2.selectByValue(empMaritalStatus);
        this.empMaritalStatus=empMaritalStatus;
    }

    @When("user clicks on Edit-Save button")
    public void userClicksOnEditSaveButton() {
        click(editEmployeePage.btnSave);
    }

    @Then("user sees a message {string}")
    public void user_sees_a_message(String message) {
        Assert.assertTrue(editEmployeePage.successMsg.getText().contains(message));
    }
    @Then("user is able to see that the firstname is updated")
    public void user_is_able_to_see_that_the_firstname_is_updated() {
        Assert.assertEquals(editEmployeePage.empFirstName.getAttribute("value"),empFirstName);
    }
    @Then("user is able to see that the middle name is updated")
    public void user_is_able_to_see_that_the_middle_name_is_updated() {
        Assert.assertEquals(editEmployeePage.empMiddleName.getAttribute("value"),empMiddleMame);
    }
    @Then("user is able to see that the lastname is updated")
    public void user_is_able_to_see_that_the_lastname_is_updated() {
        Assert.assertEquals(editEmployeePage.empLastName.getAttribute("value"),empLastName);
    }
    @Then("the employee gender is updated")
    public void the_employee_gender_is_updated() {
        if (empGender.equals(genderOption1)){
            empGenderCode="1";
            String isChecked = editEmployeePage.maleBtnStatus.getAttribute("checked");
            Assert.assertNotNull(isChecked);
        }
        if (empGender.equals(genderOption2)){
            empGenderCode="2";
            String isChecked = editEmployeePage.femaleBtnStatus.getAttribute("checked");
            Assert.assertNotNull(isChecked);
        }

    }
    @Then("the employee nationality is updated")
    public void the_employee_nationality_is_updated() {
        Select select = new Select(editEmployeePage.nationalitySelect);
        empNationCode=editEmployeePage.nationalitySelect.getAttribute("value");
        Assert.assertEquals(select.getFirstSelectedOption().getText(),empNationality);
    }
    @Then("the marital status is updated")
    public void the_marital_status_is_updated() {
        Select select = new Select(editEmployeePage.maritalStatus);
        Assert.assertEquals(select.getFirstSelectedOption().getText(),empMaritalStatus);
    }


    @And("the data is presented in database")
    public void theDataIsPresentedInDatabase() {
        List<Map<String, String>> data= DBReader.fetch("select * from hs_hr_employees where employee_id="+empID+";");
        for (Map<String, String> record : data) {
            Assert.assertEquals(empID,record.get("employee_id"));
            Assert.assertEquals(empFirstName,record.get("emp_firstname") );
            Assert.assertEquals(empMiddleMame,record.get("emp_middle_name") );
            Assert.assertEquals(empLastName,record.get("emp_lastname") );
            Assert.assertEquals(empMaritalStatus,record.get("emp_marital_status") );
            Assert.assertEquals(empGenderCode, record.get("emp_gender"));
            Assert.assertEquals(empNationCode, record.get("nation_code"));
        }
    }
}
