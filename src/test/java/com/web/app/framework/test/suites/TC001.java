package com.web.app.framework.test.suites;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.web.app.selenium.api.design.Locators;
import com.web.app.testng.api.TestNGHooks;

public class TC001 extends TestNGHooks {
	
	@BeforeTest
	public void testRelatedDetails() {
		testcaseName = "TC001";
		testcaseDescription = "Validate the login functionality of the sauce demo site.";
	}
	
	@Test
	public void validateUserShouldBeReDirectedToTheInventoryPage() {
		startApp("https://www.saucedemo.com/");
		append(locateElement("user-name"), "standard_user");
		append(locateElement("password"), "secret_sauce");
		click(locateElement("login-button"));
		Assert.assertTrue(verifyUrl("/inventory.html"));
		quit();
	}
	
	@Test
	public void validateUserShouldSeeTheErrorMessage() {
		startApp("https://www.saucedemo.com/");
		append(locateElement("user-name"), "standard_user");
		append(locateElement("password"), "secret_sauc");
		click(locateElement("login-button"));
		Assert.assertTrue(verifyExactText(locateElement(Locators.XPATH, "//h3[@data-test='error']"), "Epic sadface: Username and password do not match any user in this service"));
		quit();
	}

}