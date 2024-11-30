package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.CommonMethods;

public class DashboardPage extends CommonMethods {

    @FindBy(id = "menu_pim_addEmployee")
    public WebElement addEmployeeButton;

    @FindBy(id = "welcome")
    public WebElement welcomeMessage;

    @FindBy(id = "menu_pim_viewPimModule")
    public WebElement pimMenuButton;

    @FindBy(id = "menu_pim_viewEmployeeList")
    public WebElement employeeListButton;

    @FindBy(id = "menu_dashboard_index")
    public WebElement dashboardMenu;

    public DashboardPage() {
        PageFactory.initElements(driver, this);
    }
}
