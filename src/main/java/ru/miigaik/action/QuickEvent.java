package ru.miigaik.action;

import io.restassured.response.Response;
import org.openqa.selenium.WebDriver;
import ru.miigaik.api.AuthApi;
import ru.miigaik.api.model.auth.EmailRequestModel;
import ru.miigaik.api.model.auth.EmailResponseModel;
import ru.miigaik.browser.Browsers;
import ru.miigaik.browser.WebDrivermanagment;
import ru.miigaik.pages.ConsumerModel;
import ru.miigaik.pages.FormFirstPage;
import ru.miigaik.pages.MainPage;

import static ru.miigaik.action.Generator.setConsumersData;
import static ru.miigaik.action.Generator.setEmailToAuthRequest2Var;
import static ru.miigaik.cfg.ConfigurationProject.MAIN_PAGE;


public class QuickEvent
{
    private WebDriver driver;
    private AuthApi authApi;
    private WebDrivermanagment webDrivermanagment;
    private EmailResponseModel emailResponseModel;
    private EmailRequestModel emailRequestModel;
    private String email;
    private String token;
    private MainPage mainPage;
    private FormFirstPage formFirstPage;


    public void logIn(Browsers browsers) throws InterruptedException
    {
        authApi = new AuthApi();
        webDrivermanagment = new WebDrivermanagment();
        driver = webDrivermanagment.setDriver(browsers);
        mainPage = new MainPage(driver);
        driver.get(MAIN_PAGE);
        emailRequestModel = setEmailToAuthRequest2Var();
        email = emailRequestModel.getEmail();

        mainPage.setEmailToField(email).waitAfterEvent(5)
                .clickGetTokenButton().waitAfterEvent(5);

        Response response = authApi.authEmail(emailRequestModel);
        emailResponseModel = response.body().as(EmailResponseModel.class);
        token = emailResponseModel.getDetail();

        mainPage.waitAfterEvent(10).setTokenToField(token).waitAfterEvent(10)
                .clickLogInButton().waitAfterEvent(10)
                .isExitButtonDisplayed();
    }

    public void fillTheFirstForm() throws InterruptedException
    {
        formFirstPage = new FormFirstPage(driver);
        ConsumerModel consumerModel = setConsumersData();
        formFirstPage
                .uploadPhoto("main_icons.png")
                .quickSetName(consumerModel.getName())
                .chooseMaleSex()
                .setBirth("01.01.2000")
                .setBirthPlaceCountryAndCity("Россия", "Москва")
                .setCitizenship("РОССИЯ")
                .setPhoneNumber(consumerModel.getPhoneNumbers())
                .setHighGradeLevel()
                .setSnils("178-747-544 36")
                .setSnilsRegistrationDate("01.01.2013")
                .setDisabilityYes()
                .setEducationProgram(1)
                .setRussianPasport()
                .setDulSeries("46 16")
                .setDulNumber("466378")
                .setGovernmentAgency("Умвд №3")
                .setCodeAgency("915-781")
                .setDateOfReceiptDul("01.01.2022")
                .setZipCode(consumerModel.getZipCode())
                .setCountryOfRegistration("РОССИЯ")
                .setStateOfRegistration(consumerModel.getState())
                .setCityOfRegistration(consumerModel.getCity())
                .setStreetOfRegistration(consumerModel.getStreet())
                .setHouseOfRegistration(consumerModel.getHouse())
                .setNamingOfEducationOrganization("МИИГАиК")
                .setUniversitySpecialty("Геодез")
                .setDiplomNumberAndSeries("46871267")
                .setDateOfReceiptDiplom("01.01.2020")
                .setYearOfEnding("2020")
                .setPlaceOfWork("Завод")
                .setWorkingPosition("Геодезист")
                .setPeriodOfWork("с 2024");
    }

