package ru.miigaik.pages;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.util.List;

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
    private final By pasportRfInDropDown = By.xpath("//*[@id='react-select-7-option-0' and text()='ПАСПОРТ РФ' ]");
    private final By pasportOtherCountryInDropDown = By.xpath(".//div[text()='ПАСПОРТ ДРУГОГО ГОСУДАРСТВА']");
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
    private final By errorAgeLess14 = By.xpath(".//span[text()='Регистрация возможна только для лиц от 14 лет. Исправьте дату своего рождения.']");
    private final By errorThisFieldAreRequired = By.xpath(".//span[text()='Поле обязательно к заполнению']");
    private final By errorSumOfSnilsnumber = By.xpath(".//span[text()='Неверное контрольное число.']");
    private final By ruPfasportInField = By.xpath("//*[@id='root']/form/section[2]/div[1]/div[2]/div/div[1]/div[text()='ПАСПОРТ РФ']");
    public FormFirstPage(WebDriver driver)
    {
        super(driver);
    }


    @Step("Загрузка фото поступающего")
    public FormFirstPage uploadPhoto(String photoName) throws InterruptedException
    {

        findElementOnPage(photoUploadPlace).sendKeys
                (
                new File("./attach/"+photoName).getAbsolutePath()
                );
        waitAfterEvent(1);
        return this;
    }

    @Step("Заполнение полей ФИО")
    public FormFirstPage quickSetName(String nameValue) throws InterruptedException
    {
        setDataToInputElement(surname, nameValue);
        setDataToInputElement(name, nameValue);
        setDataToInputElement(patronymic, nameValue);
        Allure.addAttachment("В поле для имени, фамилии, отчества установленео значение", nameValue);
        waitAfterEvent(1);
        return this;
    }

    @Step("Заполнение поля фамилия")
    public FormFirstPage setSurname(String surnameValue) throws InterruptedException
    {
        setDataToInputElement(surname, surnameValue);
        Allure.addAttachment("Значение",surnameValue);
        waitAfterEvent(1);
        return this;
    }

    @Step("Заполнение поля имя")
    public FormFirstPage setName(String nameValue) throws InterruptedException
    {
        setDataToInputElement(name, nameValue);
        Allure.addAttachment("Значение", nameValue);
        waitAfterEvent(1);
        return this;
    }

    @Step("Заполнение поля отчества")
    public FormFirstPage setPatronymic(String patronymicValue) throws InterruptedException
    {
        setDataToInputElement(patronymic, patronymicValue);
        Allure.addAttachment("Значение", patronymicValue);
        waitAfterEvent(1);
        return this;
    }

    @Step("Выбор мужского пола")
    public FormFirstPage chooseMaleSex() throws InterruptedException
    {
        clickElementOnPage(sexDropDown);
        clickElementOnPage(male);
        waitAfterEvent(1);
        return this;
    }

    @Step("Выбор женского пола")
    public FormFirstPage chooseFemaleSex() throws InterruptedException
    {
        clickElementOnPage(sexDropDown);
        clickElementOnPage(female);
        waitAfterEvent(1);
        return this;
    }

    @Step("Установка даты рождения")
    public FormFirstPage setBirth(String date) throws InterruptedException
    {
        clickElementOnPage(birthData);
        setDataToInputElement(birthData, date);
        Allure.addAttachment("Значение", date);
        waitAfterEvent(1);
        return this;
    }

    @Step("Установка страны рождения")
    public FormFirstPage setBirthPlaceCountryAndCity(String countryInput, String cityInput) throws InterruptedException
    {
        String resultData = countryInput + ", " + cityInput;
        setDataToInputElement(countryAndCity, resultData);
        Allure.addAttachment("Значение", resultData);
        waitAfterEvent(1);
        return this;
    }

    @Step("Установка значения гражданства")
    public FormFirstPage setCitizenship(String citizenshipDataUpperCase) throws InterruptedException
    {

        clickElementOnPage(citizenshipDropDown);

        setDataToInputElement(citizenshipInput, citizenshipDataUpperCase);
        clickElementOnPage(By.xpath(".//div[text()='" + citizenshipDataUpperCase + "' and @role='option']"));
        Allure.addAttachment("Значение", citizenshipDataUpperCase);
        waitAfterEvent(1);
        return this;
    }

    @Step("Установка номера телефона")
    public FormFirstPage setPhoneNumber(String phoneNumberData) throws InterruptedException
    {
        clickElementOnPage(phoneNumber);
        setDataToInputElement(phoneNumber, phoneNumberData);
        Allure.addAttachment("Значение", phoneNumberData);
        waitAfterEvent(1);
        return this;
    }

    @Step("Установка среднего уровня образования")
    public FormFirstPage setMiddleGradeLevel() throws InterruptedException
    {
        clickElementOnPage(gradeLevelDropDown);
        clickElementOnPage(middleGradeLevel);
        waitAfterEvent(1);
        return this;
    }

    @Step("Установка высшего уровня образования")
    public FormFirstPage setHighGradeLevel() throws InterruptedException
    {
        clickElementOnPage(gradeLevelDropDown);
        clickElementOnPage(highGradeLevel);
        waitAfterEvent(1);
        return this;
    }

    @Step("Установка номера СНИЛСа")
    public FormFirstPage setSnils(String snilsValue) throws InterruptedException
    {
        clickElementOnPage(snils);
        setDataToInputElement(snils, snilsValue);
        Allure.addAttachment("Значение", snilsValue);
        waitAfterEvent(1);
        return this;
    }

    @Step("Установка даты регистрации СНИЛСа")
    public FormFirstPage setSnilsRegistrationDate(String date) throws InterruptedException
    {
        clickElementOnPage(snilsRegistrationDate);
        setDataToInputElement(snilsRegistrationDate, date);
        Allure.addAttachment("Значение", date);
        waitAfterEvent(1);
        return this;
    }

    @Step("Установка инвалидности 'Нет'")
    public FormFirstPage setDisabilityNo() throws InterruptedException
    {
        clickElementOnPage(disabilityDropDown);
        clickElementOnPage(disabilityNo);
        waitAfterEvent(1);
        return this;
    }

    @Step("Установка инвалидности 'Да'")
    public FormFirstPage setDisabilityYes() throws InterruptedException
    {
        clickElementOnPage(disabilityDropDown);
        clickElementOnPage(disabilityYes);
        waitAfterEvent(1);
        return this;
    }

    @Step("Выбор программы обучения")
    public FormFirstPage setEducationProgram(int programOrder) throws InterruptedException
    {
        clickElementOnPage(educationProgramDropDown);

        List<WebElement> allProgramFromElement = driver.findElements(educationList);
        String[] allProgram = allProgramFromElement.get(0).getText().split("\n");

        clickElementOnPage(By.xpath(".//div[text()='"+ allProgram[programOrder]+"']"));
        Allure.addAttachment("Выбор программы обучения", allProgram[programOrder]);
        waitAfterEvent(1);
        return this;
    }

    @Step("Установка русского паспорта")
    public FormFirstPage setRussianPasport() throws InterruptedException
    {
        clickElementOnPage(dulDropDown);
        clickElementOnPage(pasportRfInDropDown);
        waitAfterEvent(1);
        return this;
    }

    @Step("Установка паспорта другой страны")
    public FormFirstPage setOtherCountryPasport() throws InterruptedException
    {
        clickElementOnPage(dulDropDown);
        clickElementOnPage(pasportOtherCountryInDropDown);
        waitAfterEvent(1);
        return this;
    }

    @Step("Проверка отсутствия паспорта другой страны при гражданстве рф")
    public boolean isPasportOtherCountryIsAbsentIfCinizenshipRf()
    {
        clickElementOnPage(dulDropDown);
        return elementIsNotDisplayed(pasportOtherCountryInDropDown);

    }



    @Step("Установка значения серии паспорта")
    public FormFirstPage setDulSeries(String series) throws InterruptedException
    {
        clickElementOnPage(dulSeries);
        setDataToInputElement(dulSeries, series);
        Allure.addAttachment("Значение", series);
        waitAfterEvent(1);
        return this;
    }



    @Step("Проверка правильности валидации серии паспорта при гражданстве РФ")
    public boolean checkDulSeriesValidation(String dulSeries) //123456
    {
        String[] numbersOfSeries = dulSeries.split("");
        String refactorDulSeries = numbersOfSeries[0] + numbersOfSeries[1] + " " + numbersOfSeries[2] + numbersOfSeries[3];
        return findElementOnPage(By.xpath("//*[@id='root']/form/section[2]/div[2]/input[@value='" + refactorDulSeries+"']")).isDisplayed();
    }
    @Step("Установка значения номера паспорта")
    public FormFirstPage setDulNumber(String number) throws InterruptedException
    {
        clickElementOnPage(dulNumber);
        setDataToInputElement(dulNumber, number);
        Allure.addAttachment("Значение", number);
        waitAfterEvent(1);
        return this;
    }

    @Step("Установка органа, выдавшего паспорт")
    public FormFirstPage setGovernmentAgency(String nameAgency) throws InterruptedException
    {
        setDataToInputElement(governmentAgency, nameAgency);
        Allure.addAttachment("Значение", nameAgency);
        waitAfterEvent(1);
        return this;
    }

    @Step("Устаноовка кода подразделения")
    public FormFirstPage setCodeAgency(String codeOfAgency) throws InterruptedException
    {
        clickElementOnPage(codeOfGavAgency);
        setDataToInputElement(codeOfGavAgency, codeOfAgency);
        Allure.addAttachment("Значение", codeOfAgency);
        waitAfterEvent(1);
        return this;
    }

    @Step("Усстановка даты получения паспорта")
    public FormFirstPage setDateOfReceiptDul(String date) throws InterruptedException
    {
        clickElementOnPage(dateOfReceiptDul);
        setDataToInputElement(dateOfReceiptDul, date);
        Allure.addAttachment("Значение", date);
        waitAfterEvent(1);
        return this;
    }

    @Step("Устанвока индекса регистрации")
    public FormFirstPage setZipCode(String index) throws InterruptedException
    {
        clickElementOnPage(zipCode);
        setDataToInputElement(zipCode, index);
        Allure.addAttachment("Значение", index);
        waitAfterEvent(1);
        return this;
    }

    @Step("Установка страны регистрации")
    public FormFirstPage setCountryOfRegistration(String registrationCountryUpperCase) throws InterruptedException
    {
        clickElementOnPage(registrationCountryDropDown);

        setDataToInputElement(registrationCountryInput, registrationCountryUpperCase);
        clickElementOnPage(By.xpath(".//div[text()='" + registrationCountryUpperCase + "' and @role='option']"));
        Allure.addAttachment("Значение", registrationCountryUpperCase);
        waitAfterEvent(1);
        return this;
    }

    @Step("Установка области/региона регистрации")
    public FormFirstPage setStateOfRegistration(String stateValue) throws InterruptedException
    {
        clickElementOnPage(state);
        setDataToInputElement(state, stateValue);
        Allure.addAttachment("Значение", stateValue);
        waitAfterEvent(1);
        return this;
    }

    @Step("Установка города регистрации")
    public FormFirstPage setCityOfRegistration(String cityValue) throws InterruptedException
    {
        clickElementOnPage(city);
        setDataToInputElement(city, cityValue);
        Allure.addAttachment("Значение", cityValue);
        waitAfterEvent(1);
        return this;
    }

    @Step("Установка улицы регистрации")
    public FormFirstPage setStreetOfRegistration(String streetValue) throws InterruptedException {
        clickElementOnPage(street);
        setDataToInputElement(street, streetValue);
        Allure.addAttachment("Значение", streetValue);
        waitAfterEvent(1);
        return this;
    }

    @Step("Установка дома регистрации")
    public FormFirstPage setHouseOfRegistration(String houseOfRegistrationData) throws InterruptedException {
        clickElementOnPage(house);
        setDataToInputElement(house, houseOfRegistrationData);
        Allure.addAttachment("Значение", houseOfRegistrationData);
        waitAfterEvent(1);
        return this;
    }

    @Step("Установка наименование образовательной организации предыдущего места обучения")
    public FormFirstPage setNamingOfEducationOrganization(String educationOrganization) throws InterruptedException {
        clickElementOnPage(namingOfEducationOrganization);
        setDataToInputElement(namingOfEducationOrganization, educationOrganization);
        Allure.addAttachment("Значение", educationOrganization);
        waitAfterEvent(1);
        return this;
    }

    @Step("Установка направления подготовки предыдущего места обучени")
    public FormFirstPage setUniversitySpecialty(String specialty) throws InterruptedException {
        clickElementOnPage(universitySpecialty);
        setDataToInputElement(universitySpecialty, specialty);
        Allure.addAttachment("Значение", specialty);
        waitAfterEvent(1);
        return this;
    }

    @Step("Устанвока серии и номера диплома")
    public FormFirstPage setDiplomNumberAndSeries(String numberAndSeries) throws InterruptedException {
        clickElementOnPage(diplomNumberAndSeries);
        setDataToInputElement(diplomNumberAndSeries, numberAndSeries);
        Allure.addAttachment("Значение", numberAndSeries);
        waitAfterEvent(1);
        return this;
    }

    @Step("Установка даты получения диплома")
    public FormFirstPage setDateOfReceiptDiplom(String date) throws InterruptedException {
        clickElementOnPage(dateOfReceiptDiplom);
        setDataToInputElement(dateOfReceiptDiplom, date);
        Allure.addAttachment("Значение", date);
        waitAfterEvent(1);
        return this;
    }

    @Step("Установка года окончяания обучения")
    public FormFirstPage setYearOfEnding(String year) throws InterruptedException {
        clickElementOnPage(yearOfEnding);
        setDataToInputElement(yearOfEnding, year);
        Allure.addAttachment("Значение", year);
        waitAfterEvent(1);
        return this;
    }

    @Step("Устанвока места работы")
    public FormFirstPage setPlaceOfWork(String placeOfWorkData) throws InterruptedException {
        clickElementOnPage(placeOfWork);
        setDataToInputElement(placeOfWork, placeOfWorkData);
        Allure.addAttachment("Значение", placeOfWorkData);
        waitAfterEvent(1);
        return this;
    }

    @Step("Установка занимаемой должности")
    public FormFirstPage setWorkingPosition(String positiion) throws InterruptedException {
        clickElementOnPage(workingPosition);
        setDataToInputElement(workingPosition, positiion);
        Allure.addAttachment("Значение", positiion);
        waitAfterEvent(1);
        return this;
    }

    @Step("Установка периода работы")
    public FormFirstPage setPeriodOfWork(String periodOfWorkData) throws InterruptedException {
        clickElementOnPage(periodOfWork);
        setDataToInputElement(periodOfWork, periodOfWorkData);
        Allure.addAttachment("Значение", periodOfWorkData);
        waitAfterEvent(1);
        return this;
    }


    @Step("Нажатие на кнопку далее")
    public FormFirstPage clickNextButton() throws InterruptedException {
        clickElementOnPage(NextButton);
        waitAfterEvent(1);
        return this;
    }

    @Step("Проверка видимости надписи на второй странице заполнения данных после нажатия кнопки Далее")
    public boolean isTextSecondPageVisible() throws InterruptedException
    {
        waitAfterEvent(1);
        return findElementOnPage(nextPageAsssureance).isDisplayed();
    }



    @Step("Проверка видимости ошибки")
    public boolean isErrorVisibleSpanElement(String error)
    {
        System.out.println(".//span[text()='" + error +"']");
        return findElementOnPage(By.xpath(".//span[text()='" + error +"']")).isDisplayed();

    }

    @Step("Получение текста ошибки")
    public String getErrorTextSpanElement(String error)
    {
        System.out.println(".//span[text()='" + error +"']");
        return findElementOnPage(By.xpath(".//span[text()='" + error +"']")).getText();
    }

    @Step("Проверка заполнения поля паспортом РФ")
    public boolean isPasportVisibleInField()
    {
        return elementIsDisplayed(ruPfasportInField);
    }

}
