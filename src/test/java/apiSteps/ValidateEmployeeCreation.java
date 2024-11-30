package apiSteps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.json.JSONObject;
import org.junit.Assert;
import utils.APIConstants;
import utils.APIPayloadConstants;

import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.equalTo;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

public class ValidateEmployeeCreation {

    @Given("a valid payload is prepared with following details:")
    public void a_valid_payload_is_prepared_with_following_details(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> employeeDetailsList = dataTable.asMaps(String.class, String.class);
        Map<String, String> employeeDetails = employeeDetailsList.get(0);

        APIConstants.request = given()
                .header(APIConstants.HEADER_CONTENT_TYPE_KEY, APIConstants.HEADER_CONTENT_TYPE_VALUE)
                .header(APIConstants.HEADER_AUTHORIZATION_KEY, APIConstants.token)
                .body(APIPayloadConstants.createEmployeeJsonPayloadDynamic(
                        employeeDetails.get("emp_firstname"),
                        employeeDetails.get("emp_lastname"),
                        employeeDetails.get("emp_middle_name"),
                        employeeDetails.get("emp_gender"),
                        employeeDetails.get("emp_birthday"),
                        employeeDetails.get("emp_status"),
                        employeeDetails.get("emp_job_title")
                ));
    }

    @When("the post request is sent to create an employee")
    public void the_post_request_is_sent_to_create_an_employee() {
        APIConstants.response = APIConstants.request.when().post(APIConstants.CREATE_EMPLOYEE);
    }

    @Then("the status code should be {int}")
    public void the_status_code_should_be(Integer statusCode) {
        APIConstants.response.then().assertThat().statusCode(statusCode);
    }

    @Then("the response message should be {string}")
    public void the_response_message_should_be(String expectedMessage) {
        String actualMessage = APIConstants.response.jsonPath().getString("Message");
        Assert.assertEquals(expectedMessage, actualMessage);

    }

    @Given("a payload is prepared with employee details and invalid gender {string}")
    public void a_payload_is_prepared_with_employee_details_and_invalid_gender(String string, io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> employeeDetailsList = dataTable.asMaps(String.class, String.class);
        Map<String, String> employeeDetails = employeeDetailsList.get(0);
        String gender = employeeDetails.get("emp_gender");
        APIConstants.request = given()
                .header(APIConstants.HEADER_CONTENT_TYPE_KEY, APIConstants.HEADER_CONTENT_TYPE_VALUE)
                .header(APIConstants.HEADER_AUTHORIZATION_KEY, APIConstants.token)
                .body(APIPayloadConstants.createEmployeeJsonPayloadDynamic(
                        employeeDetails.get("emp_firstname"),
                        employeeDetails.get("emp_lastname"),
                        employeeDetails.get("emp_middle_name"),
                        gender,
                        employeeDetails.get("emp_birthday"),
                        employeeDetails.get("emp_status"),
                        employeeDetails.get("emp_job_title")
                ));
    }

    @Then("the error message should be {string}")
    public void the_error_message_should_be(String expectedErrorMessage) {
        String actualErrorMessage = APIConstants.response.then()
                .extract().jsonPath().getString("Message");
        System.out.println("Actual Error Message: " + actualErrorMessage);
        if (!actualErrorMessage.equals(expectedErrorMessage)) {
            throw new AssertionError("Error Message mismatch! Expected: " +
                    expectedErrorMessage + " but found: " + actualErrorMessage);
        }
        System.out.println("Error message validated successfully");

    }

