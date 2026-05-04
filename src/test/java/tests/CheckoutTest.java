package tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import tests.base.BaseTest;

public class CheckoutTest extends BaseTest {

    @Test(description = "Проверка сохранения корректных данных о пользователе",
            testName = "Проверка сохранения корректных данных о пользователе")
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

//источник данных для параметризированного теста
@DataProvider(name = "Тестовые данные для негативного чекаута")
public Object[][] checkoutData() {
    return new Object[][] {
            {"", "test", "test", "Error: First Name is required"},
            {"test", "", "test", "Error: Last Name is required"},
            {"test", "test", "", "Error: Postal Code is required"}
    };
}

//параметризированный тест
@Test(description = "Проверка ошибки сохранения некорректных данных о пользователе",
        testName = "Проверка ошибки сохранения некорректных данных о пользователе",
        dataProvider = "Тестовые данные для негативного чекаута")
public void negativeCheckout(String firstname, String lastname, String postal, String errorMessage) {
    SoftAssert softAssert = new SoftAssert();
    loginPage.open();//вызываем метод, который открывает страницу
    loginPage.login("standard_user", "secret_sauce");//авт c лог "standard_user" и пар "secret_sauce"
    productsPage.openCart();//открыть корзину
    cartPage.goToCheckout();//перейти в раздел checkout
    softAssert.assertEquals(checkoutPage.getTitle(), "Checkout: Your Information");//проверяем, что на странице есть такой залоговок
    checkoutPage.checkout(firstname, lastname, postal);
    softAssert.assertEquals(checkoutPage.getErrorMessage(), errorMessage);
    softAssert.assertAll();
}
}
