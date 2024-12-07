package base;

import config.DriverManager;
import org.openqa.selenium.WebDriver;
import java.time.Duration;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

/**
 * Abstract base class for all test classes.
 * Provides setup and teardown methods for managing WebDriver.
 */
public abstract class BaseTest {

    /**
     * Setup method to initialize the WebDriver and open the application URL.
     * Configures an implicit wait of 5 seconds for element searches.
     */
    @BeforeMethod
    public void setUp() {
        // Initialize the WebDriver
        DriverManager.initializeDriver();
        WebDriver driver = DriverManager.getDriver();

        // Set an implicit wait of 5 seconds for all element searches
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        // Open the application URL
        driver.get("https://www.saucedemo.com/");
    }

    /**
     * Teardown method to quit the WebDriver after test execution.
     */
    @AfterMethod
    public void tearDown() {
        // Quit the WebDriver
        DriverManager.quitDriver();
    }
}
