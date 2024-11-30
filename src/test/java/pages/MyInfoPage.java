package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.CommonMethods;

public class MyInfoPage extends CommonMethods {

    @FindBy(id = "menu_pim_viewMyDetails")
    public WebElement myInfoButton;

    @FindBy(id = "profile-pic")
    public WebElement profilePicture;

    @FindBy(xpath = "//a[text()='Qualifications']")
    public WebElement qualificationsButton;

    @FindBy(id = "addLanguage")
    public WebElement addLanguageButton;

    @FindBy(id = "frmLanguage")
    public WebElement dropDownLanguagesField;

    @FindBy(id = "language_code")
    public WebElement languageDropDown;

    @FindBy(id = "language_lang_type")
    public WebElement fluencyDropDown;

    @FindBy(id = "language_competency")
    public WebElement competencyDropDown;

    @FindBy(id = "language_comments")
    public WebElement commentsField;

    @FindBy(id = "btnLanguageSave")
    public WebElement languageSaveButton;

    @FindBy(xpath = "//span[@for='language_code' and contains(@class, 'validation-error') and text()='Required']")
    public WebElement languageValidationError;

    @FindBy(xpath = "//span[@for='language_lang_type' and contains(@class, 'validation-error') and text()='Required']")
    public WebElement fluencyValidationError;

    @FindBy(xpath = "//span[@for='language_competency' and contains(@class, 'validation-error') and text()='Required']")
    public WebElement competencyValidationError;

    @FindBy(xpath = "//span[contains(text(),'100 characters')]")
    public WebElement commentValidationError;

    @FindBy(xpath = "//a[text()='Contact Details']")
    public WebElement contactDetailsButton;

    @FindBy(xpath = "//input[@value='Edit']")
    public WebElement editContactDetailsButton;

    @FindBy(id = "btnSave")
    public WebElement saveContactDetailsButton;

    @FindBy(xpath = "//label[@for='contact_street1']")
    public WebElement addressField1;

    @FindBy(id = "contact_street1")
    public WebElement addressTextbox1;

    @FindBy(id = "contact_street2")
    public WebElement addressTextbox2;

    @FindBy(id = "contact_city")
    public WebElement cityTextbox;

    @FindBy(id = "contact_state")
    public WebElement stateDropdown;

    @FindBy(id = "contact_emp_zipcode")
    public WebElement zipCodeTextbox;

    @FindBy(id = "contact_country")
    public WebElement countryDropdown;

    @FindBy(id = "contact_emp_hm_telephone")
    public WebElement homePhoneTextbox;

    @FindBy(id = "contact_emp_mobile")
    public WebElement mobilePhoneTextbox;

    @FindBy(id = "contact_emp_work_telephone")
    public WebElement workPhoneTextbox;

    @FindBy(id = "contact_emp_work_email")
    public WebElement workEmailTextbox;

    @FindBy(id = "contact_emp_oth_email")
    public WebElement otherEmailTextbox;

    @FindBy(xpath = "//span[@for='contact_emp_hm_telephone' and contains(text(),'Allows')]")
    public WebElement homePhoneMessage;

    @FindBy(xpath = "//span[@for='contact_emp_mobile' and contains(text(),'Allows')]")
    public WebElement mobilePhoneMessage;

    @FindBy(xpath = "//span[@for='contact_emp_work_telephone' and contains(text(),'Allows')]")
    public WebElement workPhoneMessage;

    @FindBy(xpath = "//span[@for='contact_emp_work_email' and contains(text(),'Expected format')]")
    public WebElement workEmailMessage;

    @FindBy(xpath = "//span[@for='contact_emp_oth_email' and contains(text(),'Expected format')]")
    public WebElement otherEmailMessage;

    public MyInfoPage() {
        PageFactory.initElements(driver, this);
    }
}