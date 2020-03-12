package step_defs;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.java.it.Ma;
import domainsOrPojo.DBType;
import domainsOrPojo.EmployeeCucumberDT;
import domainsOrPojo.Employees;
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

import static java.lang.String.format;
import static org.junit.Assert.assertEquals;

public class EmployeeAppSteps {

    private static Logger LOGGER = LogManager.getLogger(EmployeeAppSteps.class);


    List<Map<String, Object>> employeeDBresult;
    Response response;
    RequestSpecification requestSpecification;

    ObjectMapper objectMapper = new ObjectMapper();

    @Given("^the user retrieved all employees from the db$")
    public void theUserRetrievedAllEmployeesFromTheDb() throws SQLException {

        DBUtils.establishConnection(DBType.MYSQL);
        employeeDBresult = DBUtils.runSqlSelectQuery("select * from employee;");
    }

    @When("^the user sends get all employees request$")
    public void theUserSendsGetAllEmployeesRequest() {
        response =
                RestAssured
                        .get(ConfigReader.getProperty("getEmployeeEndpoint"));
    }

    @Then("^users from db and response should match$")
    public void usersFromDbAndResponseShouldMatch() throws JsonProcessingException {
        LOGGER.info("data from DB " + employeeDBresult);

        LOGGER.info("data from response + " + response.asString());

        Employees[] employeesListFromResponse = objectMapper.readValue(response.asString(), Employees[].class);
        //System.out.println(employeesListFromResponse);
        assertEquals("status code miss match", 200, response.getStatusCode());
        assertEquals("invalid number employees returned in the response ", employeeDBresult.size(), employeesListFromResponse.length);
        for (int i = 0; i < employeeDBresult.size(); i++) {
            assertEquals("Employee id miss match", employeeDBresult.get(i).get("id"), employeesListFromResponse[i].getId());
            assertEquals("Employee last name miss match", employeeDBresult.get(i).get("last_name"), employeesListFromResponse[i].getLastName());
            assertEquals("Employee first name miss match", employeeDBresult.get(i).get("first_name"), employeesListFromResponse[i].getFirstName());
            assertEquals("Employee salary miss match", employeeDBresult.get(i).get("salary"), employeesListFromResponse[i].getSalary());
            LOGGER.info("Asserted employee name: " + employeesListFromResponse[i].getFirstName());
        }
        //Assert.assertEquals(employeeDBresult.get(0).get("company_id"), employeesListFromResponse[0].get);

    }

    @Given("^the user cleans \"([^\"]*)\" table in DB$")
    public void theUserCleansTableInDB(String tableName) throws Throwable {

        LOGGER.info("About to Clean " + tableName + " Table");
        DBUtils.establishConnection(DBType.MYSQL);
        DBUtils.runSqlUpdateQuery("SET FOREIGN_KEY_CHECKS = 0;");
        DBUtils.runSqlUpdateQuery("truncate table " + tableName + ";");
        DBUtils.runSqlUpdateQuery("SET FOREIGN_KEY_CHECKS = 1;");
        LOGGER.info(tableName + " Table is successfully cleaned");


        employeeDBresult = DBUtils.runSqlSelectQuery("select * from " + tableName + ";");
        LOGGER.info("retrieved all data from "+ tableName);
        LOGGER.debug("following is the result rows " + employeeDBresult);

        assertEquals("Table is not clear", 0, employeeDBresult.size());


    }

    @And("^the user created new employee in DB$")
    public void theUserCreatedNewEmployeeInDB(List<EmployeeCucumberDT> employeeCucumberDTList) throws SQLException {
        DBUtils.runSqlUpdateQuery("SET FOREIGN_KEY_CHECKS = 0;");

        for (int i = 0; i < employeeCucumberDTList.size(); i++) {
            DBUtils.runSqlInsertQuery(format("insert into db_devxschool.employee values(%d,'%s','%s',%f,%d); ",
                    employeeCucumberDTList.get(i).getId(),
                    employeeCucumberDTList.get(i).getFirstName(),
                    employeeCucumberDTList.get(i).getLastName(),
                    employeeCucumberDTList.get(i).getSalary(),
                    employeeCucumberDTList.get(i).getCompanyId()));

            //Log some message on console


            DBUtils.runSqlInsertQuery(format("insert into db_devxschool.company values(%d,'%s','%s');",
                    employeeCucumberDTList.get(i).getCompanyId(),
                    employeeCucumberDTList.get(i).getCompanyName(),
                    employeeCucumberDTList.get(i).getCompanyWebsite()));


            LOGGER.info("Sent the following insert queries");

            LOGGER.info(format("insert into db_devxschool.employee values(%d,'%s','%s',%f,%d); ",
                    employeeCucumberDTList.get(i).getId(),
                    employeeCucumberDTList.get(i).getFirstName(),
                    employeeCucumberDTList.get(i).getLastName(),
                    employeeCucumberDTList.get(i).getSalary(),
                    employeeCucumberDTList.get(i).getCompanyId()));

            LOGGER.info(format("insert into db_devxschool.company values(%d,'%s','%s');",
                    employeeCucumberDTList.get(i).getCompanyId(),
                    employeeCucumberDTList.get(i).getCompanyName(),
                    employeeCucumberDTList.get(i).getCompanyWebsite()));

        }
    }

    @When("^the user sends increment employee salary request$")
    public void theUserSendsIncrementEmployeeSalaryRequest(List<Map<String, String>> maps) {
        for (Map<String, String> map : maps) {
            response =
                    RestAssured.post(format(ConfigReader.getProperty("incrementSalaryEndpoint") + "%s/incrementsalary/%s",
                            map.get("id"),
                            map.get("incrementValue")));

            LOGGER.info("Sent increment salary request to " + map.get("id"));
            LOGGER.info("Increment value: " + map.get("incrementValue"));

        }

    }

    @Then("^the employee with id '(\\d+)' salary should be '(\\d+)'$")
    public void theEmployeeWithIdSalaryShouldBe(int id, float expectedSalary) throws Throwable {

        assertEquals("Status code mismatch", 200, response.getStatusCode());

        Employees employeesListFromResponse = objectMapper.readValue(response.asString(), Employees.class);

        assertEquals("Employee salary mismatch in the response", expectedSalary, employeesListFromResponse.getSalary(), 0);
        assertEquals("Employee ids in Response mismatch", id, employeesListFromResponse.getId());

        employeeDBresult = DBUtils.runSqlSelectQuery("select * from employee;");
        assertEquals("Employee salary mismatch in the db", expectedSalary, employeeDBresult.get(0).get("salary"));

    }

    @Then("^the employee salaries should be$")
    public void theEmployeeSalariesShouldBe(List<Map<String, Object>> expectedResults) throws SQLException {

        employeeDBresult = DBUtils.runSqlSelectQuery("select * from employee;");

        assertEquals("Results size mismatch", expectedResults.size(), employeeDBresult.size());

        for (Map<String, Object> expectedMap : expectedResults) {

            for (Map<String, Object> actual : employeeDBresult) {
                if (expectedMap.get("id").equals(actual.get("id"))) {
                    assertEquals("Salary mismatch", expectedMap.get("salary"), actual.get("salary"));
                }
            }

        }
    }

}
