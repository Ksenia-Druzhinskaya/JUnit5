package lib.ui.mobileWeb;

import io.qameta.allure.Step;
import lib.ui.NavigationUI;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWNavigationUI extends NavigationUI
{
    static {
        SAVE_BUTTON = "xpath://*[@text='Save']";
        SAVED_LABEL = "xpath://*[contains(@text, 'Saved ')]";
        SAVED_BUTTON = "xpath://*[@text='Saved']";
        BACK_BUTTON = "xpath://android.widget.ImageButton[@content-desc='Navigate up']";
        OPEN_NAVIGATION = "xpath://*[@id='mw-mf-main-menu-button']";
        MY_LISTS_LINK = "xpath://a[contains(@class,'watchlist')]";
        LOGIN_LINK = "xpath://a[contains(@class,'menu__item--login')]";
    }

    public MWNavigationUI(RemoteWebDriver driver){
        super(driver);
    }

    @Step("Open Navigation panel")
    public void openNavigation(){
        this.waitForElementAndClick(OPEN_NAVIGATION, "Cannot find and click open navigation button.", 5);
    }

    @Step("Click My Lists")
    public void clickMyLists(){
        this.tryClickElementWithFewAttempts(
                    MY_LISTS_LINK,
                    "Cannot find navigation button to My list",
                    5);
    }

    @Step("Click Login")
    public void clickLogin(){
        this.tryClickElementWithFewAttempts(
                LOGIN_LINK,
                "Cannot find navigation button to My list",
                5);
    }
}
