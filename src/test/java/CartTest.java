import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.HashMap;

public class CartTest {
/*
Залогиниться
b. Добавить товар в корзину
c. Перейти в корзину
d. Проверить (assertEquals) стоимость товара и его имя в корзине
 */
    @Test
    public void checkCart() {
        ChromeOptions options = new ChromeOptions();
        SoftAssert softAssert = new SoftAssert();
        HashMap<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("credentials_enable_service", false);
        chromePrefs.put("profile.password_manager_enabled", false);
        options.setExperimentalOption("prefs", chromePrefs);
        options.addArguments("--incognito");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-infobars");
        WebDriver driver = new ChromeDriver(options);
        //открывает страницу по указанному урлу
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");//вводим в поле "user-name" standard_user
        driver.findElement(By.name("password")).sendKeys("secret_sauce");//вводим в поле "password" secret_sauce
        driver.findElement(By.className("submit-button")).click();//авторизуемся на сайте
        driver.findElement(By.cssSelector("#add-to-cart-sauce-labs-backpack")).click();//добавляем в корзину Sauce Labs Backpack
        driver.findElement(By.cssSelector(".shopping_cart_link")).click();//переходим в корзину
        WebElement productName = driver.findElement(By.cssSelector("div.inventory_item_name"));//находим название товара
        softAssert.assertEquals(productName.getText(), "Sauce Labs Backpack");//сравниваем название товара
        //закрывает браузер
        driver.quit();
        softAssert.assertAll();
    }
}
