package lib.ui.mobileWeb;

import lib.ui.SavedPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWSavedPageObject extends SavedPageObject
{
    static {
        SAVED_LABEL_SUBSTRING_TPL = "xpath://android.widget.TextView[contains(@text, '{NUMBER} articles')]";
    }

    public MWSavedPageObject(RemoteWebDriver driver){
        super(driver);
    }
}
