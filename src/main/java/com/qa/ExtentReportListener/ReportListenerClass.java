package com.qa.ExtentReportListener;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.Date;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.crm.qa.util.TestUtil;

public class ReportListenerClass implements ITestListener , ISuiteListener{
	
	private static final String OUTPUT_FOLDER = "./reports/";
	private static final String FILE_NAME = "TestExecutionReport.html";

	private static ExtentReports extent = init();
	public static ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>();
	private static ExtentReports extentReports;
	

	private static ExtentReports init() {

		Path path = Paths.get(OUTPUT_FOLDER);
		// if directory exists?
		if (!Files.exists(path)) {
			try {
				Files.createDirectories(path);
			} catch (IOException e) {
				// fail to create directory
				e.printStackTrace();
			}
		}
		
		extentReports = new ExtentReports();
		ExtentSparkReporter reporter = new ExtentSparkReporter(OUTPUT_FOLDER + FILE_NAME);
		reporter.config().setReportName("Free CRM project");
		extentReports.attachReporter(reporter);
		extentReports.setSystemInfo("System", "MAC");
		extentReports.setSystemInfo("Author", "Swati Agarwal");
		extentReports.setSystemInfo("Build#", "1.1");
		extentReports.setSystemInfo("Team", "QA Team");
		extentReports.setSystemInfo("Customer Name", "SA");

		//extentReports.setSystemInfo("ENV NAME", System.getProperty("env"));

		return extentReports;
	}

	public void onStart(ISuite suite) {
		System.out.println("Test Suite started!");
		
	}

	public void onFinish(ISuite suite) {
		System.out.println(("Test Suite is ending!"));
		extent.flush();
		test.remove();
	}

	public void onTestStart(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		String qualifiedName = result.getMethod().getQualifiedName();
		int last = qualifiedName.lastIndexOf(".");
		int mid = qualifiedName.substring(0, last).lastIndexOf(".");
		String className = qualifiedName.substring(mid + 1, last);

		System.out.println(methodName + " started!");
		ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName(),
				result.getMethod().getDescription());

		extentTest.assignCategory(result.getTestContext().getSuite().getName());
		extentTest.assignCategory(className);
		test.set(extentTest);
		test.get().getModel().setStartTime(getTime(result.getStartMillis()));
		
		
	}

	public void onTestSuccess(ITestResult result) {
		System.out.println((result.getMethod().getMethodName() + " passed!"));
		test.get().pass("Test passed");
		//test.get().pass(result.getThrowable(), 
	//	MediaEntityBuilder.createScreenCaptureFromPath(getScreenshot()).build());
		//test.get().getModel().setEndTime(getTime(result.getEndMillis()));
		
	}

	public void onTestFailure(ITestResult result) {
		//System.out.println((result.getMethod().getMethodName() + " failed!"));
		test.get().fail("TestFailed");
		try {
			TestUtil.takeScreenshotAtEndOfTest();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	//	result.getMethod().getMethodName();

	//test.get().fail(result.getThrowable(), MediaEntityBuilder(TestUtil.takeScreenshotAtEndOfTest()).build());
		//test.get().getModel().setEndTime(getTime(result.getEndMillis()));
		
	}

	public void onTestSkipped(ITestResult result) {
		System.out.println((result.getMethod().getMethodName() + " skipped!"));
		test.get().skip("Test Skkiped");
	//	result.getMethod().getMethodName();
		//test.get().skip(result.getThrowable(),
			//MediaEntityBuilder.createScreenCaptureFromPath(TestUtil, methodName).build();
			//createScreenCaptureFromPath(TestUtil.takeScreenshotAtEndOfTest()).build());
		//test.get().getModel().setEndTime(getTime(result.getEndMillis())));
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		System.out.println(("onTestFailedButWithinSuccessPercentage for " + result.getMethod().getMethodName()));
		
	}
	private Date getTime(long millis) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(millis);
		return calendar.getTime();
	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

}
