package ru.miigaik.pages;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.security.PublicKey;
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
    private final By NextButton = By.xpath(".//button[text()='Далее']");
    private final By nextPageAsssureance = By.xpath(".//p[text()='Распечатайте сформированные документы, подпишите их и загрузите в данную анкету.']");

    private final By errorFileType = By.xpath(".//span[text()='Тип файла может быть одним из следующих: image/jpeg image/jpg image/png']");
    private final By errorFormatPhoneNumber = By.xpath(".//span[text()='Неверный формат номера телефона']");
    private final By incorrectPhoneNumber = By.xpath(".//span[text()='Введен некорректный номер телефона.']");

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

    @Step("Загрузка фото поступающего")
    public FormFirstPage uploadPhoto(String photoName)
    {

        findElementOnPage(photoUploadPlace).sendKeys(
                new File("./attach/"+photoName).getAbsolutePath()
        );
        return this;
    }

    @Step("Заполнение полей ФИО")
    public FormFirstPage quickSetName(String nameValue)
    {
        setDataToInputElement(surname, nameValue);
        setDataToInputElement(name, nameValue);
        setDataToInputElement(patronymic, nameValue);
        Allure.addAttachment("В поле для имени, фамилии, отчества установленео значение", nameValue);
        return this;
    }

    @Step("Заполнение поля фамилия")
    public FormFirstPage setSurname(String surnameValue)
    {
        setDataToInputElement(surname, surnameValue);
        Allure.addAttachment("Значение",surnameValue);
        return this;
    }

    @Step("Заполнение поля имя")
    public FormFirstPage setName(String nameValue)
    {
        setDataToInputElement(name, nameValue);
        Allure.addAttachment("Значение", nameValue);
        return this;
    }

    @Step("Заполнение поля отчества")
    public FormFirstPage setPatronymic(String patronymicValue)
    {
        setDataToInputElement(patronymic, patronymicValue);
        Allure.addAttachment("Значение", patronymicValue);
        return this;
    }

    @Step("Выбор мужского пола")
    public FormFirstPage chooseMaleSex()
    {
        clickElementOnPage(sexDropDown);
        clickElementOnPage(male);
        return this;
    }

    @Step("Выбор женского пола")
    public FormFirstPage chooseFemaleSex()
    {
        clickElementOnPage(sexDropDown);
        clickElementOnPage(female);
        return this;
    }

    @Step("Установка даты рождения")
    public FormFirstPage setBirth(String date)
    {
        clickElementOnPage(birthData);
        setDataToInputElement(birthData, date);
        Allure.addAttachment("Значение", date);
        return this;
    }

    @Step("Установка страны рождения")
    public FormFirstPage setCountryAndCity(String countryInput, String cityInput)
    {
        String resultData = countryInput + ", " + cityInput;
        setDataToInputElement(countryAndCity, resultData);
        Allure.addAttachment("Значение", resultData);
        return this;
    }

    @Step("Установка значения гражданства")
    public FormFirstPage setCitizenship(String citizenshipDataUpperCase)
    {

        clickElementOnPage(citizenshipDropDown);

        setDataToInputElement(citizenshipInput, citizenshipDataUpperCase);
        clickElementOnPage(By.xpath(".//div[text()='" + citizenshipDataUpperCase + "' and @role='option']"));
        Allure.addAttachment("Значение", citizenshipDataUpperCase);
        return this;
    }

    @Step("Установка номера телефона")
    public FormFirstPage setPhoneNumber(String phoneNumberData)
    {
        clickElementOnPage(phoneNumber);
        setDataToInputElement(phoneNumber, phoneNumberData);
        Allure.addAttachment("Значение", phoneNumberData);
        return this;
    }

    @Step("Установка среднего уровня образования")
    public FormFirstPage setMiddleGradeLevel()
    {
        clickElementOnPage(gradeLevelDropDown);
        clickElementOnPage(middleGradeLevel);
        return this;
    }

    @Step("Установка высшего уровня образования")
    public FormFirstPage setHighGradeLevel()
    {
        clickElementOnPage(gradeLevelDropDown);
        clickElementOnPage(highGradeLevel);
        return this;
    }

    @Step("Установка номера СНИЛСа")
    public FormFirstPage setSnils(String snilsValue)
    {
        clickElementOnPage(snils);
        setDataToInputElement(snils, snilsValue);
        Allure.addAttachment("Значение", snilsValue);
        return this;
    }

    @Step("Установка даты регистрации СНИЛСа")
    public FormFirstPage setSnilsRegistrationDate(String date)
    {
        clickElementOnPage(snilsRegistrationDate);
        setDataToInputElement(snilsRegistrationDate, date);
        Allure.addAttachment("Значение", date);
        return this;
    }

    @Step("Установка инвалидности 'Нет'")
    public FormFirstPage setDisabilityNo()
    {
        clickElementOnPage(disabilityDropDown);
        clickElementOnPage(disabilityNo);
        return this;
    }

    @Step("Установка инвалидности 'Да'")
    public FormFirstPage setDisabilityYes()
    {
        clickElementOnPage(disabilityDropDown);
        clickElementOnPage(disabilityYes);
        return this;
    }

    @Step("Выбор программы обучения")
    public FormFirstPage setEducationProgram(int programOrder)
    {
        clickElementOnPage(educationProgramDropDown);

        List<WebElement> allProgramFromElement = driver.findElements(educationList);
        String[] allProgram = allProgramFromElement.get(0).getText().split("\n");

        clickElementOnPage(By.xpath(".//div[text()='"+ allProgram[programOrder]+"']"));
        Allure.addAttachment("Выбор программы обучения", allProgram[programOrder]);
        return this;
    }

    @Step("Установка русского паспорта")
    public FormFirstPage setRussianPasport()
    {
        clickElementOnPage(dulDropDown);
        clickElementOnPage(pasportRf);
        return this;
    }

    @Step("Установка паспорта другой страны")
    public FormFirstPage setOtherCountryPasport()
    {
        clickElementOnPage(dulDropDown);
        clickElementOnPage(pasportOtherCountry);
        return this;
    }

    @Step("Установка значения серии паспорта")
    public FormFirstPage setDulSeries(String series)
    {
        clickElementOnPage(dulSeries);
        setDataToInputElement(dulSeries, series);
        Allure.addAttachment("Значение", series);
        return this;
    }
    @Step("Установка значения номера паспорта")
    public FormFirstPage setDulNumber(String number)
    {
        clickElementOnPage(dulNumber);
        setDataToInputElement(dulNumber, number);
        Allure.addAttachment("Значение", number);
        return this;
    }

    @Step("Установка органа, выдавшего паспорт")
    public FormFirstPage setGovernmentAgency(String nameAgency)
    {
        setDataToInputElement(governmentAgency, nameAgency);
        Allure.addAttachment("Значение", nameAgency);
        return this;
    }

    @Step("Устаноовка кода подразделения")
    public FormFirstPage setCodeAgency(String codeOfAgency)
    {
        clickElementOnPage(codeOfGavAgency);
        setDataToInputElement(codeOfGavAgency, codeOfAgency);
        Allure.addAttachment("Значение", codeOfAgency);
        return this;
    }

    @Step("Усстановка даты получения паспорта")
    public FormFirstPage setDateOfReceiptDul(String date)
    {
        clickElementOnPage(dateOfReceiptDul);
        setDataToInputElement(dateOfReceiptDul, date);
        Allure.addAttachment("Значение", date);
        return this;
    }

    @Step("Устанвока индекса регистрации")
    public FormFirstPage setZipCode(String index)
    {
        clickElementOnPage(zipCode);
        setDataToInputElement(zipCode, index);
        Allure.addAttachment("Значение", index);
        return this;
    }

    @Step("Установка страны регистрации")
    public FormFirstPage setCountryOfRegistration(String registrationCountryUpperCase)
    {
        clickElementOnPage(registrationCountryDropDown);

        setDataToInputElement(registrationCountryInput, registrationCountryUpperCase);
        clickElementOnPage(By.xpath(".//div[text()='" + registrationCountryUpperCase + "' and @role='option']"));
        Allure.addAttachment("Значение", registrationCountryUpperCase);
        return this;
    }

    @Step("Установка области/региона регистрации")
    public FormFirstPage setStateOfRegistration(String stateValue)
    {
        clickElementOnPage(state);
        setDataToInputElement(state, stateValue);
        Allure.addAttachment("Значение", stateValue);
        return this;
    }

    @Step("Установка города регистрации")
    public FormFirstPage setCityOfregistration(String cityValue)
    {
        clickElementOnPage(city);
        setDataToInputElement(city, cityValue);
        Allure.addAttachment("Значение", cityValue);
        return this;
    }

    @Step("Установка улицы регистрации")
    public FormFirstPage setStreetOfRegistration(String streetValue)
    {
        clickElementOnPage(street);
        setDataToInputElement(street, streetValue);
        Allure.addAttachment("Значение", streetValue);
        return this;
    }

    @Step("Установка дома регистрации")
    public FormFirstPage setHouseOfRegistration(String houseOfRegistrationData)
    {
        clickElementOnPage(house);
        setDataToInputElement(house, houseOfRegistrationData);
        Allure.addAttachment("Значение", houseOfRegistrationData);
        return this;
    }

    @Step("Установка наименование образовательной организации предыдущего места обучения")
    public FormFirstPage setNamingOfEducationOrganization(String educationOrganization)
    {
        clickElementOnPage(namingOfEducationOrganization);
        setDataToInputElement(namingOfEducationOrganization, educationOrganization);
        Allure.addAttachment("Значение", educationOrganization);
        return this;
    }

    @Step("Установка направления подготовки предыдущего места обучени")
    public FormFirstPage setUniversitySpecialty(String specialty)
    {
        clickElementOnPage(universitySpecialty);
        setDataToInputElement(universitySpecialty, specialty);
        Allure.addAttachment("Значение", specialty);
        return this;
    }

    @Step("Устанвока серии и номера диплома")
    public FormFirstPage setDiplomNumberAndSeries(String numberAndSeries)
    {
        clickElementOnPage(diplomNumberAndSeries);
        setDataToInputElement(diplomNumberAndSeries, numberAndSeries);
        Allure.addAttachment("Значение", numberAndSeries);
        return this;
    }

    @Step("Установка даты получения диплома")
    public FormFirstPage setDateOfReceiptDiplom(String date)
    {
        clickElementOnPage(dateOfReceiptDiplom);
        setDataToInputElement(dateOfReceiptDiplom, date);
        Allure.addAttachment("Значение", date);
        return this;
    }

    @Step("Установка года окончяания обучения")
    public FormFirstPage setYearOfEnding(String year)
    {
        clickElementOnPage(yearOfEnding);
        setDataToInputElement(yearOfEnding, year);
        Allure.addAttachment("Значение", year);
        return this;
    }

    @Step("Устанвока места работы")
    public FormFirstPage setPlaceOfWork(String placeOfWorkData)
    {
        clickElementOnPage(placeOfWork);
        setDataToInputElement(placeOfWork, placeOfWorkData);
        Allure.addAttachment("Значение", placeOfWorkData);
        return this;
    }

    @Step("Установка занимаемой должности")
    public FormFirstPage setWorkingPosition(String positiion)
    {
        clickElementOnPage(workingPosition);
        setDataToInputElement(workingPosition, positiion);
        Allure.addAttachment("Значение", positiion);
        return this;
    }

    @Step("Установка периода работы")
    public FormFirstPage setPeriodOfWork(String periodOfWorkData)
    {
        clickElementOnPage(periodOfWork);
        setDataToInputElement(periodOfWork, periodOfWorkData);
        Allure.addAttachment("Значение", periodOfWorkData);
        return this;
    }

    @Step("Проверка видимости ошибки при загрузке файла не того разрешения в место фотографии")
    public boolean isErrorFileTypeTextVisible()
    {
        boolean result = findElementOnPage(errorFileType).isDisplayed();

        String resultToString = result +"";
        Allure.addAttachment("Значение", resultToString);

        return result;
    }

    @Step("Получение текста ошибки при загрузке файла не того разрешения")
    public String getTextOfErrorFileType()
    {
        String result =  getTextOfElement(errorFileType);
        Allure.addAttachment("Значение", result);
        return result;
    }

    @Step("Нажатие на кнопку далее")
    public FormFirstPage clickNextButton()
    {
        clickElementOnPage(NextButton);
        return this;
    }

    @Step("Проверка видимости надписи на второй странице заполнения данных после нажатия кнопки Далее")
    public boolean isTextSecondPageVisible()
    {
        return findElementOnPage(nextPageAsssureance).isDisplayed();
    }

    @Step("Проверка видимости ошибки при ошибочном формате номера")
    public Boolean isErrorFormatPhoneNumberNotificationVisible()
    {
        boolean result = findElementOnPage(errorFormatPhoneNumber).isDisplayed();
        String resultToString = result +"";
        Allure.addAttachment("Значение", resultToString);

        return result;
    }

    @Step("Проверка значения ошибки ошибочный формате номера")
    public String getTextOfErrorFormatPhoneNumber()
    {
        String result =  getTextOfElement(errorFormatPhoneNumber);
        Allure.addAttachment("Значение", result);
        return result;
    }

    @Step("Проверка видимости ошибки при ошибочном коде страны")
    public Boolean isIncorrectPhoneNumberNotificationVisible()
    {
        boolean result = findElementOnPage(incorrectPhoneNumber).isDisplayed();
        String resultToString = result +"";
        Allure.addAttachment("Значение", resultToString);

        return result;
    }

    @Step("Проверка значения ошибки при ошибочном коде страны")
    public String getTextOfIncorrectPhoneNumber()
    {
        String result =  getTextOfElement(incorrectPhoneNumber);
        Allure.addAttachment("Значение", result);
        return result;
    }

}
