package com.nike.automation.smartcart.launchcentral;

import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.*;

import com.nike.automation.smartcart.base.TestBase;
import com.nike.automation.smartcart.utility.TestUtility;

public class LoginAndLogout extends TestBase {


	@BeforeTest	//check whether testcase is set to be executed or not
	public void isSkip(){		
		if(TestUtility.checkRunMode("LoginAndLogout")){
			
			throw new SkipException("Runmode is set to No, hence Skipping 'LoginAndLogout' Test Case");
			
		}else{
			
			System.out.println("Runmode is set to Yes, Hence Executing 'LoginAndLogout' Test Case");
		}
	}
	
	
	@Test
	public void testLoginAnLogout(){	
		Reporter.log("Calling Login function");
		LaunchCentralActionFunctions.Login();	
		
		Reporter.log("Calling Logout function");
		LaunchCentralActionFunctions.Logout();	
	}	
	
	
	@AfterTest
	public void closeBrowser(){		
		driver.close();
	}

}
