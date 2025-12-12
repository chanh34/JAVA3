package Test;


	import java.io.FileInputStream;
	import java.util.Properties;
	import org.openqa.selenium.By;

	public class UIMap {

	    Properties properties;

	    public UIMap(String FilePath) {
	        properties = new Properties();
	        try {
	            FileInputStream stream = new FileInputStream(FilePath);
	            properties.load(stream);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

	    public By getLocator(String ElementName) throws Exception {

	        // Lấy toàn bộ giá trị từ file properties theo key
	        String locator = properties.getProperty(ElementName);

	        // Tách loại locator và giá trị locator
	        String locatorType = locator.split("=", 2)[0];
	        String locatorValue = locator.split("=", 2)[1];

	        // Trả về By tương ứng
	        switch (locatorType.toLowerCase()) {
	            case "id":
	                return By.id(locatorValue);
	            case "name":
	                return By.name(locatorValue);
	            case "classname":
	            case "class":
	                return By.className(locatorValue);
	            case "tagname":
	            case "tag":
	                return By.tagName(locatorValue);
	            case "linktext":
	            case "link":
	                return By.linkText(locatorValue);
	            case "partiallinktext":
	                return By.partialLinkText(locatorValue);
	            case "cssselector":
	            case "css":
	                return By.cssSelector(locatorValue);
	            case "xpath":
	                return By.xpath(locatorValue);
	            default:
	                throw new Exception("Locator type not supported: " + locatorType);
	        }
	    }
	}


