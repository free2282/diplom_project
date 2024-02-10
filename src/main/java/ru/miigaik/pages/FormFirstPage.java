package ru.miigaik.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class FormFirstPage extends BasePage
{
    private final By photoUploadPlace = By.xpath(".//input[@accept='image/jpeg,image/jpg,image/png']");
//    private final By uploadPhotoText = By.xpath(".//p[text()='Загрузите фото']");
    private final By surname = By.xpath(".//input[@placeholder='Фамилия']");
    private final By name = By.xpath(".//input[@placeholder='Имя']");
    private final By patronymic = By.xpath(".//input[@placeholder='Отчество']");
    private final By sexDropDown = By.xpath("//*[@id='root']/form/section[1]/div[5]/div[2]/div");
    private final By male = By.xpath(".//div[text()='МУЖСКОЙ' and @role='option']");
    private final By female = By.xpath(".//div[text()='ЖЕНСКИЙ' and @role='option']");
    private final By birthData = By.xpath(".//input[../div/p[text()='Дата рождения'] and @placeholder='ДД.ММ.ГГГГ']");
    private final By countryAndCity = By.xpath(".//input[@placeholder='Страна, город']");

    private final By citizenshipDropDown = By.xpath("//*[@id='root']/form/section[1]/div[8]/div[2]/div/div[1]/div[2]");
    private final By citizenshipInput = By.xpath("//*[@id='react-select-3-input']");

    private final By phoneNumber = By.xpath(".//input[@placeholder='+7 (777) 777-77-77']");

    private final By gradeLevelDropDown = By.xpath("//*[@id='root']/form/section[1]/div[10]/div[2]/div/div[1]/div[2]");
    private final By middleGradeLevel = By.xpath(".//div[text()='СРЕДНЕЕ ПРОФЕССИОНАЛЬНОЕ']");
    private final By highGradeLevel = By.xpath(".//div[text()='ВЫСШЕЕ']");


    private final By snils = By.xpath(".//input[@placeholder='000-000-000 00']");
    private final By snilsRegistrationDate = By.xpath(".//input[../div/p[text()='Дата регистрации СНИЛС'] and @placeholder='ДД.ММ.ГГГГ']");

    private final By disabilityDropDown = By.xpath(".//div[text()='НЕТ']/parent::div/parent::div");
    private final By disabilityYes = By.xpath(".//div[text()='ЕСТЬ' and @role='option']");

    private final By disabilityNo = By.xpath(".//div[text()='НЕТ' and @role='option']");

    private final By educationProgramDropDown = By.xpath(".//div[text()='Выберите подходящее']/parent::div/parent::div");
    private final By educationList = By.xpath("//*[@id='react-select-6-listbox']");

    private final By dulDropDown = By.xpath("//*[@id='root']/form/section[2]/div[1]/div[2]/div");
    private final By pasportRf = By.xpath(".//div[text()='ПАСПОРТ РФ']");
    private final By pasportOtherCountry = By.xpath(".//div[text()='ПАСПОРТ ДРУГОГО ГОСУДАРСТВА']");
    private final By dulNumber = By.xpath(".//div[@class='p-0 rounded-0 flex flex-col max-sm:col-span-2 gap-1']/input[@placeholder='000000']");
    private final By dulSeries = By.xpath(".//input[@placeholder='00 00']");
    private final By governmentAgency = By.xpath("//*[@id='root']/form/section[2]/div[4]/input");
    private final By codeOfGavAgency = By.xpath(".//input[@placeholder='000-000']");
    private final By dateOfReceiptDul = By.xpath("//*[@id='root']/form/section[2]/div[6]/input[@placeholder='ДД.ММ.ГГГГ']");

    private final By zipCode = By.xpath("//*[@id='root']/form/section[3]/div[1]/input[@placeholder='000000']");
    private final By registrationCountryDropDown = By.xpath("//*[@id='root']/form/section[3]/div[2]/div[2]/div/div[1]/div[2]");
    private final By registrationCountryInput = By.xpath("//*[@id='react-select-8-input']");
    private final By state = By.xpath("//*[@id='root']/form/section[3]/div[3]/input");
    private final By city = By.xpath("//*[@id='root']/form/section[3]/div[4]/input");
    private final By street = By.xpath("//*[@id='root']/form/section[3]/div[5]/input");
    private final By house = By.xpath("//*[@id='root']/form/section[3]/div[6]/input[@placeholder='Дом, корпус, строение, квартира']");

    private final By namingOfEducationOrganization = By.xpath("//*[@id='root']/form/section[4]/div[1]/input");
    private final By universitySpecialty = By.xpath("//*[@id='root']/form/section[4]/div[2]/input");
    private final By diplomNumberAndSeries = By.xpath("//*[@id='root']/form/section[4]/div[3]/input");
    private final By dateOfReceiptDiplom = By.xpath("//*[@id='root']/form/section[4]/div[4]/input");
    private final By yearOfEnding = By.xpath("//*[@id='root']/form/section[4]/div[5]/input");
    private final By placeOfWork = By.xpath("//*[@id='root']/form/section[5]/div[1]/input");
    private final By workingPosition = By.xpath("//*[@id='root']/form/section[5]/div[2]/input");
    private final By periodOfWork = By.xpath("//*[@id='root']/form/section[5]/div[3]/input");

    public FormFirstPage(WebDriver driver)
    {
        super(driver);
    }

    @Override
    public FormFirstPage waitAfterEvent(int timeWaiting) throws InterruptedException
    {
        TimeUnit.SECONDS.sleep(timeWaiting);
        return this;
    }

    public FormFirstPage uploadPhoto(String photoName)
    {

        findElementOnPage(photoUploadPlace).sendKeys(
                new File("./attach/"+photoName).getAbsolutePath()
        );
        return this;
    }

    public FormFirstPage quickSetName(String nameData)
    {
        setDataToInputElement(surname, nameData);
        setDataToInputElement(name, nameData);
        setDataToInputElement(patronymic, nameData);
        return this;
    }

    public FormFirstPage setSurname(String surnameData)
    {
        setDataToInputElement(surname, surnameData);
        return this;
    }

    public FormFirstPage setName(String surnameData)
    {
        setDataToInputElement(name, surnameData);
        return this;
    }

    public FormFirstPage setPatronymic(String surnameData)
    {
        setDataToInputElement(patronymic, surnameData);
        return this;
    }

    public FormFirstPage chooseMaleSex()
    {
        clickElementOnPage(sexDropDown);
        clickElementOnPage(male);
        return this;
    }

    public FormFirstPage chooseFemaleSex()
    {
        clickElementOnPage(sexDropDown);
        clickElementOnPage(female);
        return this;
    }

    public FormFirstPage setBirth(String date)
    {
        clickElementOnPage(birthData);
        setDataToInputElement(birthData, date);
        return this;
    }

    public FormFirstPage setCountryAndCity(String countryInput, String cityInput)
    {
        String resultData = countryInput + ", " + cityInput;
        setDataToInputElement(countryAndCity, resultData);
        return this;
    }

    public FormFirstPage setCitizenship(String citizenshipDataUpperCase)
    {

        clickElementOnPage(citizenshipDropDown);

        setDataToInputElement(citizenshipInput, citizenshipDataUpperCase);
        clickElementOnPage(By.xpath(".//div[text()='" + citizenshipDataUpperCase + "' and @role='option']"));
        return this;
    }

    public FormFirstPage setPhoneNumber(String phoneNumberData)
    {
        clickElementOnPage(phoneNumber);
        setDataToInputElement(phoneNumber, phoneNumberData);
        return this;
    }

    public FormFirstPage setMiddleGradeLevel()
    {
        clickElementOnPage(gradeLevelDropDown);
        clickElementOnPage(middleGradeLevel);
        return this;
    }

    public FormFirstPage setHighGradeLevel()
    {
        clickElementOnPage(gradeLevelDropDown);
        clickElementOnPage(highGradeLevel);
        return this;
    }
    public FormFirstPage setSnils(String snilsData)
    {
        clickElementOnPage(snils);
        setDataToInputElement(snils, snilsData);
        return this;
    }

    public FormFirstPage setSnilsRegistrationDate(String date)
    {
        clickElementOnPage(snilsRegistrationDate);
        setDataToInputElement(snilsRegistrationDate, date);
        return this;
    }

    public FormFirstPage setDisabilityNo()
    {
        clickElementOnPage(disabilityDropDown);
        clickElementOnPage(disabilityNo);
        return this;
    }

    public FormFirstPage setDisabilityYes()
    {
        clickElementOnPage(disabilityDropDown);
        clickElementOnPage(disabilityYes);
        return this;
    }

    public FormFirstPage setEducationProgram(int programOrder)
    {
        clickElementOnPage(educationProgramDropDown);

        List<WebElement> allProgramFromElement = driver.findElements(educationList);
        String[] allProgram = allProgramFromElement.get(0).getText().split("\n");

        clickElementOnPage(By.xpath(".//div[text()='"+ allProgram[programOrder]+"']"));
        return this;
    }

    public FormFirstPage setRussianPasport()
    {
        clickElementOnPage(dulDropDown);
        clickElementOnPage(pasportRf);
        return this;
    }

    public FormFirstPage setOtherCountryPasport()
    {
        clickElementOnPage(dulDropDown);
        clickElementOnPage(pasportOtherCountry);
        return this;
    }

    public FormFirstPage setDulSeries(String series)
    {
        clickElementOnPage(dulSeries);
        setDataToInputElement(dulSeries, series);
        return this;
    }

    public FormFirstPage setDulNumber(String number)
    {
        clickElementOnPage(dulNumber);
        setDataToInputElement(dulNumber, number);
        return this;
    }

    public FormFirstPage setGovernmentAgency(String nameAgency)
    {
        setDataToInputElement(governmentAgency, nameAgency);
        return this;
    }

    public FormFirstPage setCodeAgency(String codeOfAgency)
    {
        clickElementOnPage(codeOfGavAgency);
        setDataToInputElement(codeOfGavAgency, codeOfAgency);
        return this;
    }

    public FormFirstPage setDateOfReceiptDul(String date)
    {
        clickElementOnPage(dateOfReceiptDul);
        setDataToInputElement(dateOfReceiptDul, date);
        return this;
    }

    public FormFirstPage setZipCode(String index)
    {
        clickElementOnPage(zipCode);
        setDataToInputElement(zipCode, index);
        return this;
    }

    public FormFirstPage setCountryOfRegistration(String registrationCountryUpperCase)
    {
        clickElementOnPage(registrationCountryDropDown);

        setDataToInputElement(registrationCountryInput, registrationCountryUpperCase);
        clickElementOnPage(By.xpath(".//div[text()='" + registrationCountryUpperCase + "' and @role='option']"));
        return this;
    }

    public FormFirstPage setStateOfRegistration(String stateData)
    {
        clickElementOnPage(state);
        setDataToInputElement(state, stateData);
        return this;
    }

    public FormFirstPage setCityOfregistration(String cityData)
    {
        clickElementOnPage(city);
        setDataToInputElement(city, cityData);
        return this;
    }

    public FormFirstPage setStreetOfRegistration(String streetData)
    {
        clickElementOnPage(street);
        setDataToInputElement(street, streetData);
        return this;
    }

    public FormFirstPage setHouseOfRegistration(String houseOfRegistrationData)
    {
        clickElementOnPage(house);
        setDataToInputElement(house, houseOfRegistrationData);
        return this;
    }

    public FormFirstPage setNamingOfEducationOrganization(String educationOrganization)
    {
        clickElementOnPage(namingOfEducationOrganization);
        setDataToInputElement(namingOfEducationOrganization, educationOrganization);
        return this;
    }

    public FormFirstPage setUniversitySpecialty(String specialty)
    {
        clickElementOnPage(universitySpecialty);
        setDataToInputElement(universitySpecialty, specialty);
        return this;
    }

    public FormFirstPage setDiplomNumberAndSeries(String numberAndSeries)
    {
        clickElementOnPage(diplomNumberAndSeries);
        setDataToInputElement(diplomNumberAndSeries, numberAndSeries);
        return this;
    }

    public FormFirstPage setDateOfReceiptDiplom(String date)
    {
        clickElementOnPage(dateOfReceiptDiplom);
        setDataToInputElement(dateOfReceiptDiplom, date);
        return this;
    }

    public FormFirstPage setYearOfEnding(String year)
    {
        clickElementOnPage(yearOfEnding);
        setDataToInputElement(yearOfEnding, year);
        return this;
    }

    public FormFirstPage setPlaceOfWork(String placeOfWorkData)
    {
        clickElementOnPage(placeOfWork);
        setDataToInputElement(placeOfWork, placeOfWorkData);
        return this;
    }

    public FormFirstPage setWorkingPosition(String positiion)
    {
        clickElementOnPage(workingPosition);
        setDataToInputElement(workingPosition, positiion);
        return this;
    }

    public FormFirstPage setPeriodOfWork(String periodOfWorkData)
    {
        clickElementOnPage(periodOfWork);
        setDataToInputElement(periodOfWork, periodOfWorkData);
        return this;
    }
}
