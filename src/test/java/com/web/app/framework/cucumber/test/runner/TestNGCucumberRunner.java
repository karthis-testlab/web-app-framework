package com.web.app.framework.cucumber.test.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		          features = "src/test/java/com/web/app/framework/features/SaueDemo.feature",
		          glue = "com.web.app.framework.step.definitions",
		          dryRun = false,
		          monochrome = true,
		          plugin = {
				        	"pretty",
				        	"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
				        	"rerun:src/test/resources/retry.txt"
				           }
		        )
public class TestNGCucumberRunner extends AbstractTestNGCucumberTests {

}
