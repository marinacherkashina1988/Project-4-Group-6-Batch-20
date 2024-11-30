package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.CommonMethods;

import java.util.List;

public class JobDetailsPage extends CommonMethods {

    @FindBy(xpath ="//body/div/div[3]/div/div/ul/li[6]")
    public WebElement jobClick;

    @FindBy(xpath = "//div/table/tbody/tr/td[2]")
    public WebElement IdButton;

    @FindBy(id = "btnSave")
    public WebElement saveEditDetails;

    @FindBy(id="job_eeo_category")
    public WebElement jobCategory;

    @FindBy(xpath = "//select[@id='job_job_title']")
    public WebElement jobTitleField;

    @FindBy(xpath = "//select[@id='job_emp_status']")
    public WebElement empStatusField;

    @FindBy(id = "job_joined_date")
    public WebElement joinedDate;

    @FindBy(xpath = "//select[@id='job_sub_unit']")
    public WebElement subUnit;

    @FindBy(xpath = "//select[@id='job_location']")
    public WebElement empLocation;

    @FindBy(xpath = "//body/div/div[3]/div[1]/div[2]/div[2]/form/fieldset/ol/li/select")
    public List<WebElement> requiredList;

    @FindBy(xpath = "//body/div/div[3]/div[1]/div[2]/div[2]/form/fieldset/ol/li/input")
    public List<WebElement> requiredDate;

    @FindBy(xpath="//body/div/div[3]/div/div[2]/div[2]/div[contains(text(), 'Successfully Updated')]")
    public WebElement successMessage;

    public JobDetailsPage(){
        PageFactory.initElements(driver,this);
    }
}
