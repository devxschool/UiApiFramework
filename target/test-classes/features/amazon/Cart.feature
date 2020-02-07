Feature: Cart Test
  Background:
    Given User is on the main page
    And User is logged in


  Scenario: Item is added to the cart
    When User enters 'Table spoon' in the search box
    And User clicks on the item number 1
    And User click on Add to Cart
    Then User should see 'Added to Cart'
