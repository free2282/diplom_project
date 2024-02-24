package ru.miigaik.pages;

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

    public boolean isSurnameValueOnPageMatchedWithCreatedData(String surname)
    {
        return findElementOnPage(By.xpath(".//input[@value='"+surname+"' and @placeholder='Фамилия']")).isDisplayed();
    }

    public boolean isNameValueOnPageMatchedWithCreatedData(String name)
    {
        return findElementOnPage(By.xpath(".//input[@value='"+name+"' and @placeholder='Имя']")).isDisplayed();
    }

    public boolean isPatronymicValueOnPageMatchedWithCreatedData(String patronymic)
    {
        return findElementOnPage(By.xpath(".//input[@value='"+patronymic+"' and @placeholder='Отчество']")).isDisplayed();
    }

    public boolean isSexValueOnPageMatchedWithCreatedData(String sex)
    {
        return findElementOnPage(By.xpath(".//div[text()='"+sex+"']")).isDisplayed();
    }

    public boolean isBirthOnPageMatchedWithCreatedBirthDate(String date)
    {
        return findElementOnPage(By.xpath(".//input[@value='"+date+"' and @placeholder='ДД.ММ.ГГГГ']")).isDisplayed();
    }

    public boolean isPlaceOfBirthONPageMatchedWithCreatedPlaceOfBirth(String place)
    {
        return findElementOnPage(By.xpath(".//input[@value='"+place+"' and @placeholder='Страна, город']")).isDisplayed();
    }

    public boolean isCinizenShipOnPageMatchedWithCreatedCinizenship(String cinizenship)
    {
        return findElementOnPage(By.xpath(".//div[div/p[text()='Гражданство']]/div/div/div/div[text()='"+cinizenship+"']")).isDisplayed();
    }

    public boolean isPhoneNumberOnPageMatchedWithCreatedPhoneNumber(String phoneNumber)
    {
        return findElementOnPage(By.xpath(".//input[@value='+7 "+phoneNumber+"' and @placeholder='+7 (777) 777-77-77']")).isDisplayed();
    }

    public boolean isEducationGradeOnPageMatchedWithCreatedEducationGrade(String educationLevel)
    {
        return findElementOnPage(By.xpath(".//div[text()='" +educationLevel+"']")).isDisplayed();
    }

    public boolean isSnilsOnPageMatchedWithSnils(String snils)
    {
        return findElementOnPage(By.xpath(".//input[@value='"+snils+"' and @placeholder='000-000-000 00']")).isDisplayed();
    }

    public boolean isSnilsDateOnPageMatchedWithSnilsDate(String snilsDate)
    {
        return findElementOnPage(By.xpath(".//input[@value='"+snilsDate+"' and @placeholder='ДД.ММ.ГГГГ']")).isDisplayed();
    }

    public boolean isDisabilityOnPageMatchedWithDisability(String disability)
    {
        return findElementOnPage(By.xpath(".//div[text()='"+disability+"']")).isDisplayed();
    }

    public boolean isEducationProgramOnPageMatchedWithEducationProgram(String educationProgram)
    {
        return findElementOnPage(By.xpath(".//div[text()='"+educationProgram+"']")).isDisplayed();
    }

    public boolean isPasportTypeOnPageMatchedWithPasportType(String pasportType)
    {
        return findElementOnPage(By.xpath(".//div[text()='"+pasportType+"']")).isDisplayed();
    }

    public boolean isSeriesOfPasportOnPageMatchedWithSeriesOfPasport(String seriesOfPasport)
    {
        return findElementOnPage(By.xpath(".//input[@value='"+seriesOfPasport+"' and @placeholder = '00 00']")).isDisplayed();
    }

    public boolean iSNumberOfPasportOnPageMatchedWithNumberOfPasport(String numberOfPasport)
    {
        return findElementOnPage(By.xpath(".//input[@value='"+numberOfPasport+"' and @placeholder = '000000']")).isDisplayed();
    }

    public boolean iSGovernmentAgencyGavePasportOnPageMatchedGovAgencyGavePasport(String nameOfAgency)
    {
        return findElementOnPage(By.xpath(".//input[@value='"+nameOfAgency+"']")).isDisplayed();
    }

    public boolean isGovernmentCodeOfPasportOnPageMatchedWithGovernmentCode(String code)
    {
        return findElementOnPage(By.xpath(".//input[@value='"+code+"' and @placeholder = '000-000']")).isDisplayed();
    }

    public boolean isIssuDateOfPasportGivingOnPageMatchedWithIssueDate(String date)
    {
        return findElementOnPage(By.xpath(".//input[@value='"+date+"' and @placeholder = 'ДД.ММ.ГГГГ']")).isDisplayed();
    }

    public boolean isIndexOfRegistrationOnPageMatchedWithIndex(String index)
    {
        return findElementOnPage(By.xpath(".//input[@value='"+index+"' and @placeholder='000000']")).isDisplayed();
    }

    public boolean isContyOfRegistrationOnPageMatchedWithCountry(String country)
    {
        return findElementOnPage(By.xpath(".//div[text()='"+country+"']")).isDisplayed();
    }

    public boolean isStateOfRegistrationOnPageMatchedWithState(String state)
    {
        return findElementOnPage(By.xpath(".//input[@value='"+state+"']")).isDisplayed();
    }

    public boolean isCityOfRegistrationOnPageMatchedWithCity(String city)
    {
        return findElementOnPage(By.xpath(".//input[@value='"+city+"']")).isDisplayed();
    }

    public boolean isStreetOfRegistrationOnPageMatchedWithStreet(String street)
    {
        return findElementOnPage(By.xpath(".//input[@value='"+street+"']")).isDisplayed();
    }

    public boolean isHouseOfRegistrationOnPageMatchedWithHouse(String house)
    {
        return findElementOnPage(By.xpath(".//input[@value='"+house+"']")).isDisplayed();
    }

    public boolean isNameOfEducationFormOnPageMatchedWithEducationName(String educationName)
    {
        return findElementOnPage(By.xpath(".//input[@value='"+educationName+"']")).isDisplayed();
    }

    public boolean isUniversitySpecialtyInFormOnPageMatchedWithUniversitySpecialty(String universityName)
    {
        return findElementOnPage(By.xpath(".//input[@value='"+universityName+"']")).isDisplayed();
    }

    public boolean isDiplomSeriesAndNumberOnPageMatchedWithSerieswAndNumber(String seriesAndNumber)
    {
        return findElementOnPage(By.xpath(".//input[@value='"+seriesAndNumber+"']")).isDisplayed();
    }

    public boolean isDiplomDateOfReceiptOnPageMatchedWithDateOfReceipt(String date)
    {
        return findElementOnPage(By.xpath(".//input[@value='"+date+"' and @placeholder='ДД.ММ.ГГГГ']")).isDisplayed();
    }

    public boolean isDiplomYearOfEndingOnPageMatchedWithYearOfEnding(String year)
    {
        return findElementOnPage(By.xpath(".//input[@value='"+year+"']")).isDisplayed();
    }

    public boolean isPlaceOfWorkOnPageMatchedWithPlaceOfWork(String year)
    {
        return findElementOnPage(By.xpath(".//input[@value='"+year+"']")).isDisplayed();
    }

    public boolean isWorkingPositionOnPageMatchedWithWorkingPosition(String year)
    {
        return findElementOnPage(By.xpath(".//input[@value='"+year+"']")).isDisplayed();
    }

    public boolean isPeriodOfWorkingEndingOnPageMatchedWithYearOfEnding(String year)
    {
        return findElementOnPage(By.xpath(".//input[@value='"+year+"']")).isDisplayed();
    }

    public FormOnModerationSide clickAccept() throws InterruptedException
    {
        waitAfterEvent(1);
        clickElementOnPage(buttonAccept);
        return this;
    }

    public FormOnModerationSide clickDeclin() throws InterruptedException
    {
        waitAfterEvent(1);
        clickElementOnPage(buttonDeclined);
        return this;
    }

    public FormOnModerationSide setValueToReasonField()
    {
        setDataToInputElement(reasonOfDecliningField, "РАНДОМНАЯ СТРОКА");
        return this;
    }

    public FormOnModerationSide clickSendButtot()
    {
        clickElementOnPage(buttonSendReasonOfDeclining);
        return this;
    }
}
