package rahulshettyacadamy.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacadamy.PageObjects.LandingPage;

public class BaseTest {

	public WebDriver driver;
	public LandingPage Login;

	public WebDriver initializeDriver() throws IOException {
		// Properties class
		Properties prop = new Properties();
		// to send properties file need to first convert it into InStream
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")
				+ "\\src\\main\\java\\rahulshettyacadamy\\resources\\GlobalData.properties");
		// then use load to load file
		prop.load(fis);
		//if we want to pass browser from cmd not from global property
		String broswername = 	System.getProperty("browser")!=null ? 
				System.getProperty("browser"):prop.getProperty("browser");
	//if null then used 1 statement which is after ternary operator or 2nd take from property file
		if (broswername.contains("chrome")) {
			ChromeOptions options=new ChromeOptions();
	
			if(broswername.contains("headless"))
			{
				options.addArguments("headless");
			}
			
			driver = new ChromeDriver(options);
			//driver.manage().window().setSize(new Dimension(1440,900));

		} else if (broswername.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		}
		else if (broswername.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;
		// rertun driver from this method and used in launchdriver()

	}
	
	public String getScreenShot(String testCaseName, WebDriver driver) throws IOException
	{
		TakesScreenshot ts=(TakesScreenshot)driver;
		File src= ts.getScreenshotAs(OutputType.FILE);
		File file=new File(System.getProperty("user.dir")+"\\reports\\"+testCaseName+".png");
		FileUtils.copyFile(src, file);//we store in respective path
		return System.getProperty("user.dir")+"\\reports\\"+testCaseName+".png"; 
		//then return path
	}
	
	
	public List<HashMap<String, String>> getJsonDataToMap(String Filepath) throws IOException
	{
		//read json to string as readfiletostring depreciated use StandarCharsets.UTF_8
		//StandarCharsets.UTF_8 how to convert into string
		//rather than passing hardcore path get from Test
	String jsoncontent=	FileUtils.readFileToString(new File(Filepath),StandardCharsets.UTF_8);
	
	//convert string to hash map
	//need jacksondata Bind add from maven repo
	ObjectMapper mapper=new ObjectMapper();
	//mapper.readvalue read string and convert to hashmap
	//step is like:-
	//create 2 hashmap convert into json then create list and give back
	List<HashMap<String, String>>data=mapper.readValue(jsoncontent, new TypeReference<List<HashMap<String,String>>>() {});
	return data;
		
	}

	@BeforeMethod(alwaysRun = true)
	public LandingPage LaunchApplication() throws IOException {
		WebDriver driver = initializeDriver();
		Login = new LandingPage(driver);
		Login.goTo();
		return Login;
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		driver.close();
	}
}
