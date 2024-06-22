package advance;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;

import javax.swing.*;


public class P1_ExceptionDialog {

    public static void main(String[] args) {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(
                    new BrowserType.LaunchOptions().setHeadless(false));

            BrowserContext brcx1 = browser.newContext();
            Page page = brcx1.newPage();
            page.navigate("https://letcode.in/edit");

            try {
                // Attempt to locate and click the button
                page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Submit")).click();
            } catch (Exception e) {
                // Show a popup dialog with the exception details
                showExceptionDialog(e.getMessage());
            }

            browser.close();
        }
    }

    private static void showExceptionDialog(String errorMessage) {
        JOptionPane optionPane = new JOptionPane(
                "Exception: " + errorMessage,
                JOptionPane.ERROR_MESSAGE
        );

        JDialog dialog = optionPane.createDialog("Error");
        dialog.setAlwaysOnTop(true);
        dialog.setVisible(true);
    }
}