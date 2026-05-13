package tests;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.ProductsPage;
import tests.base.BaseTest;

public class CartTest extends BaseTest {
    /*
    Залогиниться
    b. Добавить товар в корзину
    c. Перейти в корзину
    d. Проверить (assertEquals) стоимость товара и его имя в корзине
     */
    @Test(description = "Проверка добавления товара в корзину",
            testName = "Проверка добавления товара в корзину")
    @Description("Проверка добавления товара в корзину")
    @Epic("E2E")
    @Feature("Check Cart")
    @Story("Cart")
    @TmsLink("ITM-5")
    @Issue("ITM-5")
    @Owner("Kuchma Konstantin")
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

    @Test(description = "Проверка удаления товара из корзины",
            testName = "Проверка удаления товара из корзины")
    @Description("Проверка удаления товара из корзины")
    @Epic("E2E")
    @Feature("Check Cart")
    @Story("Cart")
    @TmsLink("ITM-5")
    @Issue("ITM-5")
    @Owner("Kuchma Konstantin")
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
