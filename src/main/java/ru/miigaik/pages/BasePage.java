package ru.miigaik.pages;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.miigaik.cfg.ConfigurationProject;
import org.junit.Assert;
import javax.swing.*;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;

public abstract class BasePage
{
    protected WebDriver driver;
    protected Actions actions;
    protected Wait<WebDriver> wait;


    public BasePage(WebDriver driver)
    {
        this.driver = driver;
        actions = new Actions(driver);
    }


    protected WebElement findElementOnPage(By xpath, int timeOutSeconds)
    {
        Allure.addAttachment("Поиск элемента по пути", xpath+"");
        return waitUntilPresenceOfElement(xpath, 5);
    }
    protected List<WebElement> findElementsOnPage(By xpath)
    {
        Allure.addAttachment("Поиск элемента по пути", xpath+"");
        return driver.findElements(xpath);
    }

    protected WebElement findElementOnPage(By xpath)
    {
        return waitUntilPresenceOfElement(xpath, 5);
    }
    protected void clickElementOnPage(By xpath)
    {
        findElementOnPage(xpath).click();
    }


    protected void setDataToInputElement(By xpath, String data)
    {
        findElementOnPage(xpath).sendKeys(data);
    }

    protected void setDataToInputElement(By xpath, String data, int timeWaiting) throws InterruptedException
    {
        TimeUnit.SECONDS.sleep(timeWaiting);
        findElementOnPage(xpath).sendKeys(data);
    }

    public String  getTextOfElement(By xpath)
    {
        return findElementOnPage(xpath).getText();
    }

    protected void isElementClickAble(By xpath)
    {
        assertTrue(waitUntilElementIsClickable(xpath,Duration.ofSeconds(5)).isDisplayed());
    }

    protected WebElement waitUntilPresenceOfElement(By xpath, long timeOutSeconds)
    {
        wait = new WebDriverWait(driver, timeOutSeconds);
        return wait.until(ExpectedConditions.presenceOfElementLocated(xpath));
    }

    protected WebElement waitUntilElementIsClickable(By xpath, Duration timeOutSeconds)
    {
        wait = new WebDriverWait(driver, 5);
        return wait.until(ExpectedConditions.elementToBeClickable(xpath));
    }

    protected WebElement waitUntilElementIsClickable(WebElement webElement, Duration timeOutSeconds)
    {
        wait = new WebDriverWait(driver, 5);
        return wait.until(ExpectedConditions.elementToBeClickable(webElement));
    }

    protected boolean elementIsDisplayed(By xpath, int timeOutSeconds)
    {
        try
        {
            return findElementOnPage(xpath, timeOutSeconds).isDisplayed();
        }
        catch (TimeoutException timeoutException)
        {
            return false;
        }
    }

    protected boolean elementIsDisplayed(By xpath)
    {
        try
        {
            return findElementOnPage(xpath).isDisplayed();
        }
        catch (TimeoutException timeoutException)
        {
            return false;
        }
    }

    protected boolean elementIsNotDisplayed(By xpath)
    {
        try
        {
            WebDriverWait wait = new WebDriverWait(driver, 10);
            return wait.until(ExpectedConditions.invisibilityOfElementLocated(xpath));
        }
        catch (TimeoutException timeoutException)
        {
            return false;
        }
    }


    public abstract BasePage waitAfterEvent(int timeWaiting) throws InterruptedException;


//    public WebElement waitElementHaveAttributesToBe(By xpath, int timeOutSeconds, String attributes)
//    {
//
//    }
}
