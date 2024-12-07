package config;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

/**
 * Manages the WebDriver instance lifecycle for the tests.
 * Provides methods to initialize, retrieve, and quit the WebDriver.
 */
public class DriverManager {

    // Singleton WebDriver instance
    private static WebDriver driver;

    /**
     * Initializes the WebDriver instance if it is null.
     * Configures ChromeDriver with WebDriverManager and custom options.
     */
    public static void initializeDriver() {
        if (driver == null) {
            WebDriverManager.chromedriver().browserVersion("latest").setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--start-maximized");
            options.addArguments("--disable-notifications");
            driver = new ChromeDriver(options);
        }
    }

    /**
     * Retrieves the current WebDriver instance.
     * Initializes the WebDriver if it has not been created yet.
     *
     * @return The current WebDriver instance.
     */
    public static WebDriver getDriver() {
        if (driver == null) {
            initializeDriver();
        }
        return driver;
    }

    /**
     * Quits the WebDriver instance and sets it to null.
     * Ensures the instance is not reused after being quit.
     */
    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
