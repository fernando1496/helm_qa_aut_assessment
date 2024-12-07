package tests;

import base.BaseTest;
import config.DriverManager;
import io.qameta.allure.Allure;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.LoginPage;
import pages.ProductPage;
import utils.EnvLoader;

import java.util.List;

/**
 * Test class for cart functionality.
 */
public class CartTests extends BaseTest {

    /**
     * Test to verify that products can be removed from the cart.
     */
    @Test(groups = {"positive"})
    public void testRemoveProductsFromCart() {
        Allure.step("Fetch valid credentials from environment file", () -> {
            String username = EnvLoader.getEnv("VALID_USERNAME");
            String password = EnvLoader.getEnv("VALID_PASSWORD");

            Allure.step("Perform login with valid credentials", () -> {
                LoginPage loginPage = new LoginPage(DriverManager.getDriver());
                loginPage.login(username, password);

                Allure.step("Verify login was successful", () -> {
                    Assert.assertTrue(DriverManager.getDriver().getCurrentUrl().contains("inventory.html"),
                            "Login failed or navigation to the products page was unsuccessful.");
                });
            });

            Allure.step("Add all products to cart", () -> {
                ProductPage productPage = new ProductPage(DriverManager.getDriver());
                productPage.clickAllAddToCartButtons();

                Allure.step("Navigate to the Cart page", () -> {
                    productPage.clickShoppingCartContainer();
                });

                Allure.step("Verify all products are added to the cart", () -> {
                    CartPage cartPage = new CartPage(DriverManager.getDriver());
                    List<String> cartProductNames = cartPage.getCartProductNames();
                    Assert.assertFalse(cartProductNames.isEmpty(), "Cart is empty after adding products.");
                    System.out.println("Products in cart before removal: " + cartProductNames);

                    Allure.step("Remove all products from the cart", () -> {
                        cartPage.clickAllRemoveButtons();
                    });

                    Allure.step("Verify the cart is empty after removal", () -> {
                        Assert.assertTrue(cartPage.isCartEmpty(), "Cart is not empty after removing all items.");
                        System.out.println("Cart is empty after removing all products.");
                    });
                });
            });
        });
    }
}
