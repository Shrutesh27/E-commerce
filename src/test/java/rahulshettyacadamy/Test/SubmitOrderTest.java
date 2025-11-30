package rahulshettyacadamy.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
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
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacadamy.Data.ExcelUtils;
import rahulshettyacadamy.PageObjects.CartPage;
import rahulshettyacadamy.PageObjects.CheckOutPage;
import rahulshettyacadamy.PageObjects.ConfirmationPage;
import rahulshettyacadamy.PageObjects.LandingPage;
import rahulshettyacadamy.PageObjects.OrderPage;
import rahulshettyacadamy.PageObjects.ProductCatalogue;
import rahulshettyacadamy.PageObjects.SignUp;
import rahulshettyacadamy.TestComponents.BaseTest;
import rahulshettyacadamy.abstractComponents.Validations;

public class SubmitOrderTest extends BaseTest {
	/*
	 * //below written in data provider public String Email = "raju1245@gmail.com";
	 * public String Password = "Suvarna@02"; String productName =
	 * "ADIDAS ORIGINAL";
	 */
	public String Productprice;

	@Test(dataProvider = "getDataFromExcel", groups = "Purchase")
	public void SubmitOrder(HashMap<String, String> input) throws InterruptedException, IOException {
		// As we are passing hashMap<key,value>(input) as parameter

		/*//send from hashmap
		 * String Country = "United State"; String Username = "wedrtyui"; String
		 * LastName = "Tho";
		 * 
		 * String Phone = "1234567890"; int age = 18; String Occ = "Doctor"; String
		 * gender = "Male";
		 */
		// this also shared from dataprovider String User = "Existing";
		SoftAssert SoftA = new SoftAssert();

		SignUp Signup = Login.SignUp();
		ProductCatalogue productCatalague = null;
		if (input.get("User").equals("New")) {
			Signup.Register();
			Signup.UserName_LastName(input.get("Username"), input.get("LastName"));
			Signup.EmailID_Phone(input.get("Email"),input.get("Phone"));
			Signup.Occ_gender(input.get("Occ"), input.get("gender"));
			Signup.Pass(input.get("Password"));
			Signup.age(input.get("age"));
			Signup.RegisterBtn();
			productCatalague = Login.LoginApplication(input.get("Email"), input.get("Password"));
		} else if (input.get("User").equalsIgnoreCase("Existing")) {
			productCatalague = Login.LoginApplication(input.get("Email"), input.get("Password"));
		} else {
			System.out.println(input.get("User") + "No value found");
		}
		
		String ProductcatalogueName = productCatalague.getNameProdcatalogue(input.get("productName"));
		List<WebElement> products = productCatalague.getProductList();
		System.out.println(products);
		String price = productCatalague.getPrice(input.get("productName"));
		productCatalague.AddProductToCart(input.get("productName"));
		System.out.println(ProductcatalogueName);
		System.out.println(Productprice);
		CartPage cartpage = productCatalague.goToCartPage();

		boolean Actualname = cartpage.cartProductsname(ProductcatalogueName);
		Assert.assertTrue(Actualname);
		boolean ActualPrice = cartpage.CartProductPrice(price);
		Assert.assertTrue(ActualPrice);
		CheckOutPage checkout = cartpage.goToCheckOut();
		Productprice = price;

		checkout.SelectCountry(input.get("Country"));

		ConfirmationPage Confirmationpage = checkout.PlaceOrder();
		String thanks = Confirmationpage.SubmitOrderPage();
		Assert.assertTrue(thanks.equalsIgnoreCase("Thankyou for the order."));
		String ProductOrder = productCatalague.ErrorMessage.getText();
		System.out.println("Order Staus:- " + ProductOrder);
		SoftA.assertAll();

	}

	// Verify Order is Successfully passed
	@Test(dependsOnMethods = { "SubmitOrder" }, dataProvider = "getDataFromExcel")
	public void OrderHistory(HashMap<String, String>input) {
//from any page we can go to order page but used productcatalogue as login and then go to order page
		ProductCatalogue productCatalogue = Login.LoginApplication(input.get("Email"), input.get("Password"));
		// goToPage() return object or orders page goTo in Abstractclass
		OrderPage orderPage = productCatalogue.goToOrderPage();

		System.out.println(Productprice);
		// orderPage.ProductNew(productName, Productprice);
		Assert.assertTrue(orderPage.VarifyProductDisplay(input.get("productName")), "productName");
		Assert.assertTrue(orderPage.VarifyProductPriceDisplay(input.get("productName"), Productprice), "Price");
	}

	/*
	 * @DataProvider public Object[][] getData() throws IOException {
	 * 
	 * List<HashMap<String, String>>data= getJsonDataToMap(
	 * System.getProperty("user.dir")+
	 * "\\src\\main\\java\\rahulshettyacadamy\\Data\\PurchaseOrder.json");
	 * //get(0)will get first hashmap same for get(1)2nd hashmap return new
	 * Object[][] {{data.get(0)},{data.get(1)}}; }
	 */	/* the below both is alternative
	 * // instead of passing data each as argument in metod use hashmap
	 * HashMap<String, String> map = new HashMap<String, String>(); map.put("Email",
	 * "raju1245@gmail.com"); map.put("Password", "Suvarna@02"); map.put("User",
	 * "Existing"); map.put("productName", "ADIDAS ORIGINAL"); map.put("Country",
	 * "United State"); map.put("Username", "wedrtyui"); map.put("LastName", "Tho");
	 * 
	 * map.put("Phone","1234567890"); map.put("age", "18"); map.put("Occ",
	 * "Doctor"); map.put("gender", "Male");
	 * 
	 * HashMap<String, String> map1 = new HashMap<String, String>();
	 * map1.put("Email", "suvarna5356@gmail.com"); map1.put("Password",
	 * "Suvarna@02"); map1.put("User", "New"); map1.put("productName",
	 * "ZARA COAT 3"); map1.put("Country", "India"); map1.put("Username",
	 * "wedrtyui"); map1.put("LastName", "Tho");
	 * 
	 * map1.put("Phone","1234567890"); map1.put("age", "18"); map1.put("Occ",
	 * "Doctor"); map1.put("gender", "Male");
	 */
	
	/*
	 * return new Object[][]
	 * {{"raju1245@gmail.com","Suvarna@02","ADIDAS ORIGINAL","Existing"},
	 * {"suvarna02@gmail.com","Suvarna@02","ZARA COAT 3","Existing"}};
	 */
	
	 @DataProvider
	    public Object[][] getDataFromExcel() throws IOException {

	        String filePath ="E:\\Testing\\Sele\\SeleniumFrameWorkDesign2\\src\\main\\java\\rahulshettyacadamy\\Data\\Data.xlsx";
	        String sheetName = "Sheet1";                                     

	        List<HashMap<String, String>> data =
	                ExcelUtils.getData(filePath, sheetName);

	        Object[][] dp = new Object[data.size()][1];

	        for (int i = 0; i < data.size(); i++) {
	            dp[i][0] = data.get(i);   // each row is one HashMap<String,String>
	        }

	        return dp;
	    }

	    
	
	
}
