package de.mirko_werner.playwright_cucumber.api;

import com.microsoft.playwright.options.FormData;
import com.microsoft.playwright.options.RequestOptions;
import de.mirko_werner.playwright_cucumber.models.EndPoint;
import de.mirko_werner.playwright_cucumber.models.PageObject;
import de.mirko_werner.testdata.persistence.models.Product;
import de.mirko_werner.testdata.repositories.ProductRepository;

@EndPoint("/?wc-ajax=add_to_cart")
public class AddToCartInterface extends PageObject {

    public void addRandomProductToCart(int quantity) {

        Product product = ProductRepository.getInstance().getRandomProduct();

        var response = page.request().post(getUrl(),
                RequestOptions.create()
                        .setHeader("Content-Type", "application/x-www-form-urlencoded")
                        .setForm(FormData.create()
                                .set("product_sku", "")
                                .set("product_id", product.id())
                                .set("quantity", quantity)));
        if (response.status() != 200) {
            throw new RuntimeException("Failed to add product " + product.id() + " to the cart, " +
                    "HTTP status code: " + response.status());
        }
    }
}
