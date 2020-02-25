package utilities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import domainsOrPojo.jira.CreateIssue;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class RestAPIUtils {

    private static JsonObject jsonObject;
    private static MockDataGenerator generator;
    private static RequestSpecification requestSpecification;
    private static String json;
    private static ObjectMapper objectMapper;



    public static JsonObject createMockUser(){
        jsonObject = new JsonObject();
        generator = new MockDataGenerator();

        jsonObject.addProperty("first_name", generator.getFirst_name());
        jsonObject.addProperty("last_name", generator.getLast_name());
        jsonObject.addProperty("email", generator.getEmail());
        jsonObject.addProperty("gender", generator.getGender());

        return jsonObject;
    }


    public static RequestSpecification connectToJiraAPI(String username, String token){

        requestSpecification.auth().basic(
                username,
                token)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON);

     return requestSpecification;
    }

    public static String javaToJsonConverter(Object object) throws JsonProcessingException {

        objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        json = objectMapper.writerWithDefaultPrettyPrinter()
                .writeValueAsString(object);

        return json;
    }

}
