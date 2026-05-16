package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Log4j2
public class CheckoutPage extends BasePage {
    //элементы
    private final By TITLE1 = By.cssSelector("[data-test=title]");
    private final By FIRSTNAME_FIELD = By.id("first-name");
    private final By LASTNAME_FIELD = By.id("last-name");
    private final By ZIP_FIELD = By.id("postal-code");
    private final By CONTINUE_BUTTON = By.id("continue");
    private final By ERROR_MESSAGE = By.cssSelector("[data-test=error");

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    //методы
    public String getTitle() {
        log.info("Getting tittle from page");
        return driver.findElement(TITLE1).getText();//вытаскиваем уникальное значение со страницы
    }

    @Step("Сохранение личных данных - имя: '{firstName}', фамилия: '{lastName}' и код: '{zip}'")
    public CheckoutPage checkout(String firstName, String lastName, String zip) {//заполнение полей без хардкода
        log.info("Save personal information '{}', '{}', '{}'", firstName, lastName, zip);
        driver.findElement(FIRSTNAME_FIELD).sendKeys(firstName);
        driver.findElement(LASTNAME_FIELD).sendKeys(lastName);
        driver.findElement(ZIP_FIELD).sendKeys(zip);
        driver.findElement(CONTINUE_BUTTON).click();
        return this;
    }

    @Override
    public CheckoutPage isPageOpened(){
        log.info("Checkout page is open");
        wait.until(ExpectedConditions.visibilityOfElementLocated(TITLE1));
        return this;
    }

    public String getErrorMessage() {
        log.info("Getting error message");
        return driver.findElement(ERROR_MESSAGE).getText();//возвращаем текст сообщения об ошибке
    }
}
