@Regression @Search @TS03
Feature: Verifying the Product search functionalities of "AdNabuTestStore" web application

  @TC06
  Scenario Outline: Verify search option
    Given User navigates into AdNabuTestStore website
    And User clicks on search link
    When User enters the required "<searchTerm>"
    Then User validates product search match "<titleAssert>"

    Examples: 
      | searchTerm | titleAssert |
      | shoe       | shoe        |
      | gold       | gold        |
      | 18k        | 18k         |