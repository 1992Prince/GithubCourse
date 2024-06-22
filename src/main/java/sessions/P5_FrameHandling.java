package sessions;

import com.microsoft.playwright.*;

public class P5_FrameHandling {

    public static void main(String[] args) {

        Playwright playwright = Playwright.create();
        BrowserType.LaunchOptions lp = new BrowserType.LaunchOptions();
        lp.setChannel("chrome");
        lp.setHeadless(false);
        Browser browser = playwright.chromium().launch(lp);

        BrowserContext brcx1 = browser.newContext();
        Page page = brcx1.newPage();



        // Example 1
        /*page.navigate("http://londonfreelance.org/courses/frames/index.html");

        String header =
                page.frameLocator("frame[name='main']").locator("h2").textContent();
        System.out.println("header - " + header);

        String header2 =
                page.frame("main").locator("h2").textContent();
        System.out.println("header2 - " + header2);*/

        page.navigate("https://www.formsite.com/templates/registration-form-templates/vehicle-registration-form/");

        page.locator("img[title='Vehicle-Registration-Forms-and-Examples']").click();

        page.frameLocator("//iframe[contains(@id,'frame-one')]")
                .locator("#RESULT_TextField-8").fill("Naveen Automation");


    }
}
