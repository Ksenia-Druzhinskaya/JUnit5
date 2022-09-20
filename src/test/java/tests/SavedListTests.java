//package tests;
//
//import io.qameta.allure.*;
//import lib.CoreTestCase;
//import lib.Platform;
//import lib.ui.*;
//import lib.ui.factories.*;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//
//import static lib.TestData.*;
//
//public class SavedListTests extends CoreTestCase
//{
//    private static final String
//                    login = "Ksenia.Druzhinskaya",
//                    password = "Insta11!";
//
//    @Test
//    @DisplayName("Save two articles and delete one")
//    @Description("Find two articles, save them to list, delete one article from list")
//    @Features({@Feature("Search"), @Feature("Article"), @Feature("Watch List")})
//    @Severity(SeverityLevel.NORMAL)
//    public void testSaveTwoArticlesAndDeleteOne()
//    {
//        skipStartPage();
//
//        if(Platform.getInstance().isMW()){
//
//            // Login
//            NavigationUI navigationUI = NavigationUIFactory.get(driver);
//            navigationUI.openNavigation();
//            navigationUI.clickLogin();
//            AuthorizationPageObject auth = new AuthorizationPageObject(driver);
//            auth.logIn(login, password);
//
//            // Return to mobile web
//            driver.get(MOBILE_SITE_URL + driver.getCurrentUrl().split(SITE_URL)[1]);
//
//            // Find "Java" articles
//            SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
//            searchPageObject.initSearchInput();
//            searchPageObject.typeSearchLine(SEARCH_LINE_JAVA);
//
//            // Add first article to watch list
//            searchPageObject.addArticle(ARTICLE_TITLE_JAVA);
//            // Add second article to watch list
//            searchPageObject.addArticle(SECOND_ARTICLE_TITLE_JAVA);
//
//            // Close search list
//            searchPageObject.clickCancelSearch();
//
//            // Open Watch list
//            navigationUI.openNavigation();
//            navigationUI.clickMyLists();
//
//            // Delete article from the list
//            SavedListPageObject savedListPageObject = SavedListPageObjectFactory.get(driver);
//            savedListPageObject.deleteArticle(ARTICLE_TITLE_JAVA);
//
//            // Verify that the article deleted
//            savedListPageObject.verifyArticleDeleted(ARTICLE_TITLE_JAVA);
//
//            // Verify that second article is present and added
//            savedListPageObject.verifyArticleAdded(SECOND_ARTICLE_TITLE_JAVA);
//
//        } else
//        {
//            // Find "Java" articles
//            SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
//            searchPageObject.initSearchInput();
//            searchPageObject.typeSearchLine(SEARCH_LINE_JAVA);
//
//            // Open first article
//            searchPageObject.clickByArticleWithSubstring(ARTICLE_TITLE_JAVA);
//
//            // Save "Java (programming language)" article
//            NavigationUI navigationUI = NavigationUIFactory.get(driver);
//            navigationUI.saveAndWaitSaved();
//            // Go back
//            navigationUI.clickBack();
//
//            // Open "JavaScript" article
//            searchPageObject.clickByArticleWithSubstring(SECOND_ARTICLE_TITLE_JAVA);
//            // Save "JavaScript" article
//            navigationUI.saveAndWaitSaved();
//            // Go back
//            navigationUI.clickBack();
//
//            // Go back from Search page
//            searchPageObject.clickBack();
//
//            // Click "Saved" button to see saved articles
//            navigationUI.clickSaved();
//
//            // Click "Saved 2 articles"
//            SavedPageObject savedPageObject = SavedPageObjectFactory.get(driver);
//            savedPageObject.clickSavedArticles(2);
//
//            // Delete "Java (programming language)" article from Saved
//            SavedListPageObject savedListPageObject = SavedListPageObjectFactory.get(driver);
//            savedListPageObject.deleteArticle(ARTICLE_TITLE_JAVA);
//
//            // Verify that "Java (programming language)" article is deleted
//            savedListPageObject.verifyArticleDeleted(ARTICLE_TITLE_JAVA);
//
//            // Open "JavaScript" article
//            savedListPageObject.openArticle(SECOND_ARTICLE_TITLE_JAVA);
//
//            // Verify article title
//            ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
//            String actualArticleTitle = articlePageObject.getArticleTitle();
//            Assertions.assertEquals(SECOND_ARTICLE_TITLE_JAVA, actualArticleTitle, "Article title is unexpected.");
//        }
//    }
//
//    @Test
//    @DisplayName("Save and delete article")
//    @Description("Find article, save it to list, delete it from list")
//    @Features({@Feature("Search"), @Feature("Article"), @Feature("Watch List")})
//    @Severity(SeverityLevel.NORMAL)
//    public void testSaveAndDeleteArticle()
//    {
//        skipStartPage();
//
//        // Find "Java" articles
//        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
//        searchPageObject.initSearchInput();
//        searchPageObject.typeSearchLine(SEARCH_LINE_JAVA);
//
//        if(Platform.getInstance().isMW()){
//
//            // Open first article
//            searchPageObject.clickByArticleWithSubstring(ARTICLE_TITLE_JAVA);
//
//            // Add article to list
//            ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
//            articlePageObject.addArticleToMySaved();
//
//            // Login
//            AuthorizationPageObject auth = new AuthorizationPageObject(driver);
//            auth.clickAuthButton();
//            auth.logIn(login, password);
//            // Return to mobile web
//            driver.get(MOBILE_SITE_URL + driver.getCurrentUrl().split(SITE_URL)[1]);
//            // Verify that after login the proper article opens
//            articlePageObject.waitForTitleElement();
//            Assertions.assertEquals(
//                    ARTICLE_TITLE_JAVA,
//                    articlePageObject.getArticleTitle(),
//                    "We are not on the same page after login.");
//
//            // Open Watch list
//            NavigationUI navigationUI = NavigationUIFactory.get(driver);
//            navigationUI.openNavigation();
//            navigationUI.clickMyLists();
//
//            // Delete article from the list
//            SavedListPageObject savedListPageObject = SavedListPageObjectFactory.get(driver);
//            savedListPageObject.deleteArticle(ARTICLE_TITLE_JAVA);
//        }
//    }
//}
