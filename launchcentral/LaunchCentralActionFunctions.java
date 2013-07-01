package com.nike.automation.smartcart.launchcentral;

import org.testng.Assert;
import org.testng.Reporter;

import com.nike.automation.smartcart.base.TestBase;
import com.nike.automation.smartcart.utility.TestUtility;

public class LaunchCentralActionFunctions extends TestBase{
	
	/*	Function Name := Login
	 * 	Description := This will login into launch central application
	 *  Return value := None
	 *  Arguments := None
	 */
	public static void Login(){
		Reporter.log("Navigate to url");
		driver.navigate().to(config.getProperty("testSiteURL_Feature5"));
		
		Reporter.log("maximize the browser window");
		driver.manage().window().maximize();
		
		Reporter.log("Enter Username");
		Assert.assertTrue(TestUtility.setText("Login_Username", config.getProperty("username")), "User is not able to input username");
		
		Reporter.log("Enter Password");
		Assert.assertTrue(TestUtility.setText("Login_Password", config.getProperty("password")), "User is not able to input password");
		
		Reporter.log("Clicking on the Sign In Button");
		Assert.assertTrue(TestUtility.clickObj("Sign In"), "User is not able to click on Sign In button");
		
		Reporter.log("Verify user logged in successfully by checking existance of Create New Campaign button on dashboard");
		Assert.assertTrue(TestUtility.isObjPresent("BTN_CreateNewCampaign", 3), "Create New Campaign button is not available on page");
		
	}
	
	
	
	
	/*	Function Name := Logout
	 * 	Description := This will logout from launch central application
	 *  Return value := None
	 *  Arguments := None
	 */
	public static void Logout(){
		Reporter.log("Clicking on the logout drop down");
		Assert.assertTrue(TestUtility.clickObj("Dropdown_Logout"), "User is not able to click on logout dropdown");
		
		Reporter.log("Clicking on the Logout Button");
		Assert.assertTrue(TestUtility.isObjPresent("Logout_Window", 3), "Logout button is not available on page");
		Assert.assertTrue(TestUtility.clickObj("Logout_Window"), "User is not able to click on logout button");
	}
}
