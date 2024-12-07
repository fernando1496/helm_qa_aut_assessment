package base;

import config.DriverManager;
import org.openqa.selenium.WebDriver;
import java.time.Duration;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public abstract class BaseTest {

    @BeforeMethod
    public void setUp() {
        // Initialize the WebDriver
        DriverManager.initializeDriver();
        WebDriver driver = DriverManager.getDriver();

        // Set an implicit wait of 20 seconds for all element searches
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

        // Open the application URL
        driver.get("https://www.saucedemo.com/");
    }

    @AfterMethod
    public void tearDown() {
        // Quit the WebDriver after test execution
        DriverManager.quitDriver();
    }
}
