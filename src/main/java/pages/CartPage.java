package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.util.List;

@Log4j2
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
        log.info("Getting product name");
        return driver.findElement(PRODUCT_NAME).getText();
    }

    public String getProductPrice() {//получает цену первого товара в корзине
        log.info("Getting product price");
        return driver.findElement(PRODUCT_PRICE).getText();
    }

    @Step("Открытие страницы с информацией о пользователе")
    public CheckoutPage goToCheckout() {
        log.info("Openning checkout page");
        driver.findElement(CHECKOUT_BUTTON).click();
        return new CheckoutPage(driver);
    }

    @Override
    public CartPage isPageOpened(){
        log.info("Cart page is open");
        wait.until(ExpectedConditions.visibilityOfElementLocated(CHECKOUT_BUTTON));
        return this;
    }

    @Step("Удаление товара из корзины")
    public CartPage removeFromCart() {
        log.info("Remove product from cart");
        driver.findElement(REMOVE_BUTTON).click();
        return this;
    }

    @Step("Проверяем, что корзина пуста")
    public CartPage checkEmtyCart() {
        log.info("Checking cart");
        List<WebElement> products = driver.findElements(By.cssSelector("[data-test='inventory-item-name']"));
        Assert.assertTrue(products.isEmpty());
        return this;
    }
}
