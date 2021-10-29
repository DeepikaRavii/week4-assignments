package week4.assignment;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class JquerySortable {

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://jqueryui.com/sortable/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		Actions builder = new Actions(driver);
		WebElement frame = driver.findElement(By.xpath("//div[@id='content']/iframe"));
		driver.switchTo().frame(frame);
		
		WebElement src = driver.findElement(By.xpath("//li[@class='ui-state-default ui-sortable-handle']"));
		WebElement dst = driver.findElement(By.xpath("//li[@class='ui-state-default ui-sortable-handle'][5]"));
		Point location = dst.getLocation();
		int x = location.getX();
		int y = location.getY();
		
		//Using Mouse
        //builder.dragAndDropBy(src, x, y).perform();
        
        //using another method
        builder.clickAndHold(src).moveByOffset(x, y).release().perform();
        
        //Select All
        builder.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).perform();
        

	}

}
