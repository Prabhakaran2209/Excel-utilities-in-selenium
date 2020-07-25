package excel.tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.excel.lib.util.Xls_Reader;

import io.github.bonigarcia.wdm.WebDriverManager;

public class GetLatitudeandLongitude {

	public static void main(String[] args) throws InterruptedException {

		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.mapdevelopers.com/geocode_tool.php");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		
		WebElement Pincode = driver.findElement(By.id("address"));
		
		Xls_Reader reader = new Xls_Reader("./src/main/java/com/excel/lib/util/SampleExcel.xlsx");
		String sheetName = "sheet3";
		//reader.addColumn("sheet3", "Latitude");
		//reader.addColumn("sheet3", "Longitude");
		
		int rowCount = reader.getRowCount(sheetName);

		for(int rowNum=2; rowNum<=rowCount; rowNum++){
			String Pincode_input = reader.getCellData(sheetName, "Pincode", rowNum);
			Pincode.clear();
			Pincode.sendKeys(Pincode_input);
			driver.findElement(By.xpath("//button[contains(text(),'Find Address')]")).click();
			Thread.sleep(5000);
			String lat = driver.findElement(By.id("display_lat")).getText();
			
			reader.setCellData(sheetName, "Latitude", rowNum, lat);
			System.out.println(lat);
			String lng = driver.findElement(By.id("display_lng")).getText();
			
			reader.setCellData(sheetName, "Longitude", rowNum, lng);
			System.out.println(lng);
		}
		
		driver.quit();
		
		
		
	}

}
