package com.web.app.framework.pages;

import static com.web.app.framework.utlis.properties.objects.ObjectRepositoriesController.*;
import com.web.app.testng.api.TestNGHooks;

public class LoginPage extends TestNGHooks {	
	
	public LoginPage enterUsername(String userName) {
		append(locateElement(getDomValue("LoginPage", "sf.lp.username.field")), userName);
		return this;
	}
	
	public LoginPage enterPassword(String passwpord) {
		append(locateElement(getDomValue("LoginPage", "sf.lp.password.field")), passwpord);
		return this;
	}
	
	public LandingPage clickLoginButton() {
		click(locateElement(getDomValue("LoginPage", "sf.lp.login.button")));
		return new LandingPage();
	}	

}