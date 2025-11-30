package rahulshettyacadamy.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import rahulshettyacadamy.abstractComponents.AbstractComponents;
import rahulshettyacadamy.abstractComponents.Validations;

public class SignUp extends AbstractComponents {

	WebDriver driver;

	public SignUp(WebDriver driver) {
		super(driver);
		this.driver = driver;
		// TODO Auto-generated constructor stub
	}

	Actions a;
	@FindBy(css = ".btn1")
	WebElement FirstRegister;

	@FindBy(id = "firstName")
	WebElement Username;

	@FindBy(id = "lastName")
	WebElement Lastname;

	@FindBy(id = "userEmail")
	WebElement Email;

	@FindBy(id = "userMobile")
	WebElement Mobile;

	@FindBy(css = "select[formcontrolname='occupation']")
	WebElement Occupation;
	Select s;

	@FindBy(css = "input[type='checkbox']")
	WebElement AboveAge;

	public WebElement Gender(String gender) {
		String UserGender = "//span[contains(text(),'" + gender + "')]";
		return driver.findElement(By.xpath(UserGender));
	}

	@FindBy(id = "userPassword")
	WebElement FirstPass;

	@FindBy(id = "confirmPassword")
	WebElement confirmPass;

	@FindBy(id = "login")
	WebElement RegisterBTN;

	@FindBy(css = ".headcolor")
	WebElement Successmsg;

	@FindBy(xpath = "//button[text()='Login']")
	WebElement LoginClick;

	public void Register() {
		FirstRegister.click();
	
	}

	public void RegisterBtn() throws InterruptedException {
		jsElement(RegisterBTN);
		Validations Valid = new Validations(driver);
		Valid.SignUpLoginBtn();
		// Check for error message (toast)

	}

	public void UserName_LastName(String name, String Surname) {
		Username.sendKeys(name);
		Lastname.sendKeys(Surname);
	}

	public void EmailID_Phone(String email, String Phone) {
		Email.sendKeys(email);
		Mobile.sendKeys(Phone);
	}

	public void Occ_gender(String Occ, String genders) {
		s = new Select(Occupation);
		s.selectByVisibleText(Occ);
		Gender(genders).click();
	}

	public void Pass(String password) {
		FirstPass.sendKeys(password);
		confirmPass.sendKeys(password);
	}

	public void age(String age) {

		if (Integer.parseInt(age) >= 18) {
			AboveAge.click();
		} else {
			System.out.println("Age is below 18");
		}
	}

}
