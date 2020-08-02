Feature: Order coffee

  Order success depends on the availability of necessary ingredients.
  If stocks are sufficient, drinks are sent for preparation and the customer is fined accordingly.

  Scenario: All ingredients are available
    Given Coffee_beans are available
    When Vincent orders 2 Espresso
    Then he is fined 6€
    And 2 Espresso are sent for preparation for Vincent

  Scenario: Not all ingredients are available
    Given Milk is not available
    And Coffee_beans are available
    When Vincent orders 1 Latte
    Then the order is rejected
    And no drink is sent for preparation
