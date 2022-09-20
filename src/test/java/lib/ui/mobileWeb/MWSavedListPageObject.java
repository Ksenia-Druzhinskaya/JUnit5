package lib.ui.mobileWeb;

import io.qameta.allure.Step;
import lib.ui.SavedListPageObject;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWSavedListPageObject extends SavedListPageObject
{
    static {
        ARTICLE_SUBSTRING_TPL = "xpath://ul[contains(@class,'watchlist')]//h3[contains(text(),'{TITLE}')]";
        REMOVE_FROM_SAVED_BUTTON = "xpath://ul[contains(@class,'watchlist')]//h3[contains(text(),'{TITLE}')]/../../a[contains(@class,'watched')]";
    }

    public MWSavedListPageObject(RemoteWebDriver driver){
        super(driver);
    }

    @Step("Get Remove button by '{articleTitle}' article title")
    private static String getRemoveButtonByTitle(String articleTitle){
        return REMOVE_FROM_SAVED_BUTTON.replace("{TITLE}", articleTitle);
    }

    @Step("Delete article with '{articleTitle}' title")
    public void deleteArticle(String articleTitle){
        String removeLocator = getRemoveButtonByTitle(articleTitle);
        this.waitForElementAndClick(
                    removeLocator,
                    "Cannot click button to remove article from the list.",
                    10);
        driver.navigate().refresh();
    }

    @Step("Verify that article with '{articleTitle}' title is added")
    public void verifyArticleAdded(String articleTitle){
        String removeLocator = getRemoveButtonByTitle(articleTitle);
        Assertions.assertTrue(
                this.isElementPresent(removeLocator),
                "Article '" + articleTitle + "' does not exist in watch list.");
    }

}
