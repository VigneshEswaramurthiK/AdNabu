package com.adnabu.stepdefinition;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.adnabu.base.BaseClass;
import com.adnabu.pagemanager.PageObjectManager;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class TS02_Cart_Step extends BaseClass {
	
	PageObjectManager pom = new PageObjectManager();
	
	public String productNameString;
	public String cartCount;
	
	@Given("User navigates into AdNabuTestStore website")
	public void user_navigates_into_ad_nabu_test_store_website() {
		pom.getHomePage().assertHomePage();
	}
	@Given("User clicks on search link")
	public void user_clicks_on_search_link() {
		pom.getHomePage().clickSearch();
	}
	@When("User enters the required {string}")
	public void user_enters_the_required(String string) {
		pom.getHomePage().enterSearchTerm(string);
		
	}
	@When("User clicks on required product")
	public void user_clicks_on_required_product() {
		pom.getSearchPage().selectProduct();
	}
	@When("User clicks on Add to cart button")
	public void user_clicks_on_add_to_cart_button() {
		pom.getProductPage().addToCart();
		
		WebElement btnAddToCart = findByXpath("//a[@id='cart-notification-button']");
		visibilityOf(30, btnAddToCart);
		WebElement productName = findByXpath("//h3[@class='cart-notification-product__name h4']");
		productNameString = productName.getText();
	}
	@Then("User validates item added to the cart")
	public void user_validates_item_added_to_the_cart() {
		pom.getProductPage().goToCart();

		implicitWait(15);
		String cartProductTxt = (findByXpath("//a[@class='cart-item__name h4 break']")).getText();
		Assert.assertEquals(cartProductTxt, productNameString);
	}
	@When("User clicks on Remove button")
	public void user_clicks_on_remove_button() throws IOException {
		pom.getCartPage().clickRemoveButton();
	}
	@Then("User validates item removed from the cart")
	public void user_validates_item_removed_from_the_cart() throws IOException {
		pom.getCartPage().assertProductRemovedFromCart();
	}
}