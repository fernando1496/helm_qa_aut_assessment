package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents the Cart page of the application and provides methods
 * to interact with elements on the Cart page.
 */
public class CartPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Locators
    private By cartProductNames = By.cssSelector(".inventory_item_name");
    private By continueShoppingButton = By.id("continue-shopping");
    private By removeButtons = By.xpath("//button[text()='Remove']");
    private By checkoutButton = By.id("checkout");

    /**
     * Constructor to initialize the CartPage with WebDriver and explicit wait.
     */
    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    /**
     * Clicks the "Continue Shopping" button.
     */
    public void clickContinueShopping() {
        driver.findElement(continueShoppingButton).click();
    }

    /**
     * Checks if the cart is empty by verifying no product names are present.
     */
    public boolean isCartEmpty() {
        return driver.findElements(cartProductNames).isEmpty();
    }

    /**
     * Retrieves a list of product names currently present in the cart.
     */
    public List<String> getCartProductNames() {
        return driver.findElements(cartProductNames).stream()
                .map(element -> element.getText())
                .collect(Collectors.toList());
    }

    /**
     * Clicks all "Remove" buttons to remove all products from the cart.
     */
    public void clickAllRemoveButtons() {
        List<WebElement> removeButtonsList = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(removeButtons));
        for (WebElement button : removeButtonsList) {
            button.click();
        }
    }

    /**
     * Clicks the "Checkout" button to proceed to the checkout process.
     */
    public void clickCheckoutButton() {
        WebElement checkout = wait.until(ExpectedConditions.visibilityOfElementLocated(checkoutButton));
        checkout.click();
    }
}
