package login;

import base.BaseTest;
import config.DriverManager;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.EnvLoader;

public class LoginTests extends BaseTest {

    @Test
    public void testValidLogin() {
        // Fetch credentials from the .env file
        String username = EnvLoader.getEnv("VALID_USERNAME");
        String password = EnvLoader.getEnv("VALID_PASSWORD");

        LoginPage loginPage = new LoginPage(DriverManager.getDriver());
        loginPage.login(username, password);

        // Verify the current URL is the expected inventory page
        String currentUrl = DriverManager.getDriver().getCurrentUrl();
        Assert.assertEquals(currentUrl, "https://www.saucedemo.com/inventory.html",
                "The URL after logging in is incorrect.");
    }
}
