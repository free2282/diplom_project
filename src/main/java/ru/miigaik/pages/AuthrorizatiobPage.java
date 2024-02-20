package ru.miigaik.pages;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class AuthrorizatiobPage extends BasePage
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

    public AuthrorizatiobPage(WebDriver driver)
    {
        super(driver);
    }

    @Step("Установка значения в поле 'Электронная почта'")
    public AuthrorizatiobPage setEmailToField(String email)
    {
        Allure.addAttachment("В поле для почты была положена почта с адрессом", email+"");
        setDataToInputElement(emailInputField, email);
        return this;
    }

    @Step("Нажатие на кнопку 'Получить код'")
    public AuthrorizatiobPage clickGetTokenButton()
    {
        clickElementOnPage(getTokenButton);
        return this;
    }

    @Step("Установка значения в поле 'Одноразовый код'")
    public AuthrorizatiobPage setTokenToField(String token)
    {
        setDataToInputElement(tokenInputField, token);
        return this;
    }

    @Step("Клик на кнопку 'Войти'")
    public AuthrorizatiobPage clickLogInButton()
    {
        clickElementOnPage(logInButton);
        return this;
    }

    @Step("Проверка видна ли кнопка 'Выйти'")
    public boolean isExitButtonDisplayed()
    {
        waitUntilPresenceOfElement(exitButtonOnInformationAboutYourselfPage, 10);
        return elementIsDisplayed(exitButtonOnInformationAboutYourselfPage);
    }

    @Step("Проверка исчезновения кнопки 'Войти'")
    public boolean isLogInIsNotDisplay()
    {
        return elementIsDisplayed(logInButton);
    }

    @Step("Проверка видна ли кнопка 'Получить код'")
    public boolean isGetTokenDisplayed()
    {
        return elementIsDisplayed(getTokenButton);
    }

    @Step("Нажатие на кнопку 'Назад'")
    public AuthrorizatiobPage clickBackButton()
    {
        clickElementOnPage(backButton);
        return this;
    }

    @Step("Проверка видно ли уведомление об успешном вводе email")
    public boolean isSuccessfulInputEmailNotificationVisible()
    {
        return elementIsDisplayed(successfulNotificationInputEmailText);
    }

    @Step("Проверка видна ли кнопка 'Войти'")
    public boolean isLogInButtonDisplayed()
    {
        return elementIsDisplayed(logInButton);
    }

    @Step("Проверка видно ли уведомление об ошибочном вводе email")
    public boolean isEmailIsNotValidNotificationDisplayed()
    {
        return elementIsDisplayed(invalidEmailNotification);
    }

    @Step("Проверка виден ли текст с просьбой ввести email после нажатия на кнопку 'Получить код' с пустой строкой email")
    public boolean isEmailNullNotificationDisplayed()
    {
        waitUntilPresenceOfElement(nullEmailNotification, 5);
        return elementIsDisplayed(nullEmailNotification);
    }

    @Step("Проверка видно ли уведомление о том, что код для входа не той длины")
    public boolean isTokenErrorLengthDisplayed()
    {
        return elementIsDisplayed(errorLengthTokenNotification);
    }

    @Step("Проверка уведомления о том, что код для входа ошибочен")
    public boolean isInvalidTokenNotificationDisplayed()
    {
        return elementIsDisplayed(invalidTokenNotification);
    }


    @Override
    public AuthrorizatiobPage waitAfterEvent(int timeWaiting) throws InterruptedException
    {
        TimeUnit.SECONDS.sleep(timeWaiting);
        return this;
    }
}
