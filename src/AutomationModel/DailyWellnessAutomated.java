package AutomationModel;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DailyWellnessAutomated extends AbstractAutomation implements AutomationInterface {


  public DailyWellnessAutomated(String driverPath, WebDriver browser) {
    super(driverPath, browser);
  }


  @Override
  public void execute() throws InterruptedException {

    setUp();

    System.out.println("navigating to wellness check page");
    browser.navigate().to("https://service.northeastern.edu/wellness");

    WebElement loginButton = new WebDriverWait(browser, 10)
        .until(browser -> browser.findElement(By.name("_eventId_proceed")));

    WebElement usernameFieldBox = browser.findElement(By.id("username"));
    WebElement passwordFieldBox = browser.findElement(By.id("password"));

    usernameFieldBox.sendKeys(System.getenv("MYNEUUSR"));
    passwordFieldBox.sendKeys(System.getenv("MYNEUPWD"));

    loginButton.click();

    WebElement dailyWellnessButton = new WebDriverWait(browser, 20)
        .until(browser -> browser.findElement(By.partialLinkText("Daily Wellness Check")));

    dailyWellnessButton.click();

    WebElement anySymptomsQuestion = new WebDriverWait(browser, 5)
        .until(browser -> browser.findElement(By.id("sp_formfield_have_symptoms")));

    anySymptomsQuestion.findElements(By.className("ng-pristine")).get(1).click();

    WebElement closeContactQuestion = browser.findElement(By.id("sp_formfield_close_contact"));

    closeContactQuestion.findElements(By.className("ng-pristine")).get(1).click();

    browser.findElement(By.className("btn-primary")).click();

    Alert confirmation = browser.switchTo().alert();

    confirmation.accept();

    tearDown();
  }
}
