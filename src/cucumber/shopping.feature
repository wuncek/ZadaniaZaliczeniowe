Feature: shopping steps

  Scenario: shopping on https://mystore-testlab.coderslab.pl/index.php
    Given user is log in
    When user buy some pieces of Hummingbird Printed Sweater in M size
    And buy them Pay by Check
    Then the transaction is done and user has a screenshot
