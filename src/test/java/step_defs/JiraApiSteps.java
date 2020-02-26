package step_defs;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import domainsOrPojo.jira.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utilities.ConfigReader;
import utilities.RestAPIUtils;

public class JiraApiSteps {
private Response response;
private RequestSpecification requestSpecification;

    @Given("^connect to Jira API$")
    public void connectToJiraAPI() {
        RestAssured.baseURI = ConfigReader.getProperty("jiraBaseUri");

       requestSpecification =  RestAPIUtils.connectToJiraAPI(
                ConfigReader.getProperty("jiraAPIUsername"),
                ConfigReader.getProperty("jiraAPIToken"));

    }

    @When("^create an issue type \"([^\"]*)\"$")
    public void createAnIssueType(String arg0) throws Throwable {

        Project project = new Project("JAVA");
        IssueType issueType = new IssueType(JiraIssueTypes.BUG.getIssueId());



        Fields fields = new Fields(
               project,
               "Creating a bug ticket using API",
               "Creating of an issue using project keys and issue type names using the REST API",
                issueType
        );

        CreateIssue createIssue = new CreateIssue(fields);


        System.out.println(RestAPIUtils.javaToJsonConverter(createIssue));

        response = requestSpecification.and()
                .body(RestAPIUtils.javaToJsonConverter(createIssue))
                .post("rest/api/2/issue/");


        response.prettyPrint();
    }

    @Then("^verify that issue was created successfully$")
    public void verifyThatIssueWasCreatedSuccessfully() {

        response.then().statusCode(201);
    }
}
