package advance;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;

import javax.swing.*;


public class P2_ExceptionDialog {

    public static void main(String[] args) {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(
                    new BrowserType.LaunchOptions().setHeadless(false));

            BrowserContext brcx1 = browser.newContext();
            Page page = brcx1.newPage();
            page.navigate("https://letcode.in/edit");

            // Perform multiple actions with exception handling
            performActionWithExceptionHandling(page, () -> {
                page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Submit")).click();
            }, "Failed to find and click the 'Submit' button.");

            performActionWithExceptionHandling(page, () -> {
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

    private static void performActionWithExceptionHandling(Page page, PlaywrightAction action, String errorMessage) {
        try {
            action.perform();
        } catch (Exception e) {
            showExceptionDialog(errorMessage);
        }
    }

    private static void showExceptionDialog(String errorMessage) {
        JOptionPane optionPane = new JOptionPane(
                errorMessage,
                JOptionPane.ERROR_MESSAGE
        );

        JDialog dialog = optionPane.createDialog("Error");
        dialog.setAlwaysOnTop(true);
        dialog.setVisible(true);
    }
}