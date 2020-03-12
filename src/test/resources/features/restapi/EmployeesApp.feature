Feature: Get and Create Employees


  Scenario: Get all employees Feature;
    Given the user retrieved all employees from the db
    When the user sends get all employees request
    Then users from db and response should match



    #/employees/{id}/incrementsalary/{incrementvalue}
  Scenario: Increment Salary
    Given the user cleans "employee" table in DB
    And the user cleans "company" table in DB
    And the user created new employee in DB
      | id | firstName | lastName | salary | companyId | companyName | companyWebsite |
      | 1  | John      | Doe      | 350000 | 2         | Google      | google.com     |
    When the user sends increment employee salary request
      | id | incrementValue |
      | 1  | 200            |
    Then the employee with id '1' salary should be '350200'


  Scenario: Increment Salary add multiple employees
    Given the user cleans "employee" table in DB
    And the user cleans "company" table in DB
    And the user created new employee in DB
      | id | firstName | lastName | salary | companyId | companyName | companyWebsite |
      | 1  | John      | Doe      | 350000 | 2         | Google      | google.com     |
      | 2  | Elon      | Musk     | 100000 | 3         | Amazon      | amazon.com     |
      | 3  | Steve     | Jobs     | 900000 | 4         | Apple       | apple.com      |
    When the user sends increment employee salary request
      | id | incrementValue |
      | 1  | 200            |
      | 2  | 599            |
      | 3  | 2323           |
    Then the employee salaries should be
      | id | salary |
      | 1  | 350200 |
      | 2  | 100599 |
      | 3  | 902323 |
