package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Represents the Login page and provides methods to interact with
 * its elements for login and logout functionality.
 */
public class LoginPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Locators for login elements
    private By usernameField = By.id("user-name");
    private By passwordField = By.id("password");
    private By loginButton = By.id("login-button");
    private By burgerMenuButton = By.id("react-burger-menu-btn");
    private By logoutButton = By.id("logout_sidebar_link");

    /**
     * Constructor to initialize the LoginPage with WebDriver and explicit wait.
     */
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    /**
     * Waits for the visibility of a specified element and returns it.
     */
    private WebElement waitForElement(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * Enters the username into the username input field.
     */
    public void enterUsername(String username) {
        WebElement usernameElement = waitForElement(usernameField);
        usernameElement.sendKeys(username);
    }

    /**
     * Enters the password into the password input field.
     */
    public void enterPassword(String password) {
        WebElement passwordElement = waitForElement(passwordField);
        passwordElement.sendKeys(password);
    }

    /**
     * Clicks the login button to attempt a login.
     */
    public void clickLoginButton() {
        WebElement loginButtonElement = waitForElement(loginButton);
        loginButtonElement.click();
    }

    /**
     * Clicks the burger menu button to open the navigation menu.
     */
    public void clickBurgerMenuButton() {
        WebElement menuButton = waitForElement(burgerMenuButton);
        menuButton.click();
    }

    /**
     * Clicks the logout button to log out of the application.
     */
    public void clickLogoutButton() {
        WebElement logoutBtn = waitForElement(logoutButton);
        logoutBtn.click();
    }

    /**
     * Performs the login action by entering credentials and clicking the login button.
     */
    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
    }

    /**
     * Checks if a specific error message is visible on the login page.
     */
    public boolean isErrorMessageVisible(String errorMessageText) {
        By dynamicErrorMessageLocator = By.xpath(String.format("//*[text()='%s']", errorMessageText));
        WebElement errorElement = waitForElement(dynamicErrorMessageLocator);
        return errorElement.isDisplayed();
    }
}
