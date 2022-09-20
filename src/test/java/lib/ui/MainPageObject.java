package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Pattern;

import static org.openqa.selenium.support.ui.ExpectedConditions.invisibilityOfElementLocated;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

abstract public class MainPageObject
{
    protected RemoteWebDriver driver;

    public MainPageObject(RemoteWebDriver driver){
        this.driver = driver;
    }

    public WebElement waitForElementPresent(String locator, String errorMessage, long timeoutInSeconds){
        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(errorMessage + "\n");
        return wait.until(presenceOfElementLocated(by));
    }

    public WebElement waitForElementPresent(String locator, String errorMessage){
        return waitForElementPresent(locator, errorMessage, 5);
    }

    public WebElement waitForElementAndClick(String locator, String errorMessage, long timeoutInSeconds){
        WebElement element = waitForElementPresent(locator, errorMessage, timeoutInSeconds);
        element.click();
        return element;
    }

    public WebElement waitForElementAndSendKeys(String locator, String value, String errorMessage, long timeoutInSeconds){
        WebElement element = waitForElementPresent(locator, errorMessage, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }

    public int getAmountOfElements(String locator){
        By by = this.getLocatorByString(locator);
        List elements = driver.findElements(by);
        return elements.size();
    }

    public boolean isElementPresent(String locator){
        return getAmountOfElements(locator) > 0;
    }

    public void tryClickElementWithFewAttempts(String locator, String errorMessage, int amountOfAttempts){
        int currentAttempts = 0;
        boolean needMoreAttempts = true;

        while(needMoreAttempts){
            try{
                this.waitForElementAndClick(locator, errorMessage, 1);
                needMoreAttempts = false;
            } catch (Exception e) {
                if(currentAttempts > amountOfAttempts){
                    this.waitForElementAndClick(locator, errorMessage, 1);
                }
            }
            ++currentAttempts;
        }
    }

    public boolean waitForElementNotPresent(String locator, String errorMessage, long timeoutInSeconds){
        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(errorMessage + "\n");
        return wait.until(invisibilityOfElementLocated(by));
    }

    public void AssertionsElementPresent(String locator, String errorMessage){
        try {
            By by = this.getLocatorByString(locator);
            driver.findElement(by);
        }
        catch(NoSuchElementException exception){
            Assertions.fail(errorMessage + " " + exception.getMessage());
        }
    }

    public void AssertionsSeveralListItemsExist(String locator, String errorMessageForIncorrectItemNumber){
        By by = this.getLocatorByString(locator);
        List foundElements = driver.findElements(by);
        Assertions.assertTrue( foundElements.size() > 1, errorMessageForIncorrectItemNumber );
    }

    public void AssertionsListItemsContainSearchCriteria(String locator, String searchLine, String errorMessageForIncorrectItemText){
        By by = this.getLocatorByString(locator);
        List<WebElement> foundElements = driver.findElements(by);
        foundElements.forEach(k -> Assertions.assertTrue(
                k.getText().contains(searchLine),
                errorMessageForIncorrectItemText + " '" + k.getText() + "' item does not contain '" + searchLine + "'."));
    }

    public void swipeUp(int timeOfSwipe){

//        if(!(driver instanceof AppiumDriver)) {
//            System.out.println("Method rotateScreenPortrait() does nothing for platform " + Platform.getInstance().getPlatformVar());
//            return;
//        }

        Dimension size = driver.manage().window().getSize();
        int x = size.width / 2;                  // середина экрана по горизонтали
        int start_y = (int)(size.height * 0.8);  // по вертикали 80% от верха
        int end_y = (int)(size.height * 0.2);    // по вертикали 20% от верха

        AppiumDriver driver = (AppiumDriver) this.driver;
        TouchAction action = new TouchAction(driver);
        action
                .press(x, start_y)
                .waitAction(timeOfSwipe)
                .moveTo(x, end_y)
                .release()
                .perform();
    }

    public void swipeElementToLeft(String locator, String errorMessage){

//        if( ! (driver instanceof AppiumDriver) ) {
//            System.out.println("Method rotateScreenPortrait() does nothing for platform " + Platform.getInstance().getPlatformVar());
//            return;
//        }

        WebElement element = waitForElementPresent(
                locator,
                errorMessage);

        int left_x = element.getLocation().getX();
        int right_x = left_x + element.getSize().getWidth();
        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getHeight();
        int middle_y = (upper_y + lower_y) / 2;

        AppiumDriver driver = (AppiumDriver) this.driver;
        TouchAction action = new TouchAction(driver);
        action
                .press(right_x, middle_y)
                .waitAction(300)
                .moveTo(left_x, middle_y)
                .release()
                .perform();
    }

    public void swipeUpQuick(){
        swipeUp(200);
    }

    public void swipeUpToFindElement(String locator, String errorMessage, int maxSwipes){
        int alreadySwiped = 0;
        By by = this.getLocatorByString(locator);
        while(driver.findElements(by).size() == 0){

            if(alreadySwiped > maxSwipes){
                waitForElementPresent(locator, "Cannot find element by swiping up. \n" + errorMessage, 0);
                return;
            }

            swipeUpQuick();
            ++alreadySwiped;
        }
    }

    public void scrollWebPageUp(){
//        if( ! Platform.getInstance().isMW()){
//            System.out.println("Method scrollWebPageUp() does nothing for platform " + Platform.getInstance().getPlatformVar());
//            return;
//        }
        JavascriptExecutor JSExecutor = driver;
        JSExecutor.executeScript("window.scrollBy(0, 250)");
    }

    public void scrollWebPageTillElementNotVisible(String locator, String errorMessage, int maxSwipes){
        int alreadySwiped = 0;

        WebElement element = this.waitForElementPresent(locator, errorMessage);

        while( ! this.isElementLocatedOnTheScreen(locator)){
            scrollWebPageUp();
            ++alreadySwiped;
            if(alreadySwiped > maxSwipes){
                Assertions.assertTrue(element.isDisplayed(), errorMessage);
            }
        }
    }

    public boolean isElementLocatedOnTheScreen(String locator){
        int elementLocationByY = this.waitForElementPresent(locator, "Cannot find element by locator", 1).getLocation().getY();
//        if(Platform.getInstance().isMW()){
//            JavascriptExecutor JSExecutor = driver;
//            Object jsResult = JSExecutor.executeScript("return window.pageYOffset");
//            elementLocationByY -= Integer.parseInt(jsResult.toString());
//        }
        int screenSizeByY = driver.manage().window().getSize().getHeight();
        return elementLocationByY < screenSizeByY;
    }

    private By getLocatorByString(String locatorWithType){
        String[] explodedLocator = locatorWithType.split(Pattern.quote(":"), 2);
        String byType = explodedLocator[0];
        String locator = explodedLocator[1];

        switch (byType){
            case "xpath" : return By.xpath(locator);
            case "id" : return By.id(locator);
            case "css" : return By.cssSelector(locator);
            default: throw new IllegalArgumentException("Cannot get type of locator. Locator: " + locatorWithType);
        }
    }

    public String takeScreenshot(String name){
        TakesScreenshot takesScreenshot = (TakesScreenshot)this.driver;
        File source = takesScreenshot.getScreenshotAs(OutputType.FILE);
        String path = System.getProperty("user.dir") + "/" + name + "_screenshot.png";
        try{
            FileUtils.copyFile(source, new File(path));
            System.out.println("The screenshot was taken: " + path);
        } catch (Exception e) {
            System.out.println("Cannot take screenshot. Error: " + e.getMessage());
        }
        return path;
    }

    //@Attachment
    public static byte[] attachScreenshot(String path){
        byte[] bytes = new byte[0];

        try {
            bytes = Files.readAllBytes(Paths.get(path));
        } catch (IOException e){
            System.out.println("Cannot get bytes from screenshot. Error: " + e.getMessage());
        }
        return bytes;
    }
}
