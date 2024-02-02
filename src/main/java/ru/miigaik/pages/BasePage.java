package ru.miigaik.pages;
import com.codeborne.selenide.commands.WaitUntil;
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

import static org.junit.Assert.assertTrue;

public class BasePage
{
    protected WebDriver driver;
    protected Actions actions;
    protected Wait<WebDriver> wait;


    public BasePage(WebDriver driver)
    {
        this.driver = driver;
        actions = new Actions(driver);
    }


    public WebElement findElementOnPage(By xpath, int timeOutSeconds)
    {
        return waitUntilPresenceOfElement(xpath, timeOutSeconds);
    }

    public WebElement findElementOnPage(By xpath)
    {
        return waitUntilPresenceOfElement(xpath, 105);
    }

    public void isElementClickAble(By xpath)
    {
        assertTrue(waitUntilElementIsClickable(xpath, 60).isDisplayed());
    }

    public WebElement waitUntilPresenceOfElement(By xpath, int timeOutSeconds)
    {
        wait = new WebDriverWait(driver, timeOutSeconds);
        return wait.until(ExpectedConditions.presenceOfElementLocated(xpath));
    }

    public WebElement waitUntilElementIsClickable(By xpath, int timeOutSeconds)
    {
        wait = new WebDriverWait(driver, timeOutSeconds);
        return wait.until(ExpectedConditions.elementToBeClickable(xpath));
    }

    public WebElement waitUntilElementIsClickable(WebElement webElement, int timeOutSeconds)
    {
        wait = new WebDriverWait(driver, timeOutSeconds);
        return wait.until(ExpectedConditions.elementToBeClickable(webElement));
    }

    public boolean elementIsDisplayed(By xpath, int timeOutSeconds)
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

    public boolean elementIsDisplayed(By xpath)
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
//    public WebElement waitElementHaveAttributesToBe(By xpath, int timeOutSeconds, String attributes)
//    {
//
//    }
}
