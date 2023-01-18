import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class NewAddressSteps {

    private WebDriver driver;

    @Given("user is logged in")
    public void userIsLoggedIn() {
        System.setProperty("webdriver.chrome.driver",
                "src/main/resources/drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://mystore-testlab.coderslab.pl/index.php");
        driver.findElement(By.xpath("//*[@id=\"_desktop_user_info\"]/div/a/span")).click();
        driver.findElement(By.name("email")).sendKeys("ag@test.pl");
        driver.findElement(By.name("password")).sendKeys("12345ag");
        driver.findElement(By.id("submit-login")).click();

    }

    @When("user click on Adresses button and Create new adress")
    public void userClickOnAdressesButtonAndCreateNewAdress() {
        driver.findElement(By.id("addresses-link")).click();
        driver.findElement(By.cssSelector("#content > div.addresses-footer > a > span")).click();
    }

    @And("user fill the form with {string}, {string}, {string}, {string}, {string}, {string}")
    public void userFillTheFormWith(String alias, String address, String city, String postalCode, String country, String phone) {
        driver.findElement(By.id("field-alias")).sendKeys(alias);
        driver.findElement(By.id("field-address1")).sendKeys(address);
        driver.findElement(By.id("field-city")).sendKeys(city);
        driver.findElement(By.id("field-postcode")).sendKeys(postalCode);
        driver.findElement(By.id("field-id_country")).sendKeys(country);
        driver.findElement(By.id("field-phone")).sendKeys(phone);
    }

    @Then("user cliks save button and new address is created")
    public void userCliksSaveButtonAndNewAddressIsCreated() {
        driver.findElement(By.cssSelector("#content > div > div > form > footer > button")).click();
    }

    @And("user see new address with new data {string}, {string}, {string}, {string}, {string}, {string}")
    public void userSeeNewAddressWithNewData(String alias, String address, String city, String postalCode, String country, String phone) {
        String NewAddress = driver.findElement(By.xpath("/html/body/main/section/div/div/section/section/div[2]/article/div[1]/address")).getText();
        NewAddress.contains(alias);
        NewAddress.contains(address);
        NewAddress.contains(city);
        NewAddress.contains(postalCode);
        NewAddress.contains(country);
        NewAddress.contains(phone);
        driver.quit();

    }

}
