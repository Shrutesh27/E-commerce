package rahulshettyacadamy.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {
	public static ExtentReports getReportObject()
	{
		
			String path = System.getProperty("user.dir") + "\\reports\\index.html";
			// ExtentSparkReporter this will expect path where report should be created
			ExtentSparkReporter reporter = new ExtentSparkReporter(path);
			// so we can now set config as set report name set title
			reporter.config().setReportName("Web Automation Reports");
			reporter.config().setDocumentTitle("test Results");

			// ExtentReport class is imp to driver reporting execution
			ExtentReports extent = new ExtentReports();
			// attach report to main class
			extent.attachReporter(reporter);
			extent.setSystemInfo("Tester", "Shrutesh");
			return extent;
			//return this variable to can use anywhere
		
	}
	

}
