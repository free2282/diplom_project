package ru.miigaik.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.security.PublicKey;

public class FormOnModerationSide extends BasePage
{
    private By buttonAccept = By.xpath(".//button[text()='Принять анкету']");
    private By buttonDeclined = By.xpath(".//button[text()='Отклонить']");
    private By reasonOfDecliningField = By.xpath(".//textarea");
    private By buttonSendReasonOfDeclining = By.xpath(".//button[text()='Отправить']");
    public FormOnModerationSide(WebDriver driver)
    {
        super(driver);
    }

    @Step("Совпало ли поле 'Фамилия' после создания анкеты на модераторской части")
    public boolean isSurnameValueOnPageMatchedWithCreatedData(String surname)
    {
        return findElementOnPage(By.xpath(".//input[@value='"+surname+"' and @placeholder='Фамилия']")).isDisplayed();
    }

    @Step("Совпало ли поле 'Имя' после создания анкеты на модераторской части")
    public boolean isNameValueOnPageMatchedWithCreatedData(String name)
    {
        return findElementOnPage(By.xpath(".//input[@value='"+name+"' and @placeholder='Имя']")).isDisplayed();
    }

    @Step("Совпало ли поле 'Отчество' после создания анкеты на модераторской части")
    public boolean isPatronymicValueOnPageMatchedWithCreatedData(String patronymic)
    {
        return findElementOnPage(By.xpath(".//input[@value='"+patronymic+"' and @placeholder='Отчество']")).isDisplayed();
    }

    @Step("Совпало ли поле 'Пол' после создания анкеты на модераторской части")
    public boolean isSexValueOnPageMatchedWithCreatedData(String sex)
    {
        return findElementOnPage(By.xpath(".//div[text()='"+sex+"']")).isDisplayed();
    }

    @Step("Совпало ли поле 'Дата рождения' после создания анкеты на модераторской части")
    public boolean isBirthOnPageMatchedWithCreatedBirthDate(String date)
    {
        return findElementOnPage(By.xpath(".//input[@value='"+date+"' and @placeholder='ДД.ММ.ГГГГ']")).isDisplayed();
    }

    @Step("Совпало ли поле 'Место рождения' после создания анкеты на модераторской части")
    public boolean isPlaceOfBirthONPageMatchedWithCreatedPlaceOfBirth(String place)
    {
        return findElementOnPage(By.xpath(".//input[@value='"+place+"' and @placeholder='Страна, город']")).isDisplayed();
    }

    @Step("Совпало ли поле 'Гражданство' после создания анкеты на модераторской части")
    public boolean isCinizenShipOnPageMatchedWithCreatedCinizenship(String cinizenship)
    {
        return findElementOnPage(By.xpath(".//div[div/p[text()='Гражданство']]/div/div/div/div[text()='"+cinizenship+"']")).isDisplayed();
    }

    @Step("Совпало ли поле 'Номер телефона' после создания анкеты на модераторской части")
    public boolean isPhoneNumberOnPageMatchedWithCreatedPhoneNumber(String phoneNumber)
    {
        return findElementOnPage(By.xpath(".//input[@value='+7 "+phoneNumber+"' and @placeholder='+7 (777) 777-77-77']")).isDisplayed();
    }

    @Step("Совпало ли поле 'Уровень образования' после создания анкеты на модераторской части")
    public boolean isEducationGradeOnPageMatchedWithCreatedEducationGrade(String educationLevel)
    {
        return findElementOnPage(By.xpath(".//div[text()='" +educationLevel+"']")).isDisplayed();
    }

    @Step("Совпало ли поле 'СНИЛС' после создания анкеты на модераторской части")
    public boolean isSnilsOnPageMatchedWithSnils(String snils)
    {
        return findElementOnPage(By.xpath(".//input[@value='"+snils+"' and @placeholder='000-000-000 00']")).isDisplayed();
    }

    @Step("Совпала ли поле 'Дата регистрации СНИЛС' после создания анкеты на модераторской части")
    public boolean isSnilsDateOnPageMatchedWithSnilsDate(String snilsDate)
    {
        return findElementOnPage(By.xpath(".//input[@value='"+snilsDate+"' and @placeholder='ДД.ММ.ГГГГ']")).isDisplayed();
    }

