package lib.ui;

import io.qameta.allure.Step;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class SavedListPageObject extends MainPageObject
{
    protected static String
            ARTICLE_SUBSTRING_TPL,
            REMOVE_FROM_SAVED_BUTTON;

    public SavedListPageObject(RemoteWebDriver driver){
        super(driver);
    }

    /* TEMPLATE methods */
    protected static String getResultArticleElement(String substring) {
        return ARTICLE_SUBSTRING_TPL.replace("{ARTICLE_TITLE}", substring);
    }
    /* TEMPLATE methods */

    @Step("Verify that article with '{articleTitle}' title is deleted")
    public void verifyArticleDeleted(String articleTitle){
        String articleResultLocator = getResultArticleElement(articleTitle);
        this.waitForElementNotPresent(articleResultLocator,  "The article '" + articleTitle + "' was not deleted.", 5);
    }

    @Step("Open article by '{articleTitle}' title ")
    public void openArticle(String articleTitle){
        String articleResultLocator = getResultArticleElement(articleTitle);
        this.waitForElementAndClick(articleResultLocator,  "Cannot find '" + articleTitle + "'.", 5);
    }

    public void deleteArticle(String articleTitle){}

    public void verifyArticleAdded(String articleTitle){}
}
