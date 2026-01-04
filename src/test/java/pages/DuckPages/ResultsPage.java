package pages.DuckPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.example.utils.WaitUtils;

public class ResultsPage {
    private WebDriver driver;

    public ResultsPage(WebDriver driver){
        this.driver = driver;
        WaitUtils.waitForElement(driver, returnByTab("Images"));
    }

    public void clickTab(String linkedText){
        driver.findElement(returnByTab(linkedText)).click();
    }

    public By returnByTab(String linkedText){
        return By.linkText(linkedText);
    }
}
