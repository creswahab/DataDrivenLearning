package DataDrivenPackage;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;





public class loginUsingExcel{
	WebDriver driver;
	
	String[][] data = null;
	
	
	@DataProvider(name="logindata")
	public String[][] dataprovider() throws BiffException, IOException{
		data= getexcelfile();
		
		return data;
		
	}

	public String[][] getexcelfile() throws BiffException, IOException{
		
		FileInputStream excel = new FileInputStream("C:\\Users\\awaha\\Downloads\\Testdata.xls");
		Workbook workbook = Workbook.getWorkbook(excel);
		Sheet sheet = workbook.getSheet(0);
		int rowcount = sheet.getRows();
		int columncount = sheet.getColumns();
		
		String testdata[][] = new String[rowcount-1][columncount];
		
		for (int i = 1; i <rowcount; i++) {
			for (int j = 0; j < columncount; j++) {
				testdata[i-1][j] = sheet.getCell(j, i).getContents();
			}
			
		}
		return testdata;
 	}

	@BeforeTest
	public void beforetest(){
		System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
	}
	
	@AfterTest
	public void aftertest(){
		driver.quit();
	}
	
	@Test(dataProvider="logindata")
	public void loginmethod(String uname, String pword){

		driver.get("https://opensource-demo.orangehrmlive.com");
		
		WebElement username = driver.findElement(By.id("txtUsername"));
		username.sendKeys(uname);
		
		WebElement password = driver.findElement(By.id("txtPassword"));
		password.sendKeys(pword);
		
		WebElement loginbutton = driver.findElement(By.id("btnLogin"));
		loginbutton.click();
		
		
	}
}
