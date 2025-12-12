package Test;


	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.By;
	import org.testng.annotations.Test;
	import org.testng.Assert;

	public class Testlogin {

	    WebDriver driver;

	    @Test
	    public void testLogin() {
	        // Khởi tạo ChromeDriver
	        System.setProperty("webdriver.chrome.driver", "D:\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
	        driver = new ChromeDriver();

	        driver.get("https://webpage-sinhvien-cua-ban.com/login");

	        // Nhập username
	        WebElement username = driver.findElement(By.id("username"));
	        username.sendKeys("student1");

	        // Nhập password
	        WebElement password = driver.findElement(By.id("password"));
	        password.sendKeys("123456");

	        // Click Login
	        WebElement loginButton = driver.findElement(By.id("loginBtn"));
	        loginButton.click();

	        // Kiểm tra kết quả
	        String expectedUrl = "https://webpage-sinhvien-cua-ban.com/dashboard";
	        Assert.assertEquals(driver.getCurrentUrl(), expectedUrl, "Đăng nhập thất bại!");

	        // Đóng trình duyệt
	        driver.quit();
	    }
	}

