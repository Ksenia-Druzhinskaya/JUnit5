package lib.ui.android;

import lib.ui.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidArticlePageObject extends ArticlePageObject
{
    static {
        TITLE = "xpath://*[@resource-id='pcs-edit-section-title-description']//preceding-sibling::*";
        FOOTER_ELEMENT = "xpath://*[@text='View article in browser']";
    }

    public AndroidArticlePageObject(RemoteWebDriver driver){
        super(driver);
    }
}
