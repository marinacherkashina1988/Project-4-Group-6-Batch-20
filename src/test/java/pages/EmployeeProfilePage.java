package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.CommonMethods;

public class EmployeeProfilePage extends CommonMethods {

    @FindBy (id = "empPic")
    public WebElement photoField;

    @FindBy(id = "photofile")
    public WebElement chooseFileButton;

    @FindBy(id = "btnSave")
    public WebElement uploadButton;

    @FindBy(id = "btnDelete")
    public WebElement deleteButton;

    @FindBy(xpath = "//div[@class='message success fadable']")
    public WebElement successfulUploadMessage;

    @FindBy(xpath = "//div[@class='message warning fadable']")
    public WebElement errorMessage;

    @FindBy (xpath = "//label[@class='fieldHelpBottom']")
    public WebElement recommendationMessage;

    public EmployeeProfilePage() {
        PageFactory.initElements(driver, this);
    }
}