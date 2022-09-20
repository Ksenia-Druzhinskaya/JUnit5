package lib.ui.factories;

import lib.Platform;
import lib.ui.SavedListPageObject;
import lib.ui.android.AndroidSavedListPageObject;
import lib.ui.mobileWeb.MWSavedListPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class SavedListPageObjectFactory
{
    public static SavedListPageObject get(RemoteWebDriver driver){
        if(Platform.getInstance().isAndroid()){
            return new AndroidSavedListPageObject(driver);
        } else {
            return new MWSavedListPageObject(driver);
        }
    }
}
