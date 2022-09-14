package Pages;

import Utility.BaseAppiumMethods;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

public class Account extends BaseAppiumMethods
{
    private AppiumDriver<MobileElement> driver = null;

    public Account(AppiumDriver<MobileElement> appiumDriver)
    {
        super(appiumDriver);
        driver = appiumDriver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public void goToAccount()
    {
        driver.findElement(By.id("")).click();
    }
}
