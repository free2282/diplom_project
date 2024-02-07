package ru.miigaik.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage extends BasePage
{
    private static final By emailInputField = By.xpath(".//input[@name='email']");
    private static final By getTokenButton = By.xpath(".//button[text()='Получить код']");
    private static final By tokenInputField = By.xpath(".//input[@placeholder='000000']");
    private static final By logInButton = By.xpath(".//button[text()='Войти']");
    private static final By successfulNotificationInputEmailText = By.xpath(".//div[text()='Одноразовый код был отправлен на указанную почту']");
    private static final By exitButtonOnInformationAboutYourselfPage = By.xpath(".//button[text()='Выйти']");
    private static final By backButton = By.xpath(".//button[text()='Назад']");
    private static final By invalidEmailNotification= By.xpath(".//span[text()='Неверный формат email']");
    private static final By nullEmailNotification = By.xpath(".//span[text()='Введите email']");
    private static final By errorLengthTokenNotification = By.xpath(".//span[text()='Неверная длина кода']");
    private static final By invalidTokenNotification = By.xpath(".//div[text()='Получен неверный токен.']");

    public MainPage(WebDriver driver)
    {
        super(driver);
    }

    public MainPage setEmailToField(String email)
    {
        setDataToInputElement(emailInputField, email);
        return this;
    }

    public MainPage clickGetTokenButton()
    {
        clickElementOnPage(getTokenButton);
        return this;
    }

    public MainPage setTokenToField(String token)
    {
        setDataToInputElement(tokenInputField, token);
        return this;
    }

    public MainPage clickLogInButton()
    {
        clickElementOnPage(logInButton);
        return this;
    }

    public boolean isExitButtonDisplayed()
    {
        return elementIsDisplayed(exitButtonOnInformationAboutYourselfPage);
    }

    public boolean isLogInIsNotDisplayAfterClickBackButton()
    {
        return elementIsDisplayed(logInButton);
    }

    public boolean isGetTokenDisplayed()
    {
        return elementIsDisplayed(getTokenButton);
    }

    public MainPage clickBackButton()
    {
        clickElementOnPage(backButton);
        return this;
    }

    public boolean isSuccessfulInputEmailNotificationVisible()
    {
        return elementIsDisplayed(successfulNotificationInputEmailText);
    }

    public boolean isEmailIsNotValidNotificationDisplayed()
    {
        return elementIsDisplayed(invalidEmailNotification);
    }

    public boolean isEmailNullNotificationDisplayed()
    {
        waitUntilPresenceOfElement(nullEmailNotification, 5);
        return elementIsDisplayed(nullEmailNotification);
    }

    public boolean isTokenErrorLengthDisplayed()
    {
        return elementIsDisplayed(errorLengthTokenNotification);
    }

    public boolean isInvalidTokenNotificationDisplayed()
    {
        return elementIsDisplayed(invalidTokenNotification);
    }
}
