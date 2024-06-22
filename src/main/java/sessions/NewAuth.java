package sessions;

import com.microsoft.playwright.*;

import java.nio.file.Paths;

public class NewAuth {

    public static void main(String[] args) {

        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                .setHeadless(false));
        BrowserContext browserContext = browser.newContext(new Browser.NewContextOptions()
                .setStorageStatePath(Paths.get("applogin.json")));

        Page page = browserContext.newPage();
        page.navigate("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
//        page.getByPlaceholder("Username").click();
//        page.getByPlaceholder("Username").fill("Admin");
//        page.getByPlaceholder("Password").click();
//        page.getByPlaceholder("Password").fill("admin123");
//        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Login")).click();
//
//        browserContext.storageState(new BrowserContext.StorageStateOptions()
//                .setPath(Paths.get("applogin.json")));

    }
}
