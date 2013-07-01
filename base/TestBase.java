package com.nike.automation.smartcart.base;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;

import org.openqa.selenium.ie.InternetExplorerDriver;


import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.nike.automation.smartcart.utility.Xls_Reader;

public class TestBase {
	
	private static final String Webdriver_Nike = null;
	public static Properties config=null;
	public static Properties OR=null;
	public static Properties data=null;
	
	public static WebDriver driver=null;
	
	public static Xls_Reader datatable=null;
	
	
	@BeforeSuite
	public void initalize(){
		
		try{
			config = new Properties();
			FileInputStream fp = new FileInputStream(System.getProperty("user.dir")+"\\src\\com\\nike\\automation\\smartcart\\config\\config.properties");
			config.load(fp);
			
			//loading the object repository
			OR = new Properties();
			fp = new FileInputStream(System.getProperty("user.dir")+"\\src\\com\\nike\\automation\\smartcart\\config\\OR.properties");
			OR.load(fp);
			
			datatable= new Xls_Reader(System.getProperty("user.dir")+"\\src\\com\\nike\\automation\\smartcart\\xls\\Controller.xlsx");
			
			//loading the data file
			data = new Properties();
			fp = new FileInputStream(System.getProperty("user.dir")+"\\src\\com\\nike\\automation\\smartcart\\config\\data.properties");
			OR.load(fp);			
			
			
			//checking the type of browser			
			if(config.getProperty("browserType").equalsIgnoreCase("IE")){
				driver = new InternetExplorerDriver();
				
			}else if(config.getProperty("browserType").equalsIgnoreCase("Firefox")){
				
				driver = new FirefoxDriver();
				
			}else if(config.getProperty("browserType").equalsIgnoreCase("Chrome")){
				
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\Tools\\chromedriver.exe");
				driver = new ChromeDriver();
				
			}else if(config.getProperty("browserType").equalsIgnoreCase("Firefox_Profile")){
				
				File profiler = new File(System.getProperty("user.dir")+"\\src\\com\\nike\\automation\\smartcart\\profile\\Nike.FirefoxProfile");
				FirefoxProfile profile = new FirefoxProfile(profiler);
				driver = new FirefoxDriver(profile);
			}
			
		
		}catch(Exception e){
			
			e.printStackTrace();
			Assert.fail("failing since configuration, OR, controller sheet has not loaded successfully");
		}
	
			//ProfilesIni prof =new ProfilesIni();
			//FirefoxProfile p =prof.getProfile("Webdriver.Nike");
			//FirefoxDriver driver = new FirefoxDriver(p);
			
			
			
	}
	
	
	public static WebElement getObject(String xpathKey){
			
		WebElement obj = null;
		try{
			obj = driver.findElement(By.xpath(OR.getProperty(xpathKey)));
				
		}catch(Exception e){
				
				
		}
			
		return obj;
	}
		
		
		
	public static void sleep(int intSecond){
			
		try{
			Thread.sleep(700);
				
		}catch(Exception e) {
				
			e.printStackTrace();
				
		}
	}
	
}
