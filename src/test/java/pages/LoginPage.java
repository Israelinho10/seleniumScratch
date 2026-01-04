package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.example.utils.WaitUtils;

import io.qameta.allure.Step;

public class LoginPage {

    private WebDriver driver;
    private final By userTextBox = By.id("user-name");
    private final By passTextBox = By.id("password");
    private final By loginButton = By.id("login-button");

    public LoginPage(WebDriver driver){
        this.driver = driver;
        WaitUtils.waitForElement(driver, loginButton);
    }
    
    @Step("Enter username: {user}")
    public void fillUser(String user){
        driver.findElement(userTextBox).sendKeys(user);
    }

    @Step("Enter password")
    public void fillPass(String pass){
        driver.findElement(passTextBox).sendKeys(pass);
    }

    @Step("Click login button")
    public void clickLoginButton(){
        driver.findElement(loginButton).click();
    }
}
