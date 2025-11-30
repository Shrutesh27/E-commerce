package rahulshettyacadamy.Test;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

import rahulshettyacadamy.PageObjects.CartPage;
import rahulshettyacadamy.PageObjects.CheckOutPage;
import rahulshettyacadamy.PageObjects.ConfirmationPage;
import rahulshettyacadamy.PageObjects.LandingPage;

import rahulshettyacadamy.PageObjects.ProductCatalogue;
import rahulshettyacadamy.PageObjects.SignUp;
import rahulshettyacadamy.TestComponents.BaseTest;
import rahulshettyacadamy.TestComponents.Retry;

public class ErrorValidationTest extends BaseTest {

	@Test(groups = "Errorhandling",retryAnalyzer = Retry.class)
	public void LoginErrorvalidation() throws InterruptedException, IOException {
		String productName = "ADIDAS ORIGINAL";
		String Country = "United State";
		String Username = "Shrutesh";
		String LastName = "Tho";
		String Email = "Shrutg4675@gmail.com";
		String Password = "Shrutesh@1234";
		String Phone = "1234567890";
		String age = "18";
		String Occ = "Doctor";
		String gender = "Male";
		String User = "Existing";

		SignUp Signup = Login.SignUp();
		ProductCatalogue productCatalague = null;
		if (User.equals("New")) {
			Signup.Register();
			Signup.UserName_LastName(Username, LastName);
			Signup.EmailID_Phone(Email, Phone);
			Signup.Occ_gender(Occ, gender);
			Signup.Pass(Password);
			Signup.age(age);
			Signup.RegisterBtn();
			
		Login.LoginApplication(Email, Password);
		} else if (User.equalsIgnoreCase("Existing")) {
		Login.LoginApplication(Email, Password);
		} else {
			System.out.println(User + "No value found");
		}

	}
	
	@Test
	public void ProductErroValidation() throws InterruptedException, IOException {
		String productName = "ADIDAS ORIGINAL";
		String Country = "United State";
		String Username = "Shru";
		String LastName = "Tho";
		String Email = "raju12@gmail.com";
		String Password = "Suvarna@02";
		String Phone = "1234567890";
		String age = "35";
		String Occ = "Doctor";
		String gender = "Male";
		String User = "Existing";
		SoftAssert SoftA=new SoftAssert();
		SignUp Signup = Login.SignUp();
		ProductCatalogue productCatalague = null;
		if (User.equals("New")) {
			Signup.Register();
			Signup.UserName_LastName(Username, LastName);
			Signup.EmailID_Phone(Email, Phone);
			Signup.Occ_gender(Occ, gender);
			Signup.Pass(Password);
			Signup.age(age);
			Signup.RegisterBtn();
			productCatalague = Login.LoginApplication(Email, Password);
		} else if (User.equalsIgnoreCase("Existing")) {
			productCatalague = Login.LoginApplication(Email, Password);
		} else {
			System.out.println(User + "No value found");
		}
		List<WebElement> products = productCatalague.getProductList();
		String ProductcatalogueName = productCatalague.getNameProdcatalogue(productName);
		String price = productCatalague.getPrice(productName);
		productCatalague.AddProductToCart(productName);

		
		  CartPage cartpage = productCatalague.goToCartPage();
		  
		  boolean Actualname = cartpage.cartProductsname("Zara Coat 3");
		  Assert.assertFalse(Actualname); 
		  boolean ActualPrice = cartpage.CartProductPrice(price); 
		  Assert.assertTrue(ActualPrice);
			
			/*
			 * CheckOutPage checkout = cartpage.goToCheckOut();
			 * 
			 * checkout.SelectCountry(Country);
			 * 
			 * ConfirmationPage Confirmationpage = checkout.PlaceOrder(); String thanks =
			 * Confirmationpage.SubmitOrderPage();
			 * Assert.assertTrue(thanks.equalsIgnoreCase("Thankyou for the order."));
			 */
			 
		 SoftA.assertAll();

	}

}
