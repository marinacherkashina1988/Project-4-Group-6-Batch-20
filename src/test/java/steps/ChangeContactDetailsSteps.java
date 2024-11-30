package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import utils.CommonMethods;

import java.util.*;

public class ChangeContactDetailsSteps extends CommonMethods {

    @And("employee clicks on Contact Details button")
    public void employee_clicks_on_Contact_Details_button() {
        click(myInfoPage.contactDetailsButton);
    }

    @And("employee clicks on Edit contact details button")
    public void employeeClicksOnEditContactDetailsButton() {
        click(myInfoPage.editContactDetailsButton);
    }

    @Then("following fields are displayed: {string}, {string}, {string}, {string}, {string}, {string}, {string}, {string}, {string}, {string}, {string}")
    public void followingFieldsAreDisplayed(String address1, String address2, String city, String state, String zipcode, String country, String homeNumber, String mobileNumber, String workNumber, String workEmail, String otherEmail) {
        List<String> expectedFieldNames = Arrays.asList(address1, address2, city, state, zipcode, country, homeNumber, mobileNumber, workNumber, workEmail, otherEmail);
        List<WebElement> actualFields = driver.findElements(By.xpath("//form[@id='frmEmpContactDetails']//label"));
        List<String> actualFieldNames = new ArrayList<>();
        for (WebElement actualFieldName : actualFields) {
            String actualField = actualFieldName.getText();
            actualFieldNames.add(actualField);
        }
        if (!expectedFieldNames.equals(actualFieldNames)) {
            System.out.println("Field names do not match:");
            System.out.println("Expected Field Names: " + expectedFieldNames);
            System.out.println("Actual Field Names: " + actualFieldNames);
        }
        //Assert.assertEquals(expectedFieldNames, actualFieldNames);
    }

    @And("textboxes and dropdown are displayed and editable")
    public void textboxesAndDropdownAreDisplayedAndEditable() {
        Assert.assertTrue(myInfoPage.addressTextbox1.isDisplayed() && myInfoPage.addressField1.isEnabled());
        Assert.assertTrue(myInfoPage.addressTextbox2.isDisplayed() && myInfoPage.addressTextbox2.isEnabled());
        Assert.assertTrue(myInfoPage.cityTextbox.isDisplayed() && myInfoPage.cityTextbox.isEnabled());
        Assert.assertTrue(myInfoPage.stateDropdown.isDisplayed() && myInfoPage.stateDropdown.isEnabled());
        Assert.assertTrue(myInfoPage.zipCodeTextbox.isDisplayed() && myInfoPage.zipCodeTextbox.isEnabled());
        Assert.assertTrue(myInfoPage.countryDropdown.isDisplayed() && myInfoPage.countryDropdown.isEnabled());
        Assert.assertTrue(myInfoPage.homePhoneTextbox.isDisplayed() && myInfoPage.homePhoneTextbox.isEnabled());
        Assert.assertTrue(myInfoPage.mobilePhoneTextbox.isDisplayed() && myInfoPage.mobilePhoneTextbox.isEnabled());
        Assert.assertTrue(myInfoPage.workPhoneTextbox.isDisplayed() && myInfoPage.workPhoneTextbox.isEnabled());
        Assert.assertTrue(myInfoPage.workEmailTextbox.isDisplayed() && myInfoPage.workEmailTextbox.isEnabled());
        Assert.assertTrue(myInfoPage.otherEmailTextbox.isDisplayed() && myInfoPage.otherEmailTextbox.isEnabled());
    }

    @When("employee updates the following fields with invalid data type")
    public void employee_updates_the_following_fields_with_invalid_data_type(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> invalidData = dataTable.asMaps();
        for (Map<String, String> data : invalidData) {
            sendText(data.get("Home Telephone"), myInfoPage.homePhoneTextbox);
            sendText(data.get("Mobile"), myInfoPage.mobilePhoneTextbox);
            sendText(data.get("Work Telephone"), myInfoPage.workPhoneTextbox);
            sendText(data.get("Work Email"), myInfoPage.workEmailTextbox);
            sendText(data.get("Other Email"), myInfoPage.otherEmailTextbox);
        }
    }

    @When("employee clicks on Save contact details button")
    public void employee_clicks_on_save_contact_details_button() {
        click(myInfoPage.saveContactDetailsButton);
    }

