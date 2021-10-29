package week4.assignment;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Nykaa {

	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		// Go to https://www.nykaa.com/
		driver.get("https://www.nykaa.com/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		Actions builder = new Actions(driver);

		// Mouseover on Brands and Search L'Oreal Paris
		WebElement brand = driver.findElement(By.xpath("//a[text()='brands']"));
		builder.moveToElement(brand).perform();

		// Click L'Oreal Paris
		driver.findElement(By.xpath("//a[contains(text(),'Paris')]")).click();
		/*
		 * Using character escape
		 * driver.findElement(By.xpath("(//a[text()=\"L'Oreal Paris\"])")).click();
		 */

		// Check the title contains L'Oreal Paris(Hint-GetTitle)
		String title = driver.getTitle();
		if (title.contains("L'Oreal Paris")) {
			System.out.println("YES !! Title contain L'Oreal Paris");
		} else {
			System.out.println("No !! Title contain L'Oreal Paris");
		}

		// Click sort By and select customer top rated
		driver.findElement(By.xpath("//span[text()='Sort By : popularity']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//span[text()='customer top rated']")).click();
		Thread.sleep(1000);

		// Click Category and click Hair->Click haircare->Shampoo
		driver.findElement(By.xpath("//span[text()='Category']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//span[text()='Hair']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//span[text()='Hair Care']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//span[text()='Shampoo']")).click();

		// Click->Concern->Color Protection
		driver.findElement(By.xpath("//span[text()='Concern']")).click();
		driver.findElement(By.xpath("//span[text()='Color Protection']")).click();

		// check whether the Filter is applied with Shampoo
		String eleFilterText = driver
				.findElement(By.xpath("//span[text()='Filters Applied']/parent::div/following-sibling::div//span"))
				.getText();
		if (eleFilterText.contains("Shampoo")) {
			System.out.println("Filter is applied with Shampoo");
		} else {
			System.out.println("Filter is not applied with Shampoo");
		}

		// Click on L'Oreal Paris Colour Protect Shampoo
		driver.findElement(By.xpath("//div[text()=\"L'Oreal Paris Colour Protect Shampoo\"]")).click();

		// move to new window
		Set<String> winSet = driver.getWindowHandles();
		List<String> winList = new ArrayList<String>(winSet);
		driver.switchTo().window(winList.get(1));

		// GO to the new window and select size as 175ml
		WebElement eleDrop = driver.findElement(By.xpath("//select[@title='SIZE']"));
		Select value = new Select(eleDrop);
		value.selectByVisibleText("175ml");

		// Print the MRP of the product
		String eleMrp = driver.findElement(By.xpath("(//span[text()='MRP:'])[1]/following-sibling::span")).getText()
				.substring(1);
		System.out.println("MRP: " + eleMrp);

		// Click on ADD to BAG
		driver.findElement(By.xpath("//span[text()='ADD TO BAG']")).click();

		// Go to Shopping Bag
		driver.findElement(By.xpath("//span[@class='cart-count']")).click();

		// Get into frame
		WebElement frame = driver.findElement(By.xpath("//div[@id='portal-root']//iframe"));
		driver.switchTo().frame(frame);
		// Print the Grand Total amount
		String grandTotal = driver.findElement(By.xpath("//div[@class='value medium-strong']")).getText().substring(1);
		System.out.println("Total Amount : " + grandTotal);

		// Click Proceed
		driver.findElement(By.xpath("//span[text()='Proceed']")).click();

		// Click on Continue as Guest
		driver.findElement(By.xpath("//button[text()='CONTINUE AS GUEST']")).click();

		// Check if this grand total is the same in step 14
		String finalAmount = driver.findElement(By.xpath("//div[text()='Grand Total']/following-sibling::div"))
				.getText().substring(1);
		if (grandTotal.equals(finalAmount)) {
			System.out.println("Grand Total Amount is Same : " + finalAmount);
		} else {
			System.out.println("Grand Total Amount is not Same ");
		}
		
		//Close all windows
		driver.quit();
	}

}
