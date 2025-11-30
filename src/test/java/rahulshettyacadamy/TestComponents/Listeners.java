package rahulshettyacadamy.TestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import rahulshettyacadamy.resources.ExtentReporterNG;

public class Listeners extends BaseTest implements ITestListener {
	ExtentTest test;
	ExtentReports extent = ExtentReporterNG.getReportObject();
	ThreadLocal<ExtentTest> extentTest=new ThreadLocal<ExtentTest>();
	@Override
	public void onTestStart(ITestResult result) {
		test = extent.createTest(result.getMethod().getMethodName());//gettitle of method name
		extentTest.set(test);
		//pass test in thread local so while parallel running no other method name will get display
		//this will pass thread id of test
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		extentTest.get().log(Status.PASS, "Test Pass");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		extentTest.get().fail(result.getThrowable());// to get Why fail result

		// below test will give us driver
		// first result getTestclass where method present
		// real class means @Test method
		// from field get driver what is using
//as field are associated at class level not method level so that use this
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e) {// any exception catch here
			e.printStackTrace();
		}

		// get ScrrenShot from baseTest
		String FilePath = null;
		try {
			FilePath = getScreenShot(result.getMethod().getMethodName(), driver);
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Attached SS to report
		extentTest.get().addScreenCaptureFromPath(FilePath, result.getMethod().getMethodName());
		// filepath,which test get fail
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		test = extent.createTest(result.getMethod().getMethodName());
	}

	@Override
	public void onFinish(ITestContext context) {
		extent.flush();
	}

}
