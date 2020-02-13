package utilities;

import com.google.gson.JsonObject;

public class RestAPIUtils {

    private static JsonObject jsonObject;
    private static MockDataGenerator generator;


    public static JsonObject createMockUser(){
        jsonObject = new JsonObject();
        generator = new MockDataGenerator();

        jsonObject.addProperty("first_name", generator.getFirst_name());
        jsonObject.addProperty("last_name", generator.getLast_name());
        jsonObject.addProperty("email", generator.getEmail());
        jsonObject.addProperty("gender", generator.getGender());

        return jsonObject;
    }

}
