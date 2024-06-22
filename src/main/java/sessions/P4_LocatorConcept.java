package sessions;

import com.microsoft.playwright.*;

import java.util.List;

public class P4_LocatorConcept {

    public static void main(String[] args) {

        Playwright playwright = Playwright.create();
        BrowserType.LaunchOptions lp = new BrowserType.LaunchOptions();
        lp.setChannel("chrome");
        lp.setHeadless(false);
        Browser browser = playwright.chromium().launch(lp);

        BrowserContext brcx1 = browser.newContext();
        Page page = brcx1.newPage();
        page.navigate("https://www.orangehrm.com/orangehrm-30-day-trial");


        Locator contactSales = page.locator("text = CONTACT SALES");
        int countval = contactSales.count();
        contactSales.nth(1).highlight();
        //contactSales.nth(1).click();
        System.out.println("brcx1 title is - " + page.title() + " " + countval);

        Locator privacyPolicy = page.locator("text=Privacy Policy");
        //privacyPolicy.first().click();
        List<String> privaryPolicyAllText = privacyPolicy.allTextContents();
        System.out.println("privaryPolicyAllText - " + privaryPolicyAllText);

        for (int i=0; i<privacyPolicy.count();i++){
            String text = privacyPolicy.nth(i).textContent();
            if (text.contains("Service Privacy Policy")){
                // privacyPolicy.nth(i).click();
                break;
            }
        }

        page.navigate("https://demo.opencart.com/index.php?route=account/login");
        String header = page.locator("h2:has-text('New Customer')").textContent();
        System.out.println("header - " + header);

        String header2 = page.locator("div.card-body h2:has-text('New Customer')").textContent();
        System.out.println("header2 - " + header2);

        //String header3 = page.locator("h2:has-text('Returning Customer')").textContent();
        String header3 = page.locator("'Returning Customer'").textContent();
        System.out.println("header3 - " + header3);

        Locator loginLocator = page.locator("form button:has-text('Login')");
        loginLocator.click();




    }
}
