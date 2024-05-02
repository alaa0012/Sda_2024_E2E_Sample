package utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

public class Driver {
    private static WebDriver driver;

    private Driver(){

    }

    public static WebDriver getDriver(){
        if (driver == null) {//we create new driver only if driver is null.

            String browser = ConfigReader.getProperty("browser");//from configuration.properties file we get value of driver as String

            switch (browser) {//we create driver according to configuration.properties file.

                case "chrome":
                    driver = new ChromeDriver();
                    break;
                case "edge":
                    driver = new EdgeDriver();
                    break;
                case "firefox":
                    driver = new FirefoxDriver();
                    break;
                case "headless":
                    driver = new ChromeDriver(new ChromeOptions().addArguments("--headless=new"));
                    break;
                default:
                    driver = new ChromeDriver();

            }

        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        return driver;
    }

    public static void tearDown(){
        if (driver!=null){
            driver.quit();

            driver = null; // By this way I can create a new driver
        }
    }

}