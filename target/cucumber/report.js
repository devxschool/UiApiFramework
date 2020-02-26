$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("features/restapi/jira_api_test.feature");
formatter.feature({
  "line": 1,
  "name": "Jira API Test Scenarios",
  "description": "",
  "id": "jira-api-test-scenarios",
  "keyword": "Feature"
});
formatter.background({
  "line": 2,
  "name": "",
  "description": "",
  "type": "background",
  "keyword": "Background"
});
formatter.step({
  "line": 3,
  "name": "connect to Jira API",
  "keyword": "Given "
});
formatter.match({
  "location": "JiraApiSteps.connectToJiraAPI()"
});
formatter.result({
  "duration": 875415824,
  "status": "passed"
});
formatter.scenario({
  "line": 5,
  "name": "Create an issue type - BUG",
  "description": "",
  "id": "jira-api-test-scenarios;create-an-issue-type---bug",
  "type": "scenario",
  "keyword": "Scenario",
  "tags": [
    {
      "line": 4,
      "name": "@jira_api"
    }
  ]
});
formatter.step({
  "line": 6,
  "name": "create an issue type \"Bug\"",
  "keyword": "When "
});
formatter.step({
  "line": 7,
  "name": "verify that issue was created successfully",
  "keyword": "Then "
});
formatter.match({
  "arguments": [
    {
      "val": "Bug",
      "offset": 22
    }
  ],
  "location": "JiraApiSteps.createAnIssueType(String)"
});
formatter.result({
  "duration": 2057674909,
  "status": "passed"
});
formatter.match({
  "location": "JiraApiSteps.verifyThatIssueWasCreatedSuccessfully()"
});
formatter.result({
  "duration": 52153348,
  "status": "passed"
});
});