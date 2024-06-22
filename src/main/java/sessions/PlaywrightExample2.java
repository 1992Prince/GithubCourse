package sessions;

import com.microsoft.playwright.*;

import java.util.ArrayList;
import java.util.List;

public class PlaywrightExample2 {
    public static void main(String[] args) {
        // Create a Playwright instance
        Playwright playwright = Playwright.create();

        // Define launch options with remote debugging port
        BrowserType.LaunchOptions launchOptions = new BrowserType.LaunchOptions()
                .setHeadless(false);

        // Add remote debugging port argument
        List<String> argsList = new ArrayList<>();
        argsList.add("--remote-debugging-port=9222");
        launchOptions.setArgs(argsList);

        // Launch the browser
        Browser browser = playwright.chromium().launch(launchOptions);

        // Create a new page and interact with it
        BrowserContext context = browser.newContext();
        Page page = context.newPage();

        // Navigate to a URL and interact with the page
        page.navigate("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

        /*Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Shutdown hook is running!");
            if (playwright != null) {
                //playwright.close();  // Close Playwright when the JVM exits
            }
        }));*/

        // Exit the program without closing the browser
        System.exit(0);
    }
}





