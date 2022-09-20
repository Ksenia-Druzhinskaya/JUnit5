package lib.ui.android;

import lib.ui.StartPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidStartPageObject extends StartPageObject
{
    static {
     SKIP_BUTTON = "xpath://*[@text='SKIP']";
    }

    public AndroidStartPageObject(RemoteWebDriver driver){
        super(driver);
    }
}
