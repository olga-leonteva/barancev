package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.fail;

public class ApplicationManager {
  WebDriver driver;
  private SessionHelper sessionHelper;
  private  NavigationHelper navigationHelper;
  private ContactHelper contactHelper;
  private GroupHelper groupHelper;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  public  void init() throws Exception {
    driver = setUpChromeDriver();
    driver.get("http://localhost:8080/addressbook/");
    contactHelper = new ContactHelper(driver);
    groupHelper = new GroupHelper(driver);
    navigationHelper = new NavigationHelper(driver);
    sessionHelper = new SessionHelper(driver);
    sessionHelper.login("admin", "secret");
  }



  public static WebDriver setUpChromeDriver() throws Exception {
    System.out.println("= = = = = = = = = = = = = = =");
    System.out.println("Setting up Chrome Web Driver");

    String chromeDriver = System.getenv().getOrDefault(
            "WEBDRIVER_CHROME_DRIVER",
            "C://Tools//Webdrivers//chromedriver.exe"
    );

    System.out.println("WEBDRIVER_CHROME_DRIVER = " + chromeDriver);

    System.setProperty("webdriver.chrome.driver", chromeDriver);
    WebDriver driver = new ChromeDriver();

    driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);

    System.out.println("= = = = = = = = = = = = = = =");

    return driver;
  }

  public void stop() {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  public boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  public boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  public String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }

  public GroupHelper getGroupHelper() {
    return groupHelper;
  }

  public NavigationHelper getNavigationHelper() {
    return navigationHelper;
  }

  public ContactHelper getContactHelper() {
    return contactHelper;
  }
}
