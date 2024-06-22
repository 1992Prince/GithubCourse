package sessions;

import com.microsoft.playwright.*;

import java.util.List;

public class P3_LocatorConcept {

    public static void main(String[] args) {

        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(false));

        BrowserContext brcx1 = browser.newContext();
        Page p1 = brcx1.newPage();
        p1.navigate("https://www.orangehrm.com/orangehrm-30-day-trial");


        Locator contactSales = p1.locator("text = CONTACT SALES");
        int countval = contactSales.count();
        contactSales.nth(1).highlight();
        contactSales.nth(1).hover();
        System.out.println("brcx1 title is - " + p1.title() + " " + countval);
       // contactSales.nth(1).click();
//        Locator contactSales = contactSales.nth(1);
//        contactSales.highlight();
//        contactSales.hover();
//        contactSales.click();

        // managing multiple elements
        Locator countryOptions = p1.locator("select#Form_getForm_Country option");
        System.out.println("countryOptions.count() - " + countryOptions.count());

        /*for (int i=0; i < countryOptions.count() ; i++){
        String text = countryOptions.nth(i).textContent();
        System.out.println(text);
        }*/

        List<String> countryOptionstext = countryOptions.allTextContents();
        System.out.println("countryOptionstext - " + countryOptionstext);


    }
}
