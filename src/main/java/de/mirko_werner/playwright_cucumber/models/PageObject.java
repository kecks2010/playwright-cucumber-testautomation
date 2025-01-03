package de.mirko_werner.playwright_cucumber.models;

import com.microsoft.playwright.Page;
import de.mirko_werner.playwright_cucumber.utils.ConfigLoader;
import de.mirko_werner.playwright_cucumber.utils.PageFactory;

public abstract class PageObject {

    protected Page page;

    protected PageObject() {
        this.getPage();
    }

    public Page getPage() {
        if (this.page == null) {
            this.page = PageFactory.getPage();
        }
        return this.page;
    }

    public void openUrl() {
        this.getPage().navigate(this.getUrl());
        this.getPage().waitForLoadState();
    }

    protected String getUrl() {
        String endpoint = "";

        if (this.getClass().getAnnotation(EndPoint.class) != null) {
            endpoint = this.getClass().getAnnotation(EndPoint.class).value();
        }
        return ConfigLoader.getInstance().getBaseUrl(System.getProperty("environment", "default")) + endpoint;
    }
}
