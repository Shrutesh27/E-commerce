package rahulshettyacadamy.abstractComponents;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

public class Validations extends AbstractComponents{
	WebDriver driver;
	public SoftAssert softA;
	public Validations(WebDriver driver)
	{
		super(driver);
		this.driver=driver;	
		this.softA= new SoftAssert();
	}

	@FindBy(id = "login")
	WebElement Submit;
	
	@FindBy(xpath = "//button[text()='Login']")
	WebElement LoginClick;
	
	@FindBy(css = ".invalid-feedback")
	List<WebElement> Validation;
	public void SignUpLoginBtn()
	{
		if (driver.findElements(By.cssSelector("#toast-container")).size() > 0 && ErrorMessage.isDisplayed()) {
			WaitForWebElementToAppear(ErrorMessage);
		    String toastMsg = ErrorMessage.getText();
		    
		    if(toastMsg.equalsIgnoreCase("Registered Successfully"))
		    {
		    	LoginClick.click();
		    }
		    else 
		    {
		    	softA.fail(toastMsg);
		    }
		    if(driver.findElements(By.cssSelector(".invalid-feedback")).size() > 0 && Validation.get(0).isDisplayed())
		    {
		    	WaitForWebElementToAppear(Validation.get(0));
				Validation.stream()
			    .forEach(s -> {
			        String fieldName = "";
			        try {
			            fieldName = s.findElement(By.xpath("preceding-sibling::label")).getText();
			        } catch (Exception e) {
			            fieldName = "Unknown Field";
			        }
			        String validationName = s.getText();
			        
			        softA.fail(fieldName + " :- " + validationName);
			    });			
		    }
		}
		// Check for validation messages
		else if (driver.findElements(By.cssSelector(".invalid-feedback")).size() > 0 && Validation.get(0).isDisplayed()) {
			WaitForWebElementToAppear(Validation.get(0));
			Validation.stream()
		    .forEach(s -> {
		        String fieldName = "";
		        try {
		            fieldName = s.findElement(By.xpath("preceding-sibling::label")).getText();
		        } catch (Exception e) {
		            fieldName = "Unknown Field";
		        }
		        String validationName = s.getText();
		        softA.fail(fieldName + " :- " + validationName);
		    });
			/*
			 * for(WebElement validation : Validation) { String fieldName = ""; try {
			 * fieldName =
			 * validation.findElement(By.xpath("preceding-sibling::label")).getText(); }
			 * catch (Exception e) { fieldName = "Unknown Field"; } String ValidationName =
			 * validation.getText(); System.out.println(fieldName + " :- " +
			 * ValidationName); }
			 */
		}
		softA.assertAll();
	}
	
	public void LoginBtn()
	{	
		Submit.click();
		if (driver.findElements(By.cssSelector("#toast-container")).size() > 0 && ErrorMessage.isDisplayed()) {
			WaitForWebElementToAppear(ErrorMessage);
		    String toastMsg = ErrorMessage.getText();
		    if(toastMsg.contains("Success"))
		    {
		    	WaitForWebElementToAppear(driver.findElement(By.cssSelector(".mb-3")));
		    }
		    else
		    {
		    	softA.fail(toastMsg);
		    }
		   
		    if(driver.findElements(By.cssSelector(".invalid-feedback")).size() > 0 && Validation.get(0).isDisplayed())
		    {
		    	
		    	WaitForWebElementToAppear(Validation.get(0));
				Validation.stream()
			    .forEach(s -> {
			        String fieldName = "";
			        try {
			            fieldName = s.findElement(By.xpath("preceding-sibling::label")).getText();
			        } catch (Exception e) {
			            fieldName = "Unknown Field";
			        }
			        String validationName = s.getText();
			        softA.fail(fieldName + " :- " + validationName);
			    });			
		    }
		    
		}
		
		
		  // Check for validation messages else if
		else if (driver.findElements(By.cssSelector(".invalid-feedback")).size() > 0 && Validation.get(0).isDisplayed()) 
		{
			WaitForWebElementToAppear(Validation.get(0));
			Validation.stream()
		    .forEach(s -> {
		        String fieldName = "";
		        try {
		            fieldName = s.findElement(By.xpath("preceding-sibling::label")).getText();
		        } catch (Exception e) {
		            fieldName = "Unknown Field";
		        }
		        String validationName = s.getText();
		         softA.fail(fieldName + " :- " + validationName);
		    });
		}
		else {
			System.out.println("Logged in");
		}
		softA.assertAll();
	}
	
	
}