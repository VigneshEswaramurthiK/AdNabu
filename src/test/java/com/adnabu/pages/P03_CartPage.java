package com.adnabu.pages;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.adnabu.base.BaseClass;
import com.adnabu.pagemanager.PageObjectManager;

public class P03_CartPage extends BaseClass {

	public P03_CartPage() {
		PageFactory.initElements(driver,this);
	}
	
	PageObjectManager pom = new PageObjectManager();
	public String productNameString = pom.getProductPage().productTxt;
	public String cartCountTxt;
	
	@FindBy(xpath="//a[@class='cart-item__name h4 break']")
	private WebElement productTitle;
	
	@FindBy(xpath="//a[@class='button button--tertiary']")
	private WebElement btnRemove;
	
	@FindBy(xpath="(//span[@class='visually-hidden'])[3]")
	private WebElement cartCount;
	
	@FindAll({@FindBy(xpath="//h1[@class='cart__empty-text']"),@FindBy(xpath="//h1[text()='Your cart is empty']")})
	private WebElement cartEmptyMsg;
	
	@FindAll({@FindBy(xpath="(//a[contains(text(),'Continue shopping')])[2]"),@FindBy(xpath="//a[@class='button']")})
	private WebElement btnContinueShopping;

	public WebElement getProductTitle() {
		return productTitle;
	}

	public WebElement getBtnRemove() {
		return btnRemove;
	}

	public WebElement getCartCount() {
		return cartCount;
	}

	public WebElement getCartEmptyMsg() {
		return cartEmptyMsg;
	}

	public WebElement getBtnContinueShopping() {
		return btnContinueShopping;
	}

//Assert product added to cart
	public void assertProductAddedToCart() {
		
		visibilityOf(15,productTitle);
			
		String cartProductTxt = productTitle.getText();
		Assert.assertEquals(cartProductTxt, productNameString);
		System.out.println("Product added to cart successfully");
		
		cartCountTxt = cartCount.getText();
		System.out.println("Total items in the cart is "+ cartCountTxt);
	}
//Click Remove button
	public void clickRemoveButton() throws IOException {
		takesScreenshotPage("AddCart");
		click(btnRemove);
	}
//Assert product removed from cart
	public void assertProductRemovedFromCart() throws IOException {
		visibilityOf(15, cartEmptyMsg);
		String removeTxt = btnContinueShopping.getText();
		System.out.println(removeTxt);
		
		implicitWait(5);
		takesScreenshotPage("Removed");
		
		Boolean assertRemoval = removeTxt.contains("Continue shopping");		
		Assert.assertEquals(assertRemoval, true);
	}
}
