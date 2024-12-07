package config;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverManager {

    private static WebDriver driver;

    // Initializes the WebDriver instance
    public static void initializeDriver() {
        if (driver == null) {
            WebDriverManager.chromedriver().browserVersion("latest").setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--start-maximized");
            options.addArguments("--disable-notifications");
            driver = new ChromeDriver(options);
        }
    }

    // Returns the current WebDriver instance
    public static WebDriver getDriver() {
        if (driver == null) {
            initializeDriver();
        }
        return driver;
    }

    // Quits and nullifies the WebDriver instance
    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null; // Reset the instance to ensure no reuse of stale driver
        }
    }
}