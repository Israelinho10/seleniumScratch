package pages.DuckPages;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class IndexPage {

    private WebDriver driver;

    public IndexPage(WebDriver driver){
        this.driver = driver;
    }

    public void fluentWait(WebDriver driver){
        Wait<WebDriver> wait = new FluentWait<>(driver)
        .withTimeout(Duration.ofSeconds(10))
        .pollingEvery(Duration.ofSeconds(2))
        .ignoring(NoSuchElementException.class);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("id")));
    }

    public By returnByCard(String cardText){
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.id("id"))).perform();
        return By.xpath("//h5[.='"+ cardText +"']");
    }

    public void shadowRoot(By shadowRootBy){
        WebElement host = driver.findElement(shadowRootBy);
        SearchContext shadowRoot = host.getShadowRoot();
        shadowRoot.findElement(shadowRootBy);

        File image = host.getScreenshotAs(OutputType.FILE);
        try {
			FileUtils.copyFile(image, new File("path"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        ExpectedConditions.alertIsPresent();
        ExpectedConditions.visibilityOf(host);
        ExpectedConditions.invisibilityOf(host);
        ExpectedConditions.elementToBeClickable(host);
        ExpectedConditions.titleContains("ttiel");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        driver = new ChromeDriver(options);

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", host);

        String currentWindowHandle = driver.getWindowHandle();
        Set<String> handles = driver.getWindowHandles();
        for(String windowHandle : handles){
            if(!windowHandle.equals(currentWindowHandle)){
                driver.switchTo().window(windowHandle);
            }
        }

        String mainWindow = driver.getWindowHandle();
        Set<String> windows = driver.getWindowHandles();

        for (String window : windows) {
            if(!window.equals(mainWindow)){
                driver.switchTo().window(window);
            }
        }
        driver.close();
        driver.switchTo().window(mainWindow);

        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.alertIsPresent());

        Wait<WebDriver> wait = new FluentWait<>(driver)
        .withTimeout(Duration.ofSeconds(10))
        .pollingEvery(Duration.ofMillis(500))
        .ignoring(NoSuchElementException.class)
        .ignoring(NullPointerException.class);

        wait.until(ExpectedConditions.alertIsPresent());

        driver.switchTo().defaultContent();
    }
    
    public ElementsPage clickCardByText(String cardText){
        WebElement card = driver.findElement(returnByCard(cardText));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", card);
        card.click();
        return new ElementsPage(driver);
    }
}
