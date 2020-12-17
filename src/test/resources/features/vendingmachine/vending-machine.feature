Feature: Vending Machine regression tests

  @Integration
  Scenario: Getting Vending Machine status
    When getting vending machine response
    Then response status "200"
    And the balance should be "0"
    And the change should be "0"
    And the selected product code should be "null"
    And selectable products size should be "0"
    And available products size should be "3"
    And available products should contain product code "coke"
    And available products should contain product code "pepsi"
    And available products should contain product code "soda"

  @Integration
  Scenario: Inserting coins and selecting product
    When getting vending machine response
    Then the balance should be "0"

    # add 1 pence
    When user inserts coin "1"
    Then the balance should be "1"
    And selectable products size should be "0"

    # add 5 pence
    When user inserts coin "5"
    Then the balance should be "6"
    And selectable products size should be "0"

    # add 10 pence
    When user inserts coin "10"
    Then the balance should be "16"
    And selectable products size should be "0"

    # add 10 pence
    When user inserts coin "10"
    Then the balance should be "26"
    And selectable products size should be "1"
    And selectable products should contain product code "coke"

    # add 10 pence
    When user inserts coin "10"
    Then the balance should be "36"
    And selectable products size should be "2"
    And selectable products should contain product code "coke"
    And selectable products should contain product code "pepsi"

    # add 25 pence
    When user inserts coin "25"
    Then the balance should be "61"
    And selectable products size should be "3"
    And selectable products should contain product code "coke"
    And selectable products should contain product code "pepsi"
    And selectable products should contain product code "soda"

    # user selects pepsi
    When user selects product "pepsi"
    Then the selected product code should be "pepsi"
    And the change should be "26"
    And the balance should be "0"
    And selectable products size should be "0"

  @Integration
  Scenario: Inserting coins and cancelling operation
    When getting vending machine response
    Then the balance should be "0"

    # add 25 pence
    When user inserts coin "25"
    Then the balance should be "25"
    And selectable products size should be "1"
    And selectable products should contain product code "coke"

    # user cancelling the request
    When user cancelling the request
    Then the change should be "25"
    And the balance should be "0"
    And selectable products size should be "0"
    And the selected product code should be "null"

  @Integration
  Scenario: Vending machine supplier reset operation
    When getting vending machine response
    Then the balance should be "0"

    # add 25 pence
    When user inserts coin "25"
    Then the balance should be "25"
    And selectable products size should be "1"
    And selectable products should contain product code "coke"

    # user cancelling the request
    When supplier resets operation
    Then the change should be "0"
    And the balance should be "0"
    And selectable products size should be "0"
    And the selected product code should be "null"

  @Integration
  Scenario: Inserting unsupported coin
    When getting vending machine response
    Then the balance should be "0"

    # add unsupported coin 20 pence
    When user inserts coin "20"
    Then response status "400"
    And error message "Wrong coin value. Acceptable coins are: 1, 5, 10, 25"

  @Integration
  Scenario: Selecting unavailable product code
    When getting vending machine response
    Then the balance should be "0"

    # add 25 pence
    When user inserts coin "25"
    Then the balance should be "25"
    And selectable products size should be "1"
    And selectable products should contain product code "coke"

    # user selects unavailable product pepsi
    When user selects product "pepsi"
    Then response status "400"
    And error message "Product 'pepsi' could not be selected. Acceptable product codes are: coke"

    # user selects unavailable product soda
    When user selects product "soda"
    Then response status "400"
    And error message "Product 'soda' could not be selected. Acceptable product codes are: coke"

    # user selects unsupported product milk
    When user selects product "milk"
    Then response status "400"
    And error message "Wrong product value 'milk'. Acceptable product codes are: coke"


