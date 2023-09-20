Feature: Successful shopping test

  Scenario Outline: Shopping and checkout
    Given I'm on mystore main page
    And I log in using "jankowalski123@test.com" email and "haslo" password
    And I verify user with <expectedName> name
    When I go to the clothes tab
    And I hover over Hummingbird Printed Sweater and check if it's "-20%" off
    And I choose the product to quick-view it
    And I double-check the discount
    And I select <size> size
    And I choose how many items I want <quantity>
    And I add the product(s) to a cart
    And I check if successful message popped up "Product successfully added to your shopping cart"
    And I proceed to checkout
    And I validate my address: name <expectedName>, address <expectedAddress>, city <expectedCity>, zipCode <expectedZipCode>, country <expectedCountry>, phone <expectedPhone>
    #click CONTINUE
    And I validate if "Self pick up radio" box is checked
    #click CONTINUE
    And I pay by check and agree to the terms of service
    And I take a screenshot of order details
    Then I go to order history and details
    And I check order status "Awaiting check payment" and if the price is valid
    And I close the browser


    Examples:
      | size | quantity | expectedName   | expectedAddress | expectedCity | expectedZipCode | expectedCountry  | expectedPhone |
      | M    | 5        | "Jan Kowalski" | "Oxford Street" | "London"     | "57-618"        | "United Kingdom" | "666229920"   |
      | S    | randomQ  | "Jan Kowalski" | "Oxford Street" | "London"     | "57-618"        | "United Kingdom" | "666229920"   |
      | M    | randomQ  | "Jan Kowalski" | "Oxford Street" | "London"     | "57-618"        | "United Kingdom" | "666229920"   |
      | L    | randomQ  | "Jan Kowalski" | "Oxford Street" | "London"     | "57-618"        | "United Kingdom" | "666229920"   |
      | XL   | randomQ  | "Jan Kowalski" | "Oxford Street" | "London"     | "57-618"        | "United Kingdom" | "666229920"   |






