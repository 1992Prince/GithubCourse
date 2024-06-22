package sessions;

import com.microsoft.playwright.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ReuseBrowserSession {
    private static final String WS_ENDPOINT_FILE = "wsEndpoint.txt";
    private static final int REMOTE_DEBUGGING_PORT = 9222;
    private static final String USER_DATA_DIR = "D:\\user-data-dir"; // Update with your user data directory path
    private static final String CHROME_PATH = "C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe"; // Update with your Chrome path
    private static final String TARGET_URL = "https://www.google.com";

    public static void main(String[] args) {
        File wsEndpointFile = new File(WS_ENDPOINT_FILE);

        Browser browser;

        try (Playwright playwright = Playwright.create()) {
            if (wsEndpointFile.exists()) {
                System.out.println("Connecting to existing browser session...");
                String wsEndpoint = new String(Files.readAllBytes(Paths.get(WS_ENDPOINT_FILE)));
                System.out.println("Connecting to WebSocket endpoint: " + wsEndpoint);
                browser = playwright.chromium().connectOverCDP(wsEndpoint);
            } else {
                System.out.println("Starting a new browser instance...");
                File userDataDir = new File(USER_DATA_DIR);
                if (!userDataDir.exists()) {
                    userDataDir.mkdirs();  // Ensure the user data directory exists
                    System.out.println("Created user data directory: " + USER_DATA_DIR);
                }

                ProcessBuilder pb = new ProcessBuilder(CHROME_PATH,
                        "--remote-debugging-port=" + REMOTE_DEBUGGING_PORT,
                        "--user-data-dir=" + USER_DATA_DIR);
                Process process = pb.start();

                System.out.println("Started Chrome process with PID: " + process.pid());
                System.out.println("Waiting for DevToolsActivePort file...");

                File devToolsActivePortFile = new File(USER_DATA_DIR, "DevToolsActivePort");
                boolean fileExists = false;
                for (int i = 0; i < 50; i++) {  // Retry for 5 seconds
                    if (devToolsActivePortFile.exists()) {
                        fileExists = true;
                        break;
                    }
                    TimeUnit.MILLISECONDS.sleep(100);
                }

                if (!fileExists) {
                    System.err.println("DevToolsActivePort file was not created in: " + devToolsActivePortFile.getAbsolutePath());
                    throw new IOException("DevToolsActivePort file was not created");
                }

                List<String> lines = Files.readAllLines(devToolsActivePortFile.toPath());
                if (lines.size() < 2) {
                    throw new IOException("Invalid DevToolsActivePort file contents: " + lines);
                }

                String wsEndpoint = "ws://localhost:" + lines.get(0) + "/devtools/browser/" + lines.get(1);
                System.out.println("DevToolsActivePort content: " + String.join(", ", lines));
                System.out.println("WebSocket endpoint: " + wsEndpoint);

                try (FileWriter writer = new FileWriter(WS_ENDPOINT_FILE)) {
                    writer.write(wsEndpoint);
                }

                browser = playwright.chromium().connectOverCDP(wsEndpoint);
            }

            BrowserContext context = browser.newContext();
            Page page = context.newPage();

            System.out.println("Navigating to URL: " + TARGET_URL);
            page.navigate(TARGET_URL);
            System.out.println("Page title: " + page.title());

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
