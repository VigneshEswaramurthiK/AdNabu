package com.adnabu.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.adnabu.base.BaseClass;

public class P04_ProductPage extends BaseClass {
	public P04_ProductPage() {
		PageFactory.initElements(driver,this);
	}
	public String productTxt;
	
	@FindAll({@FindBy(xpath="//button[@id='ProductSubmitButton-template--14768207495265__main']"),@FindBy(xpath="//button[@name='add']")})
	private static WebElement btnAddToCart;
	
	@FindAll({@FindBy(xpath="//a[@id='cart-notification-button']"),@FindBy(xpath="(//a[@href='/cart'])[2]")})
	private static WebElement btnGoToCart;
	
	@FindBy(xpath="//div[@class='product__title']")
	private static WebElement productTitleTxt;
	
	public static WebElement getBtnAddToCart() {
		return btnAddToCart;
	}

	public static WebElement getBtnGoToCart() {
		return btnGoToCart;
	}
	
//Add to cart
	public void addToCart() {
		visibilityOf(15, productTitleTxt);
		click(btnAddToCart);
	}
//Go to cart
	public void goToCart() {
		click(btnGoToCart);
	}
//Get selected product text
	public String getProductText() {
		productTxt = productTitleTxt.getText();
		return productTxt;
	}
//Assert Product match with searchTerm
	public void assertProductSearchMatch(String string) {
		String searchTerm = string;		
		String actualTitle = driver.getTitle();
        Assert.assertTrue(actualTitle.contains(searchTerm));
   }
}
