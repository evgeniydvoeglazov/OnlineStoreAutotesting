import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class PlacingAnOrderTest {
    public WebDriver driver;
    public WebDriverWait wait;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    private final By firstNameLocator = By.cssSelector("input#billing_first_name.input-text");
    private final By lastNameLocator = By.cssSelector("input#billing_last_name.input-text");
    private final By addressLocator = By.cssSelector("input#billing_address_1.input-text");
    private final By cityLocator = By.cssSelector("input#billing_city.input-text");
    private final By stateLocator = By.cssSelector("input#billing_state.input-text");
    private final By postCodeLocator = By.cssSelector("input#billing_postcode.input-text");
    private final By phoneNumberLocator = By.cssSelector("input#billing_phone.input-text");
    private final By buttonPlaceAnOrder = By.cssSelector("button#place_order.button.alt");
    private final By commentsLocator = By.cssSelector("textarea#order_comments.input-text");
    private final By orderHasBeenReceivedLocator = By.xpath("//p[@class='woocommerce-notice woocommerce-notice--success woocommerce-thankyou-order-received']");
    private final By loginButtonLocator = By.cssSelector("a.account");
    private final By inputUserNameLocator = By.xpath("//input[@id='username']");
    private final By inputPasswordLocator = By.xpath("//input[@id='password']");
    private final By productSearchBarLocator = By.cssSelector("input.search-field");
    private final By productSearchBarSubmitLocator = By.cssSelector("button.searchsubmit");
    private final By addToCartButtonLocator = By.xpath("(//*[@class='button product_type_simple add_to_cart_button ajax_add_to_cart'])[4]");
    private final By buttonMoreDetailingLocator = By.cssSelector("a.added_to_cart.wc-forward");
    private final By checkoutButtonLocator = By.cssSelector("a.checkout-button.button.alt.wc-forward");
    private final By enterButtonAuthorizationLocator = By.cssSelector("button.woocommerce-button.button.woocommerce-form-login__submit");
    private final By lightBoxOrderLocator = By.cssSelector(".content-inner.clearfix");

    @Test
    public void placingAnOrder_fillingInAllFieldsForPlacingAnOrder_theOrderWasSuccessfullyCompleted() {
        driver.navigate().to("https://intershop5.skillbox.ru/");
        driver.findElement(loginButtonLocator).click();
        driver.findElement(inputUserNameLocator).sendKeys("shopping");
        driver.findElement(inputPasswordLocator).sendKeys("1234");
        driver.findElement(enterButtonAuthorizationLocator).click();
        driver.findElement(productSearchBarLocator).sendKeys("Смартфон");
        driver.findElement(productSearchBarSubmitLocator).click();
        driver.findElement(addToCartButtonLocator).click();
        driver.findElement(buttonMoreDetailingLocator).click();
        driver.findElement(checkoutButtonLocator).click();
        driver.findElement(firstNameLocator).clear();
        driver.findElement(firstNameLocator).sendKeys("Алексей");
        driver.findElement(lastNameLocator).clear();
        driver.findElement(lastNameLocator).sendKeys("Козлов");
        driver.findElement(addressLocator).clear();
        driver.findElement(addressLocator).sendKeys("Ленина 3");
        driver.findElement(cityLocator).clear();
        driver.findElement(cityLocator).sendKeys("Екатеринбург");
        driver.findElement(stateLocator).clear();
        driver.findElement(stateLocator).sendKeys("Свердловская область");
        driver.findElement(postCodeLocator).clear();
        driver.findElement(postCodeLocator).sendKeys("620000");
        driver.findElement(phoneNumberLocator).clear();
        driver.findElement(phoneNumberLocator).sendKeys("+79012345678");
        driver.findElement(commentsLocator).clear();
        driver.findElement(commentsLocator).sendKeys("Позвонить за час");
        driver.findElement(buttonPlaceAnOrder).click();
        String actualTitleOrder = driver.findElement(orderHasBeenReceivedLocator).getText();
        String expectedTitleOrder = "Спасибо! Ваш заказ был получен.";
        Assert.assertEquals("Статус заказа не верный", expectedTitleOrder, actualTitleOrder);
    }
}
