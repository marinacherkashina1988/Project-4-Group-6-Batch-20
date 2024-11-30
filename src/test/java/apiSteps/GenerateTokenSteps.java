
package apiSteps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import utils.APIConstants;
import utils.APIPayloadConstants;

import static io.restassured.RestAssured.given;

public class GenerateTokenSteps {

    @Given("request to generate token with email {string} and missing password is prepared")
    public void request_to_generate_token_with_email_and_missing_password_is_prepared(String email) {
        APIConstants.request = given().header(APIConstants.HEADER_CONTENT_TYPE_KEY, APIConstants.HEADER_CONTENT_TYPE_VALUE).
                body(APIPayloadConstants.generateTokenPayload(email, ""));
    }

    @When("POST request to generate token is called")
    public void post_request_to_generate_token_is_called() {
        APIConstants.response = APIConstants.request.when().post(APIConstants.GENERATE_TOKEN);
        APIConstants.token = "Bearer " + APIConstants.response.jsonPath().getString("token");
    }

    @Then("response status code is {int}")
    public void response_status_code_is(Integer statusCode) {
        APIConstants.response.then().statusCode(statusCode);
    }

    @Then("error message {string} is displayed in response body")
    public void error_message_is_displayed_in_response_body(String errorMessage) {
        String actualErrorMessage = APIConstants.response.jsonPath().getString("Error");
        Assert.assertEquals(errorMessage, actualErrorMessage);
    }

    @When("request to generate token with missing email and password {string} is prepared")
    public void request_to_generate_token_with_missing_email_and_password_is_prepared(String password) {
        APIConstants.request = given().header(APIConstants.HEADER_CONTENT_TYPE_KEY, APIConstants.HEADER_CONTENT_TYPE_VALUE).
                body(APIPayloadConstants.generateTokenPayload("", password));
    }

    @Given("request to generate token with mismatching email {string} and correct password {string} is prepared")
    public void request_to_generate_token_with_mismatching_email_and_correct_password_is_prepared(String incorrectEmail, String password) {
        APIConstants.request = given().header(APIConstants.HEADER_CONTENT_TYPE_KEY, APIConstants.HEADER_CONTENT_TYPE_VALUE).
                body(APIPayloadConstants.generateTokenPayload(incorrectEmail, password));
    }

    @When("request to generate token with correct email {string} and mismatching password {string} is prepared")
    public void request_to_generate_token_with_correct_email_and_mismatching_password_is_prepared(String email, String incorrectPassword) {
        APIConstants.request = given().header(APIConstants.HEADER_CONTENT_TYPE_KEY, APIConstants.HEADER_CONTENT_TYPE_VALUE).
                body(APIPayloadConstants.generateTokenPayload(email, incorrectPassword));
    }

    @Given("request to generate token with valid email {string} and valid password {string} is prepared")
    public void request_to_generate_token_with_valid_email_and_valid_password_is_prepared(String email, String password) {
        APIConstants.request = given().header(APIConstants.HEADER_CONTENT_TYPE_KEY, APIConstants.HEADER_CONTENT_TYPE_VALUE).
                body(APIPayloadConstants.generateTokenPayload(email, password));
    }

    @Then("token has to match JWT format")
    public void token_has_to_match_jwt_format() {
        boolean isValidToken;
        if (APIConstants.token != null & APIConstants.token.split("\\.").length == 3 && APIConstants.token.startsWith("Bearer ")) {
            isValidToken = true;
        } else {
            isValidToken = false;
            System.out.println("Token is not valid or does not match JWT format");
        }
    }
}
