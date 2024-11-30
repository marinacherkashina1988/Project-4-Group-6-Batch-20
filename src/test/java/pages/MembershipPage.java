package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.CommonMethods;

import java.util.List;

public class MembershipPage extends CommonMethods {

    @FindBy(xpath = "//body/div/div[3]/div/div/ul/li[11]")
    public WebElement memberBTClick;

    @FindBy(xpath = "//input[@id='btnAddMembershipDetail']")
    public WebElement AddmemberBT;

    @FindBy(xpath = "//body/div/div[3]/div[1]/div[2]/div[2]/form/fieldset/ol/li/select")
    public List<WebElement> selMembership;

    @FindBy(xpath = "//body/div/div[3]/div[1]/div[2]/div[2]/form/fieldset/ol/li/input")
    public List<WebElement> dateMembership;

    @FindBy(id = "membership_membership")
    public WebElement typeOfMember;

    @FindBy(id = "membership_subscriptionPaidBy")
    public WebElement typeOfSubs;

    @FindBy(xpath = "//body/div/div[3]/div[1]/div[2]/div[2]/form/fieldset/ol/li[3]/input")
    public WebElement memberAmount;

    @FindBy(id = "btnSaveMembership")
    public WebElement SvBt;

    @FindBy(xpath = "//body/div/div[3]/div[1]/div[2]/div[2]/form/fieldset/ol/li/span")
    public WebElement errorMs;

    @FindBy(xpath = "//body/div/div[3]/div/div[3]/div[2]/div")
    public WebElement successMes;

    @FindBy(id = "membership_currency")
    public WebElement currencySelect;

    @FindBy(id = "membership_subscriptionCommenceDate")
    public WebElement commenceDate;

    @FindBy(id = "membership_subscriptionRenewalDate")
    public WebElement renewalDate;

    @FindBy(xpath = "//table/tbody/tr/td[2]")
    public List<WebElement> idNAME;

    @FindBy(xpath = "//table/tbody/tr/td[1]//*[@class='checkboxMem']")
    public WebElement idCheck;

    @FindBy(id = "delMemsBtn")
    public WebElement delete;

    @FindBy(xpath = "//div[@class='message success fadable' and contains(text(),'Deleted')]")
    public WebElement DeleteMessage;

    @FindBy(xpath = "//table/tbody/tr/td[2]")
    public WebElement MemberRec;

    @FindBy(xpath = "//table/tbody/tr/td/a")
    public WebElement MemberCheck;

    @FindBy(xpath = "//table/tbody/tr/td[3]")
    public WebElement PaidBy;

    @FindBy(xpath = "//table/tbody/tr/td[4]")
    public WebElement AmountCheck;

    @FindBy(xpath = "//table/tbody/tr/td[5]")
    public WebElement curence;

    @FindBy(xpath = "//table/tbody/tr[1]/td[not(contains(@style, 'display: none'))][position() > 1]")
    public List<WebElement> Elements;

    public MembershipPage() {
        PageFactory.initElements(driver, this);
    }
}



