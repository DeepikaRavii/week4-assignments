package week4.assignment;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ServiceNow {

	public static void main(String[] args) throws IOException {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://dev95304.service-now.com/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		// Sign in
		WebElement frame = driver.findElement(By.xpath("//div[@class='navpage-main-left ng-isolate-scope']/iframe"));
		driver.switchTo().frame(frame);
		driver.findElement(By.id("user_name")).sendKeys("admin");
		driver.findElement(By.id("user_password")).sendKeys("Testleaf@21");
		driver.findElement(By.id("sysverb_login")).click();
		driver.switchTo().defaultContent();

		// Search “incident “ Filter Navigator
		driver.findElement(By.id("filter")).sendKeys("incident");
		// Click “All”
		driver.findElement(By.xpath("(//div[text()='All'])[2]")).click();

		// Click New button (get into new frame)
		WebElement frame1 = driver.findElement(By.xpath("//div[@class='navpage-main-left ng-isolate-scope']/iframe"));
		driver.switchTo().frame(frame1);
		driver.findElement(By.xpath("//button[text()='New']")).click();
		driver.switchTo().defaultContent();

		// Select a value for Caller and Enter value for short_description
		WebElement frame2 = driver.findElement(By.xpath("//div[@class='navpage-main-left ng-isolate-scope']/iframe"));
		driver.switchTo().frame(frame2);
		driver.findElement(By.id("lookup.incident.caller_id")).click();

		// select the new window
		Set<String> winSet = driver.getWindowHandles();
		List<String> winList = new ArrayList<String>(winSet);
		driver.switchTo().window(winList.get(1));

		// now select the value for caller
		WebElement eleSource = driver.findElement(By.xpath("//select[@role='listbox']"));
		Select dropDown = new Select(eleSource);
		dropDown.selectByVisibleText("Name");
		driver.findElement(By.xpath("//a[@class='glide_ref_item_link']")).click();
		driver.switchTo().window(winList.get(0));
		driver.switchTo().frame(frame2);
		// driver.findElement(By.xpath("//span[text()='Suggestion']")).click();
		driver.findElement(By.xpath("//span[@class='icon icon-lightbulb']")).click();

		Set<String> winSet1 = driver.getWindowHandles();
		List<String> winList1 = new ArrayList<String>(winSet1);
		driver.switchTo().window(winList1.get(1));
		driver.findElement(By.xpath("//a[text()='Issue with networking']")).click();
		driver.switchTo().window(winList.get(0));
		driver.switchTo().frame(frame2);

		// Read the incident number and save it a variable
		String incidentno = driver.findElement(By.xpath("//input[@id='incident.number']")).getAttribute("value");
		System.out.println(incidentno);

		// Click on Submit button
		driver.findElement(By.xpath("//div[@class='form_action_button_container']/button")).click();

		// Search the same incident number in the next search screen as below
		driver.findElement(By.xpath("//input[@class='form-control']")).sendKeys(incidentno, Keys.ENTER);

		// Verify the incident is created successful and take snapshot of the created
		// incident.
		
		 String text = driver.findElement(By.xpath("(//tbody[@class='list2_body']/tr/td)[3]")).getText();
		 
		 if (text.contains(incidentno)) {
			System.out.println("Incident is Created("+incidentno+")");
		} else {
			System.out.println("Incident is not Created("+incidentno+")");
		}
		/*WebElement eleTable = driver.findElement(By.id("incident_table"));

		List<WebElement> tableRows = eleTable.findElements(By.xpath("//tbody[@class='list2_body']/tr"));
		System.out.println("The number of Rows in the Table : " + tableRows.size());

		
		 * WebElement firstRow = tableRows.get(1); List<WebElement> tableCol =
		 * firstRow.findElements(By.tagName("td"));
		 
		// Print all the content of the table
		for (WebElement eachRowElement : tableRows) {
			System.out.println(eachRowElement.getText());
		}
		//for (int i = 1; i < tableRows.size(); i++) {
			WebElement row = tableRows.get(1);
			row.findElement(By.xpath())
			List<WebElement> col = row.findElements(By.tagName("td"));
			String colText = col.get(1).getText();
			System.out.println(colText);
		//}*/
		File clickImgsrc = driver.getScreenshotAs(OutputType.FILE);
		File clickImgdst = new File("./snaps/Incident.png");
		FileUtils.copyFile(clickImgsrc, clickImgdst);

	}

}
