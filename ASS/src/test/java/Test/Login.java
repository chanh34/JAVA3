package Test;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Login {

    WebDriver driver;

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://localhost:8080/ABCNews/login");
    }

    @Test
    public void testLoginSuccess() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement email = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
        WebElement password = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));
        WebElement btn = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("button[type='submit']")));

        email.sendKeys("anh@test.com");
        password.sendKeys("123");

        // Scroll + JS click để tránh lỗi ElementClickInterceptedException
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", btn);
        js.executeScript("arguments[0].click();", btn);

        // Kiểm tra trang dashboard
        wait.until(ExpectedConditions.urlContains("/admin/dashboard"));
        Assert.assertEquals(driver.getCurrentUrl(), "http://localhost:8080/ABCNews/admin/dashboard");

    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}