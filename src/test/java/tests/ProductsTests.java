package tests;

import base.BaseTest;
import config.DriverManager;
import io.qameta.allure.Allure;
import models.Product;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.LoginPage;
import pages.ProductDetailPage;
import pages.ProductPage;
import utils.EnvLoader;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Test class for verifying product functionalities, including sorting, navigation, and cart actions.
 */
public class ProductsTests extends BaseTest {

    /**
     * Test to verify adding and removing products from the cart.
     */
    @Test(groups = {"positive"})
    public void testAddAndRemoveProductsFromCart() {
        Allure.step("Fetch valid credentials from environment file", () -> {
            String username = EnvLoader.getEnv("VALID_USERNAME");
            String password = EnvLoader.getEnv("VALID_PASSWORD");

            Allure.step("Login with valid credentials", () -> {
                LoginPage loginPage = new LoginPage(DriverManager.getDriver());
                loginPage.login(username, password);
                Assert.assertTrue(DriverManager.getDriver().getCurrentUrl().contains("inventory.html"),
                        "Login failed or navigation to the products page was unsuccessful.");
            });

            Allure.step("Add all products to the cart", () -> {
                ProductPage productPage = new ProductPage(DriverManager.getDriver());
                productPage.clickAllAddToCartButtons();

                Assert.assertTrue(productPage.isShoppingCartBadgeVisible(),
                        "Shopping cart badge is not visible after adding items.");

                List<Product> productPageProducts = productPage.getProducts();
                System.out.println("Products on Product Page: " + productPageProducts);

                productPage.clickShoppingCartContainer();

                CartPage cartPage = new CartPage(DriverManager.getDriver());
                List<String> cartProductNames = cartPage.getCartProductNames();
                List<String> productPageNames = productPageProducts.stream()
                        .map(Product::getName)
                        .collect(Collectors.toList());

                Assert.assertEquals(cartProductNames, productPageNames,
                        "Product names in the cart do not match those added from the products page.");

                cartPage.clickContinueShopping();
                productPage.clickAllRemoveButtons();

                Assert.assertTrue(productPage.isRemoveButtonAbsent(),
                        "Remove buttons are still present after removing all items.");
                Assert.assertTrue(productPage.isShoppingCartBadgeAbsent(),
                        "Shopping cart badge is still visible after removing all items.");

                productPage.clickShoppingCartContainer();
                Assert.assertTrue(cartPage.isCartEmpty(), "Cart is not empty after removing all items.");
            });
        });
    }

    /**
     * Test to verify sorting products by name in ascending order.
     */
    @Test(groups = {"positive"})
    public void testSortProductsByNameAscending() {
        Allure.step("Fetch valid credentials from environment file", () -> {
            String username = EnvLoader.getEnv("VALID_USERNAME");
            String password = EnvLoader.getEnv("VALID_PASSWORD");

            Allure.step("Login with valid credentials", () -> {
                LoginPage loginPage = new LoginPage(DriverManager.getDriver());
                loginPage.login(username, password);
            });

            Allure.step("Sort products by Name (A to Z)", () -> {
                ProductPage productPage = new ProductPage(DriverManager.getDriver());
                productPage.sortProducts("az");

                List<Product> products = productPage.getProducts();
                List<String> actualNames = products.stream().map(Product::getName).collect(Collectors.toList());
                List<String> expectedNames = actualNames.stream().sorted().collect(Collectors.toList());

                Assert.assertEquals(actualNames, expectedNames, "Products are not sorted by name (A to Z).");
            });
        });
    }

    /**
     * Test to verify sorting products by name in descending order.
     */
    @Test(groups = {"positive"})
    public void testSortProductsByNameDescending() {
        Allure.step("Fetch valid credentials from environment file", () -> {
            String username = EnvLoader.getEnv("VALID_USERNAME");
            String password = EnvLoader.getEnv("VALID_PASSWORD");

            Allure.step("Login with valid credentials", () -> {
                LoginPage loginPage = new LoginPage(DriverManager.getDriver());
                loginPage.login(username, password);
            });

            Allure.step("Sort products by Name (Z to A)", () -> {
                ProductPage productPage = new ProductPage(DriverManager.getDriver());
                productPage.sortProducts("za");

                List<Product> products = productPage.getProducts();
                List<String> actualNames = products.stream().map(Product::getName).collect(Collectors.toList());
                List<String> expectedNames = actualNames.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());

                Assert.assertEquals(actualNames, expectedNames, "Products are not sorted by name (Z to A).");
            });
        });
    }

    /**
     * Test to verify navigation to a product detail page using the product name.
     */
    @Test(groups = {"positive"})
    public void testNavigateToProductDetailByName() {
        Allure.step("Fetch valid credentials from environment file", () -> {
            String username = EnvLoader.getEnv("VALID_USERNAME");
            String password = EnvLoader.getEnv("VALID_PASSWORD");

            Allure.step("Login with valid credentials", () -> {
                LoginPage loginPage = new LoginPage(DriverManager.getDriver());
                loginPage.login(username, password);
            });

            Allure.step("Navigate to product detail by product name", () -> {
                ProductPage productPage = new ProductPage(DriverManager.getDriver());
                List<Product> products = productPage.getProducts();

                String expectedProductName = products.get(0).getName();
                productPage.clickProductName(expectedProductName);

                ProductDetailPage productDetailPage = new ProductDetailPage(DriverManager.getDriver());
                String actualProductName = productDetailPage.getProductTitle();

                Assert.assertEquals(actualProductName, expectedProductName,
                        "Product detail page does not display the expected product.");
            });
        });
    }

    /**
     * Test to verify navigation to all product detail pages using product images.
     */
    @Test(groups = {"positive"})
    public void testNavigateToAllProductDetailsByImage() {
        Allure.step("Fetch valid credentials from environment file", () -> {
            String username = EnvLoader.getEnv("VALID_USERNAME");
            String password = EnvLoader.getEnv("VALID_PASSWORD");

            Allure.step("Login with valid credentials", () -> {
                LoginPage loginPage = new LoginPage(DriverManager.getDriver());
                loginPage.login(username, password);
            });

            Allure.step("Verify navigation to product details using product images", () -> {
                ProductPage productPage = new ProductPage(DriverManager.getDriver());
                List<Product> products = productPage.getProducts();

                for (Product product : products) {
                    System.out.println("Verifying product: " + product);

                    productPage.clickProductImage(product.getImageDataTest());

                    ProductDetailPage productDetailPage = new ProductDetailPage(DriverManager.getDriver());
                    String actualProductName = productDetailPage.getProductTitle();

                    Assert.assertEquals(actualProductName, product.getName(),
                            "Product detail page does not display the expected product.");

                    DriverManager.getDriver().navigate().back();
                }
            });
        });
    }
}
