package AutomationModel;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CountryCodesAutomated extends AbstractAutomation implements AutomationInterface {

  private final String availableCountriesString =
      "Albania,Anguilla,Antigua and Barbuda,Aruba,Australia,Austria,Bahamas,Barbados,"
          + "Belgium,Belize,Bermuda,Bonaire and Sint Eustatius and Saba,Bulgaria,Canada,Cayman Islands,Chile,China,Colombia,"
          + "Costa Rica,Cuba,Curacao,Cyprus,Czech Republic,Denmark,Dominica,Dominican Republic,Ecuador,El Salvador,Estonia,"
          + "Finland,France,Germany,Greece,Grenada,Guadeloupe,Guam,Guatemala,Gulf of Mexico,Haiti,Honduras,Hong Kong,Hungary,"
          + "India,Indonesia,Ireland,Israel,Italy,Jamaica,Japan,Latvia,Liechtenstein,Lithuania,Luxembourg,Macau,Macedonia,"
          + "Martinique,Mexico,Montserrat,Netherlands,New Zealand,Nicaragua,Norway,Panama,Peru,Philippines,Poland,Portugal,"
          + "Puerto Rico,Romania,Saint Barthélemy,Saint Kitts and Nevis,Saint Lucia,Saint Martin,Saint Vincent and the Grenadines"
          + ",Saipan,Serbia,Singapore,Sint Maarten,Slovakia,Slovenia,South Korea,Sweden,Switzerland,Taiwan,Trinidad and Tobago"
          + ",Turkey,Turks and Caicos Islands,United Kingdom,United States,Venezuela,Vietnam,Virgin Islands (US),Virgin Islands British";

  private final List<String> availableCountries;


  public CountryCodesAutomated(String driverPath, WebDriver browser) {
    super(driverPath, browser);
    availableCountries = Arrays.asList(availableCountriesString.split("\\s*,\\s*"));
  }

  @Override
  public void execute() throws InterruptedException {
    setUp();

    browser.navigate().to("https://www.iban.com/country-codes");

    List<WebElement> tableRows = browser
        .findElements(By.xpath("/html/body/div[1]/div[2]/div/div/div/div/table/tbody/tr"));
    List<String> countryCodes = new ArrayList<>();

    for (WebElement e : tableRows) {
      List<WebElement> rowContents = e.findElements(By.cssSelector("td"));
      if (availableCountries.contains(rowContents.get(0).getText())) {
        String countryCode = rowContents.get(1).getText();
        countryCodes.add(countryCode);
      }
    }

    try {
      FileWriter writer = new FileWriter(".\\src\\GeneratedOutput\\countryCodes.txt");
      for (String code : countryCodes) {
        writer.write(String.format("| %s |", code));
        writer.write(System.getProperty("line.separator"));
      }

      writer.close();

    } catch (IOException e) {
      System.out.println("An error occurred");
      e.printStackTrace();
    }

    tearDown();
  }
}
