package tests;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import tests.base.BaseTest;

public class ProductTest extends BaseTest {

    @Test(description = "Проверка возможности просмотра карточки товара",
            testName = "Проверка возможности просмотра карточки товара")
    @Description("Проверка возможности просмотра карточки товара")
    @Epic("E2E")
    @Feature("Check product cart")
    @Story("Product cart")
    @TmsLink("ITM-5")
    @Issue("ITM-5")
    @Owner("Kuchma Konstantin")
    public void checkProductCart() {
        loginPage.open()//вызываем метод, который открывает страницу
                .login(user, password)//авт c лог "standard_user" и пар "secret_sauce"
                .clickProducts("Sauce Labs Backpack")
                .getName();
        Assert.assertEquals(productPage.getName(), "Sauce Labs Backpack");
    }
}
