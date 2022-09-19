package demo;

import static org.testng.Assert.assertNotNull;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

public class ui_test {
	WebDriver driver;
	String url="http://automationpractice.com/index.php";

	@BeforeTest
	public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver","C:\\Users\\anusree.vs\\Downloads\\chromedriver.exe");
	    driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
		driver.get(url);
		driver.manage().window().maximize();
	}
	@Test
	public void scenario1() throws Exception  {
		driver.findElement(By.id("search_query_top")).sendKeys("Dress");                          //Search Dress
		driver.findElement(By.name("submit_search")).click();                                     //click on submit
		WebElement searchResult=driver.findElement(By.xpath("//*[@id=\"center_column\"]/h1/span[1]"));// fetch the search item
		assertNotNull(searchResult);															 //assert the search result
		Thread.sleep(1000);
	}
	@Test
	public void scenario2() throws Exception  {
		driver.findElement(By.xpath("//*[@id=\"block_top_menu\"]/ul/li[1]/a")).click();           //click on women              
		driver.findElement(By.id("layered_id_attribute_group_8")).click();                        //click checkbox category
		driver.findElement(By.id("layered_category_8")).click();                                  //select colour                       
		Thread.sleep(1000);
		String expectedresults=driver.findElement(By.xpath("//*[@id=\"center_column\"]/h1/span[1]")).getText();//Get the text for category and color displayed
		String actualresult="WOMEN > CATEGORIES DRESSES > COLOR WHITE";
		if(actualresult.contentEquals(expectedresults)){                               //Verify that color and category matches
			System.out.println("The right colour and category is displayed");
		}else {
			System.out.println(" The color and category is mismatched");
		}
		
	}
	@Test
	public void scenario3() throws Exception  {
		WebElement item= driver.findElement(By.xpath("//*[@id=\"homefeatured\"]/li[1]/div/div[1]/div/a[1]/img"));// mouse hover to dress item
		Actions action= new Actions(driver);
		action.moveToElement(item).perform();
		driver.findElement(By.id("//*[@id=\"homefeatured\"]/li[1]/div/div[2]/div[2]/a[2]/span")).click();// click on "More"
		Thread.sleep(1000);
		WebElement size=driver.findElement(By.id("group_1")); 										// Inspect the size
		Select selectsize=new Select(size);
		selectsize.selectByIndex(1); 																// To select medium dress
		driver.findElement(By.xpath("//*[@id=\"color_14\"]")).click();              				//to select color blue
		driver.findElement(By.id("quantity_wanted")).clear();                                       //clear the default quantity 1
		driver.findElement(By.id("quantity_wanted")).sendKeys("3");									// send quantity as 3
		driver.findElement(By.xpath("//*[@id=\"add_to_cart\"]/button/span")).click();               //click Add to cart
		driver.findElement(By.xpath("//*[@id=\"layer_cart\"]/div[1]/div[2]/div[4]/a/span")).click();//click Proceed to checkout
		driver.findElement(By.xpath("//*[@id=\"center_column\"]/p[2]/a[1]/span")).click();
		driver.findElement(By.xpath("//*[@id=\"header\"]/div[3]/div/div/div[3]/div/a/b")).click();   //Click on cart
		String actualDressType="Faded Short Sleeve T-shirts";
		String expectedDressType=driver.findElement(By.xpath("//*[@id=\"product_1_1_0_0\"]/td[2]/p/a")).getText();
		if(actualDressType.contentEquals(expectedDressType)) {
			System.out.println(" The item is correctly added to cart");
		}else {
			System.out.println("Wrong item is added to the cart.");
		}
	}
	@AfterTest
	public void tearDown() throws Exception {
		driver.close();
	}
}