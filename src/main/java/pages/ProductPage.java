package pages;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Log4j2
public class ProductPage extends BasePage {
    private final By PRODUCT_NAME = By.cssSelector("[data-test='inventory-item-name']");

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public String getName() {
        log.info("Getting product name");
        return driver.findElement(PRODUCT_NAME).getText();//вытаскиваем название товара со страницы
    }

    @Override
    public ProductPage isPageOpened(){
        log.info("Checkout page is open");
        wait.until(ExpectedConditions.visibilityOfElementLocated(PRODUCT_NAME));
        return this;
    }
}
