package AutomationModel;

import org.openqa.selenium.WebDriver;

abstract class AbstractAutomation {

  String driverPath;
  WebDriver browser;

  public AbstractAutomation(String driverPath, WebDriver browser) {
    this.driverPath = driverPath;
    this.browser = browser;
  }

  void setUp() throws InterruptedException {
    System.out.println("*******************");
    System.out.println("launching browser");
    browser.manage().window().maximize();

    Thread.sleep(200);
  }

  void tearDown() throws InterruptedException {
    Thread.sleep(200);

    if (browser != null) {
      System.out.println("closing browser");
      browser.quit();
    }
  }
}
