package de.mirko_werner.playwright_cucumber.models;

import com.microsoft.playwright.Page;
import de.mirko_werner.cucumber.utils.ConfigLoader;
import de.mirko_werner.cucumber.utils.WebdriverFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class PageObject {

    protected Page page;
    protected WebDriverWait wait;

    protected PageObject() {
        this.getWebDriver();
    }

    public Page getPage() {
        if (this.page == null) {
            this.webDriver = WebdriverFactory.getDriver();
            this.wait = new WebDriverWait(webDriver, Duration.ofSeconds(15));
            PageFactory.initElements(webDriver, this);
        }
        return this.page;
    }

    public void openUrl() {
        this.getPage().(this.getUrl());
    }

    protected String getUrl() {
        String endpoint = "";

        if (this.getClass().getAnnotation(EndPoint.class) != null) {
            endpoint = this.getClass().getAnnotation(EndPoint.class).value();
        }
        return ConfigLoader.getInstance().getBaseUrl(System.getProperty("environment", "default")) + endpoint;
    }
}
