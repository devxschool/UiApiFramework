package utilities;

import com.github.javafaker.Faker;

public class MockDataGenerator {
    private Faker faker = new Faker();
    private String first_name;
    private String last_name;
    private String email;
    private String gender;


    public String getFirst_name(){
        first_name = faker.name().firstName();
        return first_name;
    }

    public String getLast_name(){
        last_name = faker.name().lastName();
        return last_name;
    }

    public String getGender(){
        gender = faker.regexify("male|female");
        return gender;
    }

    public String getEmail(){
        email = faker.bothify(first_name+"."+last_name+"##@test.com");
        return email;
    }

}
