package tests.base;

import io.qameta.allure.Description;
import io.qameta.allure.testng.AllureTestNg;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.ITestContext;
import org.testng.annotations.*;
import pages.*;
import utils.TestListener;

import java.util.HashMap;

@Listeners({AllureTestNg.class, TestListener.class})
public class BaseTest {

    protected WebDriver driver;//объявили драйвер что бы использовать в наследуемых классах
    protected LoginPage loginPage;
    protected ProductsPage productsPage;//объявили страницу productsPage
    protected CartPage cartPage;//объявили страницу cartPage
    protected CheckoutPage checkoutPage;//объявили страницу checkoutPage
    protected ProductPage productPage;

    @Parameters({"browser"})
    @BeforeMethod(alwaysRun = true, description = "Настройка браузера")
    @Description("Настройка браузера")
    public void setUp(@Optional("chrome") String browser, ITestContext iTestContext) {
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
            options.addArguments("--headless");// запуск тестов без открытия браузера
            driver = new ChromeDriver(options);//инициализируем объвленный ранее драйвер
        } else if (browser.equalsIgnoreCase("firefox")) {
            FirefoxOptions options = new FirefoxOptions();
            options.addArguments("--headless");// запуск тестов без открытия браузера
            driver = new FirefoxDriver(options);
            driver.manage().window().maximize();
        }
        loginPage = new LoginPage(driver);//передам все заданные настройки в страицу LoginPage
        productsPage = new ProductsPage(driver);//инициализировали страницу
        cartPage = new CartPage(driver);
        checkoutPage = new CheckoutPage(driver);
        productPage = new ProductPage(driver);

        iTestContext.setAttribute("driver", driver);//для скриншота при падении

    }

    @AfterMethod(alwaysRun = true, description = "Закрытие браузера")
    @Description("Закрытие браузера")
    //добавили (alwaysRun = true) что бы метод срабатвывал независимо от результата тестов
    public void tearDown() {
        driver.quit();
    }
}
