package tests;

import io.qameta.allure.*;
import lib.CoreTestCase;
import lib.Platform;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.jupiter.api.*;

import static lib.TestData.*;
public class ArticleTests extends CoreTestCase
{
    @BeforeEach
    public void setUp() throws Exception{
        driver = Platform.getInstance().getDriver(APPIUM_URL_PORT_4823);
        this.createAllurePropertyFile();
        this.rotateScreenPortrait();
        this.openWikiWebPageForMobileWeb();
    }

    @AfterEach
    public void tearDown(){
        driver.quit();
    }

    @Test
    @DisplayName("Verify that article title is present")
    @Description("Find article and verify that title is present")
    @Features({@Feature("Search"), @Feature("Article")})
    @Severity(SeverityLevel.NORMAL)
    public void testVerifyArticleTitlePresent(){
        skipStartPage();

        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.searchAndOpenArticle(SEARCH_LINE_JAVA, ARTICLE_TITLE_JAVA);

        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        articlePageObject.AssertionsArticleTitlePresent();
    }

    @Test
    @DisplayName("Compare article title with expected one")
    @Description("Find article, open it and verify title")
    @Features({@Feature("Search"), @Feature("Article")})
    @Severity(SeverityLevel.NORMAL)
    public void testCompareArticleTitle()
    {
        skipStartPage();

        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine(SEARCH_LINE_JAVA);
        searchPageObject.clickByArticleWithSubstring(ARTICLE_TITLE_JAVA);

        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        String actualArticleTitle = articlePageObject.getArticleTitle();

        Assertions.assertEquals(ARTICLE_TITLE_JAVA, actualArticleTitle, "Article title is unexpected.");
    }

    @Test
    @DisplayName("Swipe article")
    @Description("Find article, open it and swipe three times")
    @Features({@Feature("Search"), @Feature("Article")})
    @Severity(SeverityLevel.MINOR)
    public void testSwipeArticle()
    {
        skipStartPage();

        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.searchAndOpenArticle(SEARCH_LINE_JAVA, ARTICLE_TITLE_JAVA);

        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        articlePageObject.waitForTitleElement();

        articlePageObject.swipeUp(1000);
        articlePageObject.swipeUp(1000);
        articlePageObject.swipeUp(1000);
    }

    @Test
    @DisplayName("Swipe article to the footer")
    @Description("Find article, open it and swipe to the footer")
    @Features({@Feature("Search"), @Feature("Article")})
    @Severity(SeverityLevel.NORMAL)
    public void testSwipeArticleUpToText()
    {
        skipStartPage();

        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.searchAndOpenArticle(SEARCH_LINE_APPIUM, ARTICLE_TITLE_APPIUM);

        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        articlePageObject.waitForTitleElement();
        articlePageObject.swipeToFooter();
    }
}
