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

public class ProductCatalogTest {
    public WebDriver driver;
    public WebDriverWait wait;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    private final By catalogButtonLocator = By.xpath("//a[text()='Каталог']");
    private final By catalogTitleLocator = By.cssSelector("h1.entry-title");
    private final By catalogLightBoxLocator = By.cssSelector("header#title_bread_wrap.entry-header");

    @Test
    public void catalogTest_clickingOnCatalogButton_goingToCatalogPage() {
        driver.navigate().to("https://intershop5.skillbox.ru/");
        driver.findElement(catalogButtonLocator).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(catalogLightBoxLocator));
        String actualCatalogTitleName = driver.findElement(catalogTitleLocator).getText();
        String expectedCatalogTitleName = "КАТАЛОГ";
        Assert.assertTrue("Не отображается лайтбокс каталога", driver.findElement(catalogLightBoxLocator).isDisplayed());
        Assert.assertEquals("Название каталога отсутствует", expectedCatalogTitleName, actualCatalogTitleName);
    }

    private final By productSearchBarLocator = By.cssSelector("input.search-field");
    private final By catalogTitleFridgeLocator = By.cssSelector("h1.entry-title");
    private final By catalogLightBoxFridgeLocator = By.cssSelector("header#title_bread_wrap.entry-header");
    private final By productSearchBarSubmitLocator = By.cssSelector("button.searchsubmit");

    @Test
    public void catalogFridge_searchingOnSearchBarFridge_goingToFridgeCatalog() {
        driver.navigate().to("https://intershop5.skillbox.ru/");
        driver.findElement(productSearchBarLocator).sendKeys("холодильник");
        driver.findElement(productSearchBarSubmitLocator).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(catalogLightBoxFridgeLocator));
        String actualCatalogTitleFridgeName = driver.findElement(catalogTitleFridgeLocator).getText();
        String expectedCatalogTitleFridgeName = ("РЕЗУЛЬТАТЫ ПОИСКА: “ХОЛОДИЛЬНИК”");
        Assert.assertTrue("Не отображается лайтбокс каталога", driver.findElement(catalogLightBoxFridgeLocator).isDisplayed());
        Assert.assertEquals("Название каталога отсутствует", expectedCatalogTitleFridgeName, actualCatalogTitleFridgeName);

    }

    private final By catalogButtonMainPageLocator = By.xpath("//a[text()='Каталог']");
    private final By catalogTvLocator = By.xpath("(//a[text()='Телевизоры'])[2]");
    private final By catalogProductCategoriesLocator = By.cssSelector("div#secondary.widget-area.secondary-left.sidebar");
    private final By catalogTvTitleLocator = By.cssSelector("h1.entry-title");

    @Test
    public void catalogTvFromProductCategories_chooseTvCatalogFromProductCategories_goingToTvCatalog() {
        driver.navigate().to("https://intershop5.skillbox.ru/");
        driver.findElement(catalogButtonMainPageLocator).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(catalogProductCategoriesLocator));
        driver.findElement(catalogTvLocator).click();
        String actualCatalogTitleTvName = driver.findElement(catalogTvTitleLocator).getText();
        String expectedCatalogTitleTvName = "ТЕЛЕВИЗОРЫ";
        Assert.assertTrue("Не отображается лайтбокс каталога", driver.findElement(catalogTvTitleLocator).isDisplayed());
        Assert.assertEquals("Название каталога отсутствует", expectedCatalogTitleTvName, actualCatalogTitleTvName);
    }

    private final By washingMachineLocator = By.xpath("(//*[@class='attachment-shop_catalog size-shop_catalog wp-post-image'])[3]");
    private final By washingMachineTitleLocator = By.cssSelector("h1.product_title.entry-title");
    private final By buttonAddToCartLocator = By.xpath("(//a[text()='В корзину'])[3]");
    private final By buttonMoreDetailingLocator = By.cssSelector("a.added_to_cart.wc-forward");
    private final By productInTheCart = By.cssSelector("td.product-name");

    @Test
    public void chooseWashingMachine_chooseWashingMachinePage_goingToWashingMachinePage() {
        driver.navigate().to("https://intershop5.skillbox.ru/");
        driver.findElement(productSearchBarLocator).sendKeys("стиральная машина");
        driver.findElement(productSearchBarSubmitLocator).click();
        driver.findElement(washingMachineLocator).click();
        String actualWashingPageTitle = driver.findElement(washingMachineTitleLocator).getText();
        String expectedWashingPageTitle = "Стиральная машина BEKO WRS55P2BWW, фронтальная, 5кг, 1000об/мин";
        Assert.assertTrue("Не отображается лайтбокс каталога", driver.findElement(washingMachineTitleLocator).isDisplayed());
        Assert.assertEquals("Название товара отсутствует", expectedWashingPageTitle, actualWashingPageTitle);
    }
    @Test
    public void catalog_AddingAnItemToTheShoppingCart_theProductIsAddedToTheCart() {
        driver.navigate().to("https://intershop5.skillbox.ru/");
        driver.findElement(productSearchBarLocator).sendKeys("стиральная машина");
        driver.findElement(productSearchBarSubmitLocator).click();
        driver.findElement(buttonAddToCartLocator).click();
        driver.findElement(buttonMoreDetailingLocator).click();
        String actualProductInTheCart = driver.findElement(productInTheCart).getText();
        String expectedProductInTheCart = "Стиральная машина BEKO WRS55P2BWW, фронтальная, 5кг, 1000об/мин";
        Assert.assertTrue("Не отображается лайтбокс каталога", driver.findElement(productInTheCart).isDisplayed());
        Assert.assertEquals("Название товара отсутствует", expectedProductInTheCart, actualProductInTheCart);
    }
}
