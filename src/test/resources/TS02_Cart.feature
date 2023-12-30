@Regression @Cart @TS02
Feature: Verifying the cart functionalities of "AdNabuTestStore" web application

  @TC04
  Scenario Outline: Verify add item to cart
    Given User navigates into AdNabuTestStore website
    And User clicks on search link
    When User enters the required "<searchTerm>"
    And User clicks on required product
    And User clicks on Add to cart button
    Then User validates item added to the cart

    Examples: 
      | searchTerm |
      | gold       |
      | 18K        |
      | shoe       |

  @TC05
  Scenario Outline: Verify remove item from the cart
    Given User navigates into AdNabuTestStore website
    And User clicks on search link
    When User enters the required "<searchTerm>"
    And User clicks on required product
    And User clicks on Add to cart button
    Then User validates item added to the cart
    When User clicks on Remove button
    Then User validates item removed from the cart

    Examples: 
      | searchTerm |
      | gold       |
      | 18K        |
      | shoe       |