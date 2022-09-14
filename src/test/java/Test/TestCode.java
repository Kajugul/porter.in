package Test;

import Pages.DashBoard;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

public class TestCode extends initializeAppium
{
    DashBoard dashBoard;
    private AppiumDriver<MobileElement> driver;

    @BeforeClass
    public void initDriver() throws IOException
    {
        driver = preCondition();
        dashBoard = new DashBoard(driver);
    }

    @Test
    public void LoginPorter()
    {
        logger = extent.createTest("login into porter");
        dashBoard.getLoginPorter().doLogin();
        log("user has been logged in porter application successfully.");
    }

    @Test
    public void accountVerification()
    {
        logger = extent.createTest("account verification");
        log("verification of account");
        dashBoard.getAccount().goToAccount();
        log("user has been navigated to account page.");
    }
}