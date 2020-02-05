@etsy
Feature: Etsy login functionality

  Scenario: Happy path-positive scenario
    Given user is on th etsy home page
    When user clicks on sign in button
    And user inputs his username
    And user inputs his password
    And user clicks on sign it button
    Then verify the error message user account has been deactivated is displayed