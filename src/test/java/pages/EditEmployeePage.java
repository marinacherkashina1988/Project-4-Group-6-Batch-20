package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.CommonMethods;

import java.util.List;

public class EditEmployeePage extends CommonMethods {
    @FindBy (id="personal_txtEmpFirstName")
    public WebElement empFirstName;

    @FindBy (id="personal_txtEmpMiddleName")
    public WebElement empMiddleName;

    @FindBy (id="personal_txtEmpLastName")
    public WebElement empLastName;

    @FindBy (name="personal[optGender]")
    public List<WebElement> genderButtons;

    @FindBy (xpath = "//label[@for='personal_optGender_1']")
    public WebElement maleBtn;

    @FindBy (xpath = "//label[@for='personal_optGender_2']")
    public WebElement femaleBtn;

    @FindBy (id="personal_optGender_1")
    public WebElement maleBtnStatus;

    @FindBy (id="personal_optGender_2")
    public WebElement femaleBtnStatus;

    @FindBy (id="personal_cmbMarital")
    public WebElement maritalStatus;

    @FindBy(id = "personal_cmbNation")
    public WebElement nationalitySelect;

    @FindBy (id="btnSave")
    public WebElement btnSave;

    @FindBy (xpath = "//div[@class='message success fadable']")
    public WebElement successMsg;

    public EditEmployeePage() {
        PageFactory.initElements(driver, this);
    }
}
