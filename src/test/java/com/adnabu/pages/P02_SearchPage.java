package com.adnabu.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.adnabu.base.BaseClass;

import org.testng.Assert;

public class P02_SearchPage extends BaseClass {
	private int totalBefore;
	private int totalAfter;
	
	public P02_SearchPage() {
		PageFactory.initElements(driver,this);
	}
	
	@FindAll({@FindBy(xpath="//h1[text()='Search results']"), @FindBy(xpath= "//h1[@class='h2 center']")})
	private static WebElement txtSearchPage;
	
	@FindBy(xpath="//span[@class='facets__summary-label']")
	private static WebElement btnAvailabilityFilter;
	
	@FindAll({@FindBy(id="Filter-filter.v.availability-1"),@FindBy(xpath="(//input[@value='1'])[1]")})
	private static WebElement cbxInStock;
	
	@FindAll({@FindBy(id="Filter-filter.v.availability-2"),@FindBy(xpath="(//input[@value='0'])[1]")})
	private static WebElement cbxOutOfStock;
	
	@FindAll({@FindBy(xpath="//a[@class='facets__reset link underlined-link']"),@FindBy(xpath="(//a[@href='/collections/all'])[3]")})
	private static WebElement btnReset;
	
	@FindAll({@FindBy(xpath="(//div[@class='card__badge bottom left'])[1]"),@FindBy(xpath="")})
	private static WebElement badgeOutOfStock;
	
	@FindAll({@FindBy(id="ProductGridContainer"),@FindBy(xpath="//div[@class='product-grid-container scroll-trigger animate--slide-in']")})
	private static WebElement productContainer;
	
	@FindBy(xpath="//div[@class='card-wrapper product-card-wrapper underline-links-hover']")
	private static List<WebElement> productList;
	
	@FindBy(xpath="(//span[@class='active-facets__button-inner button button--tertiary'])[1]")
	private static WebElement badgeAvailability;
	
	@FindBy(xpath="(//li[@class='grid__item scroll-trigger animate--slide-in'])[1]")
	private static WebElement productSelect;
	
	public static WebElement getProductSelect() {
		return productSelect;
	}

	public static WebElement getTxtSearchPage() {
		return txtSearchPage;
	}

	public static WebElement getBtnAvailabilityFilter() {
		return btnAvailabilityFilter;
	}

	public static WebElement getCbxInStock() {
		return cbxInStock;
	}

	public static WebElement getCbxOutOfStock() {
		return cbxOutOfStock;
	}

	public static WebElement getBtnReset() {
		return btnReset;
	}
	
	public static WebElement getBadgeOutOfStock() {
		return badgeOutOfStock;
	}

	public static WebElement getProductContainer() {
		return productContainer;
	}

	public static List<WebElement> getProductList() {
		return productList;
	}
	
	public static WebElement getBadgeAvailability() {
		return badgeAvailability;
	}

//Assert search page
	public void assertSearchPage() {
		String txtSearchResult = txtSearchPage.getText();
		Assert.assertEquals(txtSearchResult, "Search results");
	}
//Click Availability filter
	public void clickAvailabilityFilter() {
		click(btnAvailabilityFilter);
	}
//Click In Stock checkbox
	public void clickInStockCheckBox() {
		clickJs(cbxInStock);
	}
//Click Out Of Stock checkbox
	public void clickOutOfStockCheckBox() {
		clickJs(cbxOutOfStock);
	}
//Click Reset button
	public void clickResetFilter() throws InterruptedException {
		visibilityOf(5, badgeAvailability);
		totalBefore = productList.size();
		System.out.println("Total number of products before Reset: "+ totalBefore);
		
		click(btnReset);
		
		Thread.sleep(3000);
	}
//Assert all products is out of stock
	public void assertAllProductsOutOfStock() {
		visibilityOf(5, badgeAvailability);
		String badgeTxt = badgeOutOfStock.getText();
		
		Assert.assertEquals(badgeTxt, "Sold out");	
	}
//Assert No product is out of stock
	public void assertNoProductOutOfStock() {
		visibilityOf(5, badgeAvailability);
		String badgeTxt = badgeOutOfStock.getText();
			
		Assert.assertNotEquals(badgeTxt, "Sold out");	
	}
//user_validates_that_filter_is_reset
	public void assertResetFilter() {
	totalAfter = productList.size();
	System.out.println("Total number of products after Reset: "+ totalAfter);
	Assert.assertNotEquals(totalAfter, totalBefore);
	}
//Select product
	public void selectProduct() {
		click(productSelect);
	}
}