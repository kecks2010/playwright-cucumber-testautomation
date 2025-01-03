package de.mirko_werner.playwright_cucumber.pages.components;

import com.microsoft.playwright.Locator;
import de.mirko_werner.playwright_cucumber.models.PageComponent;

import java.util.Objects;

public class ProductComponent extends PageComponent {

    private final String viewCartSelector = "css=[title='View cart']";
    private final String addToCartLinksSelector =  "css=a.button.product_type_simple.add_to_cart_button.ajax_add_to_cart";

    public void addToCart(String productName) {
        Locator addToCartLink = page.locator(addToCartLinksSelector).all().stream()
                .filter(locator -> Objects.requireNonNull(locator.getAttribute("aria-label")).contains(productName))
                .findFirst().orElse(null);
        assert addToCartLink != null;
        addToCartLink.click();
        page.click(viewCartSelector);
    }
}
