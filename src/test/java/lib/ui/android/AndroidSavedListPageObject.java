package lib.ui.android;

import io.qameta.allure.Step;
import lib.ui.SavedListPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidSavedListPageObject extends SavedListPageObject
{
    static {
        ARTICLE_SUBSTRING_TPL = "xpath://*[@text='{ARTICLE_TITLE}']";
    }

    public AndroidSavedListPageObject(RemoteWebDriver driver){
        super(driver);
    }

    @Step("Delete article")
    public void deleteArticle(String articleTitle){
        String articleResultLocator = getResultArticleElement(articleTitle);
        this.swipeElementToLeft(articleResultLocator, "Cannot find '" + articleTitle + "'.");
    }
}
