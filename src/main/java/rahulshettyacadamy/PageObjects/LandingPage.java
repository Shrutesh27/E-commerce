package rahulshettyacadamy.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacadamy.abstractComponents.AbstractComponents;
import rahulshettyacadamy.abstractComponents.Validations;

public class LandingPage extends AbstractComponents {
	WebDriver driver;

	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
//first driver is local 2nd is from parent and 2nd driver life is inside method only so assign it to local driver
		PageFactory.initElements(driver, this);// to initialize element need driver so use this
	}

	// Pagefactory to reduce syntax

	// WebElement Username = driver.findElement(By.id("userEmail"));
	@FindBy(id = "userEmail")
	WebElement UserEmail;

	// WebElement password = driver.findElement(By.id("userPassword"));
	@FindBy(id = "userPassword")
	WebElement Password;

	// WebElement Loginbtn = driver.findElement(By.id("login"));
	@FindBy(id = "login")
	WebElement Submit;
	
	@FindBy(css = ".invalid-feedback")
	List<WebElement> Validation;

	public String ErrorMessage() {
		WaitForWebElementToAppear(ErrorMessage);
		return ErrorMessage.getText();
	}

	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client/#/auth/login");
	}

	public SignUp SignUp() {
		SignUp signup = new SignUp(driver);
		return signup;
	}

	public ProductCatalogue LoginApplication(String Username, String password) {
		UserEmail.sendKeys(Username);
		Password.sendKeys(password);
		Validations valid=new Validations(driver);
		valid.LoginBtn();
		// ater this login page productcatalogue need to run so pass here
		ProductCatalogue productCatalogue = new ProductCatalogue(driver);
		return productCatalogue;
	}
	
	
}
