package ru.stqa.pft.addressbook;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.fail;

public class GroupCreationTests {
//  private WebDriver driver;
public static WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @BeforeClass(alwaysRun = true)
//  public void setUp() throws Exception {
//   // WebDriverWait wait = new WebDriverWait(driver,10);
//    driver = new FirefoxDriver();
////    baseUrl = "https://www.katalon.com/";
//    driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
//  }

  public static void setUpDriver() throws Exception {
    driver = setUpChromeDriver();
    driver.get("http://localhost:8080/addressbook/");
    login("admin", "secret");

  }

  private static void login(String username, String password) {
    driver.findElement(By.name("user")).click();
    driver.findElement(By.name("user")).clear();
    driver.findElement(By.name("user")).sendKeys(username);
    driver.findElement(By.name("pass")).click();
    driver.findElement(By.name("pass")).clear();
    driver.findElement(By.name("pass")).sendKeys(password);
    driver.findElement(By.xpath("//input[@value='Login']")).click();
  }

  private static WebDriver setUpChromeDriver() throws Exception {
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

  @Test
  public void testGroupCreation() throws Exception {

    goToGroupPage();
    initGroupCreation();
    fillGroupForm(new GroupData("test", "test1", "test2"));
    submitGroupCreation();
    reternToGroupPage();
  }

  private void reternToGroupPage() {
    driver.findElement(By.linkText("group page")).click();
  }

  private void submitGroupCreation() {
    driver.findElement(By.name("submit")).click();
  }

  private void fillGroupForm(GroupData groupData) {
    driver.findElement(By.name("group_name")).click();
    driver.findElement(By.name("group_name")).clear();
    driver.findElement(By.name("group_name")).sendKeys(groupData.getName());
    driver.findElement(By.name("group_header")).click();
    driver.findElement(By.name("group_header")).clear();
    driver.findElement(By.name("group_header")).sendKeys(groupData.getHeader());
    driver.findElement(By.name("group_footer")).click();
    driver.findElement(By.name("group_footer")).clear();
    driver.findElement(By.name("group_footer")).sendKeys(groupData.getFooter());
  }

  private void initGroupCreation() {
    driver.findElement(By.name("new")).click();
  }

  private void goToGroupPage() {
    driver.findElement(By.linkText("groups")).click();
  }


  @AfterClass(alwaysRun = true)
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
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
}
