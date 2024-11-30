package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.CommonMethods;

import java.util.List;

public class EmployeeSearchPage extends CommonMethods {

    @FindBy(id = "empsearch_id")
    public WebElement employeeIDSearchField;

    @FindBy(id = "searchBtn")
    public WebElement searchButton;

    @FindBy(id="empsearch_employee_name_empName")
    public WebElement emloyeeNameSearchField;

    @FindBy(xpath = "//table/tbody/tr/td[2]")
    public List <WebElement> allIDs;

    @FindBy(xpath = "//table/tbody/tr/td[3]")
    public List <WebElement> allFirstNames;

    @FindBy(xpath = "//table/tbody/tr/td[4]")
    public List <WebElement> allLastNames;

    @FindBy(xpath = "//td[@colspan='8']")
    public WebElement noFoundMessage;

    @FindBy (id="menu_pim_viewEmployeeList")
    public WebElement employeeListBtn;

    public EmployeeSearchPage() {

        PageFactory.initElements(driver, this);
    }
}