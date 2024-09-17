package commonFunctions;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
//All Methods Written to use it Another Class
public class FunctionLibrary {
	public static WebDriver driver;
	public static 	Properties conpro;

	//method for launch browser
	public static WebDriver startBrowser()throws Throwable
	{
		conpro = new Properties();
		conpro.load(new FileInputStream("./PropertyFiles/Environment.properties"));
		if(conpro.getProperty("Browser").equalsIgnoreCase("chrome"))
		{
			driver = new ChromeDriver();
			driver.manage().window().maximize();
		}
		else if(conpro.getProperty("Browser").equalsIgnoreCase("firefox"))
		{
			driver = new FirefoxDriver();
		}
		return driver;
	}
	//method for launch url
	public static void openUrl()
	{
		driver.get(conpro.getProperty("Url"));
	}
	//method for wait for any webelement
	public static void waitForElement(String LocatorType,String LocatorValue,String TestData)
	{
		WebDriverWait mywait = new WebDriverWait(driver, Duration.ofSeconds(Integer.parseInt(TestData)));
		if(LocatorType.equalsIgnoreCase("xpath"))
		{
			mywait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocatorValue)));
		}
		if(LocatorType.equalsIgnoreCase("name"))
		{
			mywait.until(ExpectedConditions.visibilityOfElementLocated(By.name(LocatorValue)));
		}
		if(LocatorType.equalsIgnoreCase("id"))
		{
			mywait.until(ExpectedConditions.visibilityOfElementLocated(By.id(LocatorValue)));
		}
	}
	//method for any textbox
	public static void typeAction(String LocatorType,String LocatorValue,String TestData)
	{
		if(LocatorType.equalsIgnoreCase("name"))
		{
			driver.findElement(By.name(LocatorValue)).clear();
			driver.findElement(By.name(LocatorValue)).sendKeys(TestData);
		}
		if(LocatorType.equalsIgnoreCase("xpath"))
		{
			driver.findElement(By.xpath(LocatorValue)).clear();
			driver.findElement(By.xpath(LocatorValue)).sendKeys(TestData);
		}
		if(LocatorType.equalsIgnoreCase("id"))
		{
			driver.findElement(By.id(LocatorValue)).clear();
			driver.findElement(By.id(LocatorValue)).sendKeys(TestData);
		}
	}
	//method for any like buttons,links,checkboxes, radio button and image
	public static void clickAction(String LocatorType,String LocatorValue) throws Throwable
	{
		if(LocatorType.equalsIgnoreCase("xpath"))
		{
			Thread.sleep(2000);
			driver.findElement(By.xpath(LocatorValue)).click();
		}
		if(LocatorType.equalsIgnoreCase("name"))
		{
			driver.findElement(By.name(LocatorValue)).click();	
		}
		if(LocatorType.equalsIgnoreCase("id"))
		{
			driver.findElement(By.id(LocatorValue)).sendKeys(Keys.ENTER);
		}
	}
	public static void validateTitle(String Expected_Title)
	{
		String Actual_Title = driver.getTitle();
		try {
			Assert.assertEquals(Actual_Title, Expected_Title,"Title is Not Matching");
		}catch (AssertionError a) {
			System.out.println(a.getMessage());
		}
	}
	
	
	//method for date generate
	public static String generateDate()
	{
			Date date = new Date();
			DateFormat df = new SimpleDateFormat("YYYY_MM_dd hh_mm");
			return df.format(date);
	}
		
		
	//method for close browser
	public static void closeBrowser()
	{
		driver.quit();
	}
	
	//method for listboxes
		public static void dropDownAction(String LocatorType,String LocatorValue,String TestData)
		{
			if(LocatorType.equalsIgnoreCase("xpath"))
			{
				int value = Integer.parseInt(TestData);
				Select element = new Select(driver.findElement(By.xpath(LocatorValue)));
				element.selectByIndex(value);
			}
			if(LocatorType.equalsIgnoreCase("name"))
			{
				int value = Integer.parseInt(TestData);
				Select element = new Select(driver.findElement(By.name(LocatorValue)));
				element.selectByIndex(value);
			}
			if(LocatorType.equalsIgnoreCase("id"))
			{
				int value = Integer.parseInt(TestData);
				Select element = new Select(driver.findElement(By.id(LocatorValue)));
				element.selectByIndex(value);
			}
			
		}
		//method for capture Stock number into note pad
		public static void captureStock(String LocatorType,String LocatorValue)throws Throwable
		{
			String stockNum="";
			if(LocatorType.equalsIgnoreCase("name"))
			{
				stockNum = driver.findElement(By.name(LocatorValue)).getAttribute("value");
			}
			if(LocatorType.equalsIgnoreCase("xpath"))
			{
				stockNum = driver.findElement(By.xpath(LocatorValue)).getAttribute("value");
			}
			if(LocatorType.equalsIgnoreCase("id"))
			{
				stockNum = driver.findElement(By.id(LocatorValue)).getAttribute("value");
			}
			//create note pad file into captureData folder
			FileWriter fw = new FileWriter("./CaptureData/StockNumber.txt");
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(stockNum);
			bw.flush();
			bw.close();
			
		}
		//method verify stock table
		public static void stockTable()throws Throwable
		{
			//read stock number from above note pad
			FileReader fr = new FileReader("./CaptureData/StockNumber.txt");
			BufferedReader br = new BufferedReader(fr);
			String Exp_data =br.readLine();
			if(!driver.findElement(By.xpath(conpro.getProperty("search-textbox"))).isDisplayed())
				driver.findElement(By.xpath(conpro.getProperty("search-panel"))).click();
			driver.findElement(By.xpath(conpro.getProperty("search-textbox"))).clear();
			driver.findElement(By.xpath(conpro.getProperty("search-textbox"))).sendKeys(Exp_data);
			driver.findElement(By.xpath(conpro.getProperty("search-button"))).click();
			Thread.sleep(3000);
			String Act_Data = driver.findElement(By.xpath("//table[@class='table ewTable']/tbody/tr[1]/td[8]/div/span/span")).getText();
			Reporter.log(Exp_data+"      "+Act_Data,true);
			try {
			Assert.assertEquals(Act_Data, Exp_data,"Stock number Not Matching");
			}catch (AssertionError a) {
				System.out.println(a.getMessage());
			}	
		}
		
		//method for capture supplier number into notepad
		public static void captureSupplier(String LocatorType,String LocatorValue)throws Throwable
		{
			String suppliernum="";
			if(LocatorType.equalsIgnoreCase("name"))
			{
				suppliernum = driver.findElement(By.name(LocatorValue)).getAttribute("value");
						
			}
			if(LocatorType.equalsIgnoreCase("xpath"))
			{
				suppliernum = driver.findElement(By.xpath(LocatorValue)).getAttribute("value");
			}
			if(LocatorType.equalsIgnoreCase("id"))
			{
				suppliernum= driver.findElement(By.id(LocatorValue)).getAttribute("value");
			}
			FileWriter fw = new FileWriter("./CaptureData/suppliernumber.txt");
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(suppliernum);
			bw.flush();
			bw.close();
			
		}
		//method verify supplier number from table
		public static void supplierTable()throws Throwable
		{
			FileReader fr = new FileReader("./CaptureData/suppliernumber.txt");
			BufferedReader br = new BufferedReader(fr);
			String Exp_Data = br.readLine();
			if(!driver.findElement(By.xpath(conpro.getProperty("search-textbox"))).isDisplayed())
				driver.findElement(By.xpath(conpro.getProperty("search-panel"))).click();
			driver.findElement(By.xpath(conpro.getProperty("search-textbox"))).clear();
			driver.findElement(By.xpath(conpro.getProperty("search-textbox"))).sendKeys(Exp_Data);
			driver.findElement(By.xpath(conpro.getProperty("search-button"))).click();
			Thread.sleep(4000);
			String Act_Data =driver.findElement(By.xpath("//table[@class='table ewTable']/tbody/tr[1]/td[6]/div/span/span")).getText();
			Reporter.log(Exp_Data+"        "+Act_Data,true);
			try {
				Assert.assertEquals(Act_Data, Exp_Data,"Supplier Number Is Not Matching");
			} catch (Exception e) {
				System.out.println(e.getMessage());
				
			}
		}
		
		//method for capture Customer number into notepad
		
		public static void captureCustomer(String LocatorType,String LocatorValue) throws Throwable {
			String customernum = "";
			if(LocatorType.equalsIgnoreCase("name"))
			{
				customernum = driver.findElement(By.name(LocatorValue)).getAttribute("value");
			}
			if(LocatorType.equalsIgnoreCase("xpath"))
			{
				customernum = driver.findElement(By.xpath(LocatorValue)).getAttribute("value");
			}
			if(LocatorType.equalsIgnoreCase("id"))
			{
				customernum = driver.findElement(By.id(LocatorValue)).getAttribute("value");
			}
			
			FileWriter fw =  new FileWriter("./CaptureData/customernumber.txt");
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(customernum);
			bw.flush();
			bw.close();	
		}
		
		//method to verify Customer number from table
		
		public static void customerTable() throws Throwable 
		{
			FileReader fr = new FileReader("./CaptureData/customernumber.txt");
			BufferedReader br = new BufferedReader(fr);
			String Exp_Data = br.readLine();
			if(!driver.findElement(By.xpath(conpro.getProperty("search-textbox"))).isDisplayed())
				driver.findElement(By.xpath(conpro.getProperty("search-panel"))).click();
			driver.findElement(By.xpath(conpro.getProperty("search-textbox"))).clear();
			driver.findElement(By.xpath(conpro.getProperty("search-textbox"))).sendKeys(Exp_Data);
			driver.findElement(By.xpath(conpro.getProperty("search-button"))).click();
			String Act_Data = driver.findElement(By.xpath("//table[@class='table ewTable']/tbody/tr[1]/td[5]/div/span/span")).getText();
			Reporter.log(Exp_Data+ "    " + Act_Data, true);
			try 
			{
				Assert.assertEquals(Act_Data, Exp_Data,"Customer Number is Not Matching");
			} catch (AssertionError a) {
				System.out.println(a.getMessage());
			}
		}
	
}
















