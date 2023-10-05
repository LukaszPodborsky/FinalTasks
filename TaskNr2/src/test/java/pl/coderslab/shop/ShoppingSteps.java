package pl.coderslab.shop;

import com.sun.source.tree.IfTree;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.Copy;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Random;

public class ShoppingSteps {
    private static WebDriver driver;
    private String totalPriceBeforeSubmittingText;

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
        WebElement cursorHoverOverSweater = driver.findElement(By.xpath("//*[@data-id-product='2']"));
        Actions hoverOverSweater = new Actions(driver);
        hoverOverSweater.moveToElement(cursorHoverOverSweater).perform();

        WebElement quickViewBtn = driver.findElement(By.xpath("//*[@data-id-product='2']//i"));
        quickViewBtn.click();
    }

    @And("I double-check the {string} discount")
    public void iDoubleCheckTheDiscountDiscount(String discount) throws InterruptedException {
        WebDriverWait discountWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        By save20discount = By.xpath("//*[@id='quickview-modal-2-9']/div/div/div[2]/div/div[2]/div/div[2]/div/span[2]");
        discountWait.until(ExpectedConditions.visibilityOfElementLocated(save20discount));

        WebElement discountInCart = driver.findElement(By.xpath("//*[@id='quickview-modal-2-9']/div/div/div[2]/div/div[2]/div/div[2]/div/span[2]"));
        String discountText = discountInCart.getText();
        Assertions.assertEquals(discount, discountText, "It should say 'SAVE 20%'");
    }

    @And("I select {string} size")
    public void iSelectSizeSize(String size) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        By sizeList = By.id("group_1");
        wait.until(ExpectedConditions.visibilityOfElementLocated(sizeList));
        WebElement sizeDropDown = driver.findElement(By.id("group_1"));

        Select sizeSelect = new Select(sizeDropDown);
        sizeSelect.selectByVisibleText(size);
    }

    @And("I choose {string} the amount of the selected product")
    public void iChooseQuantityTheAmountOfTheSelectedProduct(String quantity) {
        WebElement quantityInput = driver.findElement(By.id("quantity_wanted"));
        quantityInput.click();
        quantityInput.clear();
        quantityInput.sendKeys(quantity);
    }

    @And("I add the product\\(s) to a shopping cart")
    public void iAddTheProductSToAShoppingCart() {
        driver.findElement(By.className("add-to-cart")).click();
    }

    @And("I check if successful message popped up {string}")
    public void iCheckIfSuccessfulMessagePoppedUp(String successfulAlert) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        By messageLabel = By.id("myModalLabel");
        wait.until(ExpectedConditions.visibilityOfElementLocated(messageLabel));
        WebElement alertLabel = driver.findElement(By.id("myModalLabel"));
        String alertLabelText = alertLabel.getText();
        Assertions.assertEquals(successfulAlert, alertLabelText, "There should be text displayed such as: '\uE876Product successfully added to your shopping cart'");
    }

    @And("I proceed to checkout")
    public void iProceedToCheckout() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        By checkoutBtn = By.xpath("//*[contains(text(), 'Proceed to checkout')]");
        wait.until(ExpectedConditions.visibilityOfElementLocated(checkoutBtn));
        WebElement proceedToCheckoutBtn = driver.findElement(By.xpath("//*[contains(text(), 'Proceed to checkout')]"));
        proceedToCheckoutBtn.click();
        WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(3));
        By nextCheckoutBtn = By.xpath("//*[@id='main']/div/div[2]/div[1]/div[2]/div/a");
        wait1.until(ExpectedConditions.visibilityOfElementLocated(nextCheckoutBtn));
        WebElement nextProceedToCheckoutBtn = driver.findElement(By.xpath("//*[@id='main']/div/div[2]/div[1]/div[2]/div/a"));
        nextProceedToCheckoutBtn.click();
    }

    @And("I validate my address: expectedName {string}, expectedAddress {string}, expectedCity {string}, expectedZipCode {string}, expectedCountry {string}, expectedPhone {string}")
    public void iValidateMyAddress(String expectedName, String expectedAddress, String expectedCity, String expectedZipCode, String expectedCountry, String expectedPhone) {
        WebElement addressField = driver.findElement(By.xpath("//div[@class='address']"));
        String addressFieldText = addressField.getText();
        Assertions.assertTrue(addressFieldText.contains(expectedName));
        Assertions.assertTrue(addressFieldText.contains(expectedAddress));
        Assertions.assertTrue(addressFieldText.contains(expectedCity));
        Assertions.assertTrue(addressFieldText.contains(expectedZipCode));
        Assertions.assertTrue(addressFieldText.contains(expectedCountry));
        Assertions.assertTrue(addressFieldText.contains(expectedPhone));
    }

    @And("I continue to shipping method")
    public void iContinueToShippingMethod() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        By continueToShippingMethodBtnVisibility = By.xpath("//button[@name='confirm-addresses']");
        wait.until(ExpectedConditions.visibilityOfElementLocated(continueToShippingMethodBtnVisibility));
        WebElement continueToShippingMethodBtn = driver.findElement(By.xpath("//button[@name='confirm-addresses']"));
        continueToShippingMethodBtn.click();
    }

    @And("I validate if self pick up radio box is checked")
    public void iValidateIfBoxIsChecked() {
        WebElement selfPickUpRadioBox = driver.findElement(By.xpath("//form[@id='js-delivery']//input[@id='delivery_option_8']"));
        Assertions.assertTrue(selfPickUpRadioBox.isSelected(), "Self pick up radio box should be selected");
        if (!selfPickUpRadioBox.isSelected()) {
            selfPickUpRadioBox.click();
            Assertions.assertTrue(selfPickUpRadioBox.isSelected(), "Self pick up radio box should be selected after clicking on it");
        }
    }

    @And("I proceed to payment")
    public void iProceedToPayment() {
        WebElement confirmDeliveryOptionBtn = driver.findElement(By.name("confirmDeliveryOption"));
        confirmDeliveryOptionBtn.click();
    }

    @And("I pay by check and agree to the terms of service")
    public void iPayByCheckAndAgreeToTheTermsOfService() {
        WebElement payByCheckRadioBox = driver.findElement(By.id("payment-option-1"));
        payByCheckRadioBox.click();
        WebElement termsOfServiceCheckbox = driver.findElement(By.id("conditions_to_approve[terms-and-conditions]"));
        termsOfServiceCheckbox.click();

    }
    @And("I capture the total price")
    public void iCaptureTheTotalPrice() {
        WebElement totalPriceBeforeSubmitting = driver.findElement(By.xpath("//*[@id='js-checkout-summary']/div[2]/div[2]/span[2]"));
        totalPriceBeforeSubmittingText = totalPriceBeforeSubmitting.getText();
    }

    @And("I place order")
    public void iPlaceOrder() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        By placeOrderBtnVisibility = By.id("payment-confirmation");
        wait.until(ExpectedConditions.visibilityOfElementLocated(placeOrderBtnVisibility));
        WebElement placeOrderBtn = driver.findElement(By.xpath("//div[@id='payment-confirmation']//button[@type='submit']"));
        placeOrderBtn.click();
    }

    @Then("I take a screenshot of order details")
    public void iTakeAScreenshotOfOrderDetails() throws IOException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("SSSssmmHHddMMyyyy");
        String timestamp = dateFormat.format(new Date());
        String orderDetails = "orderdetails_" + timestamp + ".png";
        WebElement orderDetailsPartOfThePage = driver.findElement(By.id("content-wrapper"));
        File orderDetailsPartOfThePageScreenshot = orderDetailsPartOfThePage.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(orderDetailsPartOfThePageScreenshot, new File("D:\\Coders\\FinalTasks\\FinalTasks\\TaskNr2\\screenshots\\" + orderDetails));
    }

    @And("I go to my account details")
    public void iGoToMyAccountDetails() {
        WebElement myAccElement = driver.findElement(By.xpath("//a[@class='account'][@title='View my customer account']"));
        myAccElement.click();
    }

    @And("I click on order history and details")
    public void iClickOnOrderHistoryAndDetails() {
        WebDriverWait waitForHistory = new WebDriverWait(driver, Duration.ofSeconds(3));
        By orderHistoryText = By.id("history-link");
        waitForHistory.until(ExpectedConditions.visibilityOfElementLocated(orderHistoryText));
        WebElement orderHistoryBtn = driver.findElement(By.id("history-link"));
        orderHistoryBtn.click();
    }

    @And("I check order status {string}")
    public void iCheckOrderStatus(String status) {
        WebElement latestOrderRowForStatus = driver.findElement(By.xpath("//*[@id='content']/table/tbody/tr[1]/td[4]/span"));
        String latestOrderRowForStatusText = latestOrderRowForStatus.getText();
        Assertions.assertEquals(status, latestOrderRowForStatusText, "It should say 'Awaiting check payment'");
    }

    @And("I verify the total price if it's valid")
    public void iVerifyTheTotalPriceIfItSValid() {
        WebElement latestOrderRowForPrice = driver.findElement(By.xpath("//*[@id='content']/table/tbody/tr[1]/td[2]"));
        String latestOrderRowForPriceText = latestOrderRowForPrice.getText();
        Assertions.assertEquals(totalPriceBeforeSubmittingText, latestOrderRowForPriceText);
    }

    @And("I close the browser")
    public void iCloseTheBrowser() {
        driver.close();
    }
}
