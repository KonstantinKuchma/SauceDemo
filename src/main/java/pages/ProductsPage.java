package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class ProductsPage extends BasePage {
    public static final By SORT_AZ =
            By.xpath("//select[@class='product_sort_container']/option[@value='az']");
    public static final By SORT_ZA =
            By.xpath("//select[@class='product_sort_container']/option[@value='za']");
    public static final By SORT_LOHI =
            By.xpath("//select[@class='product_sort_container']/option[@value='lohi']");
    public static final By SORT_HILO =
            By.xpath("//select[@class='product_sort_container']/option[@value='hilo']");
    /*
            1. Описываем в классе элементы с которыми мы взаимодействуем
            2. Описываем методы взаимодействия с этими элементами
            */
    //элементы страницы
    private final String ADD_TO_CART_PATTERN =
            "//*[text()='%s']/ancestor::div[@class='inventory_item']//button[text()='Add to cart']";
    private final String REMOVE_FROM_CART_PATTERN =
            "//*[text()='%s']/ancestor::div[@class='inventory_item']//button[text()='Remove']";
    private final By TITLE = By.cssSelector("[data-test=title]");
    private final By CART = By.cssSelector(".shopping_cart_link");

    public ProductsPage(WebDriver driver) {//переопределили родительский конструктор
        super(driver);
    }

    //методы
    public ProductsPage openPage() {//открыть ссылку на станицу
        driver.get(BASE_URL + "/inventory.html");
        return this;
    }

    @Step("Открытие страницы 'Корзина'")
    public CartPage openCart() {//открыть корзину
        driver.findElement(CART).click();
        return new CartPage(driver);
    }

    public String getTitle() {
        return driver.findElement(TITLE).getText();//вытаскиваем уникальное значение со страницы
    }

    @Override
    public ProductsPage isPageOpened(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(TITLE));
        return this;
    }

    @Step("Открытие страницы товара")
    public ProductPage clickProducts(String text) {
        List<WebElement> products = driver.findElements(By.cssSelector("[data-test='inventory-item-name']"));
        WebElement neededProduct = null;

        for (WebElement product : products) {
            if (product.getText().contains(text)) {
                neededProduct = product;
                break;
            }
        }
        neededProduct.click();
        return new ProductPage(driver);
    }

    @Step("Добавление товара с именем '{product}' в корзину")
    public ProductsPage addToCart(String product) {//добавить товар в корзину по названию
        driver.findElement(By.xpath(String.format(ADD_TO_CART_PATTERN, product))).click();
        return this;
    }
}
