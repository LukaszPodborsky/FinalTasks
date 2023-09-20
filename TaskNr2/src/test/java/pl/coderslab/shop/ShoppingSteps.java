package pl.coderslab.shop;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;

public class ShoppingSteps {
    private static WebDriver driver;

    @Given("I'm on mystore main page")
    public void iMOnMystoreMainPage() {

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        driver.get("https://mystore-testlab.coderslab.pl");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

    }

    @And("I log in using {string} email and {string} password")
    public void iLogInUsingEmailAndPassword(String email, String password) {
        WebElement signInHomeBtn = driver.findElement(By.className("user-info"));
        signInHomeBtn.click();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        WebElement emailInput = driver.findElement(By.id("field-email"));
        emailInput.click();
        emailInput.clear();
        emailInput.sendKeys(email);

        WebElement passwdInput = driver.findElement(By.id("field-password"));
        passwdInput.click();
        passwdInput.clear();
        passwdInput.sendKeys(password);

        WebElement signInSubmitBtn = driver.findElement(By.id("submit-login"));
        signInSubmitBtn.click();

    }

    @And("I verify user with {string} name")
    public void iVerifyUserWithExpectedNameName(String name) {
        WebElement accountNameCheck = driver.findElement(By.className("account"));
        String accountNameCheckText = accountNameCheck.getText();
        Assertions.assertEquals(name, accountNameCheckText, "It should say 'Jan Kowalski'.");
    }

    @When("I go to the clothes tab")
    public void iGoToTheClothesTab() {
        driver.findElement(By.id("category-3")).click();
    }


    @And("I hover over Hummingbird Printed Sweater and check if it's {string} off")
    public void iHoverOverHummingbirdPrintedSweaterAndCheckIfItSOff(String sale) {
        WebElement discountOnSweaterOnSearchClothes = driver.findElement(By.xpath("//*[@data-id-product='2']//li"));
        String discountOnSweaterOnSearchClothesText = discountOnSweaterOnSearchClothes.getText();
        Assertions.assertEquals(sale, discountOnSweaterOnSearchClothesText, "It should be -20% off");
    }

    @And("I choose the product to quick-view it")
    public void iChooseTheProductToQuickViewIt() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

        WebElement cursorHoverOverSweater = driver.findElement(By.xpath("//*[@data-id-product='2']"));
        Actions hoverOverSweater = new Actions(driver);
        hoverOverSweater.moveToElement(cursorHoverOverSweater).perform();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        WebElement quickViewBtn = driver.findElement(By.xpath("//*[@data-id-product='2']//i"));
        quickViewBtn.click();

    }
}
