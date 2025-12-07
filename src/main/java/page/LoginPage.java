package page;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.ScreenshotUtils;



public class LoginPage {
	WebDriver driver;
    @FindBy(css = "#nav-link-accountList")
    private WebElement signInField;
    
    
    @FindBy(css = "a[data-nav-ref=\"nav_signin\"]")
    private WebElement signInBtn;
    
    @FindBy(css = "#ap_email_login")
    private WebElement usernameField;
    
    @FindBy(css = ".a-button-input")
    private WebElement continueBtn;
    

    @FindBy(css = "#ap_password")
    private WebElement passwordField;

    @FindBy(css = "#signInSubmit")
    private WebElement loginButton;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
	public void login(String user, String pass) throws AWTException, InterruptedException {
    	mouseOver(signInField);
    	ScreenshotUtils.takeScreenshot(driver, "SingInPage");
    	signInBtn.click();
    	 Thread.sleep(2000);
    	 ScreenshotUtils.takeScreenshot(driver, "ContinuePage1");
    	 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    	 wait.until(ExpectedConditions.visibilityOf(usernameField)).sendKeys(user);;
    	 
    	    ScreenshotUtils.takeScreenshot(driver, "ContinuePage");
//        usernameField.sendKeys(user);
    
        continueBtn.click();
//        keyboardActions();
        Thread.sleep(2000);
        passwordField.sendKeys(pass);
        loginButton.click();
        Thread.sleep(2000);
        ScreenshotUtils.takeScreenshot(driver, "AmazonHomeDashboardPage");
    }
    public void mouseOver(WebElement element) {
    	Actions a=new Actions(driver);
    	a.moveToElement(element).build().perform();
    	
	}
    public void keyboardActions() throws AWTException {
    	Robot robot = new Robot();

    	// Type OTP (example: 1234)
    	robot.keyPress(KeyEvent.VK_6);
    	robot.keyRelease(KeyEvent.VK_6);
    	robot.keyPress(KeyEvent.VK_2);
    	robot.keyRelease(KeyEvent.VK_2);
    	robot.keyPress(KeyEvent.VK_6);
    	robot.keyRelease(KeyEvent.VK_6);
    	robot.keyPress(KeyEvent.VK_4);
    	robot.keyRelease(KeyEvent.VK_4);

    	// Press Enter
    	robot.keyPress(KeyEvent.VK_ENTER);
    	robot.keyRelease(KeyEvent.VK_ENTER);
	}
}