    public void fillTheFirstFormRuWithoutSeriesDul() throws InterruptedException
    {
        formFirstPage = new FormFirstPage(driver);
        ConsumerModel consumerModel = setConsumersData();
        formFirstPage
                .uploadPhoto("main_icons.png")
                .quickSetName(consumerModel.getName())
                .chooseMaleSex()
                .setBirth("01.01.2000")
                .setBirthPlaceCountryAndCity("Россия", "Москва")
                .setCitizenship("РОССИЯ")
                .setPhoneNumber(consumerModel.getPhoneNumbers())
                .setHighGradeLevel()
                .setSnils("178-747-544 36")
                .setSnilsRegistrationDate("01.01.2013")
                .setDisabilityYes()
                .setEducationProgram(1)
                .setRussianPasport()
                .setDulNumber("466378")
                .setGovernmentAgency("Умвд №3")
                .setCodeAgency("915-781")
                .setDateOfReceiptDul("01.01.2022")
                .setZipCode(consumerModel.getZipCode())
                .setCountryOfRegistration("РОССИЯ")
                .setStateOfRegistration(consumerModel.getState())
                .setCityOfRegistration(consumerModel.getCity())
                .setStreetOfRegistration(consumerModel.getStreet())
                .setHouseOfRegistration(consumerModel.getHouse())
                .setNamingOfEducationOrganization("МИИГАиК")
                .setUniversitySpecialty("Геодез")
                .setDiplomNumberAndSeries("46871267")
                .setDateOfReceiptDiplom("01.01.2020")
                .setYearOfEnding("2020")
                .setPlaceOfWork("Завод")
                .setWorkingPosition("Геодезист")
                .setPeriodOfWork("с 2024");
    }

    public void fillTheFirstFormRuWithoutNumberSnilsAndDate() throws InterruptedException
    {
        formFirstPage = new FormFirstPage(driver);
        ConsumerModel consumerModel = setConsumersData();
        formFirstPage
                .uploadPhoto("main_icons.png")
                .quickSetName(consumerModel.getName())
                .chooseMaleSex()
                .setBirth("01.01.2000")
                .setBirthPlaceCountryAndCity("Россия", "Москва")
                .setCitizenship("РОССИЯ")
                .setPhoneNumber(consumerModel.getPhoneNumbers())
                .setHighGradeLevel()
                .setDisabilityYes()
                .setEducationProgram(1)
                .setRussianPasport()
                .setDulSeries("4717")
                .setDulNumber("466378")
                .setGovernmentAgency("Умвд №3")
                .setCodeAgency("915-781")
                .setDateOfReceiptDul("01.01.2022")
                .setZipCode(consumerModel.getZipCode())
                .setCountryOfRegistration("РОССИЯ")
                .setStateOfRegistration(consumerModel.getState())
                .setCityOfRegistration(consumerModel.getCity())
                .setStreetOfRegistration(consumerModel.getStreet())
                .setHouseOfRegistration(consumerModel.getHouse())
                .setNamingOfEducationOrganization("МИИГАиК")
                .setUniversitySpecialty("Геодез")
                .setDiplomNumberAndSeries("46871267")
                .setDateOfReceiptDiplom("01.01.2020")
                .setYearOfEnding("2020")
                .setPlaceOfWork("Завод")
                .setWorkingPosition("Геодезист")
                .setPeriodOfWork("с 2024");
    }

    public void fillTheFirstFormRuWithoutCodeAgency() throws InterruptedException
    {
        formFirstPage = new FormFirstPage(driver);
        ConsumerModel consumerModel = setConsumersData();
        formFirstPage
                .uploadPhoto("main_icons.png")
                .quickSetName(consumerModel.getName())
                .chooseMaleSex()
                .setBirth("01.01.2000")
                .setBirthPlaceCountryAndCity("Россия", "Москва")
                .setCitizenship("РОССИЯ")
                .setSnils("178-747-544 36")
                .setSnilsRegistrationDate("01.01.2013")
                .setPhoneNumber(consumerModel.getPhoneNumbers())
                .setHighGradeLevel()
                .setDisabilityYes()
                .setEducationProgram(1)
                .setRussianPasport()
                .setDulSeries("4717")
                .setDulNumber("466378")
                .setGovernmentAgency("Умвд №3")
                .setDateOfReceiptDul("01.01.2022")
                .setZipCode(consumerModel.getZipCode())
                .setCountryOfRegistration("РОССИЯ")
                .setStateOfRegistration(consumerModel.getState())
                .setCityOfRegistration(consumerModel.getCity())
                .setStreetOfRegistration(consumerModel.getStreet())
                .setHouseOfRegistration(consumerModel.getHouse())
                .setNamingOfEducationOrganization("МИИГАиК")
                .setUniversitySpecialty("Геодез")
                .setDiplomNumberAndSeries("46871267")
                .setDateOfReceiptDiplom("01.01.2020")
                .setYearOfEnding("2020")
                .setPlaceOfWork("Завод")
                .setWorkingPosition("Геодезист")
                .setPeriodOfWork("с 2024");
    }

