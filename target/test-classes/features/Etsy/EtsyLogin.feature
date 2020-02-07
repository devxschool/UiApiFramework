@etsy
Feature: Etsy login functionality

  Scenario: Happy path-positive scenario
    Given user is on th etsy home page
    When user clicks on sign in button
    And user inputs his username
    And user inputs his password
    And user clicks on sign it button
    Then verify the error message user account has been deactivated is displayed


  Scenario Outline: Multiple login accounts
    Given user is on th etsy home page
    When user clicks on sign in button
    And user inputs his username '<username>'
    And user inputs his password '<password>'
    And user clicks on sign in button
    Then verify the error message user account has been '<status>' is displayed

    Examples:
      | username              | password | status                        |
      | devxschool@gmail.com  | abc123   | Account has been deactivated. |
      | johnDoe@gmail.com     | joeDoe   | Password was incorrect        |
      | dummyaccout@gmail.com | veryHard | Email address is invalid      |
      | aaaa                  | bb       | invalid message               |


  Scenario: Search for multiple items
    #Given user is on th etsy home page
    When user adds the following items to the cart
      | babyCloth |
      | babyCloth |
      | daddy     |
      | mommy     |
    #primitive way of doing this
   # When user adds the following 'baby' to the cart
   # When user adds the following 'daddy' to the cart
   # When user adds the following 'mommy' to the cart
    Then the following items should be present in the cart
      | item      | quantity |
      | babyCloth | 2        |
      | mommy     | 1        |
      | daddy     | 1        |


  Scenario: Create accounts
    When User creates accounts with the following info
      | firstName | lastName | email                    | phoneNum    |
      | John      | Doe      | johnDoe@gmail.com        | 9990002222  |
      | Elon      | Musk     | elonMusk@gmail.com       | 1112223333  |
      | Steve     | Jobs     | stevejobs@devxschool.com | 34355567777 |
    Then the following accounts should be created
      | firstName | lastName | email                    | phoneNum      |
      | John      | Doe      | johnDoe@gmail.com        | 999-000-2222  |
      | Elon      | Musk     | elonMusk@gmail.com       | 111-222-3333  |
      | Steve     | Jobs     | stevejobs@devxschool.com | 343-5556-7777 |


