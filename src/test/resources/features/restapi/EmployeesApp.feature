Feature: Get and Create Employees



  Scenario: Get all employees Feature;
    Given the user retrieved all employees from the db
    When the user sends get all employees request
    Then users from db and response should match