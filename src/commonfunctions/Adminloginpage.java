package commonfunctions;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Adminloginpage {
	// //define Repository for login
	@FindBy(name = "txtUsername")
	WebElement username;
	@FindBy(name = "txtPassword")
	WebElement password;
	@FindBy(name = "Submit")
	WebElement Loginbtn;
	//write method
	public void adminLogin(String user,String pass)
	{
		username.sendKeys(user);
		password.sendKeys(pass);
		Loginbtn.click();
	}

}
