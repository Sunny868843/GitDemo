package driverfactory;

import org.testng.annotations.Test;

import commonfunctions.Functionlibrary;
import config.Apputil;
import utilities.ExcelFileutil;

public class DiverScript extends Apputil{
	String inputpath = "./Testinput/Dataengine.xlsx";
	String Outputpath = "./Testoutput/HybridResults.xlsx";
	String TCSheet = "TestCases";
	String TSSheet = "TestSteps";
	
	//String TSSheet = "TestSteps";
	
	@Test
	public void StartTest() throws Throwable  
	{
		boolean res = false;
		String tcres = "";
		//Create reference object
		ExcelFileutil xl = new ExcelFileutil(inputpath);
		//Count No of rows in both Sheets
		int TCCount = xl.rowCount(TCSheet);
		int TSCount = xl.cellCount(TSSheet);
	    //Iterate all rows in TCSheet 
		for(int i=1; i<=TCCount; i++)
		{
			//Read Module Statuscell
			String modulestatus = xl.getCellData(TCSheet, i, 2);
			if(modulestatus.equalsIgnoreCase("Y"))
			{
				//Read TCId Cell
				String tcid  = xl.getCellData(TCSheet, i, 0);
				//Iterate All Rows TSSSheet
				for(int j=1; j<=TSCount; j++)
				{
					//Read TCID Cell from TSSHEtt
					String tsid = xl.getCellData(TSSheet, j, 0);
					if(tcid.equalsIgnoreCase(tsid)) 
					{
						//Now REad All keyword cell data
						String Keyword = xl.getCellData(TSSheet, j, 3);
						if(Keyword.equalsIgnoreCase("adminlogin"))
						{
							String para1 = xl.getCellData(TSSheet, j, 5);
							String para2 = xl.getCellData(TSSheet, j, 6);
							res = Functionlibrary.adminLogin(para1, para2);
							
						}
						else if(Keyword.equalsIgnoreCase("branchcreate")) 
						{
							String para1 = xl.getCellData(TSSheet, j, 5);
						    String para2 = xl.getCellData(TSSheet, j, 6);
							String para3 = xl.getCellData(TSSheet, j, 7);
							String para4 = xl.getCellData(TSSheet, j, 8);
							String para5 = xl.getCellData(TSSheet, j, 9);
						    String para6 = xl.getCellData(TSSheet, j, 10);
							String para7 = xl.getCellData(TSSheet, j, 11);
							String para8 = xl.getCellData(TSSheet, j, 12);
							String para9 = xl.getCellData(TSSheet, j, 13);
							Functionlibrary.Branches_Click();
							res = Functionlibrary.branchcreate(para1, para2, para3, para4, para5, para6, para7, para8, para9);
						}
						else if(Keyword.equalsIgnoreCase("branchUpdate"))
							{
							String para1 = xl.getCellData(TSSheet, j, 5);
						    String para2 = xl.getCellData(TSSheet, j, 6);
							String para3 = xl.getCellData(TSSheet, j, 9);
							String para4 = xl.getCellData(TSSheet, j, 10);
							Functionlibrary.Branches_Click();
							res = Functionlibrary.branchUpdate(para1, para2, para3, para4);
							}
						else if(Keyword.equalsIgnoreCase("adminlogout"))
						{
							res = Functionlibrary.adminlogout();
						}   
					
						String tsres = "";
						if(res)
						{
							//if res is true wite as pass into  status cell
							tsres="Pass";
							xl.setCellData(TSSheet, j, 4, tsres, Outputpath); 
							
						}
						else
						{
							//if res is false write as fail into status cell
							tsres = "fail";
							xl.setCellData(TSSheet, j, 4,tsres, Outputpath);
						}
						tcres = tsres;
						
					}
				}
				//Write as tcres into TCsheet
				xl.setCellData(TCSheet, i, 3, tcres, Outputpath);
			}
			else
			{
				//which Testcase flag to N write as blocked into status cell in TCSheet
				xl.setCellData(TCSheet, i, 3, "Blocked", Outputpath);
				
			}
			
			
			
			
		}
		
	}
	

}
