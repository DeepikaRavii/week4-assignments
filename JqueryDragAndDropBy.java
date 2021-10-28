package week4.assignment;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class JqueryDragAndDropBy {

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://jqueryui.com/draggable/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		WebElement frame = driver.findElement(By.xpath("//div[@id='content']/iframe"));
		driver.switchTo().frame(frame);
		
		WebElement drag = driver.findElement(By.xpath("//p[text()='Drag me around']"));
		Actions builder = new Actions(driver);
		builder.dragAndDropBy(drag, 350, 100).perform();

	}

}
