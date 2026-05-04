package tests.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;
import pages.*;
import utils.TestListener;

import java.util.HashMap;

@Listeners(TestListener.class)
public class BaseTest {

    WebDriver driver;//объявили драйвер что бы использовать в наследуемых классах
    protected LoginPage loginPage;
    protected ProductsPage productsPage;//объявили страницу productsPage
    protected CartPage cartPage;//объявили страницу cartPage
    protected CheckoutPage checkoutPage;//объявили страницу checkoutPage
    protected ProductPage productPage;

    @Parameters({"browser"})
    @BeforeMethod (alwaysRun = true)
    public void setUp(@Optional("chrome") String browser) {
        if (browser.equalsIgnoreCase("chrome")) {
            //задаем все настройки браузера
            ChromeOptions options = new ChromeOptions();
            HashMap<String, Object> chromePrefs = new HashMap<>();
            chromePrefs.put("credentials_enable_service", false);
            chromePrefs.put("profile.password_manager_enabled", false);
            options.setExperimentalOption("prefs", chromePrefs);
            options.addArguments("--incognito");
            options.addArguments("--disable-notifications");
            options.addArguments("--disable-popup-blocking");
            options.addArguments("--disable-infobars");
            driver = new ChromeDriver(options);//инициализируем объвленный ранее драйвер
        } else if (browser.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
            driver.manage().window().maximize();
        }
        loginPage = new LoginPage(driver);//передам все заданные настройки в страицу LoginPage
        productsPage = new ProductsPage(driver);//инициализировали страницу
        cartPage = new CartPage(driver);
        checkoutPage = new CheckoutPage(driver);
        productPage = new ProductPage(driver);
    }

    @AfterMethod(alwaysRun = true)
    //добавили (alwaysRun = true) что бы метод срабатвывал независимо от результата тестов
    public void tearDown() {
        driver.quit();
    }
}
