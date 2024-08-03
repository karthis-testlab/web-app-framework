package com.web.app.framework.test.suites;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.web.app.framework.utils.data.handler.DataHandler;

public class TC002 {
	
	@DataProvider
	public String[][] getCsvData() {
		return DataHandler.readDataFromCsv("TestData");
	}
	
	@Test(dataProvider = "getCsvData")
	public void testCsvData(String fName, String lName, String zCode) {
		System.out.println(fName +" | "+lName+" | "+zCode+" |");
	}

}
