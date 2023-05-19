import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import pages.HomePage;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BaseTest {

    private WebDriver driver;
    protected HomePage homePage;


    @BeforeClass
    public void setUp() {


        System.setProperty("webdriver.chrome.drive", "chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*","ignore-certificate-errors");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        homePage = new HomePage(driver);

    }

    @BeforeMethod
    public void goHome() {
        driver.get("https://www.6pm.com");

    }
    public static String getCurrentDateTime() {
        // Get the current date and time
        LocalDateTime now = LocalDateTime.now();

        // Create a formatter for the desired date and time format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // Format the current date and time using the formatter
        String formattedDateTime = now.format(formatter);

        return formattedDateTime;
    }

    @AfterMethod
    public void recordFailure(ITestResult result) {
        if (ITestResult.FAILURE == result.getStatus()) {
            var camera = (TakesScreenshot) driver;
            File screenshot = camera.getScreenshotAs(OutputType.FILE);
            try {
                Files.move(screenshot.toPath(), new File("screenshots\\"+result.getName()+"_"+getCurrentDateTime() + ".png").toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}

