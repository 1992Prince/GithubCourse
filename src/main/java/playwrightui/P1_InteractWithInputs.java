package playwrightui;

import com.microsoft.playwright.*;

public class P1_InteractWithInputs {

    public static void main(String[] args) {

        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(false));

        BrowserContext brcx1 = browser.newContext();
        Page page = brcx1.newPage();
        page.navigate("https://letcode.in/edit");

        /*
        locating by id
        type() vs fill()
        type() is depreceated now
         */
        page.locator("#fullName").fill("Arjun");
        Locator joinLoc = page.locator("#join");
        joinLoc.fill("I am healthy");

        // pressing tab button
        joinLoc.press("Tab");

        // fetching value from text box
        String value = page.locator("id=getMe").getAttribute("value");
        System.out.println("Value is - " + value);
    }
}
