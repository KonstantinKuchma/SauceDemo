package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProductPage extends BasePage {
    private final By PRODUCT_NAME = By.cssSelector("[data-test='inventory-item-name']");

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public String getName() {
        return driver.findElement(PRODUCT_NAME).getText();//вытаскиваем название товара со страницы
    }

    @Override
    public ProductPage isPageOpened(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(PRODUCT_NAME));
        return this;
    }
}
