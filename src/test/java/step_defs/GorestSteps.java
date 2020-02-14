package step_defs;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import domainsOrPojo.GoRestResponse;
import domainsOrPojo.GoRestUser;
import io.restassured.RestAssured;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utilities.RestAPIUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class GorestSteps {

    RequestSpecification requestSpecification;
    Response response;
    List<Response> responses = new ArrayList<>();

    @Given("^base url \"([^\"]*)\"$")
    public void baseUrl(String arg0) throws Throwable {
        RestAssured.baseURI = arg0;
    }

    @Given("^set the headers$")
    public void set_the_headers() throws Throwable {

        requestSpecification =
                given()
                        .header("Authorization", "Bearer YGgADaRcVhfwHZaVzKjB4GIuSwVhBSmSEN5k")
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

        assertEquals("ASSERTION FAILED: CONTENT-TYPE", contentType, response.getContentType());

    }

    @Then("^verify that status code is \"([^\"]*)\"$")
    public void verifyThatStatusCodeIs(int statusCode) throws Throwable {

        if (!responses.isEmpty()) {
            for (Response response : responses) {
                response.prettyPeek();
                JsonPath jsonPath = response.jsonPath();
                System.out.println("Status Code: " + jsonPath.getInt("_meta.code"));
                assertEquals(statusCode, jsonPath.getInt("_meta.code"));
            }
        } else {
            response.prettyPeek();
            JsonPath jsonPath = response.jsonPath();
            System.out.println("Status Code: " + jsonPath.getInt("_meta.code"));
            assertEquals(statusCode, jsonPath.getInt("_meta.code"));
        }
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

    @And("^send HTTP GET request with user_id \"([^\"]*)\"$")
    public void sendHTTPGETRequestWithUser_id(String arg0) throws Throwable {

        response =
                requestSpecification
                        .given()
                        .get("/public-api/users/" + arg0)
                        .then()
                        .assertThat()
                        .body("_meta.code", equalTo(200))
                        .and()
                        .contentType(ContentType.JSON)
                        .body("result.first_name", equalTo("Sheldon"))
                        .body("result.last_name", equalTo("Pfannerstill"))
                        .extract()
                        .response();


    }

    @And("^send HTTP POST request with JsonObject$")
    public void sendHTTPPOSTRequestWithJsonObject() {
        response =
                requestSpecification
                        .body(RestAPIUtils.createMockUser())
                        .post("/public-api/users")
                        .then()
                        .assertThat()
                        .body("_meta.code", is(201))
                        .body("_meta.success", equalTo(true))
                        .body("result.first_name", nullValue())
                        .log().ifValidationFails(LogDetail.URI)
                        .extract().response();

        response.prettyPrint();
    }

    @And("^send HTTP PUT request with JsonObject and user_id \"([^\"]*)\"$")
    public void sendHTTPPUTRequestWithJsonObjectAndUser_id(int id) throws Throwable {

//       PATHPARAM
//        response =
//                requestSpecification
//                        .body(RestAPIUtils.createMockUser())
//                        .and()
//                        .pathParam("id",id)
//                        .put("/public-api/users/{id}");
////                   /public-api/users/6

//        QUERY PARAM

        response =
                requestSpecification
                        .when()
                        .queryParam("gender", "female")
                        .queryParam("first_name", "Jena")
                        .get("/public-api/users");
//                /public-api/users?gender=female&first_name=Jena
        response.prettyPrint();

    }

    @When("^the following users are created$")
    public void theFollowingUsersAreCreated(List<GoRestUser> users) {

        System.out.println(users);

        for (GoRestUser user : users) {

            response =
                    requestSpecification
                            .body(user)
                            .post("/public-api/users");

            responses.add(response);
            response.prettyPrint();
        }
    }

    @And("^the following user should be created$")
    public void theFollowingUserShouldBeCreated(List<GoRestResponse> expectedResponses) {
        System.out.println("Checking create user request response");
        for (GoRestResponse expected : expectedResponses) {

            for (Response actual : responses) {

                actual.prettyPeek();
                JsonPath jsonPath = actual.jsonPath();

                System.out.println(String.format("Comparing first names expected %s and actual %s", expected.getFirst_name(), jsonPath.getString("result.first_name")));

                if (jsonPath.getString("result.first_name").equalsIgnoreCase(expected.getFirst_name())) {
                    actual.then()
                            .assertThat()
                            .body("result.first_name", equalTo(expected.getFirst_name()))
                            .body("result.last_name", equalTo(expected.getLast_name()))
                            .body("result.gender", equalTo(expected.getGender()))
                            .log().ifValidationFails(LogDetail.URI);
                }
            }
        }


    }
}
