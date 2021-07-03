package DataDrivenPackage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


public class RecapApache {

	static List<String> usernamelist = new ArrayList<String>();
	static List<String> passwordlist = new ArrayList<String>();
	
	
	
	public void readexcel() throws IOException{
		FileInputStream excel = new FileInputStream("C:\\Users\\awaha\\Downloads\\Testdata1.xls");
		Workbook workbook = new HSSFWorkbook(excel);
		Sheet sheet = workbook.getSheetAt(0);
		
		Iterator<Row> rowiterator = sheet.iterator();
		while (rowiterator.hasNext()) {
			Row rowvalue = rowiterator.next();
			
			Iterator<Cell> columniterator = rowvalue.iterator();
			
			int i=2;
			while (columniterator.hasNext()) {
				if (i%2==0) {
					usernamelist.add(columniterator.next().getStringCellValue());
				}else {
					passwordlist.add(columniterator.next().getStringCellValue());
				}
				i++;
				
			}
		}
		
	}
	
	public void executetest(){
		for (int i = 0; i <usernamelist.size(); i++) {
			loginmethod(usernamelist.get(i), passwordlist.get(i));
		}
	}
	
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
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		RecapApache usingPOI = new RecapApache();
		usingPOI.readexcel();
		System.out.println("UserName List"+usernamelist);
		System.out.println("Password List"+passwordlist);
		usingPOI.executetest();
	}

}
