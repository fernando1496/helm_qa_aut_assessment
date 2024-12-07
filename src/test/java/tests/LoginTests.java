package tests;

import base.BaseTest;
import config.DriverManager;
import io.qameta.allure.Allure;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.EnvLoader;

/**
 * Test class for Login functionality, covering both positive and negative scenarios.
 */
public class LoginTests extends BaseTest {

    /**
     * Test to verify successful login and logout functionality.
     */
    @Test(groups = {"positive"})
    public void testValidLoginAndLogout() {
        Allure.step("Fetch credentials from the environment file", () -> {
            String username = EnvLoader.getEnv("VALID_USERNAME");
            String password = EnvLoader.getEnv("VALID_PASSWORD");

            Allure.step("Log in with valid credentials", () -> {
                LoginPage loginPage = new LoginPage(DriverManager.getDriver());
                loginPage.login(username, password);

                Allure.step("Verify user is redirected to inventory page", () -> {
                    String currentUrl = DriverManager.getDriver().getCurrentUrl();
                    Assert.assertEquals(currentUrl, "https://www.saucedemo.com/inventory.html",
                            "The URL after logging in is incorrect.");
                });
            });

            Allure.step("Log out from the application", () -> {
                LoginPage loginPage = new LoginPage(DriverManager.getDriver());
                loginPage.clickBurgerMenuButton();
                loginPage.clickLogoutButton();

                Allure.step("Verify user is redirected to the login page", () -> {
                    String logoutUrl = DriverManager.getDriver().getCurrentUrl();
                    Assert.assertEquals(logoutUrl, "https://www.saucedemo.com/",
                            "The URL after logging out is incorrect.");
                });
            });
        });
    }

    /**
     * Test to verify an error message is displayed when the username is not provided.
     */
    @Test(groups = {"negative"})
    public void testUsernameRequiredError() {
        Allure.step("Navigate to login page and click login without entering credentials", () -> {
            LoginPage loginPage = new LoginPage(DriverManager.getDriver());
            loginPage.clickLoginButton();
        });

        Allure.step("Verify 'Username is required' error message is displayed", () -> {
            String expectedErrorMessage = "Epic sadface: Username is required";
            Assert.assertTrue(new LoginPage(DriverManager.getDriver()).isErrorMessageVisible(expectedErrorMessage),
                    String.format("Error message '%s' was not displayed.", expectedErrorMessage));
        });
    }

    /**
     * Test to verify an error message is displayed when the password is not provided.
     */
    @Test(groups = {"negative"})
    public void testPasswordRequiredError() {
        Allure.step("Enter invalid username and leave the password field empty", () -> {
            LoginPage loginPage = new LoginPage(DriverManager.getDriver());
            String invalidUsername = "non_existent_user";
            loginPage.enterUsername(invalidUsername);
            loginPage.clickLoginButton();
        });

        Allure.step("Verify 'Password is required' error message is displayed", () -> {
            String expectedErrorMessage = "Epic sadface: Password is required";
            Assert.assertTrue(new LoginPage(DriverManager.getDriver()).isErrorMessageVisible(expectedErrorMessage),
                    String.format("Error message '%s' was not displayed.", expectedErrorMessage));
        });
    }

    /**
     * Test to verify an error message is displayed when invalid credentials are used.
     */
    @Test(groups = {"negative"})
    public void testInvalidCredentialsError() {
        Allure.step("Enter invalid username and password", () -> {
            LoginPage loginPage = new LoginPage(DriverManager.getDriver());
            String invalidUsername = "non_existent_user";
            String invalidPassword = "invalid_pass";
            loginPage.enterUsername(invalidUsername);
            loginPage.enterPassword(invalidPassword);
            loginPage.clickLoginButton();
        });

        Allure.step("Verify 'Invalid credentials' error message is displayed", () -> {
            String expectedErrorMessage = "Epic sadface: Username and password do not match any user in this service";
            Assert.assertTrue(new LoginPage(DriverManager.getDriver()).isErrorMessageVisible(expectedErrorMessage),
                    String.format("Error message '%s' was not displayed.", expectedErrorMessage));
        });
    }

    /**
     * Test to verify an error message is displayed when a locked user attempts to log in.
     */
    @Test(groups = {"negative"})
    public void testLockedUserError() {
        Allure.step("Enter locked username and valid password", () -> {
            LoginPage loginPage = new LoginPage(DriverManager.getDriver());
            String lockedUsername = EnvLoader.getEnv("LOCKED_USERNAME");
            String validPassword = EnvLoader.getEnv("VALID_PASSWORD");
            loginPage.enterUsername(lockedUsername);
            loginPage.enterPassword(validPassword);
            loginPage.clickLoginButton();
        });

        Allure.step("Verify 'User locked out' error message is displayed", () -> {
            String expectedErrorMessage = "Epic sadface: Sorry, this user has been locked out.";
            Assert.assertTrue(new LoginPage(DriverManager.getDriver()).isErrorMessageVisible(expectedErrorMessage),
                    String.format("Error message '%s' was not displayed.", expectedErrorMessage));
        });
    }
}
