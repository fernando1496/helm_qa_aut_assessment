package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Locators for login elements
    private By usernameField = By.id("user-name");
    private By passwordField = By.id("password");
    private By loginButton = By.id("login-button");

    // Constructor to initialize WebDriver and WebDriverWait
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Explicit wait with a timeout of 10 seconds
    }

    // Waits for the visibility of an element
    private WebElement waitForElement(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    // Actions for interacting with login elements
    public void enterUsername(String username) {
        WebElement usernameElement = waitForElement(usernameField); // Wait for the username field
        usernameElement.sendKeys(username);
    }

    public void enterPassword(String password) {
        WebElement passwordElement = waitForElement(passwordField); // Wait for the password field
        passwordElement.sendKeys(password);
    }

    public void clickLoginButton() {
        WebElement loginButtonElement = waitForElement(loginButton); // Wait for the login button
        loginButtonElement.click();
    }

    // Method to perform a login
    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
    }
}
