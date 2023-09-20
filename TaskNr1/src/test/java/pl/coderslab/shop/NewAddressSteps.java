package pl.coderslab.shop;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class NewAddressSteps {

    private static WebDriver driver;

    @Given("I'm on mystore main page")
    public void imOnMyStoreMainPage() {

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        driver.get("https://mystore-testlab.coderslab.pl");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

        WebElement signInBtn = driver.findElement(By.className("user-info"));
        signInBtn.click();
    }

    @When("I log in using {string} email and {string} password")
    public void iLogInUsingEmailAndPassword(String email, String password) {

        WebElement emailInput = driver.findElement(By.id("field-email"));
        emailInput.clear();
        emailInput.sendKeys(email);
        WebElement passwdInput = driver.findElement(By.id("field-password"));
        passwdInput.clear();
        passwdInput.sendKeys(password);
        WebElement signInBtn = driver.findElement(By.id("submit-login"));
        signInBtn.click();
    }

    @And("I verify user")
    public void iVerifyUser() {

        WebElement userNameText = driver.findElement(By.xpath("//a[@class='account']"));
        String actualUserName = userNameText.getText();
        String expectedUserName = "Jan Kowalski";
        Assertions.assertEquals(expectedUserName, actualUserName, "Username should match");
    }

    @And("I go to addresses")
    public void iGoToAddresses() {

        driver.findElement(By.id("addresses-link")).click();
    }

    @And("I create new address")
    public void iCreateNewAddress() {

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        driver.findElement(By.xpath("//a[@data-link-action='add-address']")).click();
    }

    @And("I create new address using {string} alias, {string} address, {string} city, {string} zipCode, {string} phone")
    public void newAddressSheetInputs(String alias, String address, String city, String zipCode, String phone) {

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        WebElement aliasInput = driver.findElement(By.id("field-alias"));
        aliasInput.sendKeys(alias);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        WebElement addressInput = driver.findElement(By.id("field-address1"));
        addressInput.sendKeys(address);

        WebElement cityInput = driver.findElement(By.id("field-city"));
        cityInput.sendKeys(city);

        WebElement zipCodeInput = driver.findElement(By.id("field-postcode"));
        zipCodeInput.sendKeys(zipCode);

        WebElement phoneInput = driver.findElement(By.id("field-phone"));
        phoneInput.sendKeys(phone);
    }

    @Then("I can see my new address")
    public void iCanSeeMyNewAddress() {

        driver.findElement(By.cssSelector("button.form-control-submit")).click();
    }

    @And("There is successful message alert {string}")
    public void thereIsSuccessfulMessageAlert(String successAlertAddedAddress) {

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        WebElement isItGreenAlert = driver.findElement(By.className("alert-success"));
        String isItGreenAlertText = isItGreenAlert.getText();
        Assertions.assertEquals(successAlertAddedAddress, isItGreenAlertText, "It should say 'Address successfully added!' ");
    }

    @And("I verify created address through {string} expectedAlias, {string} expectedAddress, {string} expectedCity, {string} expectedZipCode, {string} expectedPhone")
    public void iVerifyNewAddressSheet(String expectedAlias, String expectedAddress, String expectedCity, String expectedZipCode, String expectedPhone) {

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        WebElement addressField = driver.findElement(By.xpath("//section[@id='wrapper']"));
        String addressFieldText = addressField.getText();

        Assertions.assertTrue(addressFieldText.contains(expectedAlias));
        Assertions.assertTrue(addressFieldText.contains(expectedAddress));
        Assertions.assertTrue(addressFieldText.contains(expectedCity));
        Assertions.assertTrue(addressFieldText.contains(expectedZipCode));
        Assertions.assertTrue(addressFieldText.contains(expectedPhone));
    }

    @And("I remove the address")
    public void iRemoveTheAddress() {

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        WebElement addressField = driver.findElement(By.xpath("//section[@id='wrapper']"));
        String addressFieldText = addressField.getText();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        if (addressFieldText.contains("Janek")) {
            WebElement deleteBtn = driver.findElement(By.xpath("/html/body/main/section/div/div/section/section/div[2]/article/div[2]/a[2][@data-link-action='delete-address']"));
            deleteBtn.click();
        }
    }

    @And("I verify if the address is gone by receiving {string} alert")
    public void iVerifyIfTheAddressIsGoneByReceivingAlert(String successAlertDeletedAddress) {

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        WebElement isItGreenDeletionAlert = driver.findElement(By.className("alert-success"));
        String isItGreenDeletionAlertText = isItGreenDeletionAlert.getText();
        Assertions.assertEquals(successAlertDeletedAddress, isItGreenDeletionAlertText, "It should say 'Address successfully deleted!'");
    }

    @And("I close browser")
    public void iCloseBrowser() {

        driver.quit();
    }
}