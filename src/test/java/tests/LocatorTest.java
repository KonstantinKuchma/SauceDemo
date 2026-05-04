package tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import tests.base.BaseTest;

public class LocatorTest extends BaseTest {
    /*
   Создать новый Java-класс, в нем для ресурса
https://www.saucedemo.com/ составить список локаторов, можно искать на
ВСЕХ страницах приложения (driver.findElement(<локатор>)) для КАЖДОГО из
примеров локаторов ниже:
• id
• name
• classname
• tagname
• linktext
• partiallinktext
• xpath:
- поиск по атрибуту, например By.xpath("//tag[@attribute='value']");
- поиск по тексту, например By.xpath("//tag[text()='text']");
- поиск по частичному совпадению атрибута, например
By.xpath("//tag[contains(@attribute,'text')]");
- поиск по частичному совпадению текста, например
By.xpath("//tag[contains(text(),'text')]");
- ancestor, например //*[text()='Enterprise Testing']//ancestor::div
- descendant
- following
- parent
- preceding
- Подсказка: XPath Axes
- *поиск элемента с условием AND, например
//input[@class='_2zrpKA_1dBPDZ' and @type='text']
• css:
- .class
- .class1.class2
- .class1 .class2
- #id
- tagname

- tagname.class
- [attribute=value]
- [attribute~=value]
- [attribute|=value]
- [attribute^=value]
- [attribute$=value]
- [attribute*=value]
- Подсказка: https://www.w3schools.com/cssref/css_selectors.asp
    */
    @Test(description = "Проверка наличия локаторов на странице",
            testName = "Проверка наличия локаторов на странице")
    public void checkLocator() {
        driver.get("https://www.saucedemo.com/");//открывает страницу по указанному урлу
        driver.findElement(By.id("user-name")).sendKeys("standard_user");//вводим в поле "user-name" standard_user
        driver.findElement(By.name("password")).sendKeys("secret_sauce");//вводим в поле "password" secret_sauce
        driver.findElement(By.className("submit-button")).click();//авторизуемся на сайте
        driver.findElement(By.tagName("span"));
        driver.findElement(By.linkText("Twitter"));
        driver.findElement(By.partialLinkText("Sauce"));
        //xpath
        driver.findElement(By.xpath("//div[@class='inventory_container']"));
        driver.findElement(By.xpath("//div[text()='Test.allTheThings() T-Shirt (Red)']"));
        driver.findElement(By.xpath("//div[contains(@class,'inventory')]"));
        driver.findElement(By.xpath("//div[contains(text(),'T-Shirt (Red)')]"));
        driver.findElement(By.xpath("//div[@class='inventory_container']//ancestor::button[@id='add-to-cart-sauce-labs-backpack']"));
        driver.findElement(By.xpath("//div[@class='inventory_container']//descendant::div[@class='inventory_item']"));
        driver.findElement(By.xpath("//div[@class='inventory_container']//following::div[@class='inventory_item']"));
        driver.findElement(By.xpath("//div[@class='inventory_list']/parent::div"));
        driver.findElement(By.xpath("//div[@class='inventory_list']/preceding::div"));
        driver.findElement(By.xpath("//div[@class='inventory_container' and @data-test='inventory-container']"));
        //css
        driver.findElement(By.cssSelector(".pricebar"));
        driver.findElement(By.cssSelector(".bm-item.menu-item"));
        driver.findElement(By.cssSelector(".bm-menu .bm-item.menu-item"));
        driver.findElement(By.cssSelector("#item_0_title_link"));
        driver.findElement(By.cssSelector("[data-test]"));
        driver.findElement(By.cssSelector("div.inventory_item_name "));
        driver.findElement(By.cssSelector("[class='primary_header']"));
        driver.findElement(By.cssSelector("[class~='bm-item']"));
        driver.findElement(By.cssSelector("[class|='bm']"));
        driver.findElement(By.cssSelector("[href^='https']"));
        driver.findElement(By.cssSelector("[href$='.css']"));
        driver.findElement(By.cssSelector("[href*='main']"));
    }
}
