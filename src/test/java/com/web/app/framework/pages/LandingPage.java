package com.web.app.framework.pages;

import static com.web.app.framework.utlis.properties.objects.ObjectRepositoriesController.*;
import com.web.app.selenium.api.design.Locators;
import com.web.app.testng.api.TestNGHooks;

public class LandingPage extends TestNGHooks {
	
	public LandingPage clickOnAppLauncher() {
	    click(locateElement(Locators.XPATH, getDomValue("LandingPage", "sf.lap.applancher.menu")));
		return this;
	}
	
	public LandingPage typeSearchKeyword(String keyword) {
		append(locateElement(Locators.XPATH, getDomValue("LandingPage", "sf.lap.search.field")), keyword);
		return this;
	}
	
	public OpportunitiesPage clickOnTheOpportunityMenu() {
		click(locateElement(Locators.LINK_TEXT, getDomValue("LandingPage", "sf.lap.search.list.menu")));
		return new OpportunitiesPage();
	}

}
