package com.adnabu.pagemanager;

import com.adnabu.pages.P01_HomePage;
import com.adnabu.pages.P02_SearchPage;
import com.adnabu.pages.P03_CartPage;
import com.adnabu.pages.P04_ProductPage;

public class PageObjectManager {
	private P01_HomePage homePage;
	private P02_SearchPage searchPage;
	private P03_CartPage cartPage;
	private P04_ProductPage productPage;
	
	public P01_HomePage getHomePage() {
		return (homePage==null)? homePage = new P01_HomePage():homePage;
	}
	
	public P02_SearchPage getSearchPage() {
		return (searchPage==null)? searchPage = new P02_SearchPage():searchPage;
	}
	
	public P03_CartPage getCartPage() {
		return (cartPage==null)? cartPage = new P03_CartPage():cartPage;
	}
	
	public P04_ProductPage getProductPage() {
		return (productPage==null)? productPage = new P04_ProductPage():productPage;
	}
}
