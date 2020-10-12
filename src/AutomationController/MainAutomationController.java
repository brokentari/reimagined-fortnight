package AutomationController;

import AutomationModel.AutomationInterface;
import AutomationModel.CountryCodesAutomated;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class MainAutomationController {

  public static void main(String[] args) throws InterruptedException {
    String driverPath = System.getenv("DRIVERPATH");
    System.setProperty("webdriver.gecko.driver", driverPath);
    WebDriver browser = new FirefoxDriver();

    AutomationInterface wellness = new CountryCodesAutomated(driverPath, browser);

    wellness.execute();
  }
}
