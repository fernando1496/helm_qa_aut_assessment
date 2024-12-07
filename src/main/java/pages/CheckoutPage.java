package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Represents the Checkout page and provides methods to interact with
 * the elements during the checkout process.
 */
public class CheckoutPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Locators
    private By firstNameInput = By.id("first-name");
    private By lastNameInput = By.id("last-name");
    private By postalCodeInput = By.id("postal-code");
    private By continueButton = By.id("continue");
    private By finishButton = By.id("finish");
    private By backToProductsButton = By.id("back-to-products");
    private By itemTotalLabel = By.cssSelector("div.summary_subtotal_label");
    private By ponyExpressLogo = By.cssSelector("img[data-test='pony-express']");

    /**
     * Constructor to initialize the CheckoutPage with WebDriver and explicit wait.
     */
    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    /**
     * Enters the first name in the checkout form.
     */
    public void enterFirstName(String firstName) {
        WebElement firstNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameInput));
        firstNameField.sendKeys(firstName);
    }

    /**
     * Enters the last name in the checkout form.
     */
    public void enterLastName(String lastName) {
        WebElement lastNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(lastNameInput));
        lastNameField.sendKeys(lastName);
    }

    /**
     * Enters the postal code in the checkout form.
     */
    public void enterPostalCode(String postalCode) {
        WebElement postalCodeField = wait.until(ExpectedConditions.visibilityOfElementLocated(postalCodeInput));
        postalCodeField.sendKeys(postalCode);
    }

    /**
     * Clicks the "Continue" button to proceed in the checkout process.
     */
    public void clickContinue() {
        WebElement continueBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(continueButton));
        continueBtn.click();
    }

    /**
     * Clicks the "Finish" button to complete the checkout process.
     */
    public void clickFinish() {
        WebElement finishBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(finishButton));
        finishBtn.click();
    }

    /**
     * Clicks the "Back to Products" button to return to the inventory page.
     */
    public void clickBackToProducts() {
        WebElement backToProductsBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(backToProductsButton));
        backToProductsBtn.click();
    }

    /**
     * Verifies if a specific error message is visible.
     */
    public boolean isErrorMessageVisible(String errorMessageText) {
        By dynamicErrorMessageLocator = By.xpath(String.format("//*[text()='%s']", errorMessageText));
        WebElement errorElement = wait.until(ExpectedConditions.visibilityOfElementLocated(dynamicErrorMessageLocator));
        return errorElement.isDisplayed();
    }

    /**
     * Retrieves the item total text displayed on the checkout page.
     */
    public String getItemTotalText() {
        WebElement itemTotalElement = wait.until(ExpectedConditions.visibilityOfElementLocated(itemTotalLabel));
        return itemTotalElement.getText();
    }

    /**
     * Verifies if the Pony Express logo is visible on the page.
     */
    public boolean isPonyExpressLogoVisible() {
        try {
            WebElement logoElement = wait.until(ExpectedConditions.visibilityOfElementLocated(ponyExpressLogo));
            return logoElement.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Verifies if an element with the specified text is visible on the page.
     */
    public boolean isElementVisibleByText(String elementText) {
        By dynamicTextLocator = By.xpath(String.format("//*[text()='%s']", elementText));
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(dynamicTextLocator));
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
