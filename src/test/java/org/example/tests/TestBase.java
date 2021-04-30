package org.example.tests;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import com.google.common.io.Files;
import org.example.SuiteConfiguration;
import org.example.pages.HomePageHelper;
import org.example.util.LogLog4j;
import org.openqa.selenium.*;

import org.openqa.selenium.json.JsonOutput;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import ru.stqa.selenium.factory.WebDriverPool;

/**
 * Base class for TestNG-based test classes
 */
public class TestBase {

  protected static URL gridHubUrl = null;
  protected static String baseUrl;
  protected static Capabilities capabilities;
//  protected WebDriver driver;
  protected EventFiringWebDriver driver;

  public HomePageHelper homePage;
  public static final String LOGIN = "dan.marley710@gmail.com";
  public static final String PASSWORD = "dan0524003966";
  public static LogLog4j log4j = new LogLog4j();

  public static class MyListener extends AbstractWebDriverEventListener{
    @Override
    public void beforeFindBy(By by, WebElement element, WebDriver driver) {
//    super.beforeFindBy(by, element, driver);
      log4j.info("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
      log4j.info("Find element: " + by);
    }
    @Override
    public void afterFindBy(By by, WebElement element, WebDriver driver) {
//    super.afterFindBy(by, element, driver);
      log4j.info(by + " element was found!");
      log4j.info("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
      log4j.info("");
    }
    @Override
    public void onException(Throwable throwable, WebDriver driver) {
//    System.out.println("Error: " + throwable);
      log4j.error(" (!) Error: " + throwable);

      // SCREENSHOT
      getScreenshot((TakesScreenshot) driver);
    }
  }

  private static void getScreenshot(TakesScreenshot driver) {
    File tmp = driver.getScreenshotAs(OutputType.FILE);
    // Saving name and format
    File screen = new File("screenshot-" + System.currentTimeMillis() + ".png");
    // Making copy
    try {
      Files.copy(tmp,screen);
    } catch (IOException e) {
      e.printStackTrace();
    }
    log4j.info("_______________________________________");
    log4j.info("______ See screenshot: " + screen);
    log4j.info("_______________________________________");
  }

  @BeforeSuite(alwaysRun = true)
  public void initTestSuite() throws IOException {
    SuiteConfiguration config = new SuiteConfiguration();
    baseUrl = config.getProperty("site.url");
    if (config.hasProperty("grid.url") && !"".equals(config.getProperty("grid.url"))) {
      gridHubUrl = new URL(config.getProperty("grid.url"));
    }
    capabilities = config.getCapabilities();
  }

  @BeforeMethod(alwaysRun = true)
  public void initWebDriver() {
//    driver = WebDriverPool.DEFAULT.getDriver(gridHubUrl, capabilities);
    driver = new EventFiringWebDriver(WebDriverPool.DEFAULT.getDriver(gridHubUrl, capabilities));
    driver.register(new MyListener());
    //      homePage = new HomePageHelper(driver); ---------------------- = Обычная инициализация
    homePage = PageFactory.initElements(driver, HomePageHelper.class); // = Способ для PageFactory
    //----Driver initialization. Open Trello application-------
    driver.get(baseUrl);
    homePage.waitUntilPageIsLoaded();

  }

  @AfterMethod(alwaysRun = true)
  public void finishTest(ITestResult result){
    if(result.getStatus()==ITestResult.FAILURE){
      log4j.info("!-!-!-!-!-!-!-!-!-!-!-!-!-!-!-!-!-!-!-!");
      log4j.info("!-!-!-!-!-! Test was failed.. !-!-!-!-!");
      log4j.info("!-!-!-!-!-!-!-!-!-!-!-!-!-!-!-!-!-!-!-!");
      getScreenshot((TakesScreenshot) driver) ;
    }
  }

  @AfterMethod(alwaysRun = true)
  public void tearDown() {
    WebDriverPool.DEFAULT.dismissAll();
  }
}
