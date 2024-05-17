import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class RegistrationAndAuthorizationTest {
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

    private final By loginButtonLocator = By.cssSelector("a.account");
    private final By inputUserNameLocator = By.xpath("//input[@id='username']");
    private final By inputPasswordLocator = By.xpath("//input[@id='password']");
    private final By inputRegistrationButtonLocator = By.cssSelector("button.custom-register-button");
    private final By registrationUserNameLocator = By.xpath("//input[@id='reg_username']");
    private final By emailRegistrationUserLocator = By.xpath("//input[@id='reg_email']");
    private final By passwordRegistrationUserLocator = By.xpath("//input[@id='reg_password']");
    private final By registrationButtonLocator = By.xpath("//button[@class='woocommerce-Button woocommerce-button button woocommerce-form-register__submit']");
    private final By registrationDoneLocator = By.cssSelector("div.content-page");
    private final By enterButtonAuthorizationLocator = By.cssSelector("button.woocommerce-button.button.woocommerce-form-login__submit");
    private final By authorizationDoneLocator = By.xpath("//*[@class='woocommerce-MyAccount-content']//p[1]");

    @Test
    public void registrationForm_registeringEmailLogin_registrationDone() {
        driver.navigate().to("https://intershop5.skillbox.ru/");
        driver.findElement(loginButtonLocator).click();
        driver.findElement(inputRegistrationButtonLocator).click();
        driver.findElement(registrationUserNameLocator).sendKeys("shopping");
        driver.findElement(emailRegistrationUserLocator).sendKeys("testing@java.ru");
        driver.findElement(passwordRegistrationUserLocator).sendKeys("1234");
        driver.findElement(registrationButtonLocator).click();
        String actualRegistrationText = driver.findElement(registrationDoneLocator).getText();
        String expectedRegistrationText = "Регистрация завершена";
        Assert.assertEquals("Отсутствует текст завершающий регистрацию",expectedRegistrationText,actualRegistrationText);
    }

    @Test
    public void authorizationForm_enteringEmailAndPassword_authorizationDone () {
        driver.navigate().to("https://intershop5.skillbox.ru/");
        driver.findElement(loginButtonLocator).click();
        driver.findElement(inputUserNameLocator).sendKeys("shopping");
        driver.findElement(inputPasswordLocator).sendKeys("1234");
        driver.findElement(enterButtonAuthorizationLocator).click();
        String actualTitleAccount = driver.findElement(authorizationDoneLocator).getText();
        String expectedTitleAccount = "Привет shopping (Выйти)";
        Assert.assertEquals("Не заходит в мой аккаунт",expectedTitleAccount,actualTitleAccount);
    }
}
