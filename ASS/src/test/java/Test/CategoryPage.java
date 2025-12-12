package Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CategoryPage {
    WebDriver driver;

    // ==== Locators chính xác dựa theo JSP ====
    @FindBy(name = "id")
    WebElement txtId;

    @FindBy(name = "name")
    WebElement txtName;

    @FindBy(xpath = "//button[contains(text(),'Thêm mới')]")
    WebElement btnAdd;

    @FindBy(xpath = "//button[contains(text(),'Cập nhật')]")
    WebElement btnUpdate;

    public CategoryPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // ==== CREATE ====
    public void addCategory(String id, String name) {
        txtId.clear();
        txtId.sendKeys(id);
        txtName.clear();
        txtName.sendKeys(name);
        btnAdd.click();
    }

    // ==== EDIT ====
    public void editCategory(String oldId, String newName) {
        // Click link "Sửa"
        driver.findElement(By.xpath("//td[text()='" + oldId + "']/following-sibling::td//a[contains(text(),'Sửa')]"))
              .click();

        // Chờ trang load form edit
        try { Thread.sleep(500); } catch (InterruptedException e) {}

        txtName.clear();
        txtName.sendKeys(newName);
        btnUpdate.click();
    }

    // ==== DELETE ====
    public void deleteCategory(String id) {
        driver.findElement(By.xpath("//td[text()='" + id + "']/following-sibling::td//a[contains(text(),'Xóa')]"))
              .click();

        // Selenium tự confirm nếu cần
        try {
            driver.switchTo().alert().accept();
        } catch (Exception e) {
            // Không có alert thì bỏ qua
        }
    }

    // ==== Kiểm tra tồn tại ====
    public boolean categoryExists(String id) {
        return driver.findElements(By.xpath("//td[text()='" + id + "']")).size() > 0;
    }
}
