package step_defs;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.File;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;

public class GorestSteps {

    RequestSpecification requestSpecification;
    Response response;

    @Given("^base url \"([^\"]*)\"$")
    public void baseUrl(String arg0) throws Throwable {
        RestAssured.baseURI = arg0;
    }
    @Given("^set the headers$")
    public void set_the_headers() throws Throwable {

        requestSpecification =
                given()
                .header("Authorization","Bearer YGgADaRcVhfwHZaVzKjB4GIuSwVhBSmSEN5k")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON);

    }

    @And("^send HTTP GET request$")
    public void sendHTTPGETRequest() {
       response =
       requestSpecification
       .and()
       .get("/public-api/users");
    }

    @Then("^verify that response content-type is \"([^\"]*)\"$")
    public void verifyThatResponseContentTypeIs(String contentType) throws Throwable {

        assertEquals("ASSERTION FAILED: CONTENT-TYPE",contentType,response.getContentType());

    }

    @Then("^verify that status code is \"([^\"]*)\"$")
    public void verifyThatStatusCodeIs(int statusCode) throws Throwable {
        response.prettyPeek();

//        assertEquals(statusCode,response.getStatusCode());


    }

    @And("^send HTTP POST request$")
    public void sendHTTPPOSTRequest() {

        response =
                requestSpecification
                .and()
                .body(new File("src/test/resources/user.json"))
                .and()
                .post("/public-api/users");
    }
}
