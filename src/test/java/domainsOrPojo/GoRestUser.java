package domainsOrPojo;

import com.github.javafaker.Faker;

public class GoRestUser {

    private String email;
    private String first_name;
    private String last_name;
    private String gender;


    public String getEmail() {
        Faker faker = new Faker();
        email = faker.internet().emailAddress();
        return email;
    }

    public void setEmail(String email) {
        Faker faker = new Faker();
        this.email = faker.internet().emailAddress();
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "{" +
                "\"email\": " + "\"" + getEmail() + "\"," +
                "\"first_name\": " + "\"" + first_name + "\"," +
                "\"last_name\": " + "\"" + last_name + "\"," +
                "\"gender\": " + "\"" + gender + "\"}";

    }
}
