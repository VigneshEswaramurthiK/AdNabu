@Regression @Filter @TS01
Feature: Verifying the Product Filter functionalities of "AdNabuTestStore" web application

  @TC01
  Scenario Outline: Verify Availability - In stock filter in catalog page
    Given User navigates into AdNabuTestStore website
    And User clicks on Catalog link
    When User clicks on In stock - Availability filter
    Then User validates No products is Out of stock

  @TC02
  Scenario Outline: Verify Availability - Out of stock filter in catalog page
    Given User navigates into AdNabuTestStore website
    And User clicks on Catalog link
    When User clicks on Out of stock - Availability filter
    Then User validates all products is Out of stock

  @TC03
  Scenario Outline: Verify Reset filter option in catalog page
    Given User navigates into AdNabuTestStore website
    And User clicks on Catalog link
    When User clicks on Out of stock - Availability filter
    Then User validates all products is Out of stock
    When User clicks on Reset button
    Then User validates that filter is reset