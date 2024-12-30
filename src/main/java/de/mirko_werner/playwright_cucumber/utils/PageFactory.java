package de.mirko_werner.playwright_cucumber.utils;

import com.microsoft.playwright.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class PageFactory {

    private static final ThreadLocal<Map<String, Object>> THREAD_LOCAL_PLAYWRIGHT_PARAMS = new ThreadLocal<>();

    private static void initialize() {
        Map<String, Object> params = new HashMap<>();
        Playwright playwright = Playwright.create();
        Browser browser;
        String browserType = System.getProperty("browser", "chrome");
        switch (browserType) {
            case "chrome" -> {
                browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false)
                        .setArgs(List.of("--window-size=1900,1000")).setChannel("chrome"));
            }
            case "firefox" -> {
                browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false)
                        .setArgs(List.of("-width=1900", "-height=1000")).setChannel("firefox"));
            }
            default -> throw new IllegalStateException("Unknown browser: " + browserType);
        }
        params.put("playwright", Playwright.create());
        params.put("browser", browser);
        PageFactory.THREAD_LOCAL_PLAYWRIGHT_PARAMS.set(params);
    }

    private static Map<String, Object> getPlaywrightParams() {
        if (THREAD_LOCAL_PLAYWRIGHT_PARAMS.get() == null || THREAD_LOCAL_PLAYWRIGHT_PARAMS.get().toString().contains("(null)")) {
            initialize();
        }
        return THREAD_LOCAL_PLAYWRIGHT_PARAMS.get();
    }

    public static void setNewPage() {
        Browser browser = (Browser) getPlaywrightParams().get("browser");
        BrowserContext browserContext = ((Browser) getPlaywrightParams().get("browser")).newContext();
        Page page = browserContext.newPage();

        getPlaywrightParams().put("browserContext", browserContext);
        getPlaywrightParams().put("page", page);
    }

    public static Page getPage() {
        return (Page) getPlaywrightParams().get("page");
    }

    public static void closeContext() {
        ((BrowserContext) getPlaywrightParams().get("browserContext")).close();
        getPlaywrightParams().remove("page");
        getPlaywrightParams().remove("browserContext");
    }
}
