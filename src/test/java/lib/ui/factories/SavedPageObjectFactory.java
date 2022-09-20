package lib.ui.factories;

import lib.Platform;
import lib.ui.SavedPageObject;
import lib.ui.android.AndroidSavedPageObject;
import lib.ui.mobileWeb.MWSavedPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class SavedPageObjectFactory
{
    public static SavedPageObject get(RemoteWebDriver driver){
        if(Platform.getInstance().isAndroid()){
            return new AndroidSavedPageObject(driver);
        } else {
            return new MWSavedPageObject(driver);
        }
    }
}
