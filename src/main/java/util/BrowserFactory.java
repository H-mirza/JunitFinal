package util;

	import java.io.FileInputStream;
	import java.io.IOException;
	import java.io.InputStream;
	import java.util.Properties;
	import java.util.concurrent.TimeUnit;

	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.chrome.ChromeDriver;

	public class BrowserFactory {

		static WebDriver driver;
		static String browser;
		static String url;
		
		public static void readConfig() {
			InputStream input;
			try {
				input = new FileInputStream("src\\main\\java\\config\\config.properties");
			
		Properties prop = new Properties();			
			prop.load(input);
			browser = prop.getProperty("browser");
			  url = prop.getProperty("url");
			
			
			} catch (IOException e) {
				e.printStackTrace();			}
		}
		public static WebDriver init() {
			
			readConfig();
			
			if (browser.equalsIgnoreCase("chrome")) {
				System.setProperty("webdriver.chrome.driver", "driver\\chromedriver.exe");
				driver  = new ChromeDriver();
				
			}
			
			driver.manage().deleteAllCookies();
			driver.get("http://techfios.com/test/101/");
			driver.manage().window().maximize();
			driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			
			return driver;
		}
		
		public static void tearDown() {
			driver.close();
			driver.quit();
			
		}
		
	}