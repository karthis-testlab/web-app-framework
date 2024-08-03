package com.web.app.framework.step.definitions;

import org.testng.Assert;

import com.web.app.selenium.api.base.SeleniumBase;
import com.web.app.selenium.api.base.SeleniumBaseCucumber;
import com.web.app.selenium.api.design.Locators;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SauceDemoSteps extends SeleniumBaseCucumber {
	
	@Given("User want to purchase the sauce lab merchandise product on the offical cart site")
	public void user_want_to_purchase_the_sauce_lab_merchandise_product_on_the_offical_cart_site() {
		startApp("https://www.saucedemo.com/");
	}
	@When("Regitered user able to login with valid user credentials {string} and {string}")
	public void regitered_user_able_to_login_with_valid_user_credentials_and(String string, String string2) {
		append(locateElement("user-name"), "standard_user");
		append(locateElement("password"), "secret_sauc");
		click(locateElement("login-button"));
	}
	@Then("User able to the see {string} the product they wants")
	public void user_able_to_the_see_the_product_they_wants(String string) {
		Assert.assertTrue(verifyExactText(locateElement(Locators.XPATH, "//h3[@data-test='error']"), "Epic sadface: Username and password do not match any user in this service"));
		quit();
	}

}