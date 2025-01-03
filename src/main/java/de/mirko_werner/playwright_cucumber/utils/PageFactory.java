package de.mirko_werner.playwright_cucumber.utils;

import com.microsoft.playwright.*;

public class PageFactory {

    private static final ThreadLocal<Playwright> THREAD_LOCAL_PLAYWRIGHT = ThreadLocal.withInitial(Playwright::create);
    private static final ThreadLocal<Browser> THREAD_LOCAL_BROWSER = new ThreadLocal<>();
    private static final ThreadLocal<BrowserContext> THREAD_LOCAL_BROWSER_CONTEXT = new ThreadLocal<>();
    private static final ThreadLocal<Page> THREAD_LOCAL_PAGE = new ThreadLocal<>();

    private static void initialize() {
        Playwright playwright = THREAD_LOCAL_PLAYWRIGHT.get();
        Browser browser;
        String browserType = System.getProperty("browser", "chrome");
        switch (browserType) {
            case "chrome" -> browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                    .setHeadless(false)
                    .setChannel("chrome"));
            case "firefox" -> browser = playwright.firefox().launch(new BrowserType.LaunchOptions()
                    .setHeadless(false)
                    .setChannel("firefox"));
            default -> throw new IllegalStateException("Unknown browser: " + browserType);
        }
        THREAD_LOCAL_BROWSER.set(browser);
    }

    private static Browser getBrowser() {
        if (THREAD_LOCAL_BROWSER.get() == null || THREAD_LOCAL_BROWSER.get().toString().contains("(null)")) {
            initialize();
        }
        return THREAD_LOCAL_BROWSER.get();
    }

    public static void closePlaywright() {
        THREAD_LOCAL_BROWSER.get().close();
        THREAD_LOCAL_BROWSER.remove();
        THREAD_LOCAL_PLAYWRIGHT.get().close();
        THREAD_LOCAL_PLAYWRIGHT.remove();
    }

    public static void setNewPage() {
        THREAD_LOCAL_BROWSER_CONTEXT.set(getBrowser().newContext(new Browser.NewContextOptions().setViewportSize(1900,1000)));
        THREAD_LOCAL_PAGE.set(THREAD_LOCAL_BROWSER_CONTEXT.get().newPage());
    }

    public static Page getPage() {
        return THREAD_LOCAL_PAGE.get();
    }

    public static void closeContext() {
        THREAD_LOCAL_BROWSER_CONTEXT.get().close();
        THREAD_LOCAL_BROWSER_CONTEXT.remove();
    }
}
