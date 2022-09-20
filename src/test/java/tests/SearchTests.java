package tests;

import io.qameta.allure.*;
import lib.CoreTestCase;
import lib.Platform;
import lib.ui.SearchPageObject;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebElement;

import static lib.TestData.*;
import static lib.ui.MainPageObject.attachScreenshot;

public class SearchTests extends CoreTestCase
{
    @BeforeEach
    public void setUp() throws Exception{
        driver = Platform.getInstance().getDriver(APPIUM_URL_PORT_4723);
        this.createAllurePropertyFile();
        this.rotateScreenPortrait();
        this.openWikiWebPageForMobileWeb();
    }

    @AfterEach
    public void tearDown(){
        driver.quit();
    }

    @Test
    @DisplayName("Search article")
    @Description("Search article, verify expected search result")
    @Features(@Feature("Search"))
    @Severity(SeverityLevel.BLOCKER)
    public void testSearchArticle(){
            skipStartPage();

            SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
            searchPageObject.initSearchInput();
            searchPageObject.typeSearchLine(SEARCH_LINE_JAVA);
            attachScreenshot(searchPageObject.takeScreenshot("article"));
            searchPageObject.waitForSearchResult(ARTICLE_TITLE_JAVA);
    }

    @Test
    @DisplayName("Verify all found articles contain search line")
    @Description("Search articles, verify that all found results contain search line")
    @Features({@Feature("Search"), @Feature("Article")})
    @Severity(SeverityLevel.BLOCKER)
    public void testAllFoundArticlesContainSearchLine(){
        skipStartPage();

        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine(SEARCH_LINE_JAVA);
        attachScreenshot(searchPageObject.takeScreenshot("article"));
        searchPageObject.waitAtLeastOneSearchResult();
        searchPageObject.AssertionsAllFoundArticlesContainSearchCriteria(SEARCH_LINE_JAVA);
    }

    @Test
    @DisplayName("Verify default search text")
    @Description("Open Search page, verify default search text")
    @Features(@Feature("Search"))
    @Severity(SeverityLevel.MINOR)
    public void testVerifyDefaultSearchText(){
        skipStartPage();

        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();

        WebElement searchInput = searchPageObject.waitForSearchInputPresent();
        String actualSearchInputText = searchInput.getText();
        attachScreenshot(searchPageObject.takeScreenshot("article"));
        Assertions.assertEquals(SEARCH_INPUT_TEXT, actualSearchInputText, "Search input does not have default text.");
    }

    @Test
    @DisplayName("Cancel search")
    @Description("Type search line in search input, then cancel search")
    @Features(@Feature("Search"))
    @Severity(SeverityLevel.MINOR)
    public void testCancelSearch(){
        skipStartPage();

        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");

        searchPageObject.waitForCancelButtonToAppear();
        searchPageObject.clickCancelSearch();
        attachScreenshot(searchPageObject.takeScreenshot("article"));
        searchPageObject.waitForCancelButtonToDisappear();
    }

    @Test
    @DisplayName("Search and clear search")
    @Description("Find articles, clear search line and verify that is is cleared")
    @Features(@Feature("Search"))
    @Severity(SeverityLevel.NORMAL)
    public void testSearchAndClearSearch(){
        skipStartPage();

        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine(SEARCH_LINE_JAVA);

        searchPageObject.waitAtLeastOneSearchResult();
        attachScreenshot(searchPageObject.takeScreenshot("article"));
        searchPageObject.AssertionsSeveralArticlesFound();

        WebElement searchInput = searchPageObject.waitForSearchInputPresent();
        searchInput.clear();
        searchPageObject.waitForArticleListEmpty();
    }

    @Test
    @DisplayName("Clear search and verify that Close button disappears")
    @Description("Find articles, clear search line and verify that Close button disappears")
    @Features(@Feature("Search"))
    @Severity(SeverityLevel.MINOR)
    public void testClearSearchAndVerifyCloseButtonDisappears(){
        skipStartPage();

        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine(SEARCH_LINE_JAVA);

        searchPageObject.waitAtLeastOneSearchResult();
        attachScreenshot(searchPageObject.takeScreenshot("article"));
        searchPageObject.AssertionsSeveralArticlesFound();

        WebElement searchInput = searchPageObject.waitForSearchInputPresent();
        searchInput.clear();
        searchPageObject.waitForCancelButtonToDisappear();
    }

}