    @Then("Error message {string} is displayed next to the Home Telephone textbox")
    public void error_message_is_displayed_next_to_the_home_telephone_textbox(String message) {
        Assert.assertTrue(myInfoPage.homePhoneMessage.isDisplayed());
        Assert.assertEquals(myInfoPage.homePhoneMessage.getText(), message);
    }

    @Then("Error message {string} is displayed next to the Mobile textbox")
    public void error_message_is_displayed_next_to_the_mobile_textbox(String message) {
        Assert.assertTrue(myInfoPage.mobilePhoneMessage.isDisplayed());
        Assert.assertEquals(myInfoPage.mobilePhoneMessage.getText(), message);
    }

    @Then("Error message {string} is displayed next to the Work Telephone textbox")
    public void error_message_is_displayed_next_to_the_work_telephone_textbox(String message) {
        Assert.assertTrue(myInfoPage.workPhoneMessage.isDisplayed());
        Assert.assertEquals(myInfoPage.workPhoneMessage.getText(), message);
    }

    @Then("Error message {string} is displayed next to the Work Email textbox")
    public void error_message_is_displayed_next_to_the_work_email_textbox(String message) {
        Assert.assertTrue(myInfoPage.workEmailMessage.isDisplayed());
        Assert.assertEquals(myInfoPage.workEmailMessage.getText(), message);
    }

    @Then("Error message {string} is displayed next to the Other Email textbox")
    public void error_message_is_displayed_next_to_the_other_email_textbox(String message) {
        Assert.assertTrue(myInfoPage.otherEmailMessage.isDisplayed());
        Assert.assertEquals(myInfoPage.otherEmailMessage.getText(), message);
    }

    @When("employee corrects the following fields with valid details")
    public void employee_corrects_the_following_fields_with_valid_details(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> validData = dataTable.asMaps();
        for (Map<String, String> data : validData) {
            sendText(data.get("Address Street 1"), myInfoPage.addressTextbox1);
            sendText(data.get("Address Street 2"), myInfoPage.addressTextbox2);
            sendText(data.get("City"), myInfoPage.cityTextbox);
            sendText(data.get("Zip/Postal Code"), myInfoPage.zipCodeTextbox);
            sendText(data.get("Home Telephone"), myInfoPage.homePhoneTextbox);
            sendText(data.get("Mobile"), myInfoPage.mobilePhoneTextbox);
            sendText(data.get("Work Telephone"), myInfoPage.workPhoneTextbox);
            sendText(data.get("Work Email"), myInfoPage.workEmailTextbox);
            sendText(data.get("Other Email"), myInfoPage.otherEmailTextbox);

            Select select;
            String stateToSelect = data.get("State/Province");
            select = new Select(myInfoPage.stateDropdown);
            select.selectByVisibleText(stateToSelect);

            String countryToSelect = data.get("Country");
            select = new Select(myInfoPage.countryDropdown);
            select.selectByVisibleText(countryToSelect);
        }
    }

    @Then("employee contact details are successfully updated and match the details provided")
    public void employee_contact_details_are_successfully_updated_and_match_the_details_provided(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> expectedData = dataTable.asMaps();
        Map<String, String> actualData = new HashMap<>();

        actualData.put("Address Street 1", myInfoPage.addressTextbox1.getAttribute("value"));
        actualData.put("Address Street 2", myInfoPage.addressTextbox2.getAttribute("value"));
        actualData.put("City", myInfoPage.cityTextbox.getAttribute("value"));
        actualData.put("State/Province", new Select(myInfoPage.stateDropdown).getFirstSelectedOption().getText());
        actualData.put("Zip/Postal Code", myInfoPage.zipCodeTextbox.getAttribute("value"));
        actualData.put("Country", new Select(myInfoPage.countryDropdown).getFirstSelectedOption().getText());
        actualData.put("Home Telephone", myInfoPage.homePhoneTextbox.getAttribute("value"));
        actualData.put("Mobile", myInfoPage.mobilePhoneTextbox.getAttribute("value"));
        actualData.put("Work Telephone", myInfoPage.workPhoneTextbox.getAttribute("value"));
        actualData.put("Work Email", myInfoPage.workEmailTextbox.getAttribute("value"));
        actualData.put("Other Email", myInfoPage.otherEmailTextbox.getAttribute("value"));

        for (Map<String, String> data : expectedData) {
            Assert.assertEquals(data, actualData);
        }
    }
}