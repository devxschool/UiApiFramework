Feature: Jira API Test Scenarios
 Background:
   Given connect to Jira API
@jira_api
  Scenario: Create an issue type - BUG
    When create an issue type "Bug"
    Then verify that issue was created successfully
