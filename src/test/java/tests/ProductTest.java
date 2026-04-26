package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ProductTest extends BaseTest {

    @Test
    public void checkProductCart() {
        loginPage.open();//вызываем метод, который открывает страницу
        loginPage.login("standard_user", "secret_sauce");//авт c лог "standard_user" и пар "secret_sauce"
        productsPage.clickProducts("Sauce Labs Backpack");
        productPage.getName();
        Assert.assertEquals(productPage.getName(), "Sauce Labs Backpack");
    }
}
