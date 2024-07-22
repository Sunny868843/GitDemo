package commonfunctions;

import org.openqa.selenium.By;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;

import config.Apputil;

public class Functionlibrary extends Apputil {
	//method for login
		public static boolean adminLogin(String username,String password)
		{
			driver.findElement(By.xpath(conpro.getProperty("ObjUser"))).sendKeys(username);
			driver.findElement(By.xpath(conpro.getProperty("ObjPass"))).sendKeys(password);
			driver.findElement(By.xpath(conpro.getProperty("ObjLogin"))).click();
			String Expected ="adminflow";
			String Actual = driver.getCurrentUrl();
			if(Actual.toLowerCase().contains(Expected.toLowerCase()))
			{
				Reporter.log("Login Success::"+Expected+"    "+Actual,true);
				return true;
				
			}
			else
			{
				Reporter.log("Login Fail::"+Expected+"    "+Actual,true);
				return false;
			}
		}
		//method for branch button
		public static void Branches_Click()
		{
			driver.findElement(By.xpath(conpro.getProperty("ObjBranches"))).click();
		}
		//method for branch creation
		public static boolean branchcreate(String BranchName,String Address1,String Address2,
				String Address3,String Area,String Zipcode,String Country,String state,String City) throws Throwable
		{
			driver.findElement(By.xpath(conpro.getProperty("ObjNewBranch"))).click();
			driver.findElement(By.xpath(conpro.getProperty("ObjBranchName"))).sendKeys(BranchName);
			driver.findElement(By.xpath(conpro.getProperty("ObjAddress1"))).sendKeys(Address1);
			driver.findElement(By.xpath(conpro.getProperty("ObjAddress2"))).sendKeys(Address2);
			driver.findElement(By.xpath(conpro.getProperty("ObjAddress3"))).sendKeys(Address3);
			driver.findElement(By.xpath(conpro.getProperty("ObjArea"))).sendKeys(Area);
			driver.findElement(By.xpath(conpro.getProperty("ObjZipcode"))).sendKeys(Zipcode);
			new Select(driver.findElement(By.xpath(conpro.getProperty("ObjCountry")))).selectByVisibleText(Country);
			new Select(driver.findElement(By.xpath(conpro.getProperty("ObjState")))).selectByVisibleText(state);
			new Select(driver.findElement(By.xpath(conpro.getProperty("Objcity")))).selectByVisibleText(City);
			driver.findElement(By.xpath(conpro.getProperty("Objsubmit"))).click();
			//capture Alert message
			String Expected = driver.switchTo().alert().getText();
			Thread.sleep(4000);
			driver.switchTo().alert().accept();
			String Actual ="New Branch with id";
			if(Expected.toLowerCase().contains(Actual.toLowerCase()))
			{
				Reporter.log(Expected,true);
				return true;
			}
			else
			{
				Reporter.log("Fail to Create Branch",true);
				return false;
			}
		}
		//method for branch updation
		public static boolean branchUpdate(String branchname,String address,String Area,String zipcode) throws Throwable
		{
			driver.findElement(By.xpath(conpro.getProperty("ObjEdit"))).click();
			WebElement element1 = driver.findElement(By.xpath(conpro.getProperty("ObjBranch")));
			element1.clear();
			element1.sendKeys(branchname);
			WebElement element2 = driver.findElement(By.xpath(conpro.getProperty("ObjAddress")));
			element2.clear();
			element2.sendKeys(address);
			WebElement element3 = driver.findElement(By.xpath(conpro.getProperty("ObjAreaName")));
			element3.clear();
			element3.sendKeys(Area);
			WebElement element4 = driver.findElement(By.xpath(conpro.getProperty("Objzip")));
			element4.clear();
			element4.sendKeys(zipcode);
			driver.findElement(By.xpath(conpro.getProperty("ObjUpdate"))).click();
			String Expected =driver.switchTo().alert().getText();
			Thread.sleep(4000);
			driver.switchTo().alert().accept();
			String Actual ="Branch updated Sucessfully";
			if(Expected.equalsIgnoreCase(Actual))
			{
				Reporter.log(Expected,true);
				return true;
              }
			else
			{
				Reporter.log("Fail to Update Branch",true);
				return false;
			}
		}
		
		
		//Method for logout
		public static boolean adminlogout()
		{
		driver.findElement(By.xpath(conpro.getProperty("ObjLogout"))).click();
		if(driver.findElement(By.xpath(conpro.getProperty("ObjLogin"))).isDisplayed())
		{
			Reporter.log("Logout Sucess", true);
			return true;
		}
		else
		{
			Reporter.log("Login Failed");
			return true;
		}

		}
		
		
		
		
		
		
}
