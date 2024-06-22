package sessions;
import com.microsoft.playwright.*;

public class PlaywrightExample {
    public static void main(String[] args) {
        try (Playwright playwright = Playwright.create()) {
            // Connect to the running Chrome instance
            Browser browser = playwright.chromium().connectOverCDP("http://localhost:9222");

            // Create a new page or get an existing one
            BrowserContext context = browser.contexts().get(0); // Get the first context
            Page page;
            if (context.pages().isEmpty()) {
                page = context.newPage();
            } else {
                page = context.pages().get(0); // Get the existing page
            }

            // Navigate to the desired URL and interact with the page
            page.navigate("https://www.facebook.com");
            page.fill("input[name='email']", "pikahu@gmail.com");
        }
    }
}