    public void fillTheFirstFormWithoutAutoPhone() throws InterruptedException
    {
        formFirstPage = new FormFirstPage(driver);
        ConsumerModel consumerModel = setConsumersData();
        formFirstPage
                .uploadPhoto("main_icons.png")
                .quickSetName(consumerModel.getName())
                .chooseMaleSex()
                .setBirth("01.01.2000")
                .setBirthPlaceCountryAndCity("Россия", "Москва")
                .setCitizenship("РОССИЯ")
                .setHighGradeLevel()
                .setSnils("178-747-544 36")
                .setSnilsRegistrationDate("01.01.2013")
                .setDisabilityYes()
                .setEducationProgram(1)
                .setRussianPasport()
                .setDulSeries("46 16")
                .setDulNumber("466378")
                .setGovernmentAgency("Умвд №3")
                .setCodeAgency("915-781")
                .setDateOfReceiptDul("01.01.2022")
                .setZipCode(consumerModel.getZipCode())
                .setCountryOfRegistration("РОССИЯ")
                .setStateOfRegistration(consumerModel.getState())
                .setCityOfRegistration(consumerModel.getCity())
                .setStreetOfRegistration(consumerModel.getStreet())
                .setHouseOfRegistration(consumerModel.getHouse())
                .setNamingOfEducationOrganization("МИИГАиК")
                .setUniversitySpecialty("Геодез")
                .setDiplomNumberAndSeries("46871267")
                .setDateOfReceiptDiplom("01.01.2020")
                .setYearOfEnding("2020")
                .setPlaceOfWork("Завод")
                .setWorkingPosition("Геодезист")
                .setPeriodOfWork("с 2024");
    }

    public void fillTheFirstFormWithoutAutoBirthDate() throws InterruptedException
    {
        formFirstPage = new FormFirstPage(driver);
        ConsumerModel consumerModel = setConsumersData();
        formFirstPage
                .uploadPhoto("main_icons.png")
                .quickSetName(consumerModel.getName())
                .chooseMaleSex()
                .setBirthPlaceCountryAndCity("Россия", "Москва")
                .setCitizenship("РОССИЯ")
                .setPhoneNumber(consumerModel.getPhoneNumbers())
                .setHighGradeLevel()
                .setSnils("178-747-544 36")
                .setSnilsRegistrationDate("01.01.2013")
                .setDisabilityYes()
                .setEducationProgram(1)
                .setRussianPasport()
                .setDulSeries("46 16")
                .setDulNumber("466378")
                .setGovernmentAgency("Умвд №3")
                .setCodeAgency("915-781")
                .setDateOfReceiptDul("01.01.2022")
                .setZipCode(consumerModel.getZipCode())
                .setCountryOfRegistration("РОССИЯ")
                .setStateOfRegistration(consumerModel.getState())
                .setCityOfRegistration(consumerModel.getCity())
                .setStreetOfRegistration(consumerModel.getStreet())
                .setHouseOfRegistration(consumerModel.getHouse())
                .setNamingOfEducationOrganization("МИИГАиК")
                .setUniversitySpecialty("Геодез")
                .setDiplomNumberAndSeries("46871267")
                .setDateOfReceiptDiplom("01.01.2020")
                .setYearOfEnding("2020")
                .setPlaceOfWork("Завод")
                .setWorkingPosition("Геодезист")
                .setPeriodOfWork("с 2024");
    }

    public void fillMinFormForeignPasport() throws InterruptedException
    {
        FormFirstPage form = new FormFirstPage(driver);
        ConsumerModel consumerModel = setConsumersData();
        form
                .setSurname(consumerModel.getName())
                .setName(consumerModel.getName())
                .chooseMaleSex()
                .setCitizenship("КАЗАХСТАН")
                .setBirthPlaceCountryAndCity("Казахстан","Павлодар")
                .setBirth("01.01.2000")
                .setOtherCountryPasport()
                .setDulNumber("467817")
                .setGovernmentAgency("Казахстан МВД")
                .setDateOfReceiptDul("01.01.2022")
                .setZipCode("915627")
                .setCountryOfRegistration("КАЗАХСТАН")
                .setCityOfRegistration("Павлодар")
                .setStreetOfRegistration("Ленина 42")
                .setHouseOfRegistration("4")
                .setPhoneNumber(consumerModel.getPhoneNumbers())
                .setMiddleGradeLevel()
                .setNamingOfEducationOrganization("МИИГАиК")
                .setUniversitySpecialty("Геодезист")
                .setDiplomNumberAndSeries("12412331243")
                .setDateOfReceiptDiplom("01.01.2020")
                .setYearOfEnding("2020")
                .setPlaceOfWork("ТК НТК 'Трубопровод'")
                .setWorkingPosition("Геодезитст")
                .setDisabilityNo()
                .setEducationProgram(2)
                .clickNextButton().isTextSecondPageVisible();
    }
    public FormFirstPage getFormFirstPage()
    {
        return formFirstPage;
    }
    public WebDriver getDriver()
    {
        return driver;
    }
}
