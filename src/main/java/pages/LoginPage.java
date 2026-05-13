package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {

    /*
        1. Описываем в классе элементы с которыми мы взаимодействуем
        2. Описываем методы взаимодействия с этими элементами
        */
    //элементы страницы
    private final By USERNAME_FIELD = By.id("user-name");
    private final By PASSWORD_FIELD = By.name("password");
    private final By LOGIN_BUTTON = By.className("submit-button");
    private final By ERROR_MESSAGE = By.cssSelector("[data-test=error");

    public LoginPage(WebDriver driver) {//переопределили родительский конструктор
        super(driver);
    }

    //методы
    @Step("Открытие страницы Login")
    public LoginPage open() {//открывает страницу по указанному урлу
        driver.get(BASE_URL);
        return this;
    }

    @Override
    public LoginPage isPageOpened(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(LOGIN_BUTTON));
        return this;
    }

    @Step("Вход в магазин с именем пользователя: '{user}' и паролем: '{password}'")
    public ProductsPage login(String user, String password) {//авторизация без хардкода
        driver.findElement(USERNAME_FIELD).sendKeys(user);
        driver.findElement(PASSWORD_FIELD).sendKeys(password);
        driver.findElement(LOGIN_BUTTON).click();
        return new ProductsPage(driver);
    }

    @Step("Вход в магазин с именем пользователя: '{user}' и паролем: '{password}'")
    public LoginPage loginnegative(String user, String password) {//авторизация без хардкода
        driver.findElement(USERNAME_FIELD).sendKeys(user);
        driver.findElement(PASSWORD_FIELD).sendKeys(password);
        driver.findElement(LOGIN_BUTTON).click();
        return this;
    }

    public String getErrorMessage() {
        return driver.findElement(ERROR_MESSAGE).getText();//возвращаем текст сообщения об ошибке
    }
}
