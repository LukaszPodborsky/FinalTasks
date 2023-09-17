Feature: User edit info

  Scenario Outline: User can add new address
    Given I'm on mystore main page
    When I log in using "jankowalski123@test.com" email and "haslo" password
    And I verify user
    And I go to addresses
    And I create new address
    And I create new address using <keywordAlias> alias, <keywordAddress> address, <keywordCity> city, <keywordZipCode> zipCode, <keywordPhone> phone
    Then I can see my new address
    And There is successful message alert "Address successfully added!"
    And I verify created address through <expectedAlias> expectedAlias, <expectedAddress> expectedAddress, <expectedCity> expectedCity, <expectedZipCode> expectedZipCode, <expectedPhone> expectedPhone
    And I remove the address
    And I verify if the address is gone by receiving "Address successfully deleted!" alert
    And I close browser

    Examples:
      | keywordAlias | keywordAddress  | keywordCity | keywordZipCode | keywordPhone | expectedAlias | expectedAddress | expectedCity | expectedZipCode | expectedPhone |
      | "Janek"      | "Oxford Street" | "London"    | "57-618"       | "666229920"  | "Janek"       | "Oxford Street" | "London"     | "57-618"        | "666229920"   |


