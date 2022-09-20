package lib.ui.android;

import lib.ui.NavigationUI;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidNavigationUI extends NavigationUI
{
    static {
        SAVE_BUTTON = "xpath://*[@text='Save']";
        SAVED_LABEL = "xpath://*[contains(@text, 'Saved ')]";
        SAVED_BUTTON = "xpath://*[@text='Saved']";
        BACK_BUTTON = "xpath://android.widget.ImageButton[@content-desc='Navigate up']";
    }

    public AndroidNavigationUI(RemoteWebDriver driver){
        super(driver);
    }
}
