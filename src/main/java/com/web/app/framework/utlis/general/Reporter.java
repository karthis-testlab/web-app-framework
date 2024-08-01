package com.web.app.framework.utlis.general;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.aeonbits.owner.ConfigFactory;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.web.app.framework.utlis.properties.ConfigPropertiesHandler;

public class Reporter {
	
	static ConfigPropertiesHandler config = ConfigFactory.create(ConfigPropertiesHandler.class);
	
	private static ExtentReports extent;
	private static final ThreadLocal<ExtentTest> parentTest = new ThreadLocal<ExtentTest>();
	private static final ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>();
	private static final ThreadLocal<String> testName = new ThreadLocal<String>();	
	
	public static void startReport() {
		String folderName = System.getProperty("user.dir")+"/reports/"+new SimpleDateFormat(config.getReportFolderNamePattern()).format(new Date());
		File folder = new File(folderName);
		if(!folder.exists()) {
			folder.mkdirs();
		}
		ExtentSparkReporter htmlReporter = new ExtentSparkReporter(folderName + "/" + config.getReportFileName());
		
		htmlReporter.config().setTheme(Theme.STANDARD);
		htmlReporter.config().setDocumentTitle("Salesforce");
		htmlReporter.config().setEncoding("utf-8");
		htmlReporter.config().setReportName("Salesforce");
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		
	}
	
	public static void main(String[] args) {
		startReport();
	}

}