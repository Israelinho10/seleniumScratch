package pages;

import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;

import com.example.utils.WaitUtils;

import io.qameta.allure.Step;

public class HomePage {
    private WebDriver driver;
    private final By inventoryImage = By.className("inventory_item_img");

    public HomePage(WebDriver driver){
        this.driver = driver;
        waitUntilPageIsLoaded();
    }

    public void waitUntilPageIsLoaded(){
        WaitUtils.waitForElement(driver, inventoryImage);
    }

    @Step("Validating if page is loaded")
    public boolean isPageLoaded(){
        try{
            return driver.findElement(inventoryImage).isDisplayed();
        } catch (NoSuchElementException | StaleElementReferenceException e){
            return false;
        }
    }
}
