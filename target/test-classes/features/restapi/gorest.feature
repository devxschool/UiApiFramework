@API
Feature: Gorest Rest API Testing

  Background: Setting up the base url and the headers
    Given base url "https://gorest.co.in"
    And set the headers

  @api_get
  Scenario: Gorest GET request
    And send HTTP GET request
    Then verify that response content-type is "application/json; charset=UTF-8"
    Then verify that status code is "200"

  @api_post
  Scenario: Gorest POST request
    And send HTTP POST request
    And verify that response content-type is "application/json; charset=UTF-8"
    Then verify that status code is "201"

  @api_getUser
  Scenario: Gorest GET request
    And send HTTP GET request with user_id "6"

  @api_postWithJsonObject
  Scenario: Gorest POST request
    And send HTTP POST request with JsonObject


  @api_putWithJsonObject
  Scenario: Gorest PUT request
    And send HTTP PUT request with JsonObject and user_id "6"

  @smoke
  Scenario: Gorest Create Users
    When the following users are created
      | first_name | last_name | gender |
      | John       | Doe       | male   |
      | Elon       | Musk      | male   |
      | Steve      | Jobs      | male   |
    Then verify that status code is "201"
    And the following user should be created
      | message                            | first_name | last_name | gender |
      | OK. Everything worked as expected. | John       | Doe       | male   |
      | OK. Everything worked as expected. | Elon       | Musk      | male   |
      | OK. Everything worked as expected. | Steve     | Jobs      | male   |


    @api-test
    Scenario: Get all the users from gorest
      When get all the users




