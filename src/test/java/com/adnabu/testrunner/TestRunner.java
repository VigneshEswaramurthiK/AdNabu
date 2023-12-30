package com.adnabu.testrunner;

import org.junit.AfterClass;
import org.junit.runner.RunWith;

import com.adnabu.base.BaseClass;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(dryRun = false, tags = "@TS01", stepNotifications = true, plugin = {"pretty","json:target//index.json"}, features = "src\\test\\resources", glue = "com.adnabu.stepdefinition")
public class TestRunner extends BaseClass {
	@AfterClass
	public static void afterClass() {
		Reporting.generateJVMReport(getProjectPath () + "\\target\\index.json");
	}
}