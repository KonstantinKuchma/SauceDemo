package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeMethod;
import pages.*;

import java.util.HashMap;

public class BaseTest {

    WebDriver driver;//объявили драйвер что бы использовать в наследуемых классах
    LoginPage loginPage;
    ProductsPage productsPage;//объявили страницу productsPage
    CartPage cartPage;//объявили страницу cartPage
    CheckoutPage checkoutPage;//объявили страницу checkoutPage
    ProductPage productPage;

    @BeforeMethod
    public void setUp() {
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

        loginPage = new LoginPage(driver);//передам все заданные настройки в страицу LoginPage
        productsPage = new ProductsPage(driver);//инициализировали страницу
        cartPage = new CartPage(driver);
        checkoutPage = new CheckoutPage(driver);
        productPage = new ProductPage(driver);
    }

    //@AfterMethod(alwaysRun = true)
    //добавили (alwaysRun = true) что бы метод срабатвывал независимо от результата тестов
    public void tearDown() {
        driver.quit();
    }
}
