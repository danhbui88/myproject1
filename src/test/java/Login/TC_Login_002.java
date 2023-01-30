package Login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TC_Login_002 {
	
	public static String browser = "chrome";
	public static WebDriver driver;

	@BeforeTest
	public void logintoapplication() {
		if (browser.equals("Firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else if (browser.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (browser.equals("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}

		driver.get("https://qa.alphacco.com/Login");
		driver.findElement(By.id("MainContent_txtEmail")).sendKeys("alphaccotest1@outlook.com");
		driver.findElement(By.id("MainContent_txtPassword")).sendKeys("wrongpass1!");
		driver.findElement(By.id("MainContent_btnLogin")).click();
	}

	@AfterTest
	public void logoutfromapplication() {
		driver.quit();
	}

	@Test
	public void login1() throws InterruptedException {
		Thread.sleep(3000);
		String mytitle=driver.findElement(By.xpath("//*[@id=\"MainContent_loginPanelBody\"]/p")).getText();
		String expectedtitle=" Invalid user id or password";
		Assert.assertEquals(mytitle, expectedtitle);
		System.out.println("User is not able to login");
		driver.close();
	}


}
