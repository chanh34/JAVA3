package Test;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.annotations.*;

public class CategoryTest extends LoginBase {

    String baseUrl = "http://localhost:8080/ABCNews/admin/categories";

    String testId = "tt";
    String testName = "Tin Test";
    String updatedName = "Tin Test Updated";

    @BeforeClass
    public void setupCategory() {
      
        driver.get(baseUrl);
    }

    @Test(priority = 1)
    public void testAddCategory() {

        WebElement id = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("id")));
        WebElement name = driver.findElement(By.name("name"));

        id.clear();
        id.sendKeys(testId);

        name.clear();
        name.sendKeys(testName);

        driver.findElement(By.cssSelector("button.btn-create")).click();

        WebElement row = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//td[normalize-space()='" + testId + "']")));

        Assert.assertNotNull(row);
    }

    @Test(priority = 2)
    public void testEditCategory() {

        driver.get(baseUrl);

        WebElement editBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//td[normalize-space()='" + testId + "']/following-sibling::td/a[contains(@class,'btn-update')]")
        ));
        editBtn.click();

        WebElement name = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("name")));
        name.clear();
        name.sendKeys(updatedName);

        driver.findElement(By.cssSelector("button.btn-update")).click();

        WebElement updatedRow = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//td[normalize-space()='" + testId + "']/following-sibling::td[normalize-space()='" + updatedName + "']")));

        Assert.assertNotNull(updatedRow);
    }

    @Test(priority = 3)
    public void testDeleteCategory() {

        driver.get(baseUrl);

        WebElement deleteBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//td[normalize-space()='" + testId + "']/following-sibling::td/a[contains(@class,'btn-delete')]")
        ));
        deleteBtn.click();

        try {
            driver.switchTo().alert().accept();
        } catch (Exception ignored) {}

        wait.until(ExpectedConditions.invisibilityOfElementLocated(
                By.xpath("//td[normalize-space()='" + testId + "']")));

        Assert.assertTrue(true);
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
