Feature: Amazon Search

  Test Search for Item functionality
  Tests are run against chrome browser.

  #Runs once before every scenario in this Feature file
  Background:
    Given User is on the main page
    And User is logged in


  Scenario: Search For an Item
    you can put Description here  for Search
    #Main page is amazon.co
    When User enters 'Iphone' in the search box
    Then All results should contain 'Iphone' in the title

  Scenario: Item Details
      When User enters 'Ball' in the search box
      And User selects the first 'Iphone'
      Then User should see the price in the detail

  Scenario: Carts
    And User is logged in
    When User enters 'Iphone' in the search box
    And User selects the first 'Iphone'
    And User adds the selected item to the cart
    Then User should see 'Iphone' in the cart
    And User should see the price in the detail
    But User shouldnt see the 'Iphone' characterics





















  
