package com.web.app.framework.utlis.general;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.aeonbits.owner.ConfigFactory;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.model.Media;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.aventstack.extentreports.reporter.configuration.ViewName;
import com.web.app.framework.utlis.properties.ConfigPropertiesHandler;

public class Reporter {

	static ConfigPropertiesHandler config = ConfigFactory.create(ConfigPropertiesHandler.class);

	private static ExtentReports extent;
	private static final ThreadLocal<ExtentTest> parentTest = new ThreadLocal<ExtentTest>();
	private static final ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>();
	private static final ThreadLocal<String> testName = new ThreadLocal<String>();

	public static void startReport() {
		String folderName = System.getProperty("user.dir") + "/reports/"
				+ new SimpleDateFormat(config.getReportFolderNamePattern()).format(new Date());
		File folder = new File(folderName);
		if (!folder.exists()) {
			folder.mkdirs();
		}
		ExtentSparkReporter htmlReporter = new ExtentSparkReporter(folderName + "/" + config.getReportFileName());
		htmlReporter.viewConfigurer().viewOrder().as(new ViewName[] { 
				ViewName.DASHBOARD, 
				ViewName.TEST,
				ViewName.AUTHOR, 
				ViewName.DEVICE, 
				ViewName.EXCEPTION, 
				ViewName.LOG })
		.apply();
		htmlReporter.config().setTheme(Theme.STANDARD);
		htmlReporter.config().setDocumentTitle("Salesforce");
		htmlReporter.config().setEncoding("utf-8");
		htmlReporter.config().setReportName("Salesforce");
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);

	}

	public static void startTestCase(String testcaseName, String testcaseDescription) {
		ExtentTest parent = extent.createTest(testcaseName, testcaseDescription);
		parentTest.set(parent);
	}

	public static void setNode(String nodeName) {
		testName.set(nodeName);
		ExtentTest child = parentTest.get().createNode(testName.get());
		test.set(child);
	}

	public static void pass(String message) {
		test.get().pass(MarkupHelper.createLabel("[PASS]: "+message, ExtentColor.GREEN));
	}

	public static void fail(String message, String image) {
		test.get().fail(MarkupHelper.createLabel("[FAIL]: "+message, ExtentColor.RED));		
		test.get().fail(attachSnapshot(image));
	}

	public static void warning() {
		test.get().warning(MarkupHelper.createLabel("WARNING!!", ExtentColor.AMBER));
	}

	public static void info() {
		test.get().info(MarkupHelper.createLabel("INFO!!", ExtentColor.BLUE));
	}

	public static void endReport() {
		extent.flush();
	}

	private static Media attachSnapshot(String image) {
		Media media = MediaEntityBuilder.createScreenCaptureFromBase64String(image).build();
		return media;
	}

}