package step_defs;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utilities.ConfigReader;
import utilities.DBUtils;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class EmployeeAppSteps {

    private static Logger LOGGER = LogManager.getLogger(EmployeeAppSteps.class);


    List<Map<String, Object>> employeeDBresult;
    Response response;
    RequestSpecification requestSpecification;


    @Given("^the user retrieved all employees from the db$")
    public void theUserRetrievedAllEmployeesFromTheDb()  {
        DBUtils.establishConnection();
        employeeDBresult =  DBUtils.runSqlQuery("select * from employee;");
    }

    @When("^the user sends get all employees request$")
    public void theUserSendsGetAllEmployeesRequest() {
        response =
                RestAssured
                        .get(ConfigReader.getProperty("getEmployeeEndpoint"));
    }

    @Then("^users from db and response should match$")
    public void usersFromDbAndResponseShouldMatch() {
        LOGGER.info("data from DB " +   employeeDBresult);

        LOGGER.info("data from response + " + response.asString());
    }
}
