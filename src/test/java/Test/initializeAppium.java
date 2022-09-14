package Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class initializeAppium
{
    AppiumDriver<MobileElement> driver = null;
    FileInputStream input;
    public static Properties prop = new Properties();
    ExtentReports extent;
    ExtentHtmlReporter htmlReporter;
    ExtentTest logger;

    public AppiumDriver<MobileElement> preCondition() throws IOException
    {
        htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/test-output/testReport.html");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        htmlReporter.config().setDocumentTitle("Extent Report Demo");
        htmlReporter.config().setReportName("Test Report");
        htmlReporter.config().setTheme(Theme.STANDARD);
        htmlReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
        logger = extent.createTest("init");
        logger.log(Status.INFO, "initialization of Appium driver..");
        input = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/config.properties");
        prop.load(input);
        if (prop.getProperty("platform").equalsIgnoreCase("android"))
        {
            DesiredCapabilities cab = new DesiredCapabilities();
            cab.setCapability(MobileCapabilityType.DEVICE_NAME, "realme C30");
            cab.setCapability(MobileCapabilityType.UDID, "2610172000003927");//ab01dcd
            cab.setCapability("platformName", "Android");
            cab.setCapability(MobileCapabilityType.PLATFORM_VERSION, "11");
            cab.setCapability("appPackage", "com.theporter.android.customerapp");
            cab.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
            cab.setCapability("appActivity", "com.theporter.android.customerapp.RootActivity");
            cab.setCapability(MobileCapabilityType.APP, System.getProperty("user.dir") + "/apkFolder/porter_debug.apk");
            cab.setCapability(MobileCapabilityType.NO_RESET, "true");
            cab.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 60);
            cab.setCapability("autoGrantPermissions", true);
            URL url = new URL("http://0.0.0.0:4723/wd/hub");
            driver = new AndroidDriver<MobileElement>(url, cab);
            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        }
        else if (prop.getProperty("platform").equalsIgnoreCase("ios"))
        {
            DesiredCapabilities cab = new DesiredCapabilities();
            cab.setCapability(MobileCapabilityType.DEVICE_NAME, "deviceName");
            cab.setCapability(MobileCapabilityType.UDID, "deviceID");//ab01dcd
            cab.setCapability("platformName", "ios");
            cab.setCapability(MobileCapabilityType.PLATFORM_VERSION, "platformVersion");
            cab.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
            cab.setCapability(MobileCapabilityType.APP, System.getProperty("user.dir") + "/apkFolder/porter_debug.ipa");
            cab.setCapability(MobileCapabilityType.NO_RESET, "true");
            cab.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 60 * 2);
           // URL url = new URL(urlPath);
            //driver = new IOSDriver<MobileElement>(url, cab);
            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        }
        logger.log(Status.INFO, "Appium server started successfully.");
        return driver;
    }

    @AfterTest(alwaysRun = true)
    public void quitAppium() throws InterruptedException
    {

        logger = extent.createTest("quitAppium");
        logger.log(Status.INFO, "quitting the appium server...");
        extent.flush();

        Thread.sleep(5000);
        if (driver != null)
        {
            driver.quit();
        }
        else
        {
            log("appium driver is already closed.");
        }

    }

    public void log(String message)
    {
        Reporter.log(message);
        System.out.println(message);
        logger.log(Status.INFO, message);
    }

}
