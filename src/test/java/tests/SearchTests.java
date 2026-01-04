package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.example.utils.WaitUtils;

import base.BaseTest;
import pages.DuckPages.SearchPage;

public class SearchTests extends BaseTest{
    
    @Test(enabled = false)
    public void searchTest(){
        SearchPage searchPage = new SearchPage(driver);
        String searchText = "prueba";
        searchPage.search(searchText);
        WaitUtils.waitForTitleToContains(driver, searchText);
        Assert.assertTrue(driver.getTitle().contains(searchText));
    }

    @Test(enabled = false)
    public void searchTestAndWait(){
        SearchPage searchPage = new SearchPage(driver);
        String searchText = "prueba";
        searchPage.search(searchText);
        Assert.assertTrue(driver.getTitle().contains(searchText));
    }

    @Test(enabled = false)
    public void clickDummy(){
        SearchPage searchPage = new SearchPage(driver);
        searchPage.clickDummy();
    }
}
