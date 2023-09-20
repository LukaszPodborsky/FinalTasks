Feature: Successful shopping test

  Scenario Outline: Shopping and checkout
    Given I'm on mystore main page
    And I log in using "jankowalski123@test.com" email and "haslo" password
    And I verify user with <expectedName> name
    When I go to the clothes tab
    And I hover over Hummingbird Printed Sweater and check if it's "-20%" off
    And I choose the product to quick-view it
    And I double-check the <expectedDiscount> discount
    And I select <size> size
    And I choose <quantity> the amount of the selected product
    And I add the product(s) to a shopping cart
    And I check if successful message popped up "Product successfully added to your shopping cart"
    And I proceed to checkout
    And I validate my address: expectedName <expectedName>, expectedAddress <expectedAddress>, expectedCity <expectedCity>, expectedZipCode <expectedZipCode>, expectedCountry <expectedCountry>, expectedPhone <expectedPhone>
    And I continue to shipping method
    And I validate if self pick up radio box is checked
    And I proceed to payment
    And I pay by check and agree to the terms of service
    And I capture the total price
    And I place order
    Then I take a screenshot of order details
    And I go to my account details
    And I click on order history and details
    And I check order status "Awaiting check payment"
    And I verify the total price if it's valid
    And I close the browser

    Examples:
      | expectedDiscount | size | quantity | expectedName   | expectedAddress | expectedCity | expectedZipCode | expectedCountry  | expectedPhone |
      | "SAVE 20%"       | "M"  | "5"      | "Jan Kowalski" | "Oxford Street" | "London"     | "57-618"        | "United Kingdom" | "666229920"   |
      | "SAVE 20%"       | "S"  | "1"      | "Jan Kowalski" | "Oxford Street" | "London"     | "57-618"        | "United Kingdom" | "666229920"   |
      | "SAVE 20%"       | "M"  | "10"     | "Jan Kowalski" | "Oxford Street" | "London"     | "57-618"        | "United Kingdom" | "666229920"   |
      | "SAVE 20%"       | "L"  | "99"     | "Jan Kowalski" | "Oxford Street" | "London"     | "57-618"        | "United Kingdom" | "666229920"   |
      | "SAVE 20%"       | "XL" | "13"     | "Jan Kowalski" | "Oxford Street" | "London"     | "57-618"        | "United Kingdom" | "666229920"   |







