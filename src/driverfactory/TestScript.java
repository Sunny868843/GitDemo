package driverfactory;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import commonfunctions.AddEmppage;
import commonfunctions.Adminloginpage;
import commonfunctions.Adminlogout;
import utilities.ExcelFileutil;

public class TestScript {
	
WebDriver driver;
String inputpath = "./Testinput/Empdata.xlsx";
String outputpath = "./Testoutput/PomResults.xlsx";
@BeforeTest
public void setup()
{
	driver = new FirefoxDriver();
	driver.manage().window().maximize();
	driver.get("http://orangehrm.qedgetech.com/");
	driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	Adminloginpage login = PageFactory.initElements(driver, Adminloginpage.class);
	login.adminLogin("Admin", "Qedge123!@#");	
}
 @Test
 public void starttest() throws Throwable
 {
	ExcelFileutil xl = new ExcelFileutil(inputpath);
	int rc = xl.rowCount("Emp");
	for (int i =1; i<rc; i++)
	{
		String fname= xl.getCellData("Emp", i, 0);
		String mname = xl.getCellData("Emp", i, 1);
		String lname = xl.getCellData("Emp", i, 2);
		AddEmppage emp =PageFactory.initElements(driver, AddEmppage.class);
		boolean res =emp.addEmp(fname, mname, lname);
		if(res)
		{
			//if res is true write as pass
			xl.setCellData("Emp", i, 3, "Pass", outputpath);
		}
		else
		{
			//if res is false write as Fail
			xl.setCellData("Emp", i, 3, "Fail", outputpath);
		}
	}
 }
 
 @AfterTest 
 public void tearDown() throws Throwable
 {
	 Adminlogout logout =PageFactory.initElements(driver, Adminlogout.class);
		logout.adminLogout();
		driver.close();
				 
 }
}

















	 
	 




