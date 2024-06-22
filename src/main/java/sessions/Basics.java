package sessions;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class Basics {

    public static void main(String[] args) {

        Playwright playwright = Playwright.create();


        //Browser browser = playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(false));
        // Browser browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false));
        BrowserType.LaunchOptions lp = new BrowserType.LaunchOptions();
        lp.setChannel("chrome");
        lp.setHeadless(false);
        Browser browser = playwright.chromium().launch(lp);
        Page page = browser.newPage();
        page.navigate("https://naveenautomationlabs.com/opencart/index.php?route=common/home");

        String title = page.title();
        String url = page.url();

        System.out.println(title + " " + url);

        browser.close();
        playwright.close();


    }
}
