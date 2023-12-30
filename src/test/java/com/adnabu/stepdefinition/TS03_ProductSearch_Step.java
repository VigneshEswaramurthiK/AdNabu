package com.adnabu.stepdefinition;

import com.adnabu.base.BaseClass;
import com.adnabu.pagemanager.PageObjectManager;

import io.cucumber.java.en.Then;

public class TS03_ProductSearch_Step extends BaseClass {
	PageObjectManager pom = new PageObjectManager();
	
	@Then("User validates product search match {string}")
	public void user_validates_product_search_match(String string) {
		pom.getProductPage().assertProductSearchMatch(string);
   }
}