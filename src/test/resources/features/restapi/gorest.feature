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
    Then verify that status code is "200"


