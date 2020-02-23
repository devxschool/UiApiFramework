package step_defs;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import domainsOrPojo.GoRestResponse;
import domainsOrPojo.GoRestUser;
import domainsOrPojo.ResponseBody;
import domainsOrPojo.User;
import io.restassured.RestAssured;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utilities.MockDataGenerator;
import utilities.RestAPIUtils;

import java.io.File;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;


import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class GorestSteps {

    private static Logger LOGGER = LogManager.getLogger(GorestSteps.class);

    RequestSpecification requestSpecification;
    Response response;
    List<Response> responses = new ArrayList<>();
    Gson gson;
    MockDataGenerator mockDataGenerator;
    ObjectMapper objectMapper;

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

        MockDataGenerator mockDataGenerator = new MockDataGenerator();

        User user = new User(
                mockDataGenerator.getFirst_name(),
                mockDataGenerator.getLast_name(),
                mockDataGenerator.getEmail(),
                mockDataGenerator.getGender(),
                "1990-12-12"
        );
        gson = new Gson();
        String json = gson.toJson(user);  // serialization
        response =
                requestSpecification
                        .and()
                        .body(json)
                        .and()
                        .post("/public-api/users");


        response.prettyPrint();
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


        LOGGER.debug("IM in debug mode so I want to print info with details " + users);
        LOGGER.info("ABOUT TO SEND GO REST REQUEST ON THE FOLLOWING USERS ");
        LOGGER.warn("You could break something");
        LOGGER.error("Something was broken");
        LOGGER.fatal("Things went horribly bad");



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
                    LOGGER.info("Comparing the content of actual and expected users " + jsonPath.getString("result.first_name") + " " + expected.getFirst_name());
                    actual.then()
                            .assertThat()
                            .body("result.first_name", equalTo(expected.getFirst_name()))
                            .body("result.last_name", equalTo(expected.getLast_name()))
                            .body("result.gender", equalTo(expected.getGender()))
                            .log().ifValidationFails(LogDetail.URI);
                }
                else{
                    LOGGER.error("Cannot compare because first names are not matching");
                }
            }
        }


    }

    @When("^get all the users$")
    public void getAllTheUsers() {

       response =  requestSpecification
               .and().get("/public-api/users");

       gson = new Gson();


       ResponseBody responseBody = gson.fromJson(response.asString(), ResponseBody.class);
        //deserialization
        System.out.println(responseBody.get_meta().isSuccess());


        LocalDate currentDate = LocalDate.now();   // "2020-02-17"

        for (User user: responseBody.getUsers()) {
        LocalDate dob = LocalDate.parse(user.getDob());

        Period age = Period.between(dob,currentDate); // calculates difference between current and dob

            if(age.getYears()>=10&age.getYears()<=50){
                System.out.println(String.format("Email: %s and age %s",user.getEmail(),age.getYears()));

            }
        }





    }

    @When("^the users are created with the following emails \"([^\"]*)\"$")
    public void theUsersAreCreatedWithTheFollowingEmails(String email) throws Throwable {

        mockDataGenerator = new MockDataGenerator();

        User user = new User(
                mockDataGenerator.getFirst_name(),
                mockDataGenerator.getLast_name(),
                email,
                mockDataGenerator.getGender(),
                "1990-12-12"
        );

        objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        // will ignore unrecognized properties

        String json = objectMapper.writerWithDefaultPrettyPrinter()  // will do pretty print
                                  .writeValueAsString(user); // Sereliazation

       response =
               requestSpecification
               .body(json)
               .and()
               .post("/public-api/users");

        System.out.println(json);

    }

    @Then("^verify that error message is \"([^\"]*)\"$")
    public void verifyThatErrorMessageIs(String arg0) throws Throwable {


    ResponseBody responseBody =  objectMapper.readValue(response.asString(),ResponseBody.class);
    assertEquals(arg0,responseBody.getUsers().get(0).getMessage());



    }

    @Then("^verify that response status code is \"([^\"]*)\"$")
    public void verifyThatResponseStatusCodeIs(int arg0) throws Throwable {

      ResponseBody responseBody =  objectMapper.readValue(response.asString(),ResponseBody.class);

      assertEquals(arg0,responseBody.get_meta().getCode());
      System.out.println(responseBody.get_meta().getMessage());

    }
}
