package pages.DuckPages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class SearchPage {
    private WebDriver driver;

    private By searchBox = By.name("q");
    private By dummyBy = By.id("dummy");

    public SearchPage(WebDriver driver){
        this.driver = driver;
    }

    public void clickDummy(){
        try{
            driver.findElement(dummyBy).click();
        } catch (NoSuchElementException e) {
            System.out.println("EXCEPTION");
        }
    }

    public ResultsPage search(String text){
        driver.findElement(searchBox).sendKeys(text + "\n");
        return new ResultsPage(driver);
    }
}
