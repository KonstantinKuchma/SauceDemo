package tests;

import org.testng.annotations.*;
import tests.base.BaseTest;
import utils.Retry;

import static org.testng.AssertJUnit.assertEquals;

public class LoginTest extends BaseTest {

    @Test(description = "Проверка входа в систему с позитивными кредами",
            testName = "Проверка входа в систему с позитивными кредами",
            retryAnalyzer = Retry.class)
    public void checkLoginWithPositiveCred() {
        loginPage.open();//вызываем метод, который открывает страницу
        loginPage.login("standard_user", "secret_sauce");//авт c лог "standard_user" и пар "secret_sauce"
        assertEquals(productsPage.getTitle(), "Products");//проверяем, что на странице есть такой залоговок
    }

    //источник данных для параметризированного теста
    @DataProvider(name = "Тестовые данные для негативного логина")
    public Object[][] loginData() {
        return new Object[][] {
                {"standard_user", "", "Epic sadface: Password is required"},
                {"", "secret_sauce", "Epic sadface: Username is required"},
                {"test", "test", "Epic sadface: Username and password do not match any user in this service"}
        };
    }

    //параметризированный тест
    @Test(description = "Проверка входа в систему с негативными кредами",
            testName = "Проверка входа в систему с негативными кредами",
            dataProvider = "Тестовые данные для негативного логина")
    public void negativeLogin(String user, String password, String errorMessage) {
        loginPage.open();//вызываем метод, который открывает страницу
        loginPage.login(user, password);//авторизация с параметрами
        assertEquals(loginPage.getErrorMessage(), errorMessage);
    }
}
