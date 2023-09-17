Feature: Successful shopping test

  Scenario Outline: Shopping and checkout
    Given I'm on mystore main page
    And I log in using "jankowalski123@test.com" email and "haslo" password
    And I verify user
    Then I go to clothes tab
    And I hover over Hummingbird Printed Sweater and check if it's 20% off
    And I choose the product to quick-view it
    And I double-check the discount
    And //TODO
    And //TODO
    And I add the product to cart
    And I triple-check the discount
    And I proceed to checkout
    And //TODO I check






