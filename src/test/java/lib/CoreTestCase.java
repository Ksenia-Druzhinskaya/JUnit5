package lib;

import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import lib.ui.StartPageObject;
import lib.ui.android.AndroidStartPageObject;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.FileOutputStream;
import java.util.Properties;

import static lib.TestData.MOBILE_SITE_URL;

public class CoreTestCase
{
    public RemoteWebDriver driver;

    @Step("Rotate screen portrait")
    protected void rotateScreenPortrait(){
        if(driver instanceof AppiumDriver) {
            AppiumDriver driver = (AppiumDriver) this.driver;
            driver.rotate(ScreenOrientation.PORTRAIT);
        } else {
            System.out.println("Method rotateScreenPortrait() does nothing for platform " + Platform.getInstance().getPlatformVar());
        }
    }

    @Step("Rotate screen landscape")
    protected void rotateScreenLandscape(){
        if(driver instanceof AppiumDriver) {
            AppiumDriver driver = (AppiumDriver) this.driver;
            driver.rotate(ScreenOrientation.LANDSCAPE);
        } else {
            System.out.println("Method rotateScreenLandscape() does nothing for platform " + Platform.getInstance().getPlatformVar());
        }
    }

    @Step("Skip Start page")
    protected void skipStartPage()
    {
        if (Platform.getInstance().isAndroid())
        {
            StartPageObject startPageObject = new AndroidStartPageObject(driver);;
            startPageObject.skipStartPage();
        }
    }

    @Step("Background application")
    protected void backgroundApp(int seconds){
        if(driver instanceof AppiumDriver) {
            AppiumDriver driver = (AppiumDriver) this.driver;
            driver.runAppInBackground(seconds);
        } else {
            System.out.println("Method backgroundApp() does nothing for platform " + Platform.getInstance().getPlatformVar());
        }
    }

    @Step("Open wiki from mobile page")
    protected void openWikiWebPageForMobileWeb(){
        if(Platform.getInstance().isMW()) {
            driver.get(MOBILE_SITE_URL);
        } else {
            System.out.println("Method openWikiWebPageForMobileWeb() does nothing for platform " + Platform.getInstance().getPlatformVar());
        }
    }

    protected void createAllurePropertyFile(){
        String path = System.getProperty("allure.results.directory");
        try{
            Properties properties = new Properties();
            FileOutputStream fileOutputStream = new FileOutputStream(path + "/environment.properties");
            properties.setProperty("Environment", Platform.getInstance().getPlatformVar());
            properties.store(fileOutputStream, "See https://github.com/allure-framework/allure-app/wiki/Environment");
            fileOutputStream.close();
        } catch (Exception e) {
            System.err.println("IO problem when writing allure properties file");
            e.printStackTrace();
        }
    }
}
