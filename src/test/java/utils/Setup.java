package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

import io.cucumber.java.Before;

public class Setup {
	private static WebDriver driver;
	/**
	 * This method is used to open browser. This method is called before the
	 * invocation of each test method in the given class. In this method we need to
	 * pass browser name which will invoke the respective driver.
	 *
	 * @throws MalformedURLException the malformed URL exception
	 * @Before Methods annotated with @Before will execute before every scenario.
	 */ //java dog

	@Before
	public void setWebDriver() {
		 System.out.println("=== DEBUT SETUP ===");
		String browser = System.getProperty("browser");
		if(browser==null) {
		   browser = "chrome";
		}
		System.out.println("Browser = " + browser);

		switch(browser) {
		
		case "chrome":
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.addArguments("--incognito");
			driver = new ChromeDriver(chromeOptions);
			driver.manage().window().maximize();
		break;
		
		case "firefox":
			FirefoxProfile profile = new FirefoxProfile();
			FirefoxOptions firefoxOptions = new FirefoxOptions();
			//firefoxOptions.setCapability("platform", Platform.WIN10);
			firefoxOptions.setProfile(profile);
			driver = new FirefoxDriver(firefoxOptions);
	        System.out.println("FirefoxDriver créé");
			driver.manage().window().maximize();
			
			break;
			
		case "edge":
			driver = new EdgeDriver();
			 System.out.println("EdgeDriver créé");
			driver.manage().window().maximize();
			break;
			
			default:
				throw new IllegalArgumentException("Browser\"" + browser+"\" is not supported.");
		         }
		System.out.println("=== FIN SETUP ===");
	}
	/*GETTER*/
	public static WebDriver getDriver() {
		return driver;
		
			
			
			
			
		}
}
