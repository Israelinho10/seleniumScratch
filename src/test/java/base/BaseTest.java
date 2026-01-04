package base;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

import com.example.config.ConfigReader;

import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import io.qameta.allure.testng.AllureTestNg;

@Listeners({AllureTestNg.class})
public class BaseTest {

    protected WebDriver driver;

    @BeforeMethod
    public void setUp(){
        String browser = ConfigReader.get("browser");
        System.out.println("BROWSER: " + browser);
        if(browser.equalsIgnoreCase("chrome")){
            ChromeOptions options = new ChromeOptions();
            boolean isHeadless = Boolean.parseBoolean(ConfigReader.get("headless"));
            if(isHeadless){
                options.addArguments("--headless=new");
            }
            driver = new ChromeDriver(options);
        } else if (browser.equalsIgnoreCase("brave")) {
            ChromeOptions options = new ChromeOptions();

            // Path to Brave browser
            options.setBinary(ConfigReader.get("bravePath"));

            // Optional: allow notifications, disable automation banner, etc.
            options.addArguments("--remote-allow-origins=*");

            // Headless mode
            boolean isHeadless = Boolean.parseBoolean(
                System.getProperty("headless", ConfigReader.get("headless")));
            if(isHeadless)
                options.addArguments("--headless=new");

            driver = new ChromeDriver(options);
        } else {
            driver = new EdgeDriver();
        }

        driver.manage().window().maximize();
        driver.get(ConfigReader.get("baseUrl"));
    }

    @AfterMethod
    public void tearDown(ITestResult result){
        if(ITestResult.FAILURE == result.getStatus()){
            System.out.println("ENTERING SCREENSHOT");
            screenShot();
            /*File src = saveScreenshot();
            try {
                Allure.addAttachment("screendshot", FileUtils.openInputStream(src));
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }*/
        }
        if(driver!=null){
            driver.quit();
        }
    }

    public File saveScreenshot(){
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String screenshotPath ="target/allure-results/screenshot-" + System.currentTimeMillis() + ".png";
        try{
            FileUtils.copyFile(src, new File(screenshotPath));
        }catch(IOException e){
            System.out.println("ERROR: " + e.getMessage());
        }

        return src;
    }

    @Attachment(value = "Failure Screenshot", type = "image/png")
    public byte[] screenShot(){
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
}
