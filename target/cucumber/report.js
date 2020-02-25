$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("features/restapi/gorest.feature");
formatter.feature({
  "line": 2,
  "name": "Gorest Rest API Testing",
  "description": "",
  "id": "gorest-rest-api-testing",
  "keyword": "Feature",
  "tags": [
    {
      "line": 1,
      "name": "@API"
    },
    {
      "line": 1,
      "name": "@smoke"
    }
  ]
});
formatter.scenarioOutline({
  "line": 53,
  "name": "Verify invalid emails",
  "description": "",
  "id": "gorest-rest-api-testing;verify-invalid-emails",
  "type": "scenario_outline",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "line": 52,
      "name": "@api_email_test"
    }
  ]
});
formatter.step({
  "line": 54,
  "name": "the users are created with the following emails \"\u003cemails\u003e\"",
  "keyword": "When "
});
formatter.step({
  "line": 55,
  "name": "verify that response status code is \"422\"",
  "keyword": "Then "
});
formatter.step({
  "line": 56,
  "name": "verify that error message is \"\u003cerrorMessage\u003e\"",
  "keyword": "Then "
});
formatter.examples({
  "line": 57,
  "name": "",
  "description": "",
  "id": "gorest-rest-api-testing;verify-invalid-emails;",
  "rows": [
    {
      "cells": [
        "emails",
        "errorMessage"
      ],
      "line": 58,
      "id": "gorest-rest-api-testing;verify-invalid-emails;;1"
    },
    {
      "cells": [
        "test.com",
        "Email is not a valid email address."
      ],
      "line": 59,
      "id": "gorest-rest-api-testing;verify-invalid-emails;;2"
    },
    {
      "cells": [
        "amy.smith@",
        "Email is not a valid email address."
      ],
      "line": 60,
      "id": "gorest-rest-api-testing;verify-invalid-emails;;3"
    },
    {
      "cells": [
        "",
        "Email cannot be blank."
      ],
      "line": 61,
      "id": "gorest-rest-api-testing;verify-invalid-emails;;4"
    }
  ],
  "keyword": "Examples"
});
formatter.background({
  "line": 4,
  "name": "Setting up the base url and the headers",
  "description": "",
  "type": "background",
  "keyword": "Background"
});
formatter.step({
  "line": 5,
  "name": "base url \"https://gorest.co.in\"",
  "keyword": "Given "
});
formatter.step({
  "line": 6,
  "name": "set the headers",
  "keyword": "And "
});
formatter.match({
  "arguments": [
    {
      "val": "https://gorest.co.in",
      "offset": 10
    }
  ],
  "location": "GorestSteps.baseUrl(String)"
});
formatter.result({
  "duration": 10939983941,
  "status": "passed"
});
formatter.match({
  "location": "GorestSteps.set_the_headers()"
});
formatter.result({
  "duration": 151655894,
  "status": "passed"
});
formatter.scenario({
  "line": 59,
  "name": "Verify invalid emails",
  "description": "",
  "id": "gorest-rest-api-testing;verify-invalid-emails;;2",
  "type": "scenario",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "line": 52,
      "name": "@api_email_test"
    },
    {
      "line": 1,
      "name": "@smoke"
    },
    {
      "line": 1,
      "name": "@API"
    }
  ]
});
formatter.step({
  "line": 54,
  "name": "the users are created with the following emails \"test.com\"",
  "matchedColumns": [
    0
  ],
  "keyword": "When "
});
formatter.step({
  "line": 55,
  "name": "verify that response status code is \"422\"",
  "keyword": "Then "
});
formatter.step({
  "line": 56,
  "name": "verify that error message is \"Email is not a valid email address.\"",
  "matchedColumns": [
    1
  ],
  "keyword": "Then "
});
formatter.match({
  "arguments": [
    {
      "val": "test.com",
      "offset": 49
    }
  ],
  "location": "GorestSteps.theUsersAreCreatedWithTheFollowingEmails(String)"
});
formatter.result({
  "duration": 3077356304,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "422",
      "offset": 37
    }
  ],
  "location": "GorestSteps.verifyThatResponseStatusCodeIs(int)"
});
formatter.result({
  "duration": 88009235,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "Email is not a valid email address.",
      "offset": 30
    }
  ],
  "location": "GorestSteps.verifyThatErrorMessageIs(String)"
});
formatter.result({
  "duration": 423506,
  "status": "passed"
});
formatter.background({
  "line": 4,
  "name": "Setting up the base url and the headers",
  "description": "",
  "type": "background",
  "keyword": "Background"
});
formatter.step({
  "line": 5,
  "name": "base url \"https://gorest.co.in\"",
  "keyword": "Given "
});
formatter.step({
  "line": 6,
  "name": "set the headers",
  "keyword": "And "
});
formatter.match({
  "arguments": [
    {
      "val": "https://gorest.co.in",
      "offset": 10
    }
  ],
  "location": "GorestSteps.baseUrl(String)"
});
formatter.result({
  "duration": 93304,
  "status": "passed"
});
formatter.match({
  "location": "GorestSteps.set_the_headers()"
});
formatter.result({
  "duration": 2099187,
  "status": "passed"
});
formatter.scenario({
  "line": 60,
  "name": "Verify invalid emails",
  "description": "",
  "id": "gorest-rest-api-testing;verify-invalid-emails;;3",
  "type": "scenario",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "line": 52,
      "name": "@api_email_test"
    },
    {
      "line": 1,
      "name": "@smoke"
    },
    {
      "line": 1,
      "name": "@API"
    }
  ]
});
formatter.step({
  "line": 54,
  "name": "the users are created with the following emails \"amy.smith@\"",
  "matchedColumns": [
    0
  ],
  "keyword": "When "
});
formatter.step({
  "line": 55,
  "name": "verify that response status code is \"422\"",
  "keyword": "Then "
});
formatter.step({
  "line": 56,
  "name": "verify that error message is \"Email is not a valid email address.\"",
  "matchedColumns": [
    1
  ],
  "keyword": "Then "
});
formatter.match({
  "arguments": [
    {
      "val": "amy.smith@",
      "offset": 49
    }
  ],
  "location": "GorestSteps.theUsersAreCreatedWithTheFollowingEmails(String)"
});
formatter.result({
  "duration": 2338960443,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "422",
      "offset": 37
    }
  ],
  "location": "GorestSteps.verifyThatResponseStatusCodeIs(int)"
});
formatter.result({
  "duration": 2477967,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "Email is not a valid email address.",
      "offset": 30
    }
  ],
  "location": "GorestSteps.verifyThatErrorMessageIs(String)"
});
formatter.result({
  "duration": 269250,
  "status": "passed"
});
formatter.background({
  "line": 4,
  "name": "Setting up the base url and the headers",
  "description": "",
  "type": "background",
  "keyword": "Background"
});
formatter.step({
  "line": 5,
  "name": "base url \"https://gorest.co.in\"",
  "keyword": "Given "
});
formatter.step({
  "line": 6,
  "name": "set the headers",
  "keyword": "And "
});
formatter.match({
  "arguments": [
    {
      "val": "https://gorest.co.in",
      "offset": 10
    }
  ],
  "location": "GorestSteps.baseUrl(String)"
});
formatter.result({
  "duration": 50676,
  "status": "passed"
});
formatter.match({
  "location": "GorestSteps.set_the_headers()"
});
formatter.result({
  "duration": 881211,
  "status": "passed"
});
formatter.scenario({
  "line": 61,
  "name": "Verify invalid emails",
  "description": "",
  "id": "gorest-rest-api-testing;verify-invalid-emails;;4",
  "type": "scenario",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "line": 52,
      "name": "@api_email_test"
    },
    {
      "line": 1,
      "name": "@smoke"
    },
    {
      "line": 1,
      "name": "@API"
    }
  ]
});
formatter.step({
  "line": 54,
  "name": "the users are created with the following emails \"\"",
  "matchedColumns": [
    0
  ],
  "keyword": "When "
});
formatter.step({
  "line": 55,
  "name": "verify that response status code is \"422\"",
  "keyword": "Then "
});
formatter.step({
  "line": 56,
  "name": "verify that error message is \"Email cannot be blank.\"",
  "matchedColumns": [
    1
  ],
  "keyword": "Then "
});
formatter.match({
  "arguments": [
    {
      "val": "",
      "offset": 49
    }
  ],
  "location": "GorestSteps.theUsersAreCreatedWithTheFollowingEmails(String)"
});
formatter.result({
  "duration": 1675352296,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "422",
      "offset": 37
    }
  ],
  "location": "GorestSteps.verifyThatResponseStatusCodeIs(int)"
});
formatter.result({
  "duration": 2815004,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "Email cannot be blank.",
      "offset": 30
    }
  ],
  "location": "GorestSteps.verifyThatErrorMessageIs(String)"
});
formatter.result({
  "duration": 296579,
  "status": "passed"
});
});