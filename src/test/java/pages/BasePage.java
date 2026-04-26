package pages;

import org.openqa.selenium.WebDriver;

public class BasePage {

    public final String BASE_URL = "https://www.saucedemo.com/";
    WebDriver driver;//объявляем драйвер

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }
}
