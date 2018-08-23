package ru.stqa.pft.addressbook;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.fail;

public class ContactCreationTests {


    public static WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();

    @BeforeClass(alwaysRun = true)
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
  public void testContactCreation(){
      goToAddNewContactPage();
      fillContactCreationForm(new ContactData("firstName", "middleName", "lastName",
              "nick", "title", "address", "email", "home"));
      clickEnter();
      goToHomePage();

  }
  private void goToAddNewContactPage(){
      driver.findElement(By.linkText("add new")).click();
  }
  private void fillContactCreationForm(ContactData contactData){
      driver.findElement(By.name("firstname")).click();
      driver.findElement(By.name("firstname")).clear();
      driver.findElement(By.name("firstname")).sendKeys(contactData.getFirstname());
      driver.findElement(By.name("middlename")).click();
      driver.findElement(By.name("middlename")).clear();
      driver.findElement(By.name("middlename")).sendKeys(contactData.getMiddlename());
      driver.findElement(By.name("lastname")).click();
      driver.findElement(By.name("lastname")).clear();
      driver.findElement(By.name("lastname")).sendKeys(contactData.getLastname());
      driver.findElement(By.name("nickname")).click();
      driver.findElement(By.name("nickname")).clear();
      driver.findElement(By.name("nickname")).sendKeys(contactData.getNickname());
      driver.findElement(By.name("title")).click();
      driver.findElement(By.name("title")).clear();
      driver.findElement(By.name("title")).sendKeys(contactData.getTitle());
      driver.findElement(By.name("address")).click();
      driver.findElement(By.name("address")).clear();
      driver.findElement(By.name("address")).sendKeys(contactData.getAddress());
      driver.findElement(By.name("email")).click();
      driver.findElement(By.name("email")).clear();
      driver.findElement(By.name("email")).sendKeys(contactData.getEmail());
      driver.findElement(By.name("home")).click();
      driver.findElement(By.name("home")).clear();
      driver.findElement(By.name("home")).sendKeys(contactData.getHome());
  }

  public void clickEnter(){
      driver.findElement(By.name("submit")).click();
  }

  public void goToHomePage(){
      driver.findElement(By.linkText("home")).click();
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
