package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Represents the Product Detail page and provides methods to interact with its elements.
 */
public class ProductDetailPage {
    private WebDriver driver;

    // Locator for the product title on the detail page
    private By productTitle = By.className("inventory_details_name");

    /**
     * Constructor to initialize the ProductDetailPage with WebDriver.
     */
    public ProductDetailPage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Retrieves the product title displayed on the detail page.
     */
    public String getProductTitle() {
        WebElement titleElement = driver.findElement(productTitle);
        return titleElement.getText();
    }
}
