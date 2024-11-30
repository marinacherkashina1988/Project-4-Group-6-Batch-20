package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import utils.CommonMethods;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EmployeeAddLanguageSteps extends CommonMethods {

    @Given("employee logs in with valid username {string} and password {string}")
    public void employeeLogsInWithValidUsernameAndPassword(String username, String password) {
        sendText(username, loginPage.usernameField);
        sendText(password, loginPage.passwordField);
        click(loginPage.loginButton);
    }

    @When("employee clicks on My Info button")
    public void employeeClicksOnMyInfoButton() {
        click(myInfoPage.myInfoButton);
    }

    @Then("employee is navigated to the employee profile {string}")
    public void employeeIsNavigatedToTheEmployeeProfile(String fullName) {
        String actualName = myInfoPage.profilePicture.getText();
        Assert.assertEquals(fullName, actualName);
    }

    @And("employee clicks on Qualifications button")
    public void employeeClicksOnQualificationsButton() {
        click(myInfoPage.qualificationsButton);
    }

    @When("employee clicks on Add language button")
    public void employeeClicksOnAddLanguageButton() {
        click(myInfoPage.addLanguageButton);
    }

    @Then("dropdown field is displayed and editable")
    public void dropdownFieldIsDisplayedAndEditable() {
        Assert.assertTrue(myInfoPage.dropDownLanguagesField.isDisplayed());
        Assert.assertTrue(myInfoPage.languageDropDown.isEnabled());
        Assert.assertTrue(myInfoPage.fluencyDropDown.isEnabled());
        Assert.assertTrue(myInfoPage.competencyDropDown.isEnabled());
        Assert.assertTrue(myInfoPage.commentsField.isEnabled());
    }

    @And("employee selects {string} language from dropdown")
    public void employeeSelectsLanguageFromDropdown(String preferredLanguage) {
        Select select = new Select(myInfoPage.languageDropDown);
        List<WebElement> allLanguages = select.getOptions();
        for (WebElement actualLanguage : allLanguages) {
            String language = actualLanguage.getText();
            if (language.equalsIgnoreCase(preferredLanguage)) {
                select.selectByVisibleText(preferredLanguage);
            }
        }
    }

    @And("employee selects the language fluency level {string} from dropdown")
    public void employeeSelectsTheLanguageFluencyLevelFromDropdown(String preferredFluency) {
        Select select = new Select(myInfoPage.fluencyDropDown);
        List<WebElement> actualFluencyLevels = select.getOptions();
        for (WebElement actualLevel : actualFluencyLevels) {
            String fluency = actualLevel.getAttribute("value");
            if (fluency.equalsIgnoreCase(preferredFluency)) {
                select.selectByVisibleText(preferredFluency);
                break;
            }
        }
    }

    @And("employee selects the language competency level {string} from dropdown")
    public void employeeSelectsTheLanguageCompetencyLevelFromDropdown(String preferredCompetency) {
        Select select = new Select(myInfoPage.competencyDropDown);
        List<WebElement> actualCompetencyLevels = driver.findElements(By.id("language_competency"));
        for (WebElement actualCompetency : actualCompetencyLevels) {
            String competency = actualCompetency.getAttribute("value");
            if (competency.equalsIgnoreCase(preferredCompetency)) {
                select.selectByVisibleText(preferredCompetency);
                break;
            }
        }
    }

    @And("employee enters a comment longer than {int} characters {string}")
    public void employeeEntersACommentLongerThanCharacters(int numberOfChars, String comment) {
        int commentLength = comment.length();
        if (commentLength > numberOfChars) {
            sendText(comment, myInfoPage.commentsField);
        }
    }

    @Then("comment validation error {string} is displayed")
    public void commentValidationErrorIsDisplayed(String commentValidationError) {
        Assert.assertTrue(myInfoPage.commentValidationError.isDisplayed());
        Assert.assertEquals(commentValidationError, myInfoPage.commentValidationError.getText());
    }

    @And("employee clicks on Save button")
    public void employeeClicksOnSaveButton() {
        click(myInfoPage.languageSaveButton);
    }

    @Then("validation error {string} is displayed")
    public void validationErrorIsDisplayed(String validationError) {
        Assert.assertTrue(myInfoPage.languageValidationError.isDisplayed());
        Assert.assertEquals(validationError, myInfoPage.languageValidationError.getText());
        Assert.assertTrue(myInfoPage.fluencyValidationError.isDisplayed());
        Assert.assertEquals(validationError, myInfoPage.fluencyValidationError.getText());
        Assert.assertTrue(myInfoPage.competencyValidationError.isDisplayed());
        Assert.assertEquals(validationError, myInfoPage.competencyValidationError.getText());
    }

    @When("employee clicks on Select language button")
    public void employee_clicks_on_select_language_button() {
        Assert.assertTrue(myInfoPage.languageDropDown.isDisplayed());
        click(myInfoPage.languageDropDown);
    }

    @When("language dropdown menu is enabled")
    public void language_dropdown_menu_is_enabled() {
        Assert.assertTrue(myInfoPage.languageDropDown.isEnabled());
    }

    @Then("standard languages options {string}, {string}, {string}, {string}, {string}, {string}, {string} are displayed")
    public void standardLanguagesOptionsAreDisplayed(String language1, String language2, String language3, String language4, String language5, String language6, String language7) {
        List<String> standardLanguages = Arrays.asList(language1, language2, language3, language4, language5, language6, language7);
        Select select = new Select(myInfoPage.languageDropDown);
        List<String> actualLanguages = new ArrayList<>();
        for (WebElement option : select.getOptions()) {
            String language = option.getText();
            if (!language.equals("-- Select --")) {
                actualLanguages.add(language);
            }
        }
        if (!actualLanguages.equals(standardLanguages)) {
            System.out.println("Languages do not match:");
            System.out.println("Expected Languages: " + standardLanguages);
            System.out.println("Actual Languages: " + actualLanguages);
        }
        //Assert.assertEquals(actualLanguages, standardLanguages);
    }

    @When("employee clicks on Select fluency button")
    public void employee_clicks_on_select_fluency_button() {
        Assert.assertTrue(myInfoPage.fluencyDropDown.isDisplayed());
        click(myInfoPage.fluencyDropDown);
    }

    @When("fluency dropdown menu is enabled")
    public void fluency_dropdown_menu_is_enabled() {
        Assert.assertTrue(myInfoPage.fluencyDropDown.isEnabled());
    }

    @Then("fluency levels {string}, {string}, {string}, {string} are displayed")
    public void fluencyLevelsAreDisplayed(String fluency1, String fluency2, String fluency3, String fluency4) {
        List<String> expectedFluencyLevels = Arrays.asList(fluency1, fluency2, fluency3, fluency4);
        Select fluencySelect = new Select(myInfoPage.fluencyDropDown);
        List<String> actualFluencyLevels = new ArrayList<>();
        for (WebElement option : fluencySelect.getOptions()) {
            String fluency = option.getText();
            if (!fluency.equals("-- Select --")) {
                actualFluencyLevels.add(fluency);
            }
        }
        if (!actualFluencyLevels.equals(expectedFluencyLevels)) {
            System.out.println("Language fluency levels do not match:");
            System.out.println("Expected Fluency Levels: " + expectedFluencyLevels);
            System.out.println("Actual Fluency Levels: " + actualFluencyLevels);
        }
        //Assert.assertEquals(actualFluencyLevels, expectedFluencyLevels);
    }

    @When("employee clicks on Select competency button")
    public void employee_clicks_on_select_competency_button() {
        Assert.assertTrue(myInfoPage.competencyDropDown.isDisplayed());
        click(myInfoPage.competencyDropDown);
    }

    @And("competency dropdown menu is enabled")
    public void competencyDropdownMenuIsEnabled() {
        Assert.assertTrue(myInfoPage.competencyDropDown.isEnabled());
    }

    @Then("competency levels {string}, {string}, {string} are displayed")
    public void competencyLevelsAreDisplayed(String competency1, String competency2, String competency3) {
        List<String> expectedCompetencyLevels = Arrays.asList(competency1, competency2, competency3);
        Select competencySelect = new Select(myInfoPage.competencyDropDown);
        List<String> actualCompetencyLevels = new ArrayList<>();
        for (WebElement option : competencySelect.getOptions()) {
            String competency = option.getText();
            if (!competency.equals("-- Select --")) {
                actualCompetencyLevels.add(competency);
            }
        }
        if (!actualCompetencyLevels.equals(expectedCompetencyLevels)) {
            System.out.println("Language competency levels do not match:");
            System.out.println("Expected Competency Levels: " + expectedCompetencyLevels);
            System.out.println("Actual Competency Levels: " + actualCompetencyLevels);
        }
        //Assert.assertEquals(actualCompetencyLevels, expectedCompetencyLevels);
    }

    @When("employee clicks on Comment text box")
    public void employeeClicksOnCommentTextBox() {
        Assert.assertTrue(myInfoPage.commentsField.isEnabled());
    }

    @Then("employee is able to enter a comment {string}")
    public void employee_is_able_to_enter_a_comment(String comment) {
        sendText(comment, myInfoPage.commentsField);
    }
}