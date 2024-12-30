package de.mirko_werner.playwright_cucumber.utils;

import com.microsoft.playwright.BrowserContext;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {

    @Before
    public void before() {
        PageFactory.setNewPage();
    }

    @After
    public void after() {
        PageFactory.closeContext();
    }
}
