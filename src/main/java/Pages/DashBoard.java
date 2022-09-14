package Pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class DashBoard
{
    private AppiumDriver driver;
    public LoginPorter loginPorter;
    public Account account;

    public DashBoard(AppiumDriver  driver)
    {
        this.driver = driver;
    }

    public LoginPorter getLoginPorter()
    {
        return (loginPorter == null) ? loginPorter = new LoginPorter(this.driver) : loginPorter;
    }

    public Account getAccount()
    {
        return (account == null) ? account = new Account(this.driver) : account;
    }


}
