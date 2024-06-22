package sessions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeTest {

    public static void main(String[] args) {

        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("debuggerAddress", "localhost:9222");

        WebDriver driver = new ChromeDriver(options);
        driver.get("https://www.facebook.com");
        driver.findElement(By.name("email")).sendKeys("pikahu@gmail.com");

    }
}
