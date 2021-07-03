package DataDrivenPackage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;



public class loginUsingDataProvider {

	
	String[][] data = {
			{"admin","dummy"},
			{"Admin","dummy"},
			{"dummy","admin123"},
			{"Admin","admin123"}
			};
	
	@DataProvider(name="logindata")
	public String[][] loginDataProvider(){
		return data;
	}
	
	@Test(dataProvider="logindata")
	public void loginmethod(String uname, String pword){
		System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://opensource-demo.orangehrmlive.com");
		
		
		WebElement username = driver.findElement(By.id("txtUsername"));
		username.sendKeys(uname);
		
		WebElement password = driver.findElement(By.id("txtPassword"));
		password.sendKeys(pword);
		
		WebElement loginbutton = driver.findElement(By.id("btnLogin"));
		loginbutton.click();
		driver.quit();
		

	}
	
}
