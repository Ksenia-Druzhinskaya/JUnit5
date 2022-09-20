package lib.ui;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class SearchPageObject extends MainPageObject
{
    protected static String
            SEARCH_INIT_ELEMENT,
            SEARCH_INPUT,
            SEARCH_INPUT_ID,
            SEARCH_CANCEL_BUTTON,
            SEARCH_RESULT,
            SEARCH_RESULT_TITLE,
            SEARCH_RESULT_BY_SUBSTRING_TPL,
            SEARCH_RESULT_LIST,
            BACK_BUTTON,
            REMOVE_FROM_SAVED_BUTTON,
            ADD_TO_SAVED_BUTTON;

    public SearchPageObject(RemoteWebDriver driver)
    {
        super(driver);
    }

    /* TEMPLATE methods */
    private static String getResultSearchElement(String substring) {
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }
    /* TEMPLATE methods */

    @Step("Init search input")
    public void initSearchInput() {
        this.waitForElementPresent(SEARCH_INIT_ELEMENT,
                "Cannot find search input after clicking search init element.");
        this.waitForElementAndClick(SEARCH_INIT_ELEMENT, "Cannot find and click search init element.", 5);
    }

    @Step("Type '{searchLine}' search line")
    public void typeSearchLine(String searchLine) {
        this.waitForElementAndSendKeys(SEARCH_INPUT, searchLine, "Cannot find and type into search input.", 5);
    }

    @Step("Wait for search result with '{substring}' substring")
    public void waitForSearchResult(String substring) {
        String searchResultLocator = getResultSearchElement(substring);
        this.waitForElementPresent(searchResultLocator, "Cannot find search result with '" + substring + "'.", 15);
    }

    @Step("Wait at least one search result")
    public void waitAtLeastOneSearchResult() {
        this.waitForElementPresent(SEARCH_RESULT, "Search result is empty.", 15);
    }

    @Step("Click by article with '{substring}' substring")
    public void clickByArticleWithSubstring(String substring) {
        String searchResultLocator = getResultSearchElement(substring);
        this.waitForElementAndClick(searchResultLocator, "Cannot find and click search result with '" + substring + "'.", 15);
    }

    @Step("Wait for Cancel button to appear")
    public void waitForCancelButtonToAppear() {
        this.waitForElementPresent(SEARCH_CANCEL_BUTTON, "Cannot find Search Cancel button.", 5);
    }

    @Step("Wait for Cancel button to disappear")
    public void waitForCancelButtonToDisappear() {
        this.waitForElementNotPresent(SEARCH_CANCEL_BUTTON, "Search Cancel button is still present.", 5);
    }

    @Step("Click Cancel search")
    public void clickCancelSearch()
    {
        this.waitForElementAndClick(SEARCH_CANCEL_BUTTON, "Cannot find and click Search Cancel button.", 5);
    }

    @Step("Search article by '{searchLine}' search line and open article")
    public void AssertionsAllFoundArticlesContainSearchCriteria(String searchLine) {
        this.AssertionsListItemsContainSearchCriteria(
                SEARCH_RESULT_TITLE,
                searchLine,
                "Found article does not contain search line '" + searchLine + "'.");
    }

    @Step("Wait for search input is present")
    public WebElement waitForSearchInputPresent(){
        return waitForElementPresent(SEARCH_INPUT, "Cannot find search input.", 5);
    }

    @Step("Verify that several articles are found")
    public void AssertionsSeveralArticlesFound(){
        this.AssertionsSeveralListItemsExist(SEARCH_RESULT_LIST, "Only one article is found.");
    }

    @Step("Wait for article is empty")
    public boolean waitForArticleListEmpty(){
        return this.waitForElementNotPresent(SEARCH_RESULT, "Search result is not empty.", 5);
    }

    @Step("Click Back button")
    public void clickBack() {
        this.waitForElementAndClick(BACK_BUTTON, "Cannot find Back button.", 5);
    }

    @Step("Search articles by '{searchLine}' search line and open article with '{substring}' substring")
    public void searchAndOpenArticle(String searchLine, String articleTitle){
        initSearchInput();
        typeSearchLine(searchLine);
        clickByArticleWithSubstring(articleTitle);
    }

    public void addArticle(String articleTitle){}
}
