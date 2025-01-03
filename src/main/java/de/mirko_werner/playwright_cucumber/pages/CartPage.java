package de.mirko_werner.playwright_cucumber.pages;

import de.mirko_werner.playwright_cucumber.models.EndPoint;
import de.mirko_werner.playwright_cucumber.models.PageObject;

@EndPoint("/cart")
public class CartPage extends PageObject {

    private final String productNameFieldSelector = "css=td[class='product-name'] a";
    private final String productQuantityFieldSelector = "css=input[type='number']";
    private final String productPriceFieldSelector = "css=span[class='woocommerce-Price-amount amount']";
    private final String totalPriceFieldSelector = "xpath=//th[contains(text(), 'Total')]/following::td//bdi";
    private final String checkoutButtonSelector = "css=a[class='checkout-button button alt wc-forward']";

    public String getProductName() {
        return page.waitForSelector(productNameFieldSelector).textContent();
    }

    public String getProductQuantity() {
        return page.waitForSelector(productQuantityFieldSelector).getAttribute("value");
    }

    public String getProductPrice() {
        return page.waitForSelector(productPriceFieldSelector).textContent();
    }

    public String getTotalPrice() {
        return page.waitForSelector(totalPriceFieldSelector).textContent();
    }

    public void proceedToCheckout() {
        page.waitForSelector(checkoutButtonSelector).click();
    }
}
