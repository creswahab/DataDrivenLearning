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
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;



public class RecapDDP {
	
	WebDriver driver;
	String data [][]= null;
	
	
	@DataProvider(name="logindata")
	public String[][] loginDataProvider() throws BiffException, IOException{
		String[][] data= readexcel();
		return data;
	}
	
	public String[][] readexcel() throws BiffException, IOException{
		
		FileInputStream excel = new FileInputStream("C:\\Users\\awaha\\Downloads\\Testdata.xls");
		Workbook workbook = Workbook.getWorkbook(excel);
		Sheet sheet = workbook.getSheet(0);
		int rowcount = sheet.getRows();
		int columncount = sheet.getColumns();
		
		String testdata[][]  =  new String [rowcount-1][columncount];
		
		for (int i = 1; i < rowcount; i++) {
			for (int j = 0; j < columncount; j++) {
				
				testdata[i-1][j] = sheet.getCell(j,i).getContents();
			}
			
		}
		return testdata;
	}
	
	@BeforeSuite
	public void beforelaunch(){
		System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
	}
	
	@AfterSuite
	public void afterlaunch(){
		driver.quit();
	}
	
	@Test(dataProvider="logindata")
	public void loginmethod(String uname, String pword){
		
		
		driver.get("https://opensource-demo.orangehrmlive.com");
		
		WebElement userName = driver.findElement(By.name("txtUsername"));
		userName.sendKeys(uname);
		
		WebElement passWord = driver.findElement(By.name("txtPassword"));
		passWord.sendKeys(pword);
		
		WebElement loginButton = driver.findElement(By.id("btnLogin"));
		loginButton.click();
	
		
}
	
}
