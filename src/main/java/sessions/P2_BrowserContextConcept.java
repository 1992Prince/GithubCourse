package sessions;

import com.microsoft.playwright.*;

public class P2_BrowserContextConcept {

    public static void main(String[] args) {

        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(false));

        BrowserContext brcx1 = browser.newContext();
        Page p1 = brcx1.newPage();
        p1.navigate("https://www.orangehrm.com/orangehrm-30-day-trial");
        p1.fill("#Form_getForm_Name", "Naveen");
        System.out.println("brcx1 title is - " + p1.title());

        BrowserContext brcx2 = browser.newContext();
        Page p2 = brcx2.newPage();
        p2.navigate("https://www.google.com/");
        p2.fill("#APjFqb", "Pikachu");
        System.out.println("brcx1 title is - " + p2.title());



    }
}
