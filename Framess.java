package week4.assignment;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Framess {

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://chercher.tech/practice/frames-example-selenium-webdriver");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		//fetch the topic text value from the header
		String text = driver.findElement(By.xpath("//span[contains(text(),'Topic')]")).getText();
		
		//get into frame1
		WebElement frame = driver.findElement(By.xpath("//div[@class='card mb-3']/iframe"));
		driver.switchTo().frame(frame);
		//pass the text value into input box
		driver.findElement(By.tagName("input")).sendKeys(text);
		//get into inner frame
		driver.switchTo().frame("frame3");
		driver.findElement(By.id("a")).click();
		
		//switch to main page
		driver.switchTo().defaultContent();
		
		//get into second case frame2
		WebElement frame2 = driver.findElement(By.xpath("//div[@class='card mb-3']/iframe[2]"));
		driver.switchTo().frame(frame2);
		//select one value from combo box
		driver.findElement(By.xpath("//select[@id='animals']/option[3]")).click();
				
		
	}

}
