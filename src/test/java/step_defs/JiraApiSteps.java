package step_defs;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import domainsOrPojo.jira.CreateIssue;
import domainsOrPojo.jira.Fields;
import domainsOrPojo.jira.IssueType;
import domainsOrPojo.jira.Project;
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

        RestAPIUtils.connectToJiraAPI(
                ConfigReader.getProperty("jiraAPIUsername"),
                ConfigReader.getProperty("jiraAPIToken"));

    }

    @When("^create an issue type \"([^\"]*)\"$")
    public void createAnIssueType(String arg0) throws Throwable {

        Project project = new Project("API Test");
        IssueType issueType = new IssueType("Bug");

        Fields fields = new Fields(
               project,
               "Creating a bug ticket using API",
               "Creating of an issue using project keys and issue type names using the REST API",
                issueType
        );

        CreateIssue createIssue = new CreateIssue(fields);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);


    }

    @Then("^verify that issue was created successfully$")
    public void verifyThatIssueWasCreatedSuccessfully() {
    }
}
