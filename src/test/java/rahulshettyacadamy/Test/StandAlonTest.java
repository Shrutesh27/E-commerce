package rahulshettyacadamy.Test;

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
import org.testng.Assert;

public class StandAlonTest {

	public static void main(String[] args) throws InterruptedException {
		String productName = "ADIDAS ORIGINAL";
		String Country = "United State";

		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(5));
		driver.get("https://rahulshettyacademy.com/client/#/auth/login");
		//
		driver.findElement(By.id("userEmail")).sendKeys("suvarna02@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Suvarna@02");
		driver.findElement(By.id("login")).click();
		w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		/*
		 * for(WebElement product:products) { String productName=
		 * product.findElement(By.cssSelector("b")).getText();
		 * System.out.println(productName); if(productName.contains("ADIDAS")) {
		 * product.findElement(By.cssSelector("button[class='btn w-10 rounded']")).click
		 * (); } }
		 */

		WebElement product = products.stream()
				.filter(s -> s.findElement(By.cssSelector("b")).getText().contains(productName)).findFirst()
				.orElse(null);
		String price = product.findElement(By.cssSelector(".card-body .text-muted")).getText();
		product.findElement(By.cssSelector("button[class='btn w-10 rounded']")).click();
		// from rahul it is:- ".card-body button:last-of-type" for button
		w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		w.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		List<WebElement> CartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
		List<WebElement> cartProductPrice = driver.findElements(By.cssSelector(".prodTotal p"));
		// Anymatch will return tru or false will check if any match return value true
		// or false
		// won't used filter coz not returning any WebElement or product
		boolean Actualname = CartProducts.stream().anyMatch(s -> s.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(Actualname);
		boolean ActualPrice = cartProductPrice.stream().anyMatch(s -> s.getText().equalsIgnoreCase(price));
		Assert.assertTrue(ActualPrice);

		driver.findElement(By.cssSelector(".totalRow button")).click();

		w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".payment")));
		WebElement SendCountry = driver.findElement(By.cssSelector("input[placeholder*='Country']"));
		Actions a = new Actions(driver);
		// new way to send key using action first is elemnet second value to pass
		a.sendKeys(SendCountry, Country).build().perform();
		List<WebElement> Countries = driver.findElements(By.cssSelector(".list-group-item span"));
		/*
		 * //To Grap Text from DropDown List<String> Cou=
		 * Countries.stream().map(s->s.getText()).collect(Collectors.toList());
		 * System.out.println(Cou);
		 */
		Countries.stream().filter(s -> s.getText().toLowerCase().contains(Country.toLowerCase())).findFirst()
				.ifPresent(s -> s.click());

		/*
		 * Select s; WebElement Month=
		 * driver.findElement(By.xpath("//Select[@class='input ddl'][1]")); s= new
		 * Select(Month); s.selectByVisibleText("03"); WebElement date=
		 * driver.findElement(By.xpath("//Select[@class='input ddl'][2]")); s= new
		 * Select(date); s.selectByVisibleText("27");
		 */

		js.executeScript("window.scrollBy(0,900)");
		/*
		 * driver.findElement(By.xpath("//a[contains(@class,'action__submit')]")).click(
		 * );
		 * driver.findElement(By.xpath("//a[contains(@class,'action__submit')]")).click(
		 * );
		 */
		WebElement Placeholder= driver.findElement(By.xpath("//a[contains(@class,'action__submit')]"));
		try {
			Placeholder.click();
			System.out.println("Click through Automation");
		
		}catch (Exception e){
			js.executeScript("arguments[0].click();", Placeholder);
	        System.out.println("Successfully clicked on Button using JavaScript!");
		}
		
		js.executeScript("window.scrollTo(0, 0);");
		
		String Thanks= driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(Thanks.equalsIgnoreCase("Thankyou for the order."));
		driver.close();
	}

}
