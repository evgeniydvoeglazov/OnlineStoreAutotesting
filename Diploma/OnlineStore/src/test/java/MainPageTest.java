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

public class MainPageTest {
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

    private final By promoBooksLocator = By.xpath("(//div[@class='promo-widget-wrap'])[1]");
    private final By catalogBooksTitleLocator = By.cssSelector(".entry-title");
    private final By lightBoxBooksLocator = By.cssSelector("div.inner");

    @Test
    public void clickingOnTheBookSection_GoingToTheBookCatalog() {
        driver.navigate().to("https://intershop5.skillbox.ru/");
        driver.findElement(promoBooksLocator).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(lightBoxBooksLocator));
        String actualLinkBookCatalog = driver.findElement(catalogBooksTitleLocator).getText();
        String expectedTitle = "КНИГИ";
        Assert.assertTrue("Не отображается лайтбокс для выбранного каталога", driver.findElement(lightBoxBooksLocator).isDisplayed());
        Assert.assertEquals("Название каталога не соответствует выбранному", expectedTitle, actualLinkBookCatalog);
    }

    private final By promoPadLocator = By.xpath("(//div[@class='promo-widget-wrap'])[2]");
    private final By catalogPadTitleLocator = By.cssSelector(".entry-title");
    private final By lightBoxPadLocator = By.cssSelector("div.inner");

    @Test
    public void clickingOnThePadPromoSection_GoingToPadCatalog() {
        driver.navigate().to("https://intershop5.skillbox.ru/");
        driver.findElement(promoPadLocator).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(lightBoxPadLocator));
        String actualLinkPadCatalog = driver.findElement(catalogPadTitleLocator).getText();
        String expectedTitlePad = "ПЛАНШЕТЫ";
        Assert.assertTrue("Не отображается лайтбокс для выбранного каталога", driver.findElement(lightBoxPadLocator).isDisplayed());
        Assert.assertEquals("Название каталога не соответствует выбранному", expectedTitlePad, actualLinkPadCatalog);
    }
}
