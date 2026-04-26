package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.ProductsPage;

public class CartTest extends BaseTest {
    /*
    Залогиниться
    b. Добавить товар в корзину
    c. Перейти в корзину
    d. Проверить (assertEquals) стоимость товара и его имя в корзине
     */
    @Test
    public void checkCart() {
        SoftAssert softAssert = new SoftAssert();
        loginPage.open();//вызываем метод, который открывает страницу
        loginPage.login("standard_user", "secret_sauce");//авт c лог "standard_user" и пар "secret_sauce"
        productsPage.addToCart(ProductsPage.ADDTOCART_SAUCELABSBACKPACK);//добавить элемент в корзину
        productsPage.openCart();//открыть корзину
        cartPage.getProductName();//получаем и проверяем имя первого товара в корзине
        softAssert.assertEquals(cartPage.getProductName(), "Sauce Labs Backpack");
        cartPage.getProductPrice();//получаем и проверяем цену первого товара в корзине
        softAssert.assertEquals(cartPage.getProductPrice(), "$29.99");
        softAssert.assertAll();
    }

    @Test
    public void checkRemoveCart() {
        SoftAssert softAssert = new SoftAssert();
        loginPage.open();//вызываем метод, который открывает страницу
        loginPage.login("standard_user", "secret_sauce");//авт c лог "standard_user" и пар "secret_sauce"
        productsPage.addToCart(ProductsPage.ADDTOCART_SAUCELABSBACKPACK);//добавить элемент в корзину
        productsPage.openCart();//открыть корзину
        cartPage.getProductName();//получаем и проверяем имя первого товара в корзине
        Assert.assertEquals(cartPage.getProductName(), "Sauce Labs Backpack");
        cartPage.removeFromCart();//удаляем товар
        cartPage.checkEmtyCart();
    }
}
