package sessions;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;

import java.nio.file.Paths;

public class Example {
    public static void main(String[] args) {
        try (Playwright playwright = Playwright.create()) {
            BrowserType.LaunchOptions lp = new BrowserType.LaunchOptions();
            lp.setChannel("chrome");
            lp.setHeadless(false);
            Browser browser = playwright.chromium().launch(lp);
            BrowserContext context = browser.newContext();
            context.tracing().start(new Tracing.StartOptions()
                    .setScreenshots(true)
                    .setSnapshots(true)
                    .setSources(true));
            Page page = context.newPage();
            //page.navigate("https://naveenautomationlabs.com/opencart/index.php?route=account/login");
            page.navigate("https://www.google.com/");
            page.getByLabel("Search", new Page.GetByLabelOptions().setExact(true)).click();
            page.getByLabel("Search", new Page.GetByLabelOptions().setExact(true)).fill("Narendra Modi");
            page.getByRole(AriaRole.SEARCH).getByRole(AriaRole.IMG).first().click();
            page.getByLabel("Search", new Page.GetByLabelOptions().setExact(true)).click();
            page.getByLabel("Search", new Page.GetByLabelOptions().setExact(true)).click();
            page.getByLabel("Search", new Page.GetByLabelOptions().setExact(true)).click();
            page.navigate("https://www.google.com/search?q=Narendra+Modi&sca_esv=bd5de69f547e2ef3&source=hp&ei=lpVlZsaJFKfQ1sQPqO-z8Ao&iflsig=AL9hbdgAAAAAZmWjpk5zNBjl1tEZh-Qxj3-pu9gS5EEB&ved=0ahUKEwjGrJTuuM6GAxUnqJUCHaj3DK4Q4dUDCA0&uact=5&oq=Narendra+Modi&gs_lp=Egdnd3Mtd2l6Ig1OYXJlbmRyYSBNb2RpMgsQLhiABBixAxiDATILEAAYgAQYsQMYgwEyCxAAGIAEGLEDGIMBMgsQABiABBixAxiDATIIEAAYgAQYsQMyCxAAGIAEGLEDGIMBMgsQABiABBixAxiDATILEAAYgAQYsQMYgwEyDhAAGIAEGLEDGIMBGIoFMggQABiABBixA0i20wFQkhtYjENwBngAkAEBmAHIA6ABuReqAQowLjEwLjIuMS4xuAEDyAEA-AEBmAIToAL_FqgCCsICEBAAGAMY5QIY6gIYjAMYjwHCAhAQLhgDGOUCGOoCGIwDGI8BwgIFEAAYgATCAg4QLhiABBixAxiDARiKBcICBRAuGIAEwgIOEC4YgAQYsQMY0QMYxwHCAg4QLhiABBjHARiOBRivAcICCxAuGIAEGMcBGK8BwgIOEC4YgAQYsQMYgwEY1ALCAggQLhiABBixA8ICDRAAGIAEGLEDGIMBGAqYAxySBwc2LjYuNi4xoAeRnAE&sclient=gws-wiz");
            page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("News").setExact(true)).click();
            page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Images")).click();

            context.tracing().stop(new Tracing.StopOptions()
                    .setPath(Paths.get("trace.zip")));
        }
    }
}
