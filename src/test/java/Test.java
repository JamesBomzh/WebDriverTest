import org.junit.After;
import org.junit.Before;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.HashMap;
import java.util.Map;


public class Test {
    private WebDriver driver;
    private Map<String, Object> vars;
    JavascriptExecutor js;
    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        js = (JavascriptExecutor) driver;
    }
    @After
    public void tearDown() {
        driver.quit();
    }
    @org.junit.Test
    public void test() {
        driver.get("https://www.istqb.org/"); // собственно, тестируемый веб-сайт
        driver.findElement(By.id("mod-search-searchword")).click(); // клик на строку поиска
        driver.findElement(By.id("mod-search-searchword")).sendKeys("Foundation Level 2018");
        // ввод поискового запроса
        driver.findElement(By.id("mod-search-searchword")).sendKeys(Keys.ENTER); // нажатие ввода
        if (driver.findElements(By.cssSelector(".search-results")).size() > 0) { // проверка на непустоту результатов
            WebElement foundationLevel = driver.findElement(By.linkText("Foundation Level Automotive Software Tester"));
            // сохранение нужного нам веб-элемента, а именно нужной статьи
            js.executeScript("arguments[0].scrollIntoView(true);", foundationLevel);
            // скроллим, пока не будет видно нашу статью
            foundationLevel.click();
            // клик на статью
            assertEquals("Foundation Level Automotive Software Tester" , driver.findElement(By.cssSelector(".article-title")).getText());
            // убеждаемся, что статья имеет именно тот заголовок
            // Также можно проверить с использованием driver.getTitle(), но там не совсем "Foundation Level Automotive Software Tester"
        }
    }
}
