package utils;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;


public class APIConstants {

    public static  String token;
    public static final String baseURL = RestAssured.baseURI
            = "http://hrm.syntaxtechs.net/syntaxapi/api";
    public static Response response;
    public static RequestSpecification request;

    public static final String CREATE_EMPLOYEE=baseURL+"/createEmployee.php";
    public static final String GENERATE_TOKEN=baseURL+"/generateToken.php";
    public static final String GET_ONE_EMPLOYEE=baseURL+"/getOneEmployee.php";
    public static final String UPDATE_EMPLOYEE=baseURL+"/updateEmployee.php";
    public static final String DELETE_EMPLOYEE=baseURL+"/deleteEmployee.php";
    public static final String CREATE_USER=baseURL+"/createUser.php";

    public static final String HEADER_CONTENT_TYPE_KEY=baseURL+"Content/Type";
    public static final String HEADER_CONTENT_TYPE_VALUE=baseURL+"application/json";
    public static final String HEADER_AUTHORIZATION_KEY="Authorization";

    public static final String CONFIG_FILE_PATH="src/resources/config/config.properties";
}