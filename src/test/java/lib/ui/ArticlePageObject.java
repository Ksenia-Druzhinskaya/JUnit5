package lib.ui;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class ArticlePageObject extends MainPageObject
{
    protected static String
                TITLE,
                FOOTER_ELEMENT;

    public ArticlePageObject(RemoteWebDriver driver){
        super(driver);
    }

    @Step("Wait for title element")
    public WebElement waitForTitleElement(){
        return this.waitForElementPresent(TITLE, "Cannot find article title on page.", 15);
    }

    @Step("Get article title")
    public String getArticleTitle(){
        WebElement titleElement = waitForTitleElement();
        return titleElement.getText();
    }

    @Step("Swipe article to footer")
    public void swipeToFooter(){
//        if(Platform.getInstance().isAndroid()) {
//            this.swipeUpToFindElement(
//                    FOOTER_ELEMENT,
//                    "Cannot find the end of the article.",
//                    40);
//        } else {
//            this.scrollWebPageTillElementNotVisible(
//                    FOOTER_ELEMENT,
//                    "Cannot find the end of the article.",
//                    40);
//        }
    }

    @Step("Verify that article title is present")
    public void AssertionsArticleTitlePresent(){
        this.AssertionsElementPresent(TITLE, "Article title is not present.");
    }

    public void addArticleToMySaved(){
    }
}
