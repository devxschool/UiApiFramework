@expedia
Feature: List Your Property
  Scenario: New window for property listing
    Given the user is on the main expedia page
    When the user clicks on 'list your property button'
    Then the new tab should open
      And how much could you earn text is be displayed