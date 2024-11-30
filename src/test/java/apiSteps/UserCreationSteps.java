package apiSteps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import utils.APIConstants;
import utils.APIPayloadConstants;

import static io.restassured.RestAssured.given;

public class UserCreationSteps {

    @Given("request is prepared by providing name {string}, email {string} and password {string}")
    public void request_is_prepared_by_providing_name_email_and_password(String name, String email, String password) {
        APIConstants.request = given().
                header(APIConstants.HEADER_CONTENT_TYPE_KEY, APIConstants.HEADER_CONTENT_TYPE_VALUE).
                body(APIPayloadConstants.createUser(name, email, password));
    }

    @When("Post call is made")
    public void post_call_is_made() {
        APIConstants.response = APIConstants.request.when().post(APIConstants.CREATE_USER);
    }

    @Then("the status code for this is {int}")
    public void the_status_code_for_this_is(Integer status) {
        APIConstants.response.then().assertThat().statusCode(status);
    }

    @Then("and confirmation message appears {string}")
    public void and_confirmation_message_appears(String message) {
        String Message = APIConstants.response.jsonPath().getString("Message");
        Assert.assertEquals(Message, message);
    }

    @Given("request is prepared with name {string}, email {string} and password {string}")
    public void request_is_prepared_with_name_email_and_password(String name, String email, String password) {
        APIConstants.request = given().
                header(APIConstants.HEADER_CONTENT_TYPE_KEY, APIConstants.HEADER_CONTENT_TYPE_VALUE).
                body(APIPayloadConstants.createUser(name, email, password));
    }


    @Then("the status for this is {int}")
    public void the_status_for_this_is(Integer statusCode) {
        APIConstants.response.then().assertThat().statusCode(statusCode);
    }

    @Then("the error message appears {string}")
    public void the_error_message_appears(String expectedErrorMessage) {
        String errorMessage = null;
        if (APIConstants.response.jsonPath().get("Message") != null) {
            errorMessage = APIConstants.response.jsonPath().getString("Message");
        }
        else if (APIConstants.response.jsonPath().get("condition") != null &&
                APIConstants.response.jsonPath().get("data") != null) {
            // Get the error message from the "data" field
            errorMessage = APIConstants.response.jsonPath().getString("data");
        }
        Assert.assertEquals("Error message does not match", expectedErrorMessage, errorMessage);
    }
}