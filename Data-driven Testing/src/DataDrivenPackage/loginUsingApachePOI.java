package DataDrivenPackage;

import java.io.FileInputStream;
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


public class loginUsingApachePOI {
		
	static List<String> userNameList = new ArrayList<String>();
	static List<String> passwordList = new ArrayList<String>();
	
	public void readExcel() throws IOException{
		FileInputStream excel = new FileInputStream("C:\\Users\\awaha\\Downloads\\Testdata1.xls");
		Workbook workbook = new HSSFWorkbook(excel);
		Sheet sheet = workbook.getSheetAt(0);
		
		Iterator<Row> rowIterator = sheet.iterator();
		
		while (rowIterator.hasNext()) {
			Row rowvalue = rowIterator.next();
			
			Iterator<Cell> columnIterator = rowvalue.iterator();
			
			
			int i=2;
			while (columnIterator.hasNext()) {
				if (i%2==0) {
					userNameList.add(columnIterator.next().getStringCellValue());
				}else {
					passwordList.add(columnIterator.next().getStringCellValue());
				}
				i++;
			}
		}
			
		
			
		}
	
	public void executeTest(){
		for (int i = 0; i <userNameList.size(); i++) {
			loginmethod(userNameList.get(i), passwordList.get(i));
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
			loginUsingApachePOI usingPOI = new loginUsingApachePOI();
			usingPOI.readExcel();
			System.out.println("UserName List"+userNameList);
			System.out.println("Password List"+passwordList);
			usingPOI.executeTest();
	}

}
