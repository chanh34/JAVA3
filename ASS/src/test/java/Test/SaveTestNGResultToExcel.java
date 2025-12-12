package Test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;

import org.testng.annotations.*;

import java.io.File;
import java.io.FileOutputStream;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.*;

public class SaveTestNGResultToExcel {

    WebDriver driver;

    HSSFWorkbook workbook;
    HSSFSheet sheet;
    Map<String, Object[]> TestNGResults;

    @BeforeClass
    public void setup() {

        // Nếu máy bạn cần set path chromedriver thì mở dòng này
        // System.setProperty("webdriver.chrome.driver", "D:\\chromedriver.exe");

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

        driver.get("http://localhost:8080/ABCNews/login");

        // Tạo Excel
        workbook = new HSSFWorkbook();
        sheet = workbook.createSheet("Login Data");
        TestNGResults = new HashMap<>();
    }

    @Test(description = "Get Email and Password From Login Page")
    public void getLoginData() {

        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            WebElement email = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.id("email")));

            WebElement password = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.id("password")));

            // Nếu form CHƯA có dữ liệu sẵn thì nhập vào
            email.sendKeys("anh@test.com");
            password.sendKeys("123");

            // LẤY GIÁ TRỊ TỪ FORM
            String emailValue = email.getAttribute("value");
            String passwordValue = password.getAttribute("value");

            // LƯU VÀO MAP (để ghi Excel)
            TestNGResults.put("1", new Object[]{
                    1d, emailValue, passwordValue
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @AfterClass
    public void writeExcel() {

        try {
            // HEADER
            Row header = sheet.createRow(0);
            header.createCell(0).setCellValue("ID");
            header.createCell(1).setCellValue("Email");
            header.createCell(2).setCellValue("Password");

            int rownum = 1;

            for (String key : TestNGResults.keySet()) {
                Row row = sheet.createRow(rownum++);
                Object[] objArr = TestNGResults.get(key);
                int cellnum = 0;

                for (Object obj : objArr) {
                    Cell cell = row.createCell(cellnum++);
                    cell.setCellValue(obj.toString());
                }
            }

            FileOutputStream out =
                    new FileOutputStream(new File("LoginData.xls"));
            workbook.write(out);
            out.close();

            System.out.println("Đã xuất email & password ra file LoginData.xls");

            driver.quit();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
