import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.*;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class ShoppingSteps {

    private WebDriver driver;

    @Given("user is log in")
    public void userIsLogIn() {
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

    @When("user buy some pieces of Hummingbird Printed Sweater in M size")
    public void userBuyPiecesOfHummingbirdPrintedSweaterInMSize() {
        driver.findElement(By.xpath("//*[@id=\"search_widget\"]/form/input[2]")).sendKeys("Hummingbird Printed Sweater");
        driver.findElement(By.xpath("//*[@id=\"search_widget\"]/form/button/i")).click();
        driver.findElement(By.xpath("//*[@id=\"js-product-list\"]/div[1]/div/article/div/div[1]/a/img")).click();
        driver.findElement(By.id("group_1")).sendKeys("M");

        WebElement quantity = driver.findElement(By.cssSelector("#quantity_wanted"));
        quantity.sendKeys(Keys.CONTROL + "a");
        quantity.sendKeys(Keys.DELETE);
        quantity.sendKeys("5");

        driver.findElement(By.xpath("//*[@id=\"add-to-cart-or-refresh\"]/div[2]/div/div[2]/button")).click();
        driver.findElement(By.xpath("//*[@id=\"blockcart-modal\"]/div/div/div[2]/div/div[2]/div/div/a")).click();
        driver.findElement(By.xpath("//*[@id=\"main\"]/div/div[2]/div[1]/div[2]/div/a")).click();

    }

    @And("buy them Pay by Check")
    public void buyThemPayByCheck() {
        driver.findElement(By.xpath("//*[@id=\"checkout-addresses-step\"]/div/div/form/div[2]/button")).click();
        driver.findElement(By.xpath("//*[@id=\"js-delivery\"]/button")).click();
        driver.findElement(By.xpath("//*[@id=\"payment-option-1-container\"]/label/span")).click();
        driver.findElement(By.id("conditions_to_approve[terms-and-conditions]")).click();
        driver.findElement(By.xpath("//*[@id=\"payment-confirmation\"]/div[1]/button")).click();

    }

    @Then("the transaction is done and user has a screenshot")
    public void theTransactionIsDoneAndUserHasAScreenshot() throws IOException {
        TakesScreenshot shopscreen=(TakesScreenshot)driver;
        File source=shopscreen.getScreenshotAs(OutputType.FILE);
        FileHandler.copy(source,new File("C:/CodersLab/testScreenshot.png"));

    }
}
