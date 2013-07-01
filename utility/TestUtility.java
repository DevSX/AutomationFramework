package com.nike.automation.smartcart.utility;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.nike.automation.smartcart.base.TestBase;

public class TestUtility extends TestBase {
	
	
	public static boolean checkRunMode(String testCase){
		//for the excel file to read
		for(int i=2; i<=datatable.getRowCount("Test Cases"); i++){
			
			if(datatable.getCellData("Test Cases", "TCID", i).equalsIgnoreCase(testCase)){
				
				if(datatable.getCellData("Test Cases", "Runmode", i).equalsIgnoreCase("Y")){
					
					return false;
					
				}else{
					
					return true;
				}
				
			}
		}
		
		return true;
	}
	
	
	
	//two dimension array to get data 0 1,02
	public static Object[][] getData(String sheetName) {
		
			// return and read test data from excel
			int rows = (datatable.getRowCount(sheetName) - 1);
			if(rows <= 0){
				
				Object[][] testData = new Object[1][];
				return testData;
			}
			
			rows = datatable.getRowCount(sheetName);
			int cols = datatable.getColumnCount(sheetName);
			
			Object data[][] = new Object[rows-1][cols];
			
			for(int rowNum=2; rowNum<=rows; rowNum++){
				
				for(int colNum=0; colNum<cols; colNum++){
					
					data[rowNum-2][colNum] = datatable.getCellData(sheetName, colNum, rowNum);
				}
			}
			
			return data;	
	}
	
	
	/**
	 * 
	 * @param xpathKey
	 * @return
	 */
	//Get WebElement
	public static boolean isElementPresent(String xpathKey){
		
		try{
			WebElement webElem = getObject(xpathKey);
			if(!(webElem == null)){
				
				return true;
				
			}else{
				
				return false;
			}
			
		}catch(Exception e){
			
			return false;
			
		}
		
		
	}
	
	
	
	
	/**
	 * This function is used to click any object on the web page
	 * @param xpathKey
	 * @return element - true/false
	 */
	public static boolean clickObj(String xpathKey){
		
		boolean element = false;
		try{
			element = isElementPresent(xpathKey);
			
			if(element==true){
				
				getObject(xpathKey).click();
				
				
			}else{
				
				element = false;
			}
			
			
		}catch(Exception e){
			
			e.printStackTrace();
			Assert.fail("Failing on the clickObj() function");
		}
		
		return element;
	}
	
	
	
	
	public static boolean setText(String xpathKey, String value){
		
		boolean element = false;
		
		try{
			element = isElementPresent(xpathKey);
			if(element ==  true){
				
				// First Clearing the value present in the text field
				getObject(xpathKey).clear();
				
				// typing the value in the text field
				getObject(xpathKey).sendKeys(value);
				element = true;
				
			}else{
				
				element = false;
			}
			
			
		}catch(Exception e){
			
			e.printStackTrace();
			Assert.fail("Failing on the setText() function");
			
		}
		
		return element;
	}
	
	
	
	public static void selectSize(String xpathKey, String strSize){
		
		WebElement sizeOptions = getObject(xpathKey);
//		List<WebElement> sizeList = sizeOptions.findElements(By.cssSelector("li"));
		List<WebElement> sizeList = sizeOptions.findElements(By.tagName("li"));
		
		for(int i=0; i<sizeList.size(); i++){
			
			System.out.println(sizeList.get(i).getText());
			if(strSize.equalsIgnoreCase(sizeList.get(i).getText())){
				
				System.out.println(sizeList.get(i).getText());
//				Actions builder = new Actions(driver);
//				builder.moveToElement(sizeList.get(i)).click();
				
			//	sizeList.get(i).click();
	
				
				break;
			}
			
		}
	}
	
	
	
  
	// Written bY R for testing they  select size
	
	public static void Select_Size_R(String Size_Main) {
		
		String xPath_Start = "html/body/ul[1]/li[";
		String xPath_End = "]/a";
		int i =2;
		boolean end=false;
		
		while (true) {
			try {
				
				String Size_Val = driver.findElement(By.xpath(xPath_Start+i+xPath_End)).getText();
				double temp1 = Double.parseDouble(Size_Main);
				double temp = Double.parseDouble(Size_Val);
			
				if (temp1==temp) {
					String xpath_new = xPath_Start+i+xPath_End;
					System.out.println(xpath_new);
					driver.findElement(By.xpath(xPath_Start+i+xPath_End)).click(); //Need to check this line
					System.out.println("Temp "+temp);
				 }
			   }
			catch (Exception e) { 
				end = true;
				break;
			}if (end)
				break;
			i=i+1;
		}
	}
		//This function is used to select state dropdown to pick a City for address.  .//*[@id='singleState']
		//Place a dropdown functionality here
		public static void SelectTXT_State_SHIPMENTPAGE(String xPath_Dropdown,String State_Name) {
	
						  
				  WebElement dropdown = (WebElement) driver.findElement(By.xpath(xPath_Dropdown));
				  
				  List<WebElement> item = dropdown.findElements(By.tagName("option"));
				  
				  for (int i=0; i<item.size(); i++) {
				   
				   if (item.get(i).getText().equalsIgnoreCase(State_Name)){
				    item.get(i).click();
				   }
				   
				  }
				
			 }
		
		//A Code
		
		public static boolean selectValueFromDrpDwn(String xPathKey, String strValue){
			
			try{
				Select element;
				if(!strValue.equals("")){
					
					element = new Select(getObject(xPathKey));
					element.selectByVisibleText(strValue);
				}
				return true;
				
				
			}catch(Exception e){
				
				e.printStackTrace();
				return false;
			}
			
			
		}

		
		
		public static void Button_Click_ID (String ID_Name){
			
			driver.findElement(By.id(ID_Name)).click();
					
		
		}
		
		
		/**
		 * 
		 * @param xPathKey
		 * @param maxTime
		 * @return boolean flag= true/false
		 */
		//This function is use for when you are on the line waiting for a product. 
		
		public static boolean isObjPresent(String xPathKey, int maxTime){
			
			boolean flag = false;
			
			for(int i=0; i<maxTime; i++) {
				
				try{
					Thread.sleep(1000);
					if(isElementPresent(xPathKey)){
						
						flag = true;
						
					}else{
						
						if(i<=maxTime){
							
							continue;
						}
						
					}
				
				}catch(Exception e){
				
					continue;
				}
			}
			
			return flag;
		}
		
	}
		
	
	

