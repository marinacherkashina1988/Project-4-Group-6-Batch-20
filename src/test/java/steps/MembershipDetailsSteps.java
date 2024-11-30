package steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import utils.CommonMethods;

import java.util.List;
import java.util.Map;

public class MembershipDetailsSteps extends CommonMethods {

    @When("employee clicks on Membership button")
    public void employee_clicks_on_membership_button() {
        click(membershipPage.memberBTClick);
    }
    @When("employee click on Add Top Button")
    public void employee_click_on_add_top_button() {
        click(membershipPage.AddmemberBT);
    }
    @Then("employee can see all the required fields are displayed and editable")
    public void employee_can_see_all_the_required_fields_are_displayed_and_editable() {
        List<WebElement>selectors=membershipPage.selMembership;

        for (WebElement select:selectors){
            Assert.assertTrue("The selectors are not displayed or validated",select.isDisplayed()&& select.isEnabled());
        }
        List<WebElement>dates=membershipPage.dateMembership;

        for (WebElement date:dates){
            Assert.assertTrue("The dates are not displayed or validated",date.isDisplayed()&& date.isEnabled());
        }

    }
    @When("employee selects {string} from dropdown")
    public void employee_selects_from_dropdown(String Member) {
        Select sel1=new Select(membershipPage.typeOfMember);
        List<WebElement>membership=sel1.getOptions();

        for (WebElement member:membership){
            String member1=member.getText();
            if (member1.equalsIgnoreCase(Member)){
                sel1.selectByVisibleText(Member);
            }
        }
    }
    @When("employee selects paid by {string}")
    public void employee_selects_paid_by(String pay) {
        Select sel1=new Select(membershipPage.typeOfSubs);
        List<WebElement>Paids=sel1.getOptions();

        for (WebElement paid:Paids){
            if (paid.getText().equalsIgnoreCase(pay)){
                sel1.selectByVisibleText(pay);
            }
        }

    }
    @When("employee enters subscription amount {string}")
    public void employee_enters_subscription_amount(String amount) {
        sendText(amount,membershipPage.memberAmount);
    }
    @When("employee selects {string} from the currency dropdown menu")
    public void employee_selects_from_the_currency_dropdown_menu(String currency) {
        Select sel2 =new Select(membershipPage.currencySelect);
        List<WebElement>Currency= sel2.getOptions();
        for (WebElement cur:Currency){
            String currr=cur.getText();
            if (currr.equalsIgnoreCase(currency)){
                sel2.selectByVisibleText(currency);
            }
        }
    }
    @When("empployee selects {string} as commence date")
    public void empployee_selects_as_commence_date(String commencedate) {
        sendText(commencedate, membershipPage.commenceDate);
    }
    @When("employee selects {string} as renewal date")
    public void employee_selects_as_renewal_date(String renewal) {
        sendText(renewal, membershipPage.renewalDate);


    }
    @Then("employee clicks on Save membership button")
    public void employee_clicks_on_save_membership_button() {
        click(membershipPage.SvBt);

    }
    @Then("message {string} is displayed")
    public void message(String string) {
        String Message=membershipPage.successMes.getText().substring(0,18);
        Assert.assertEquals("Mismatch in success Message",Message,string);
        Assert.assertTrue(membershipPage.successMes.isDisplayed());

    }
    @When("employee select {string}")
    public void employee_select_to_delete(String MemberShip) {
        List<WebElement>Members=membershipPage.idNAME;

        for (WebElement member:Members){
            if (member.getText().equals(MemberShip)){
                click(membershipPage.idCheck);
            }
        }

    }
    @When("employee clicks on Delete button")
    public void employee_clocks_on_delete_button() {
        click(membershipPage.delete);

    }
    @Then("message for deleted membership {string} is displayed")
    public void message_for_deleted_membership_is_displayed(String deleteMessage) {
        String deleteM=membershipPage.DeleteMessage.getText().substring(0,20);
        Assert.assertEquals("Mismatch at delete Message ",deleteM,deleteMessage);
        Assert.assertTrue(membershipPage.DeleteMessage.isDisplayed());
    }

    @When("employee click on membership {string}")
    public void employee_click_on_membership(String memberIDBT) {
        String membership=membershipPage.MemberRec.getText();
        if (membership.equalsIgnoreCase(memberIDBT)){
            click(membershipPage.MemberCheck);
        }
    }

    @Then("all the saved details are checked {string},{string},{string},{string},{string},{string}")
    public void all_the_saved_details_are_checked(String Membership, String subscription, String amount, String currency, String commenceDate, String renewalDate) {
        String[]Expected={Membership,subscription,amount,currency,commenceDate,renewalDate};

        List<WebElement> actualElements = membershipPage.Elements;
        Assert.assertEquals("Mismatch in element count", Expected.length, actualElements.size());

        for (int i = 0; i < actualElements.size(); i++) {
            String actualText = actualElements.get(i).getText().trim();
            Assert.assertEquals("Mismatch at details " + i, Expected[i], actualText);
        }
    }
}