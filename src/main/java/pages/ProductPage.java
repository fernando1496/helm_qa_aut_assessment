package pages;

import models.Product;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the Product Page and provides methods to interact with its elements.
 */
public class ProductPage {
    private WebDriver driver;

    // Locators
    private By addToCartButtons = By.xpath("//button[text()='Add to cart']");
    private By removeButtons = By.xpath("//button[text()='Remove']");
    private By shoppingCartBadge = By.cssSelector(".shopping_cart_badge");
    private By shoppingCartContainer = By.xpath("//div[@id='shopping_cart_container']");
    private By productNames = By.cssSelector(".inventory_item_name");
    private By productPrices = By.cssSelector(".inventory_item_price");
    private By productImages = By.xpath("//img[contains(@data-test, 'inventory-item')]");
    private By productSortDropdown = By.cssSelector(".product_sort_container");

    /**
     * Constructor to initialize the ProductPage with WebDriver.
     */
    public ProductPage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Clicks all "Add to Cart" buttons on the page.
     */
    public void clickAllAddToCartButtons() {
        List<WebElement> buttons = driver.findElements(addToCartButtons);
        for (WebElement button : buttons) {
            button.click();
        }
    }

    /**
     * Clicks all "Remove" buttons on the page.
     */
    public void clickAllRemoveButtons() {
        List<WebElement> buttons = driver.findElements(removeButtons);
        for (WebElement button : buttons) {
            button.click();
        }
    }

    /**
     * Verifies if "Remove" buttons are absent.
     */
    public boolean isRemoveButtonAbsent() {
        return driver.findElements(removeButtons).isEmpty();
    }

    /**
     * Verifies if the shopping cart badge is visible.
     */
    public boolean isShoppingCartBadgeVisible() {
        return !driver.findElements(shoppingCartBadge).isEmpty();
    }

    /**
     * Verifies if the shopping cart badge is absent.
     */
    public boolean isShoppingCartBadgeAbsent() {
        return driver.findElements(shoppingCartBadge).isEmpty();
    }

    /**
     * Clicks the shopping cart container to navigate to the cart.
     */
    public void clickShoppingCartContainer() {
        driver.findElement(shoppingCartContainer).click();
    }

    /**
     * Retrieves a list of products with their names, prices, and image data-test attributes.
     */
    public List<Product> getProducts() {
        List<WebElement> nameElements = driver.findElements(productNames);
        List<WebElement> priceElements = driver.findElements(productPrices);
        List<WebElement> imageElements = driver.findElements(productImages);

        List<Product> products = new ArrayList<>();
        for (int i = 0; i < nameElements.size(); i++) {
            String name = nameElements.get(i).getText();
            String price = priceElements.get(i).getText();
            String imageDataTest = imageElements.get(i).getAttribute("data-test");

            // Debugging log to verify the data-test attribute value
            System.out.println("Product: " + name + ", Price: " + price + ", Image Data-Test: " + imageDataTest);

            products.add(new Product(name, price, imageDataTest));
        }
        return products;
    }

    /**
     * Sorts the products on the page based on the specified sort option.
     */
    public void sortProducts(String sortOption) {
        WebElement dropdownElement = driver.findElement(productSortDropdown);
        Select dropdown = new Select(dropdownElement);
        dropdown.selectByValue(sortOption);
    }

    /**
     * Clicks on a product name to navigate to its detail page.
     */
    public void clickProductName(String productName) {
        List<WebElement> productElements = driver.findElements(productNames);
        for (WebElement product : productElements) {
            if (product.getText().equals(productName)) {
                product.click();
                break;
            }
        }
    }

    /**
     * Clicks on a product image to navigate to its detail page based on its data-test attribute.
     */
    public void clickProductImage(String imageDataTest) {
        List<WebElement> imageElements = driver.findElements(productImages);

        // Log the number of elements found for debugging
        System.out.println("Number of product images found: " + imageElements.size());

        // Ensure the list is not empty
        if (imageElements.isEmpty()) {
            throw new RuntimeException("No product images found on the page.");
        }

        for (WebElement image : imageElements) {
            String dataTest = image.getAttribute("data-test");
            System.out.println("Found image with data-test: " + dataTest); // Debugging log
            if (dataTest.equals(imageDataTest)) {
                image.click();
                return;
            }
        }
        throw new RuntimeException("Product image with data-test '" + imageDataTest + "' not found.");
    }
}
