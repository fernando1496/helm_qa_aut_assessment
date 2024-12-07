package tests;

import base.BaseTest;
import config.DriverManager;
import io.qameta.allure.Allure;
import models.Product;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.CheckoutPage;
import pages.LoginPage;
import pages.ProductPage;
import utils.EnvLoader;

import java.util.List;

/**
 * Test class for Checkout functionality, covering both positive and negative scenarios.
 */
public class CheckoutTests extends BaseTest {

    /**
     * Test to verify that an error is displayed when the First Name field is left empty during checkout.
     */
    @Test(groups = {"negative"})
    public void testFirstNameIsRequired() {
        Allure.step("Fetch valid credentials from environment file", () -> {
            String username = EnvLoader.getEnv("VALID_USERNAME");
            String password = EnvLoader.getEnv("VALID_PASSWORD");

            Allure.step("Perform login with valid credentials", () -> {
                LoginPage loginPage = new LoginPage(DriverManager.getDriver());
                loginPage.login(username, password);
            });

            Allure.step("Add all products to cart and proceed to checkout", () -> {
                ProductPage productPage = new ProductPage(DriverManager.getDriver());
                productPage.clickAllAddToCartButtons();
                productPage.clickShoppingCartContainer();

                CartPage cartPage = new CartPage(DriverManager.getDriver());
                cartPage.clickCheckoutButton();
            });

            Allure.step("Verify error when First Name is missing", () -> {
                CheckoutPage checkoutPage = new CheckoutPage(DriverManager.getDriver());
                checkoutPage.enterLastName("Doe");
                checkoutPage.enterPostalCode("12345");
                checkoutPage.clickContinue();

                Assert.assertTrue(checkoutPage.isErrorMessageVisible("Error: First Name is required"),
                        "First Name required error message not displayed.");
            });
        });
    }

    /**
     * Test to verify that an error is displayed when the Last Name field is left empty during checkout.
     */
    @Test(groups = {"negative"})
    public void testLastNameIsRequired() {
        Allure.step("Fetch valid credentials from environment file", () -> {
            String username = EnvLoader.getEnv("VALID_USERNAME");
            String password = EnvLoader.getEnv("VALID_PASSWORD");

            Allure.step("Perform login with valid credentials", () -> {
                LoginPage loginPage = new LoginPage(DriverManager.getDriver());
                loginPage.login(username, password);
            });

            Allure.step("Add all products to cart and proceed to checkout", () -> {
                ProductPage productPage = new ProductPage(DriverManager.getDriver());
                productPage.clickAllAddToCartButtons();
                productPage.clickShoppingCartContainer();

                CartPage cartPage = new CartPage(DriverManager.getDriver());
                cartPage.clickCheckoutButton();
            });

            Allure.step("Verify error when Last Name is missing", () -> {
                CheckoutPage checkoutPage = new CheckoutPage(DriverManager.getDriver());
                checkoutPage.enterFirstName("John");
                checkoutPage.enterPostalCode("12345");
                checkoutPage.clickContinue();

                Assert.assertTrue(checkoutPage.isErrorMessageVisible("Error: Last Name is required"),
                        "Last Name required error message not displayed.");
            });
        });
    }

    /**
     * Test to verify that an error is displayed when the Postal Code field is left empty during checkout.
     */
    @Test(groups = {"negative"})
    public void testPostalCodeIsRequired() {
        Allure.step("Fetch valid credentials from environment file", () -> {
            String username = EnvLoader.getEnv("VALID_USERNAME");
            String password = EnvLoader.getEnv("VALID_PASSWORD");

            Allure.step("Perform login with valid credentials", () -> {
                LoginPage loginPage = new LoginPage(DriverManager.getDriver());
                loginPage.login(username, password);
            });

            Allure.step("Add all products to cart and proceed to checkout", () -> {
                ProductPage productPage = new ProductPage(DriverManager.getDriver());
                productPage.clickAllAddToCartButtons();
                productPage.clickShoppingCartContainer();

                CartPage cartPage = new CartPage(DriverManager.getDriver());
                cartPage.clickCheckoutButton();
            });

            Allure.step("Verify error when Postal Code is missing", () -> {
                CheckoutPage checkoutPage = new CheckoutPage(DriverManager.getDriver());
                checkoutPage.enterFirstName("John");
                checkoutPage.enterLastName("Doe");
                checkoutPage.clickContinue();

                Assert.assertTrue(checkoutPage.isErrorMessageVisible("Error: Postal Code is required"),
                        "Postal Code required error message not displayed.");
            });
        });
    }

    /**
     * End-to-end test for the checkout process, including product addition, verification, and order completion.
     */
    @Test(groups = {"positive"})
    public void testCheckoutEndToEnd() {
        Allure.step("Fetch valid credentials from environment file", () -> {
            String username = EnvLoader.getEnv("VALID_USERNAME");
            String password = EnvLoader.getEnv("VALID_PASSWORD");

            Allure.step("Perform login with valid credentials", () -> {
                LoginPage loginPage = new LoginPage(DriverManager.getDriver());
                loginPage.login(username, password);
            });

            Allure.step("Add all products to cart and proceed to checkout", () -> {
                ProductPage productPage = new ProductPage(DriverManager.getDriver());
                List<Product> products = productPage.getProducts();
                productPage.clickAllAddToCartButtons();
                productPage.clickShoppingCartContainer();

                CartPage cartPage = new CartPage(DriverManager.getDriver());
                cartPage.clickCheckoutButton();

                CheckoutPage checkoutPage = new CheckoutPage(DriverManager.getDriver());
                checkoutPage.enterFirstName("John");
                checkoutPage.enterLastName("Doe");
                checkoutPage.enterPostalCode("12345");
                checkoutPage.clickContinue();

                Allure.step("Verify item total matches calculated total", () -> {
                    double totalPrice = products.stream()
                            .mapToDouble(product -> Double.parseDouble(product.getPrice().replace("$", "")))
                            .sum();
                    String itemTotalText = checkoutPage.getItemTotalText();
                    Assert.assertEquals(itemTotalText.replace("Item total: $", ""), String.valueOf(totalPrice));
                });

                Allure.step("Complete checkout process", () -> {
                    checkoutPage.clickFinish();

                    Assert.assertTrue(checkoutPage.isElementVisibleByText("Checkout: Complete!"),
                            "Checkout Complete text is not visible.");
                    Assert.assertTrue(checkoutPage.isPonyExpressLogoVisible(),
                            "Pony Express logo is not visible.");

                    checkoutPage.clickBackToProducts();
                    Assert.assertEquals(DriverManager.getDriver().getCurrentUrl(), "https://www.saucedemo.com/inventory.html",
                            "Not on the Inventory page after clicking Back to Products.");
                });
            });
        });
    }
}
