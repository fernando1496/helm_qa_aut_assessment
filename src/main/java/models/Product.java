package models;

/**
 * Represents a product with its name, price, and optional image data-test attribute.
 */
public class Product {
    private String name; // Product name
    private String price; // Product price
    private String imageDataTest; // Data-test attribute for product image (optional)

    /**
     * Constructor for backward compatibility.
     *
     * @param name  The name of the product.
     * @param price The price of the product.
     */
    public Product(String name, String price) {
        this.name = name;
        this.price = price;
    }

    /**
     * Constructor for full initialization including the image data-test attribute.
     *
     * @param name          The name of the product.
     * @param price         The price of the product.
     * @param imageDataTest The data-test attribute of the product image.
     */
    public Product(String name, String price, String imageDataTest) {
        this.name = name;
        this.price = price;
        this.imageDataTest = imageDataTest;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getImageDataTest() {
        return imageDataTest;
    }

    @Override
    public String toString() {
        return "Product{name='" + name + "', price='" + price + "', imageDataTest='" + imageDataTest + "'}";
    }
}
