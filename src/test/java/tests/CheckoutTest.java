package tests;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class CheckoutTest extends BaseTest {

    @Test
    public void checkoutWithPositiveCred() {
        SoftAssert softAssert = new SoftAssert();
        loginPage.open();//вызываем метод, который открывает страницу
        loginPage.login("standard_user", "secret_sauce");//авт c лог "standard_user" и пар "secret_sauce"
        productsPage.openCart();//открыть корзину
        cartPage.goToCheckout();//перейти в раздел checkout
        softAssert.assertEquals(checkoutPage.getTitle(), "Checkout: Your Information");//проверяем, что на странице есть такой залоговок
        checkoutPage.checkout("test", "test", "test");
        softAssert.assertEquals(checkoutPage.getTitle(), "Checkout: Overview");//проверяем, что на странице есть такой залоговок
        softAssert.assertAll();
    }

    @Test
    public void checkoutWithEmrtyFirstName() {
        SoftAssert softAssert = new SoftAssert();
        loginPage.open();//вызываем метод, который открывает страницу
        loginPage.login("standard_user", "secret_sauce");//авт c лог "standard_user" и пар "secret_sauce"
        productsPage.openCart();//открыть корзину
        cartPage.goToCheckout();//перейти в раздел checkout
        softAssert.assertEquals(checkoutPage.getTitle(), "Checkout: Your Information");//проверяем, что на странице есть такой залоговок
        checkoutPage.checkout("", "test", "test");
        softAssert.assertEquals(checkoutPage.getErrorMessage(), "Error: First Name is required");
        softAssert.assertAll();
    }

    @Test
    public void checkoutWithEmrtyLastName() {
        SoftAssert softAssert = new SoftAssert();
        loginPage.open();//вызываем метод, который открывает страницу
        loginPage.login("standard_user", "secret_sauce");//авт c лог "standard_user" и пар "secret_sauce"
        productsPage.openCart();//открыть корзину
        cartPage.goToCheckout();//перейти в раздел checkout
        softAssert.assertEquals(checkoutPage.getTitle(), "Checkout: Your Information");//проверяем, что на странице есть такой залоговок
        checkoutPage.checkout("test", "", "test");
        softAssert.assertEquals(checkoutPage.getErrorMessage(), "Error: Last Name is required");
        softAssert.assertAll();
    }

    @Test
    public void checkputWithEmrtyZipCode() {
        SoftAssert softAssert = new SoftAssert();
        loginPage.open();//вызываем метод, который открывает страницу
        loginPage.login("standard_user", "secret_sauce");//авт c лог "standard_user" и пар "secret_sauce"
        productsPage.openCart();//открыть корзину
        cartPage.goToCheckout();//перейти в раздел checkout
        softAssert.assertEquals(checkoutPage.getTitle(), "Checkout: Your Information");//проверяем, что на странице есть такой залоговок
        checkoutPage.checkout("test", "test", "");
        softAssert.assertEquals(checkoutPage.getErrorMessage(), "Error: Postal Code is required");
        softAssert.assertAll();
    }
}
