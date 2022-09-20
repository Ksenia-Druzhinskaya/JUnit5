package lib.ui.mobileWeb;

import io.qameta.allure.Step;
import lib.ui.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWArticlePageObject extends ArticlePageObject
{
    static {
        TITLE = "xpath://*[@id='firstHeading']/span";
        FOOTER_ELEMENT = "css:footer";
    }

    private static final String
            OPTIONS_ADD_TO_MY_LIST_BUTTON = "xpath://a[contains(@class, 'menu__item--page-actions-watch')]",
            OPTIONS_REMOVE_FROM_MY_LIST_BUTTON = "xpath://a[contains(@class, 'menu__item--page-actions-watch watched')]";

    public MWArticlePageObject(RemoteWebDriver driver){
        super(driver);
    }

    @Step("Add article to saved list")
    public void addArticleToMySaved(){
        this.removeArticleFromSavedIfItAdded();
        this.waitForElementAndClick(OPTIONS_ADD_TO_MY_LIST_BUTTON, "Cannot find option to add article to reading list", 5);
    }

    @Step("Remove article from saved list if it is added")
    public void removeArticleFromSavedIfItAdded(){
        if(this.isElementPresent(OPTIONS_REMOVE_FROM_MY_LIST_BUTTON)){
            this.waitForElementAndClick(
                    OPTIONS_REMOVE_FROM_MY_LIST_BUTTON,
                    "Cannot find option to add article to reading list",
                    5);
            this.waitForElementPresent(
                    OPTIONS_ADD_TO_MY_LIST_BUTTON,
                    "Cannot find button to add an article to saved list after removing it from this list before");
        }
    }
}
