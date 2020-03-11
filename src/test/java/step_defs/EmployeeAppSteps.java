package step_defs;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import domainsOrPojo.DBType;
import domainsOrPojo.Employees;
import domainsOrPojo.ResponseBody;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
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
        DBUtils.establishConnection(DBType.MYSQL);
        employeeDBresult =  DBUtils.runSqlQuery("select * from employee;");
    }

    @When("^the user sends get all employees request$")
    public void theUserSendsGetAllEmployeesRequest() {
        response =
                RestAssured
                        .get(ConfigReader.getProperty("getEmployeeEndpoint"));
    }

    @Then("^users from db and response should match$")
    public void usersFromDbAndResponseShouldMatch() throws JsonProcessingException {
        LOGGER.info("data from DB " +   employeeDBresult);

        LOGGER.info("data from response + " + response.asString());
        ObjectMapper objectMapper = new ObjectMapper();
        Employees[] employeesListFromResponse = objectMapper.readValue(response.asString(), Employees[].class);
        //System.out.println(employeesListFromResponse);
        Assert.assertEquals("status code miss match", 200, response.getStatusCode());
        Assert.assertEquals("invalid number employees returned in the response ", employeeDBresult.size(), employeesListFromResponse.length);
        for (int i = 0; i < employeeDBresult.size(); i++) {
            Assert.assertEquals("Employee id miss match", employeeDBresult.get(i).get("id"), employeesListFromResponse[i].getId());
            Assert.assertEquals("Employee last name miss match", employeeDBresult.get(i).get("last_name"), employeesListFromResponse[i].getLastName());
            Assert.assertEquals("Employee first name miss match", employeeDBresult.get(i).get("first_name"), employeesListFromResponse[i].getFirstName());
            Assert.assertEquals("Employee salary miss match", employeeDBresult.get(i).get("salary"), employeesListFromResponse[i].getSalary());
            LOGGER.info("Asserted employee name: " + employeesListFromResponse[i].getFirstName());
        }
        //Assert.assertEquals(employeeDBresult.get(0).get("company_id"), employeesListFromResponse[0].get);

    }
}
