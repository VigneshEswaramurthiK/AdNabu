package com.adnabu.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import com.adnabu.base.BaseClass;

import org.testng.Assert;

public class P01_HomePage extends BaseClass {
	public P01_HomePage() {
		PageFactory.initElements(driver,this);
	}
	@FindBy(xpath="//h2[@class='rich-text__heading rte inline-richtext h1 scroll-trigger animate--slide-in']")
	private static WebElement txtHomePageAssert;
	
	@FindBys({@FindBy(id="HeaderMenu-catalog"), @FindBy(xpath= "//a[@id='HeaderMenu-catalog']")})
	private static WebElement btnCatalog;
	
	@FindAll({@FindBy(xpath="(//summary[@aria-label='Search'])[1]"),@FindBy(xpath="(//summary[@class='header__icon header__icon--search header__icon--summary link focus-inset modal__toggle'])[1]")})
	private static WebElement btnSearch;
	
	@FindAll({@FindBy(id="Search-In-Modal-1"),@FindBy(xpath="(//input[@class='search__input field__input'])[1]")})
	private static WebElement inputSearch;

	public static WebElement getBtnCatalog() {
		return btnCatalog;
	}

	public static WebElement getBtnSearch() {
		return btnSearch;
	}
	
	public static WebElement getInputSearch() {
		return inputSearch;
	}
	
	public static WebElement getTxtHomePageAssert() {
		return txtHomePageAssert;
	}
	
	//Assert Home page
	public void assertHomePage() {
		String homePageTxt = txtHomePageAssert.getText();
		Assert.assertEquals(homePageTxt, "Browse our latest products");
	}
	
	//Click catalog
	public void clickCatalog() {
		click(btnCatalog);
	}
	//click search button
	public void clickSearch() {
		click(btnSearch);
	}
	//Enter Search term
	public void enterSearchTerm(String string) {
		sendKeysEnter(inputSearch, string);
	}
}