package playwrightui;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;

public class P2_LocateByRole {

    public static void main(String[] args) {

        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(false));

        BrowserContext brcx1 = browser.newContext();
        Page page = brcx1.newPage();
        page.navigate("https://letcode.in/buttons");

        Locator textLocator = page.getByRole(AriaRole.BUTTON,
                new Page.GetByRoleOptions().setName("Goto Home"));
        textLocator.click();
    }
}
