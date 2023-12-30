package com.adnabu.stepdefinition;

import com.adnabu.base.BaseClass;

import io.cucumber.java.After;
import io.cucumber.java.Before;

public class HooksClass extends BaseClass {
	@Before
	public void beforeScene() {
		launchBrowser("chrome");
		goToUrl("https://adnabu-arjun.myshopify.com/");
		maximizeWindow();
	}
	
	@After
	public void afterScene() {
		quitWindows();
	}
}