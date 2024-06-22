package sessions;

import com.microsoft.playwright.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class CaptureNetworkCalls {
    public static void main(String[] args) {
        // Map to store request and response logs
        Map<String, Map<String, Object>> networkLogs = new ConcurrentHashMap<>();
        AtomicInteger counter = new AtomicInteger(0); // Atomic counter to create unique request IDs
        Map<Request, String> requestIds = new ConcurrentHashMap<>(); // Map to store request to ID mappings

        try (Playwright playwright = Playwright.create()) {
            BrowserType browserType = playwright.chromium();
            Browser browser = browserType.launch(new BrowserType.LaunchOptions().setHeadless(false));
            BrowserContext context = browser.newContext();

            // Log all network requests
            context.onRequest(request -> {
                String requestId = "req-" + counter.getAndIncrement();
                Map<String, Object> requestLog = new HashMap<>();
                requestLog.put("requestUrl", request.url());
                requestLog.put("requestMethod", request.method());
                requestLog.put("requestHeaders", request.headers());
                requestIds.put(request, requestId); // Store the requestId for this request
                networkLogs.put(requestId, requestLog);
            });

            // Log all network responses
            context.onResponse(response -> {
                String requestId = requestIds.get(response.request());
                Map<String, Object> log = networkLogs.get(requestId);
                if (log != null) {
                    log.put("responseUrl", response.url());
                    log.put("responseStatus", response.status());
                    log.put("responseHeaders", response.headers());
                    try {
                        String body = response.text();
                        log.put("responseBody", body);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    Map<String, Object> responseLog = new HashMap<>();
                    responseLog.put("responseUrl", response.url());
                    responseLog.put("responseStatus", response.status());
                    responseLog.put("responseHeaders", response.headers());
                    try {
                        String body = response.text();
                        responseLog.put("responseBody", body);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    networkLogs.put(requestId, responseLog);
                }
            });

            Page page = context.newPage();
            page.navigate("https://www.rahulshettyacademy.com/");

            // Perform any other actions on the page as needed

            // Wait for a few seconds to ensure all network calls are captured
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

          //  browser.close();
        }

        // Output the captured network logs
        System.out.println("Network Logs:");
        for (Map.Entry<String, Map<String, Object>> entry : networkLogs.entrySet()) {
            System.out.println("Request ID: " + entry.getKey());
            System.out.println("Details: " + entry.getValue());
        }
    }
}