    @Step("Совпала ли поле 'Инвалидность' после создания анкеты на модераторской части")
    public boolean isDisabilityOnPageMatchedWithDisability(String disability)
    {
        return findElementOnPage(By.xpath(".//div[text()='"+disability+"']")).isDisplayed();
    }

    @Step("Совпала ли поле 'Программа ДПО' после создания анкеты на модераторской части")
    public boolean isEducationProgramOnPageMatchedWithEducationProgram(String educationProgram)
    {
        return findElementOnPage(By.xpath(".//div[text()='"+educationProgram+"']")).isDisplayed();
    }

    @Step("Совпало ли поле 'Тип документа удостоверяющего личность' после создания анкеты на модераторской части")
    public boolean isPasportTypeOnPageMatchedWithPasportType(String pasportType)
    {
        return findElementOnPage(By.xpath(".//div[text()='"+pasportType+"']")).isDisplayed();
    }

    @Step("Совпало ли поле 'Серия документа' после создания анкеты на модераторской части")
    public boolean isSeriesOfPasportOnPageMatchedWithSeriesOfPasport(String seriesOfPasport)
    {
        return findElementOnPage(By.xpath(".//input[@value='"+seriesOfPasport+"' and @placeholder = '00 00']")).isDisplayed();
    }

    @Step("Совпало ли поле 'Номер документа' после создания анкеты на модераторской части")
    public boolean iSNumberOfPasportOnPageMatchedWithNumberOfPasport(String numberOfPasport)
    {
        return findElementOnPage(By.xpath(".//input[@value='"+numberOfPasport+"' and @placeholder = '000000']")).isDisplayed();
    }

    @Step("Совпало ли поле 'Орган, выдавший документ удостоверяющий личность' после создания анкеты на модераторской части")
    public boolean iSGovernmentAgencyGavePasportOnPageMatchedGovAgencyGavePasport(String nameOfAgency)
    {
        return findElementOnPage(By.xpath(".//input[@value='"+nameOfAgency+"']")).isDisplayed();
    }

    @Step("Совпало ли поле 'Код подразделения' после создания анкеты на модераторской части")
    public boolean isGovernmentCodeOfPasportOnPageMatchedWithGovernmentCode(String code)
    {
        return findElementOnPage(By.xpath(".//input[@value='"+code+"' and @placeholder = '000-000']")).isDisplayed();
    }

    @Step("Совпало ли поле 'Дата выдачи' после создания анкеты на модераторской части")
    public boolean isIssuDateOfPasportGivingOnPageMatchedWithIssueDate(String date)
    {
        return findElementOnPage(By.xpath(".//input[@value='"+date+"' and @placeholder = 'ДД.ММ.ГГГГ']")).isDisplayed();
    }

    @Step("Совпало ли поле 'Индекс' после создания анкеты на модераторской части")
    public boolean isIndexOfRegistrationOnPageMatchedWithIndex(String index)
    {
        return findElementOnPage(By.xpath(".//input[@value='"+index+"' and @placeholder='000000']")).isDisplayed();
    }

    @Step("Совпало ли поле 'Страна' после создания анкеты на модераторской части")
    public boolean isContyOfRegistrationOnPageMatchedWithCountry(String country)
    {
        return findElementOnPage(By.xpath(".//div[text()='"+country+"']")).isDisplayed();
    }

    @Step("Совпало ли поле 'Регион' после создания анкеты на модераторской части")
    public boolean isStateOfRegistrationOnPageMatchedWithState(String state)
    {
        return findElementOnPage(By.xpath(".//input[@value='"+state+"']")).isDisplayed();
    }

    @Step("Совпало ли поле 'Населённый пункт' после создания анкеты на модераторской части")
    public boolean isCityOfRegistrationOnPageMatchedWithCity(String city)
    {
        return findElementOnPage(By.xpath(".//input[@value='"+city+"']")).isDisplayed();
    }

