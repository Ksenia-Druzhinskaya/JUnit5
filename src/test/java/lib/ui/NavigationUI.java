package lib.ui;

import io.qameta.allure.Step;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class NavigationUI extends MainPageObject
{
    protected static String
                        SAVE_BUTTON,
                        SAVED_LABEL,
                        SAVED_BUTTON,
                        BACK_BUTTON,
                        OPEN_NAVIGATION,
                        MY_LISTS_LINK,
                        LOGIN_LINK;

    public NavigationUI(RemoteWebDriver driver){
        super(driver);
    }

    @Step("Click Save button")
    public void clickSave() {
        this.waitForElementAndClick(SAVE_BUTTON, "Cannot find Save button.", 5);
    }

    @Step("Wait for articles to save")
    public void waitSaved() {
        this.waitForElementPresent(SAVED_LABEL, "Article is not saved.", 5);
    }

    @Step("Click Save button and wait for saved")
    public void saveAndWaitSaved(){
        clickSave();
        waitSaved();
    }

    @Step("Click Back button")
    public void clickBack() {
        this.waitForElementAndClick(BACK_BUTTON, "Cannot find Back button.", 5);
    }

    @Step("Click on Saved button")
    public void clickSaved() {
        this.waitForElementAndClick(SAVED_BUTTON, "Cannot find Saved button.", 5);
    }

    public void openNavigation(){}

    public void clickMyLists(){}

    public void clickLogin(){}
}
