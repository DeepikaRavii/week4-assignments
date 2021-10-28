package week4.assignment;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class JqueryResizeable {

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://jqueryui.com/resizable/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		WebElement frame = driver.findElement(By.xpath("//div[@id='content']/iframe"));
		driver.switchTo().frame(frame);
		
		WebElement resize = driver.findElement(By.xpath("(//div[@id='resizable']/div)[3]"));
		Actions builder = new Actions(driver);
		builder.moveToElement(resize).click().dragAndDropBy(resize, 350, 50).perform();
	}

}
