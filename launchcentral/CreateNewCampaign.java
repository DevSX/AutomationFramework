package com.nike.automation.smartcart.launchcentral;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.nike.automation.smartcart.base.TestBase;
import com.nike.automation.smartcart.utility.TestUtility;

public class CreateNewCampaign extends TestBase{
	
	@BeforeTest	//check whether testcase is set to be executed or not
	public void isSkip(){		
		if(TestUtility.checkRunMode("CreateNewCampaign")){
			
			throw new SkipException("Runmode is set to No, hence Skipping 'LoginAndLogout' Test Case");
			
		}else{
			
			System.out.println("Runmode is set to Yes, Hence Executing 'LoginAndLogout' Test Case");
		}
	}
	
	@Test(dataProvider = "getData")
	public void testCreateNewCampaign(String CampaignTitle, String TwitterAccount){
		Reporter.log("Calling Login function");
		LaunchCentralActionFunctions.Login();	
		
		Reporter.log("Clicking on Create New Campaign button");
		Assert.assertTrue(TestUtility.clickObj("BTN_CreateNewCampaign"), "User is not able to click on Create New Campaign button");
		
		Reporter.log("Clicking on Digital button");
		Assert.assertTrue(TestUtility.clickObj("BTN_Digital"), "User is not able to click on Digital button");
		
		Reporter.log("Clicking on Start button");
		Assert.assertTrue(TestUtility.clickObj("BTN_Start"), "User is not able to click on Start button");
		
		Reporter.log("Verify Page Title");
		String title = driver.findElement(By.xpath("TTLE")).getText();
		Assert.assertEquals(title, "SET UP YOUR CAMPAINGN INFORMATION", "Page title found is not correct");
		
		Reporter.log("Enter Campaign Title");
		Assert.assertTrue(TestUtility.setText("TXTBOX_CampaignTitle", CampaignTitle), "User is not able to enter Text in the campaign field");		

		Reporter.log("Clicking on US Region button");
		Assert.assertTrue(TestUtility.clickObj("BTN_USRegion"), "User is not able to click on US region button");
		
		Reporter.log("Select value from Twitter Account Dropdown");
		Assert.assertTrue(TestUtility.selectValueFromDrpDwn("TwitterAccount_Dropdown", TwitterAccount), "User is not able to select value from twitter account field");
		
		Reporter.log("Clicking on Campaign type button");
		Assert.assertTrue(TestUtility.clickObj("BNT_CampaignType"), "User is not able to click on Campaign Type button");
		
		Reporter.log("Clicking on Save and Continue button");
		Assert.assertTrue(TestUtility.clickObj("BNT_SaveAndContinue"), "User is not able to click on Save and Continue button");
				
		Reporter.log("Calling Logout function");
		LaunchCentralActionFunctions.Logout();			
	}
	
	@DataProvider
	public Object[][] getData(){		
			return TestUtility.getData("CreateNewCampaign");			
	}
}
