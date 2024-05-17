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

public class ShoppingCartTest {
    public WebDriver driver;
    public WebDriverWait wait;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    private final By returnButtonLocator = By.cssSelector(".restore-item");
    private final By cartIsEmptyLocator = By.cssSelector("p.cart-empty.woocommerce-info");
    private final By deleteProductLocator = By.cssSelector("a.remove");
    private final By fieldCouponCodeLocator = By.cssSelector("input#coupon_code");
    private final By titleCheckoutPageLocator = By.cssSelector("h2.post-title");
    private final By checkoutButtonLocator = By.cssSelector("a.checkout-button.button.alt.wc-forward");
    private final By applyCouponButtonLocator = By.cssSelector("button[name='apply_coupon']");
    private final By productSearchBarLocator = By.cssSelector("input.search-field");
    private final By productSearchBarSubmitLocator = By.cssSelector("button.searchsubmit");
    private final By addToCartButtonLocator = By.xpath("(//*[@class='button product_type_simple add_to_cart_button ajax_add_to_cart'])[4]");
    private final By buttonMoreDetailingLocator = By.cssSelector("a.added_to_cart.wc-forward");
    private final By discountCertificateMassageLocator = By.cssSelector(".woocommerce-message");
    private final By deleteCertificateButtonLocator = By.cssSelector("a.woocommerce-remove-coupon");
    private final By productInTheCart = By.cssSelector("td.product-name");
    private final By discountLightBoxLocator = By.cssSelector("tr.cart-discount.coupon-sert500");

    @Test
    public void shoppingCart_deleteProductInCart_productIsDeleteFromCart() {
        driver.navigate().to("https://intershop5.skillbox.ru/");
        driver.findElement(productSearchBarLocator).sendKeys("Смартфон");
        driver.findElement(productSearchBarSubmitLocator).click();
        driver.findElement(addToCartButtonLocator).click();
        driver.findElement(buttonMoreDetailingLocator).click();
        driver.findElement(deleteProductLocator).click();
        String actualResult = driver.findElement(cartIsEmptyLocator).getText();
        String expectedResult = "Корзина пуста.";
        Assert.assertEquals("Не появляется надпись,что товар удален", expectedResult, actualResult);
    }

    @Test
    public void shoppingCart_deleteProductAndReturnProductInCart_productIsDeleteAndReturnInCurt() {
        driver.navigate().to("https://intershop5.skillbox.ru/");
        driver.findElement(productSearchBarLocator).sendKeys("Смартфон");
        driver.findElement(productSearchBarSubmitLocator).click();
        driver.findElement(addToCartButtonLocator).click();
        driver.findElement(buttonMoreDetailingLocator).click();
        driver.findElement(deleteProductLocator).click();
        driver.findElement(returnButtonLocator).click();
        String actualProductInCart = driver.findElement(productInTheCart).getText();
        String expectedProductInCart = "SAMSUNG Galaxy S20 8/128Gb, SM-G980F, серый";
        Assert.assertEquals("Товар не вернулся в корзину",expectedProductInCart,actualProductInCart);
    }

    @Test
    public void shoppingCart_addDiscountCoupon_discountCouponIsDone() {
        driver.navigate().to("https://intershop5.skillbox.ru/");
        driver.findElement(productSearchBarLocator).sendKeys("Смартфон");
        driver.findElement(productSearchBarSubmitLocator).click();
        driver.findElement(addToCartButtonLocator).click();
        driver.findElement(buttonMoreDetailingLocator).click();
        driver.findElement(fieldCouponCodeLocator).sendKeys("sert500");
        driver.findElement(applyCouponButtonLocator).click();
        String actualPriceWithCoupon = driver.findElement(discountCertificateMassageLocator).getText();
        String expectedPriceWithCoupon = "Купон успешно добавлен.";
        Assert.assertEquals("Купон со скидкой не применился",expectedPriceWithCoupon,actualPriceWithCoupon);
    }

    @Test
    public void shoppingCart_applyAndDeleteCoupon_couponIsDelete() {
        driver.navigate().to("https://intershop5.skillbox.ru/");
        driver.findElement(productSearchBarLocator).sendKeys("Смартфон");
        driver.findElement(productSearchBarSubmitLocator).click();
        driver.findElement(addToCartButtonLocator).click();
        driver.findElement(buttonMoreDetailingLocator).click();
        driver.findElement(fieldCouponCodeLocator).sendKeys("sert500");
        driver.findElement(applyCouponButtonLocator).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(deleteCertificateButtonLocator));
        driver.findElement(deleteCertificateButtonLocator).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(discountLightBoxLocator));
        String actualTotalPriceWithoutCoupon = driver.findElement(discountCertificateMassageLocator).getText();
        String expectedTotalPriceWithoutCoupon = "Купон удален.";
        Assert.assertEquals("Купон со скидкой не удаляется",expectedTotalPriceWithoutCoupon,actualTotalPriceWithoutCoupon);
    }

    @Test
    public void shoppingCart_clickingTheCheckoutButton_goesToTheCheckoutPage() {
        driver.navigate().to("https://intershop5.skillbox.ru/");
        driver.findElement(productSearchBarLocator).sendKeys("Смартфон");
        driver.findElement(productSearchBarSubmitLocator).click();
        driver.findElement(addToCartButtonLocator).click();
        driver.findElement(buttonMoreDetailingLocator).click();
        driver.findElement(checkoutButtonLocator).click();
        String actualTitleCheckoutPage = driver.findElement(titleCheckoutPageLocator).getText();
        String expectedTitleCheckoutPage = "Оформление заказа";
        Assert.assertEquals("Не Открывается страница оформления заказа",expectedTitleCheckoutPage,actualTitleCheckoutPage);
    }
}
