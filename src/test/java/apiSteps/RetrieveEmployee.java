package apiSteps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import utils.APIConstants;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static io.restassured.RestAssured.given;

public class RetrieveEmployee {

    @Given("a request is prepared to get the created the employee by valid ID {string}")
    public void a_request_is_prepared_to_get_the_created_the_employee_by_valid_id(String employee_id) {
            APIConstants.request = given().
                    header(APIConstants.HEADER_CONTENT_TYPE_KEY,APIConstants.HEADER_CONTENT_TYPE_VALUE).
                    header(APIConstants.HEADER_AUTHORIZATION_KEY,APIConstants.token).
                    queryParam("employee_id",employee_id);
    }

    @When("a GET call is made to get the employee")
    public void a_get_call_is_made_to_get_the_employee() {
        APIConstants.response=APIConstants.request.when().get(APIConstants.GET_ONE_EMPLOYEE);
    }
    @Then("the status for get call is {int}")
    public void the_status_for_get_call_is(int statusCode) {
        APIConstants.response.then().assertThat().statusCode(statusCode);
    }
    @Then("the data coming from the GET call should be")
    public void the_data_coming_from_the_get_call_should_be(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String,String>> expectedData=dataTable.asMaps();
        Map<String,String> actualData = APIConstants.response.jsonPath().get("employee");
        for (Map<String,String> employeeData: expectedData){
            Set<String> keys = employeeData.keySet();
            for (String key:keys){
                String expectedValue=employeeData.get(key);
                String actualValue=actualData.get(key);
                Assert.assertEquals(expectedValue,actualValue);
            }
        }
    }

    @Given("a request is prepared to get the created the employee by invalid ID {string}")
    public void aRequestIsPreparedToGetTheCreatedTheEmployeeByInvalidID(String invalid_id) {
        APIConstants.request = given().
                header(APIConstants.HEADER_CONTENT_TYPE_KEY,APIConstants.HEADER_CONTENT_TYPE_VALUE).
                header(APIConstants.HEADER_AUTHORIZATION_KEY,APIConstants.token).
                queryParam("employee_id",invalid_id);
    }

    @Then("the error status for get call is {int}")
    public void theErrorStatusForGetCallIs(int statusCode) {
        APIConstants.response.then().assertThat().statusCode(statusCode);
    }

    @And("the error message is {string}")
    public void theErrorMessageIs(String errorMessage) {
        String actualMessage = APIConstants.response
                .then()
                .extract()
                .jsonPath()
                .getString("massage");
        assert actualMessage.equals(errorMessage);
    }


}
