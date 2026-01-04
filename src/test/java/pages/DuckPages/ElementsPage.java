package pages.DuckPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.example.utils.WaitUtils;

public class ElementsPage {
    public WebDriver driver;

    public By accordionBy = By.cssSelector(".accordion");

    public ElementsPage(WebDriver driver){
        this.driver = driver;
        WaitUtils.waitForElement(driver, accordionBy);
    }

    public By returnBySubmenu(String submenuText){
        return By.xpath("//span[.='"+ submenuText +"']");
    }

    public void clickSubmenuByText(String submenuText){
        driver.findElement(returnBySubmenu(submenuText)).click();
    }
}
