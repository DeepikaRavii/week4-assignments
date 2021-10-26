package week4.assignment;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ScreenshotAndFrameCount {

	public static void main(String[] args) throws IOException {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://www.leafground.com/pages/frame.html");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		// got to frame
		WebElement frame = driver.findElement(By.xpath("//div[@id='wrapframe']/iframe"));
		driver.switchTo().frame(frame);
		//screenshot of click me button
		WebElement eleClick = driver.findElement(By.id("Click"));
		File clickImgsrc = eleClick.getScreenshotAs(OutputType.FILE);
		File clickImgdst = new File("./snaps/ClickImage.png");
		FileUtils.copyFile(clickImgsrc, clickImgdst);
		//come out from frame
		driver.switchTo().parentFrame();
		//to get no of frames in webpage
		List<WebElement> frameCount = driver.findElements(By.tagName("iframe"));
		System.out.println(frameCount.size());
		
		
	}

	
	
}