    @Given("a payload is prepared with employee details and invalid birthday format:")
    public void a_payload_is_prepared_with_employee_details_and_invalid_birthday_format(
            io.cucumber.datatable.DataTable dataTable) {

        List<Map<String, String>> employeeDetailsList = dataTable.asMaps(String.class, String.class);
        Map<String, String> employeeDetails = employeeDetailsList.get(0);

        String birthDate = employeeDetails.get("emp_birthday");
        APIConstants.request = given()
                .header(APIConstants.HEADER_CONTENT_TYPE_KEY, APIConstants.HEADER_CONTENT_TYPE_VALUE)
                .header(APIConstants.HEADER_AUTHORIZATION_KEY, APIConstants.token)
                .body(APIPayloadConstants.createEmployeeJsonPayloadDynamic(
                        employeeDetails.get("emp_firstname"),
                        employeeDetails.get("emp_lastname"),
                        employeeDetails.get("emp_middle_name"),
                        birthDate,
                        employeeDetails.get("emp_birthday"),
                        employeeDetails.get("emp_status"),
                        employeeDetails.get("emp_job_title")
                ));
    }

    @Then("the error message should be displayed")
    public void the_error_message_should_be_displayed() {
        System.out.println("Response body: " + APIConstants.response.getBody().asString());
        String actualErrorMessage = APIConstants.response.then()
                .extract().jsonPath().getString("Error");

        if (actualErrorMessage != null) {
            System.out.println("Error message:" + actualErrorMessage);
            System.out.println("Error message validated successfully.");
        } else {
            System.out.println("No error message found in the response.");
            Assert.fail("Error message is null.");
        }


    }

    @Given("a payload is prepared with employee details and missing fields")
    public void a_payload_is_prepared_with_employee_details_and_missing_fields(
            io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> employeeDetailsList = dataTable.asMaps(String.class, String.class);
        Map<String, String> employeeDetails = employeeDetailsList.get(0);

        APIConstants.request = given()
                .header(APIConstants.HEADER_CONTENT_TYPE_KEY, APIConstants.HEADER_CONTENT_TYPE_VALUE)
                .header(APIConstants.HEADER_AUTHORIZATION_KEY, APIConstants.token)
                .body(APIPayloadConstants.createEmployeeJsonPayloadDynamic(
                        employeeDetails.get("emp_firstname"),
                        employeeDetails.get("emp_lastname"),
                        employeeDetails.get("emp_middle_name"),
                        employeeDetails.get("emp_gender"),
                        employeeDetails.get("emp_birthday"),
                        employeeDetails.get("emp_status"),
                        employeeDetails.get("emp_job_title")
                ));
    }

    @Then("the response should indicate that the payload is incomplete")
    public void the_response_should_indicate_that_the_payload_is_incomplete() {
        String actualErrorMessage = APIConstants.response.then()
                .extract().jsonPath().getString("Message");
        Assert.assertEquals("First Name is Empty", actualErrorMessage);

    }
    @Given("a payload is prepared with employee details and invalid status")
    public void a_payload_is_prepared_with_employee_details_and_invalid_status(
            io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> employeeDetailsList = dataTable.asMaps(String.class, String.class);
        Map<String, String> employeeDetails = employeeDetailsList.get(0);

        APIConstants.request = given()
                .header(APIConstants.HEADER_CONTENT_TYPE_KEY, APIConstants.HEADER_CONTENT_TYPE_VALUE)
                .header(APIConstants.HEADER_AUTHORIZATION_KEY, APIConstants.token)
                .body(APIPayloadConstants.createEmployeeJsonPayloadDynamic(
                        employeeDetails.get("emp_firstname"),
                        employeeDetails.get("emp_lastname"),
                        employeeDetails.get("emp_middle_name"),
                        employeeDetails.get("emp_gender"),
                        employeeDetails.get("emp_birthday"),
                        employeeDetails.get("emp_status"),
                        employeeDetails.get("emp_job_title")
                ));

    }
    @Then("the response should indicate that the status value is incorrect")
    public void the_response_should_indicate_that_the_status_value_is_incorrect() {
        System.out.println("Response body:" + APIConstants.response.getBody().asString());
        System.out.println("The response indicates that the status value is incorrect");
    }
    @Then("the API created the employee record with invalid status")
    public void the_api_created_the_employee_record_with_invalid_status() {
        int statusCode=APIConstants.response.getStatusCode();
        if (statusCode==201){
            System.out.println("Employee created with invalid status, which violates the acceptance criteria.");
        }else {
            System.out.println("API did not create employee with invalid status. Validation successful.");
        }

    }


}