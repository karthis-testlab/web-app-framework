package com.web.app.framework.utlis.properties.objects;

import org.aeonbits.owner.Config;

@Config.Sources(value = {"file:${user.dir}/src/test/resources/ObjectsRepositories/LandingPage.properties"})
public interface LandingPageElements extends Config {
	
	@Key(value = "sf.lap.applancher.menu")
	String getApplauncherMenuObject();

}
