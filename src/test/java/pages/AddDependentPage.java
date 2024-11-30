package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.CommonMethods;

import java.util.List;

public class AddDependentPage extends CommonMethods {

    @FindBy(xpath = "//ul[@id='sidenav']//a[contains(@href,'viewDependents')]")
    public WebElement dependentsButton;

    @FindBy(id = "btnAddDependent")
    public WebElement addDependentsButton;

    @FindBy(xpath = "//select[@id='dependent_relationshipType']/option[@value='child']")
    public WebElement childOption;


    @FindBy(id = "dependent_name")
    public WebElement dependentName;

    @FindBy(xpath = "//select[@class='formSelect validation-error']")
    public WebElement selectTextbox;

    @FindBy(id = "dependent_relationshipType")
    public WebElement selectRelationship;

    @FindBy(id = "dependent_relationship")
    public WebElement specifyRelationship;

    @FindBy(id = "dependent_dateOfBirth")
    public WebElement dateOfBirth;

    @FindBy(xpath = "//img[@class='ui-datepicker-trigger']")
    public WebElement dobCalender;

    @FindBy(id = "btnSaveDependent")
    public WebElement saveDependent;

    @FindBy(id = "delDependentBtn")
    public WebElement deleteDependent;

    @FindBy(xpath = "//select[@class='ui-datepicker-month']")
    public WebElement monthDropdown;

    @FindBy(xpath = "//select[@class='ui-datepicker-year']")
    public WebElement yearDropdown;

    @FindBy(xpath = "//table[@class='ui-datepicker-calendar']/tbody/tr/td")
    public List<WebElement> dayDropdown;


    @FindBy(id = "empsearch_employee_name_empName")
    public WebElement nameToSearch;

    @FindBy(id = "searchBtn")
    public WebElement searchEmployee;

    @FindBy(id = "empsearch_id")
    public WebElement empID;

    @FindBy(xpath = "//table[@id='resultTable']/tbody/tr")
    public List<WebElement> resultTable;

    @FindBy(xpath = "//div[@id='ui-datepicker-div']//table[@class='ui-datepicker-calendar']")
    public List<WebElement> dayElement;

    @FindBy(xpath = "//table[@id='resultTable']//tr/td/a[text()='Oliverica Olala']")
    public WebElement employeeName;

    @FindBy(xpath = "//div[@id='pdMainContainer']")
    public WebElement personalDetailsPage;

    @FindBy(xpath = "//span[@class='validation-error']")
    public WebElement errorMessage;

    @FindBy(xpath ="//tr[contains(@class,'odd')]//input[@type='checkbox']")
    public WebElement checkbox;

    @FindBy(xpath = "//h1[text()='Assigned Dependents']")
    public WebElement assignedDependentsSection;

    @FindBy(xpath = "//td[@class='dependentName']/a[text()='ama']")
    public WebElement dependentLink;


    @FindBy(xpath = "//form[@id='frmEmpDelDependents']")
    public WebElement assignedDependentsTable;

    @FindBy (xpath = "//div[@class='message success fadable']")
    public WebElement successMsg;


    @FindBy(xpath = "//table[@id='dependent_list']/tbody/tr/td[2]")
    public List<WebElement> dependentRows;
    public List<WebElement> getDependentsRows(){
        return dependentRows;
    }





    public AddDependentPage() {
        PageFactory.initElements(driver, this);

    }
}