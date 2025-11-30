package rahulshettyacadamy.PageObjects;

import java.util.Collection;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacadamy.abstractComponents.AbstractComponents;

public class CartPage extends AbstractComponents{


WebDriver driver;
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	
	@FindBy (css=".cartSection h3")
	List<WebElement> cartProducts;
	
	@FindBy (css=".prodTotal p")
	List<WebElement> CartProductPrice;
	
	@FindBy (css=".totalRow button")
	WebElement GoToCheckoutp;
	
	public boolean cartProductsname(String Productname)
	{
		String CartProductname= cartProducts.stream().filter(s -> s.getText().equalsIgnoreCase(Productname)).map(s->s.getText()).findFirst().orElse(null);
		System.out.println(CartProductname);
		boolean Productmatch=CartProductname.equalsIgnoreCase(Productname);
		return Productmatch;
	}
	
	public boolean CartProductPrice(String ProductPrice)
	{
	
		String CartProductPrices= CartProductPrice.stream().filter(s -> s.getText().equalsIgnoreCase(ProductPrice)).map(s->s.getText()).findFirst().orElse(null);
		System.out.println(CartProductPrices);
		boolean PriceMatch=CartProductPrices.equalsIgnoreCase(ProductPrice);
		return PriceMatch;
	}
	
	public CheckOutPage goToCheckOut()
	{
		jsDown();
		if(GoToCheckoutp.isDisplayed())
		{
			GoToCheckoutp.click();
		}
		else {
			jsElement(GoToCheckoutp);
			System.out.println("Clicked through Js");
		}
		
		CheckOutPage CheckoutPage=new CheckOutPage(driver);
		return CheckoutPage;
	}
	
	
	
	
	
	
}
