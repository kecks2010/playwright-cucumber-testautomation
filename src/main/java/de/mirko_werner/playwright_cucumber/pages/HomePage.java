package de.mirko_werner.playwright_cucumber.pages;

import de.mirko_werner.playwright_cucumber.models.PageObject;

public class HomePage extends PageObject {

    private final String productsHeadline = "h2";

    public void waitUntilPageIsLoaded() {
        page.waitForSelector(productsHeadline);
    }
}
