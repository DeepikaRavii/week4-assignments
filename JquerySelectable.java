package week4.assignment;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class JquerySelectable {

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://jqueryui.com/selectable/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		WebElement frame = driver.findElement(By.xpath("//div[@id='content']/iframe"));
		driver.switchTo().frame(frame);
		/*
		// Using Mouse
		 WebElement first = driver.findElement(By.xpath("//li[text()='Item 1']"));
		 WebElement last = driver.findElement(By.xpath("//li[text()='Item 5']"));
		 Actions builder = new Actions(driver);
		 builder.clickAndHold(first).moveToElement(last).release().perform();*/
		
		// Using Keyboard
		WebElement first = driver.findElement(By.xpath("//li[text()='Item 1']"));
		WebElement second = driver.findElement(By.xpath("//li[text()='Item 5']"));
		WebElement third = driver.findElement(By.xpath("//li[text()='Item 4']"));
		WebElement fourth = driver.findElement(By.xpath("//li[text()='Item 2']"));
		Actions builder = new Actions(driver);
		builder.keyDown(Keys.CONTROL).click(first).click(second).click(third).click(fourth).keyUp(Keys.CONTROL)
				.perform();
	}

}
