package tests;

import io.qameta.allure.*;
import org.testng.annotations.*;
import tests.base.BaseTest;
import utils.AllureUtils;
import utils.Retry;

import static org.testng.AssertJUnit.assertEquals;
import static utils.AllureUtils.takeScreenshot;

public class LoginTest extends BaseTest {

    @Test(description = "Проверка входа в систему с позитивными кредами",
            testName = "Проверка входа в систему с позитивными кредами",
            retryAnalyzer = Retry.class)
    @Description("Проверка входа в систему с позитивными кредами")
    @Epic("E2E")
    @Feature("Login in to SauceDemo")
    @Story("Positive Login")
    @Severity(SeverityLevel.CRITICAL)
    @Link("https://www.saucedemo.com/")
    @TmsLink("ITM-5")
    @Issue("ITM-5")
    @Flaky
    @Owner("Kuchma Konstantin")
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
    @Description("Проверка входа в систему с негативными кредами")
    @Epic("E2E")
    @Feature("Login in to SauceDemo")
    @Story("Nefative Login")
    @TmsLink("ITM-5")
    @Issue("ITM-5")
    @Owner("Kuchma Konstantin")
    public void negativeLogin(String user, String password, String errorMessage) {
        loginPage.open();//вызываем метод, который открывает страницу
        loginPage.login(user, password);//авторизация с параметрами
        assertEquals(loginPage.getErrorMessage(), errorMessage);
    }
}
