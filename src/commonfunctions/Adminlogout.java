package commonfunctions;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Adminlogout {
	@FindBy(xpath = "//a[@id='welcome']")
	WebElement clickwelcome;
	@FindBy(xpath = "//a[normalize-space()='Logout']")
	WebElement clicklogout;
	public void adminLogout() throws Throwable
	{
		clickwelcome.click();
		Thread.sleep(1000);
		clicklogout.click();	

}
}
