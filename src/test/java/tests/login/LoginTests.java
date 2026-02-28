package tests.login;

import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import base.BaseTest;
import io.qameta.allure.Story;
import io.qameta.allure.testng.AllureTestNg;
import pages.HomePage;
import pages.LoginPage;

@Listeners({ AllureTestNg.class })
public class LoginTests extends BaseTest {

    @Test(enabled = true, dataProvider = "data2")
    public void def(String user) throws IOException {
        System.out.println("DEF");
        System.out.println(user);
    }

    @Story("Login with valid credentials")
    @Test(enabled = false)
    public void loginWithValidCredentials() {
        String user = "standard_user";
        String pass = "secret_sauc";

        LoginPage loginPage = new LoginPage(driver);

        loginPage.fillUser(user);
        loginPage.fillPass(pass);
        loginPage.clickLoginButton();

        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isPageLoaded());
    }
}
