package rahulshettyacadamy.PageObjects;

import java.util.Collection;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import rahulshettyacadamy.abstractComponents.AbstractComponents;

public class OrderPage extends AbstractComponents{


public WebDriver driver;
	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	
	@FindBy (css="tr td:nth-child(3)")
	List<WebElement> Productname;
	
	@FindBy (css="tr td:nth-child(4)")
	List<WebElement> ProductPrice;
	
	public boolean VarifyProductDisplay(String ProductName)
	{
		return Productname.stream().anyMatch(s->s.getText().equalsIgnoreCase(ProductName));
		
	}
	
	/*
	 * public void ProductNew(String ProductName,String ProductPrice) {
	 * Productname.stream().filter(s->s.getText().equalsIgnoreCase(ProductName)).map
	 * (s->s.findElement(By.xpath("./following-sibling::td[1]")).getText()).
	 * findFirst().ifPresent(s->System.out.println(s)); //System.out.println(Price);
	 * }
	 */
	
	public boolean VarifyProductPriceDisplay(String ProductName,String ProductPrice)
	{
	
		return Productname.stream().filter(s->s.getText().equalsIgnoreCase(ProductName)).anyMatch(s->s.findElement(By.xpath("./following-sibling::td[1]")).getText().equalsIgnoreCase(ProductPrice));
	}
	
	
}
