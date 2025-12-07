package tests;

import java.awt.AWTException;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.epam.healenium.SelfHealingDriver;

import page.LoginPage;
import utils.ScreenshotUtils;

public class LoginTest {
    private WebDriver originalDriver;
    private SelfHealingDriver driver;
    private LoginPage loginPage;

    @Parameters({"browser"})
    @BeforeClass
    public void setup(String browser) throws MalformedURLException {
        String gridUrl = "http://selenium-hub:4444/wd/hub";

        if (browser.equalsIgnoreCase("chrome")) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless=new");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--disable-gpu");
            options.addArguments("--window-size=1920,1080");
            originalDriver = new RemoteWebDriver(new URL(gridUrl), options);
        } else if (browser.equalsIgnoreCase("edge")) {
            EdgeOptions options = new EdgeOptions();
            options.addArguments("--headless=new");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--disable-gpu");
            options.addArguments("--window-size=1920,1080");
            originalDriver = new RemoteWebDriver(new URL(gridUrl), options);
        } else {
            throw new IllegalArgumentException("Browser " + browser + " not supported");
        }

        driver = SelfHealingDriver.create(originalDriver);
        loginPage = new LoginPage(driver);
    }

    @Test
    public void testLogin() throws InterruptedException {
        try {
            driver.get("https://www.amazon.ca");
            Thread.sleep(2000);
            ScreenshotUtils.takeScreenshot(driver, "AmazonHomePage");
            loginPage.login("8259626265", "Natwest@2026");
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    @AfterClass
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
