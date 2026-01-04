package tests.elements;

import org.testng.annotations.Test;

import base.BaseTest;
import pages.DuckPages.ElementsPage;
import pages.DuckPages.IndexPage;

public class ElementsTests extends BaseTest{

    @Test (enabled = false)
    public void fillTextBox(){
        IndexPage indexPage = new IndexPage(driver);
        ElementsPage elementsPage = indexPage.clickCardByText("Elements");
        elementsPage.clickSubmenuByText("Text Box");
    }
}
