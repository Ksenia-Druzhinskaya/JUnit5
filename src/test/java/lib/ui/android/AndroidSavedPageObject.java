package lib.ui.android;

import lib.ui.SavedPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidSavedPageObject extends SavedPageObject
{
    static {
        SAVED_LABEL_SUBSTRING_TPL = "xpath://android.widget.TextView[contains(@text, '{NUMBER} articles')]";
    }

    public AndroidSavedPageObject(RemoteWebDriver driver){
        super(driver);
    }
}
