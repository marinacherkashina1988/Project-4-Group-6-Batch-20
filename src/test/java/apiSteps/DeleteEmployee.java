package apiSteps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import utils.APIConstants;
import utils.APIPayloadConstants;
import utils.SharedData;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class DeleteEmployee {
    public String firstName;
    public String lastName;

    @Given("a request is prepared to delete the created the employee by a stored Employee ID")
    public void a_request_is_prepared_to_delete_the_created_the_employee_by_a_stored_employee_id() {
        System.out.println("ID "+SharedData.getInstance().getId());
        APIConstants.request = given().
                header(APIConstants.HEADER_CONTENT_TYPE_KEY,APIConstants.HEADER_CONTENT_TYPE_VALUE).
                header(APIConstants.HEADER_AUTHORIZATION_KEY,APIConstants.token).
                queryParam("employee_id",SharedData.getInstance().getId());
    }
    @Given("user sees the details of the employee")
    public void user_sees_the_details_of_the_employee() {
        System.out.println("Employee data:");
        Map<String,String> actualData = APIConstants.response.jsonPath().get("employee");
        actualData.forEach((key, value) -> System.out.println(key + ": " + value));
    }
    @When("a DEL call is made to delete the employee")
    public void a_del_call_is_made_to_delete_the_employee() {
        APIConstants.response=APIConstants.request.when().delete(APIConstants.DELETE_EMPLOYEE);
    }
    @Then("the message is {string}")
    public void the_message_is(String msg) {
            String actualMessage = APIConstants.response
                    .then()
                    .extract()
                    .jsonPath()
                    .getString("message");
            assert actualMessage.equals(msg);
    }

    @Given("a request is prepared for creating the employee using data {string}, {string}, {string}, {string}, {string}, {string},{string}")
    public void a_request_is_prepared_for_creating_the_employee_using_data(String firstname, String lastname, String middlename,
                                                                           String gender, String birthday, String status, String jobTitle) {
        APIConstants.request = given().
                header(APIConstants.HEADER_CONTENT_TYPE_KEY,APIConstants.HEADER_CONTENT_TYPE_VALUE).
                header(APIConstants.HEADER_AUTHORIZATION_KEY,APIConstants.token).
                body(APIPayloadConstants.createEmployeeJsonPayloadDynamic(firstname,lastname,middlename,gender,birthday,status,jobTitle));
        this.firstName=firstname;
        this.lastName=lastname;
    }
    @When("a POST call is made to create the employee")
    public void a_post_call_is_made_to_create_the_employee() {
        APIConstants.response = APIConstants.request.when().post(APIConstants.CREATE_EMPLOYEE);
    }
    @Then("the employee id {string} is stored and values are validated")
    public void the_employee_id_is_stored_and_values_are_validated(String empPath) {
        SharedData.getInstance().setId(APIConstants.response.jsonPath().getString(empPath));
        System.out.println("Employee ID: "+SharedData.getInstance().getId());
        APIConstants.response.then().assertThat().
                body("Employee.emp_firstname",equalTo(firstName));
        APIConstants.response.then().assertThat().
                body("Employee.emp_lastname",equalTo(lastName));
    }


    @Then("user gets a message {string}")
    public void userGetsAMessage(String msg) {
        String actualMessage = APIConstants.response
                .then()
                .extract()
                .jsonPath()
                .getString("Massage");
        assert actualMessage.equals(msg);
        //full response to check
        String jsonResponse = APIConstants.response
                .then()
                .extract()
                .asString();
        System.out.println("Full JSON Response: " + jsonResponse);
    }

    @Then("user gets en error code {int}")
    public void userGetsEnErrorCode(int statusCode) {
       APIConstants.response.then().assertThat().statusCode(statusCode);
    }

    @Given("a request is prepared to delete the created employee without ID")
    public void aRequestIsPreparedToDeleteTheCreatedEmployeeWithoutID() {
        APIConstants.request = given().
                header(APIConstants.HEADER_CONTENT_TYPE_KEY,APIConstants.HEADER_CONTENT_TYPE_VALUE).
                header(APIConstants.HEADER_AUTHORIZATION_KEY,APIConstants.token);
    }

    @And("user gets an error {string}")
    public void userGetsAnError(String msg) {
        String actualMessage = APIConstants.response
                .then()
                .extract()
                .jsonPath()
                .getString("Error");
        assert actualMessage.equals(msg);
    }

    @Given("a request is prepared to delete the employee with employee ID {string} that does not exist")
    public void aRequestIsPreparedToDeleteTheEmployeeWithEmployeeIDThatDoesNotExist(String invalidID) {
        APIConstants.request = given().
               header(APIConstants.HEADER_CONTENT_TYPE_KEY,APIConstants.HEADER_CONTENT_TYPE_VALUE).
               header(APIConstants.HEADER_AUTHORIZATION_KEY,APIConstants.token).
                queryParam("employee_id",invalidID);
    }
}

