package lib.ui.mobileWeb;

import io.qameta.allure.Step;
import lib.ui.SearchPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWSearchPageObject extends SearchPageObject
{
    static {
        SEARCH_INIT_ELEMENT = "xpath://*[@id='searchIcon']";
        SEARCH_INPUT = "xpath://*[@class='mw-overlays-container']//input";
        SEARCH_INPUT_ID = "id:searchInput";
        SEARCH_CANCEL_BUTTON = "xpath://div[@class='header-action']/button[contains(@class,'cancel')]";
        SEARCH_RESULT = "xpath://ul[contains(@class,'page-list')]/li[@class='page-summary']";
        SEARCH_RESULT_TITLE = "xpath://*[contains(@class,'results-list-container')]//ul";
        SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://*[contains(@class,'results-list-container')]//li[@title='{SUBSTRING}']";
        SEARCH_RESULT_LIST = "xpath://*[contains(@class,'results-list-container')]/ul/li";
        BACK_BUTTON = "xpath://android.view.ViewGroup/*[contains(@class, 'ImageButton')]";
        REMOVE_FROM_SAVED_BUTTON = "xpath://ul[contains(@class,'page-list')]/li[@title='{TITLE}']/a[contains(@class,'watched')]";
        ADD_TO_SAVED_BUTTON = "xpath://ul[contains(@class,'page-list')]/li[@title='{TITLE}']/a[contains(@class,'watch-this-article mw-ui-button')]";
    }

    public MWSearchPageObject(RemoteWebDriver driver)
    {
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
    }

    @Step("Get Add button by '{articleTitle}' title")
    private static String getAddButtonByTitle(String articleTitle){
        return ADD_TO_SAVED_BUTTON.replace("{TITLE}", articleTitle);
    }

    @Step("Add article with '{articleTitle}' title")
    public void addArticle(String articleTitle){
        this.removeArticleIfItAdded(articleTitle);
        String addLocator = getAddButtonByTitle(articleTitle);
        this.waitForElementAndClick(
                addLocator,
                "Cannot click button to add article to the list.",
                10);
    }

    @Step("Remove article with '{articleTitle}' title if it is added")
    public void removeArticleIfItAdded(String articleTitle){
        String removeLocator = getRemoveButtonByTitle(articleTitle);
        if(this.isElementPresent(removeLocator)){
            this.waitForElementAndClick(
                    removeLocator,
                    "Cannot find option to remove article from watch list",
                    5);
        }
    }
}
