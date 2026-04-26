package tests;

import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

public class LoginTest extends BaseTest {

    @Test
    public void checkLoginWithPositiveCred() {
        loginPage.open();//вызываем метод, который открывает страницу
        loginPage.login("standard_user", "secret_sauce");//авт c лог "standard_user" и пар "secret_sauce"
        assertEquals(productsPage.getTitle(), "Products");//проверяем, что на странице есть такой залоговок
    }

    @Test
    public void checkLoginWithEmrtyPassword() {
        loginPage.open();//вызываем метод, который открывает страницу
        loginPage.login("standard_user", "");//авторизация c логином "standard_user" и пустым паролем
        assertEquals(loginPage.getErrorMessage(), "Epic sadface: Password is required");
    }

    @Test
    public void checkLoginWithEmrtyUser() {
        loginPage.open();//вызываем метод, который открывает страницу
        loginPage.login("", "secret_sauce");//авторизация c пустым логином и паролем "secret_sauce"
        assertEquals(loginPage.getErrorMessage(), "Epic sadface: Username is required");
    }

    @Test
    public void checkLoginWithNegatineCred() {
        loginPage.open();//вызываем метод, который открывает страницу
        loginPage.login("test", "test");//авторизация с невалидными данными
        assertEquals(loginPage.getErrorMessage(), "Epic sadface: Username and password do not match any user " +
                "in this service");
    }
}
