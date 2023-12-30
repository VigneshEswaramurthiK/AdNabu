package com.adnabu.stepdefinition;

import com.adnabu.base.BaseClass;
import com.adnabu.pagemanager.PageObjectManager;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class TS01_ProductFilter_Step extends BaseClass {
	
	PageObjectManager pom = new PageObjectManager();
	
	@Given("User clicks on Catalog link")
	public void user_clicks_on_catalog_link() {
		pom.getHomePage().clickCatalog();
	}
	
	@When("User clicks on In stock - Availability filter")
	public void user_clicks_on_in_stock_availability_filter() {
		pom.getSearchPage().clickAvailabilityFilter();
		pom.getSearchPage().clickInStockCheckBox();
	}
	
	@Then("User validates No products is Out of stock")
	public void user_validates_no_products_is_out_of_stock() {
		pom.getSearchPage().assertNoProductOutOfStock();
	}
	
	@When("User clicks on Out of stock - Availability filter")
	public void user_clicks_on_out_of_stock_availability_filter() {
		pom.getSearchPage().clickAvailabilityFilter();
		pom.getSearchPage().clickOutOfStockCheckBox();
	}
	
	@Then("User validates all products is Out of stock")
	public void user_validates_all_products_is_out_of_stock() {
		pom.getSearchPage().assertAllProductsOutOfStock();
	}
	
	@When("User clicks on Reset button")
	public void user_clicks_on_reset_button() throws InterruptedException {
		pom.getSearchPage().clickResetFilter();
	}
	@Then("User validates that filter is reset")
	public void user_validates_that_filter_is_reset() {
		pom.getSearchPage().assertResetFilter();
	}
}