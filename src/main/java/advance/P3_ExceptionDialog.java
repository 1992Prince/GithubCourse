package advance;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;

import javax.swing.*;


public class P3_ExceptionDialog {

    public static void main(String[] args) {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(
                    new BrowserType.LaunchOptions().setHeadless(false));

            BrowserContext brcx1 = browser.newContext();
            Page page = brcx1.newPage();
            page.navigate("https://letcode.in/edit");

            // Perform multiple actions with exception handling and retry option
            performActionWithRetry(page, () -> {
                page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Submit")).click();
            }, "Failed to find and click the 'Submit' button.");

            performActionWithRetry(page, () -> {
                page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Username")).fill("myUsername");
            }, "Failed to find and fill the 'Username' textbox.");

            // Close the browser
            browser.close();
        }
    }

    @FunctionalInterface
    interface PlaywrightAction {
        void perform() throws Exception;
    }

    private static void performActionWithRetry(Page page, PlaywrightAction action, String errorMessage) {
        while (true) {
            try {
                action.perform();
                break; // Action succeeded, exit retry loop
            } catch (Exception e) {
                int result = showRetryDialog(errorMessage);
                if (result == JOptionPane.NO_OPTION) {
                    break; // User chose not to retry, exit retry loop
                }
            }
        }
    }

    private static int showRetryDialog(String errorMessage) {
        Object[] options = {"Retry", "Cancel"};
        int choice = JOptionPane.showOptionDialog(
                null,

                errorMessage + "\nDo you want to retry?",
                "Error",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.ERROR_MESSAGE,
                null,
                options,
                options[0]
        );
        return choice;
    }
}