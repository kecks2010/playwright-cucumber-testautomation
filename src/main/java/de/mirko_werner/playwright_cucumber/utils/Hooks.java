package de.mirko_werner.playwright_cucumber.utils;

import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;

public class Hooks {

    @Before(order = 100)
    public void before() {
        PageFactory.setNewPage();
    }

    @After
    public void after() {
        PageFactory.closeContext();
    }

    @AfterAll
    public static void afterAll() {
        PageFactory.closePlaywright();
    }
}
