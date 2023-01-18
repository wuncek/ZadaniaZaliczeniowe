Feature: new address

  Scenario Outline: adding new address to login user on https://mystore-testlab.coderslab.pl/index.php page
    Given user is logged in
    When user click on Adresses button and Create new adress
    And user fill the form with "<alias>", "<address>", "<city>", "<postalCode>", "<country>", "<phone>"
    Then user cliks save button and new address is created
    And user see new address with new data "<alias>", "<address>", "<city>", "<postalCode>", "<country>", "<phone>"


Examples:
    |alias|address|city|postalCode|country|phone|
    |Firmowy|Aleje Marszałkowskie|Warszawa|67-000|United Kingdom|23456789|
