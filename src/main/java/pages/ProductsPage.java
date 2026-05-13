package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ProductsPage extends BasePage {
    /*
            1. Описываем в классе элементы с которыми мы взаимодействуем
            2. Описываем методы взаимодействия с этими элементами
            */
    //элементы страницы
    public static final By ADDTOCART_SAUCELABSBACKPACK = By.cssSelector("#add-to-cart-sauce-labs-backpack");
    public static final By ADDTOCART_SAUCELABSBBIKELIGHT = By.cssSelector("#add-to-cart-sauce-labs-bike-light");
    public static final By ADDTOCART_SAUCELABSBOLTTSHIRT = By.cssSelector("#add-to-cart-sauce-labs-bolt-t-shirt");
    public static final By ADDTOCART_SAUCELABSFLEECEJACKET = By.cssSelector("#add-to-cart-sauce-labs-fleece-jacket");
    public static final By ADDTOCART_SAUCELABSONESIE = By.cssSelector("#add-to-cart-sauce-labs-onesie");
    public static final By ADDTOCART_TESTALLTHETHINGS = By.cssSelector("#add-to-cart-test.allthethings()-t-shirt-(red)");
    public static final By REMOVE_SAUCELABSBACKPACK = By.cssSelector("#remove-sauce-labs-backpack");
    public static final By REMOVE_SAUCELABSBBIKELIGHT = By.cssSelector("#remove-sauce-labs-bike-light");
    public static final By REMOVE_SAUCELABSBOLTTSHIRT = By.cssSelector("#remove-sauce-labs-bolt-t-shirt");
    public static final By REMOVE_SAUCELABSFLEECEJACKET = By.cssSelector("#remove-sauce-labs-fleece-jacket");
    public static final By REMOVE_SAUCELABSONESIE = By.cssSelector("#remove-sauce-labs-onesie");
    public static final By REMOVE_TESTALLTHETHINGS = By.cssSelector("#remove-test.allthethings()-t-shirt-(red)");
    public static final By SORT_AZ = By.xpath("//select[@class='product_sort_container']/option[@value='az']");
    public static final By SORT_ZA = By.xpath("//select[@class='product_sort_container']/option[@value='za']");
    public static final By SORT_LOHI = By.xpath("//select[@class='product_sort_container']/option[@value='lohi']");
    public static final By SORT_HILO = By.xpath("//select[@class='product_sort_container']/option[@value='hilo']");
    private final By TITLE = By.cssSelector("[data-test=title]");
    private final By CART = By.cssSelector(".shopping_cart_link");

    public ProductsPage(WebDriver driver) {//переопределили родительский конструктор
        super(driver);
    }

    //методы
    public void openPage() {//открыть ссылку на станицу
        driver.get(BASE_URL + "/inventory.html");
    }

    @Step("Добавление товара с именем '{product}' в корзину")
    public void addToCart(By locator) {//добавить товар в корзину по локатору
        driver.findElement(locator).click();
    }

    @Step("Открытие страницы 'Корзина'")
    public void openCart() {//открыть корзину
        driver.findElement(CART).click();
    }

    public String getTitle() {
        return driver.findElement(TITLE).getText();//вытаскиваем уникальное значение со страницы
    }

    @Step("Открытие страницы товара")
    public void clickProducts(String text) {
        List<WebElement> products = driver.findElements(By.cssSelector("[data-test='inventory-item-name']"));
        WebElement neededProduct = null;

        for (WebElement product : products) {
            if (product.getText().contains(text)) {
                neededProduct = product;
                break;
            }
        }
        neededProduct.click();
    }
}
