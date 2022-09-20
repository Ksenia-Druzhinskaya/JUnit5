package lib.ui;

import io.qameta.allure.Step;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class StartPageObject extends MainPageObject
{
    protected static String SKIP_BUTTON;

    public StartPageObject(RemoteWebDriver driver){
        super(driver);
    }

    @Step("Skip Start page")
    public void skipStartPage() {
        this.waitForElementAndClick(SKIP_BUTTON, "Cannot find Skip button on Start page.", 5);
    }
}
