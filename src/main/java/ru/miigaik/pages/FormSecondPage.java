package ru.miigaik.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FormSecondPage extends BasePage
{
    private final By referenceToMiigaikYandexMap = By.xpath(".//a[@href='https://yandex.ru/maps/org/miigaik/1082312243/?ll=37.662039%2C55.763493&z=16']");
    private final By sendButton = By.xpath(".//button[text()='Отправить']");
    private final By downloadPersonalList = By.xpath(".//a[text()='Скачать личный листок']");
    private final By uploadPersonalListLocator = By.xpath("//*[@id='root']/form/section/div[2]/div[2]/input");
    private final By downloadIncomingStatement = By.xpath(".//a[text()='Скачать заявление поступающего']");
    private final By uploadIncomingStatementLocator = By.xpath("//*[@id='root']/form/section/div[3]/div[2]/input");
    private final By downloadConsentToTheProcessingOfPersonalData = By.xpath(".//a[text()='Скачать Согласие на обработку ПД']");
    private final By uploadConsentToTheProcessingOfPersonalDataLocator = By.xpath("//*[@id='root']/form/section/div[4]/div[2]/input");
    private final By uploadPasportLocator = By.xpath("//*[@id='root']/form/section/div[5]/input");
    private final By uploadDiplomLocator = By.xpath("//*[@id='root']/form/section/div[6]/input");
    private final By uploadDocumentAboutChangingFIOLocator = By.xpath("//*[@id='root']/form/section/div[7]/input");
    private final By uploadSnilsLocator = By.xpath("//*[@id='root']/form/section/div[8]/input");
    private final By miigaikOnMap = By.xpath(".//h1[text()='МИИГАиК']");
    private final By succesfullFormSending = By.xpath(".//div[text()='Форма успешно отправлена на проверку']");
    private final By notificationUploadFile = By.xpath(".//span[text()='Загрузите файл']");
    private final By formOnModeration = By.xpath(".//p[text()='Форма находится на модерации']");
    private final By exitButton = By.xpath(".//button[text()='Выйти']");
    private String currentTimeOfCreatingForm;
    private String currentDate;

    public FormSecondPage(WebDriver driver)
    {
        super(driver);
    }

    @Step("Клик на ссылку на расположение МИИГАиК на яндекс картах")
    public FormSecondPage clickMapRef()
    {

        driver.findElement(referenceToMiigaikYandexMap).click();
        wait = new WebDriverWait(driver, 10);
        switchToWindow();

        wait.until(ExpectedConditions.urlToBe("https://yandex.ru/maps/org/miigaik/1082312243/?ll=37.662039%2C55.763493&z=16"));
        return this;
    }

    @Step("Нажатие на кнопку выйти на второй странице анкеты")
    public FormSecondPage clickExitButton()
    {
        findElementOnPage(exitButton);
        return this;
    }

    @Step("Проверка виден ли МИИГАиК на картах")
    public boolean isMiigaikOnMapVisible()
    {
        return findElementOnPage(miigaikOnMap).isDisplayed();
    }

    @Step("Загрузка персонального листа поступающего")
    public FormSecondPage uploadPersonalList(String fileName) throws InterruptedException
    {
        findElementOnPage(uploadPersonalListLocator).sendKeys
                (
                        new File("./attach/filesForTest/"+fileName).getAbsolutePath()
                );
        return this;
    }

    @Step("Загрузка заявление поступающего")
    public FormSecondPage uploadIncomingStatement(String fileName) throws InterruptedException
    {
        findElementOnPage(uploadIncomingStatementLocator).sendKeys
                (
                        new File("./attach/filesForTest/"+fileName).getAbsolutePath()
                );
        return this;
    }

    @Step("Загрузка согласие об обработке ПД")
    public FormSecondPage uploadConsentToTheProcessingOfPersonalData(String fileName) throws InterruptedException
    {
        findElementOnPage(uploadConsentToTheProcessingOfPersonalDataLocator).sendKeys
                (
                        new File("./attach/filesForTest/"+fileName).getAbsolutePath()
                );
        return this;
    }

    @Step("Загрузка паспорта")
    public FormSecondPage uploadPasport(String fileName) throws InterruptedException
    {
        findElementOnPage(uploadPasportLocator).sendKeys
                (
                        new File("./attach/filesForTest/"+fileName).getAbsolutePath()
                );
        return this;
    }

    @Step("Загрузка диплома")
    public FormSecondPage uploadDiplom(String fileName) throws InterruptedException
    {
        findElementOnPage(uploadDiplomLocator).sendKeys
                (
                        new File("./attach/filesForTest/"+fileName).getAbsolutePath()
                );
        return this;
    }

    @Step("Загрузка документа о смене ФИО")
    public FormSecondPage uploadDocumentAboutChangingFIO(String fileName) throws InterruptedException
    {
        findElementOnPage(uploadDocumentAboutChangingFIOLocator).sendKeys
                (
                        new File("./attach/filesForTest/"+fileName).getAbsolutePath()
                );
        return this;
    }

    @Step("Загрузка СНИЛс")
    public FormSecondPage uploadSnils(String fileName) throws InterruptedException
    {
        findElementOnPage(uploadSnilsLocator).sendKeys
                (
                        new File("./attach/filesForTest/"+fileName).getAbsolutePath()
                );
        waitAfterEvent(3);
        return this;
    }

    @Step("Проверка видно ли уведомлние об успешной отправке формы")
    public boolean isFormSendingSuccessful()
    {
        waitUntilPresenceOfElement(succesfullFormSending, 1);
        return findElementOnPage(succesfullFormSending).isDisplayed();
    }

    @Step("Нажатие на кнопку отправить")
    public FormSecondPage clickSendButton() throws InterruptedException
    {
        Date currentDate = new Date();
        SimpleDateFormat onlyTime = new SimpleDateFormat("HH:mm");
        String formattedTime = onlyTime.format(currentDate);

        SimpleDateFormat onlyDay = new SimpleDateFormat("dd");
        String formattedDay = onlyDay.format(currentDate);

        setCurrentDate(formattedDay + " ");
        setCurrentTimeOfCreatingForm(formattedTime);

        System.out.println(getCurrentTimeOfCreatingForm());
        clickElementOnPage(sendButton);
        return this;
    }

    @Step("Видно ли уведомление с просьбой загрузить файл")
    public boolean isNotificationToUploadFileVisible()
    {
        return findElementOnPage(notificationUploadFile).isDisplayed();
    }

    @Step("Видно ли уведомление с просьбой загрузить файл")
    public boolean isFormOnModeration()
    {
        return findElementOnPage(formOnModeration).isDisplayed();
    }

    public String getCurrentTimeOfCreatingForm()
    {
        return currentTimeOfCreatingForm;
    }

    public void setCurrentTimeOfCreatingForm(String currentTimeOfCreatingForm)
    {
        this.currentTimeOfCreatingForm = currentTimeOfCreatingForm;
    }

    public String getCurrentDate()
    {
        return currentDate;
    }

    public void setCurrentDate(String currentDate)
    {
        this.currentDate = currentDate;
    }
}
