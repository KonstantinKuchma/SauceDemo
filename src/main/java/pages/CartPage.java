package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

public class CartPage extends BasePage {
    //элементы
    private final By PRODUCT_NAME = By.cssSelector(".inventory_item_name");
    private final By PRODUCT_PRICE = By.cssSelector(".inventory_item_price");
    private final By CHECKOUT_BUTTON = By.cssSelector("[data-test=checkout");
    private final By REMOVE_BUTTON = By.cssSelector("[data-test=remove-sauce-labs-backpack");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    //методы
    public String getProductName() {//получает название первого товара в корзине
        return driver.findElement(PRODUCT_NAME).getText();
    }

    public String getProductPrice() {//получает цену первого товара в корзине
        return driver.findElement(PRODUCT_PRICE).getText();
    }

    @Step("Открытие страницы с информацией о пользователе")
    public void goToCheckout() {
        driver.findElement(CHECKOUT_BUTTON).click();
    }

    @Step("Удаление товара из корзины")
    public void removeFromCart() {
        driver.findElement(REMOVE_BUTTON).click();
    }

    @Step("Проверяем, что корзина пуста")
    public void checkEmtyCart() {
        List<WebElement> products = driver.findElements(By.cssSelector("[data-test='inventory-item-name']"));
        Assert.assertTrue(products.isEmpty());
    }
}