    @Step("Совпало ли поле 'Улица' после создания анкеты на модераторской части")
    public boolean isStreetOfRegistrationOnPageMatchedWithStreet(String street)
    {
        return findElementOnPage(By.xpath(".//input[@value='"+street+"']")).isDisplayed();
    }

    @Step("Совпало ли поле 'Дом, корпус, строение, квартира' после создания анкеты на модераторской части")
    public boolean isHouseOfRegistrationOnPageMatchedWithHouse(String house)
    {
        return findElementOnPage(By.xpath(".//input[@value='"+house+"']")).isDisplayed();
    }

    @Step("Совпало ли поле 'Наименование образовательной организации' после создания анкеты на модераторской части")
    public boolean isNameOfEducationFormOnPageMatchedWithEducationName(String educationName)
    {
        return findElementOnPage(By.xpath(".//input[@value='"+educationName+"']")).isDisplayed();
    }

    @Step("Совпало ли поле 'Специальность / направление подготовки' после создания анкеты на модераторской части")
    public boolean isUniversitySpecialtyInFormOnPageMatchedWithUniversitySpecialty(String universityName)
    {
        return findElementOnPage(By.xpath(".//input[@value='"+universityName+"']")).isDisplayed();
    }

    @Step("Совпало ли поле 'Серия и номер диплома' после создания анкеты на модераторской части")
    public boolean isDiplomSeriesAndNumberOnPageMatchedWithSerieswAndNumber(String seriesAndNumber)
    {
        return findElementOnPage(By.xpath(".//input[@value='"+seriesAndNumber+"']")).isDisplayed();
    }

    @Step("Совпало ли поле 'Дата выдачи' после создания анкеты на модераторской части")
    public boolean isDiplomDateOfReceiptOnPageMatchedWithDateOfReceipt(String date)
    {
        return findElementOnPage(By.xpath(".//input[@value='"+date+"' and @placeholder='ДД.ММ.ГГГГ']")).isDisplayed();
    }

    @Step("Совпало ли поле 'Год окончания' после создания анкеты на модераторской части")
    public boolean isDiplomYearOfEndingOnPageMatchedWithYearOfEnding(String year)
    {
        return findElementOnPage(By.xpath(".//input[@value='"+year+"']")).isDisplayed();
    }

    @Step("Совпало ли поле 'Место работы' после создания анкеты на модераторской части")
    public boolean isPlaceOfWorkOnPageMatchedWithPlaceOfWork(String year)
    {
        return findElementOnPage(By.xpath(".//input[@value='"+year+"']")).isDisplayed();
    }

    @Step("Совпало ли поле 'Занимаемая должность' после создания анкеты на модераторской части")
    public boolean isWorkingPositionOnPageMatchedWithWorkingPosition(String year)
    {
        return findElementOnPage(By.xpath(".//input[@value='"+year+"']")).isDisplayed();
    }

    @Step("Совпало ли поле 'Период работы' после создания анкеты на модераторской части")
    public boolean isPeriodOfWorkingEndingOnPageMatchedWithYearOfEnding(String year)
    {
        return findElementOnPage(By.xpath(".//input[@value='"+year+"']")).isDisplayed();
    }

    @Step("Нажатие на кнопку принятия анкеты")
    public FormOnModerationSide clickAccept() throws InterruptedException
    {
        waitAfterEvent(1);
        clickElementOnPage(buttonAccept);
        return this;
    }

    @Step("Нажатие на кнопку отклонения анкеты")
    public FormOnModerationSide clickDeclin() throws InterruptedException
    {
        waitAfterEvent(1);
        clickElementOnPage(buttonDeclined);
        return this;
    }

    @Step("Установка значения причины отклонения")
    public FormOnModerationSide setValueToReasonField()
    {
        setDataToInputElement(reasonOfDecliningField, "РАНДОМНАЯ СТРОКА");
        return this;
    }

    @Step("Нажатие на кнопку отправить после указания причины отклонения формы")
    public FormOnModerationSide clickSendButtot()
    {
        clickElementOnPage(buttonSendReasonOfDeclining);
        return this;
    }
}
