package ru.miigaik.action;

import io.restassured.response.Response;
import org.openqa.selenium.WebDriver;
import ru.miigaik.api.AuthApi;
import ru.miigaik.api.model.auth.EmailRequestModel;
import ru.miigaik.api.model.auth.EmailResponseModel;
import ru.miigaik.browser.Browsers;
import ru.miigaik.browser.WebDrivermanagment;
import ru.miigaik.pages.*;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import static ru.miigaik.action.Generator.setConsumersData;
import static ru.miigaik.action.Generator.setEmailToAuthRequest2Var;
import static ru.miigaik.cfg.ConfigurationProject.MAIN_PAGE;


public class PreparedActions
{
    private WebDriver driver;
    private AuthApi authApi;
    private WebDrivermanagment webDrivermanagment;
    private EmailResponseModel emailResponseModel;
    private EmailRequestModel emailRequestModel;
    private String email;
    private String token;
    private AuthrorizatiobPage authrorizatiobPage;
    private FormFirstPage formFirstPage;
    private FormSecondPage formSecondPage;

    public PreparedActions(Browsers browsers)
    {
        authApi = new AuthApi();
        webDrivermanagment = new WebDrivermanagment();
        driver = webDrivermanagment.setDriver(browsers);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public PreparedActions logIn() throws InterruptedException
    {
        authrorizatiobPage = new AuthrorizatiobPage(driver);
        driver.get(MAIN_PAGE);
        emailRequestModel = setEmailToAuthRequest2Var();
        email = emailRequestModel.getEmail();

        authrorizatiobPage.setEmailToField(email)
                .waitAfterEvent(5)
                .clickGetTokenButton()
                .waitAfterEvent(5);

        Response response = authApi.authEmail(emailRequestModel);
        emailResponseModel = response.body().as(EmailResponseModel.class);
        token = emailResponseModel.getDetail();

        authrorizatiobPage.waitAfterEvent(5).setTokenToField(token).waitAfterEvent(5)
                .clickLogInButton().waitAfterEvent(5);
        return this;
    }

    public PreparedActions logInOnModerationPage() throws InterruptedException
    {
        String adminEmail = "admin@ad.min";

        authrorizatiobPage = new AuthrorizatiobPage(driver);
        driver.get(MAIN_PAGE);
        emailRequestModel = setEmailToAuthRequest2Var();
        emailRequestModel.setEmail(adminEmail);


        authrorizatiobPage.setEmailToField(adminEmail)
                .waitAfterEvent(5)
                .clickGetTokenButton()
                .waitAfterEvent(5);

        Response response = authApi.authEmail(emailRequestModel);
        emailResponseModel = response.body().as(EmailResponseModel.class);
        token = emailResponseModel.getDetail();

        authrorizatiobPage.waitAfterEvent(5).setTokenToField(token).waitAfterEvent(5)
                .clickLogInButton().waitAfterEvent(5);
        return this;
    }

    public PreparedActions fillTheFirstForm() throws InterruptedException
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
        return this;
    }

    public PreparedActions fillTheFirstFormRuWithoutSeriesDul() throws InterruptedException
    {
        formFirstPage = new FormFirstPage(driver);
        ConsumerModel consumerModel = setConsumersData();
        int educationProgram = new Random().nextInt(8);
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
                .setEducationProgram(educationProgram)
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
        return this;
    }

    public PreparedActions fillTheFirstFormRuWithoutNumberSnilsAndDate() throws InterruptedException
    {
        formFirstPage = new FormFirstPage(driver);
        ConsumerModel consumerModel = setConsumersData();
        int educationProgram = new Random().nextInt(8);
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
                .setEducationProgram(educationProgram)
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
        return this;
    }

    public PreparedActions fillTheFirstFormRuWithoutCodeAgency() throws InterruptedException
    {
        formFirstPage = new FormFirstPage(driver);
        ConsumerModel consumerModel = setConsumersData();
        int educationProgram = new Random().nextInt(8);
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
                .setEducationProgram(educationProgram)
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
        return this;
    }

    public PreparedActions fillTheFirstFormWithoutAutoPhone() throws InterruptedException
    {
        formFirstPage = new FormFirstPage(driver);
        ConsumerModel consumerModel = setConsumersData();
        int educationProgram = new Random().nextInt(8);
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
                .setEducationProgram(educationProgram)
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
        return this;
    }

    public PreparedActions fillTheFirstFormWithoutAutoBirthDate() throws InterruptedException
    {
        formFirstPage = new FormFirstPage(driver);
        ConsumerModel consumerModel = setConsumersData();
        int educationProgram = new Random().nextInt(8);
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
                .setEducationProgram(educationProgram)
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
        return this;
    }

    public PreparedActions fillMinFormForeignPasport() throws InterruptedException
    {
        formFirstPage = new FormFirstPage(driver);
        ConsumerModel consumerModel = setConsumersData();
        formFirstPage
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
                .setEducationProgram(2);
        return this;
    }

    public PreparedActions fillSecondForm() throws InterruptedException
    {
        formSecondPage = new FormSecondPage(driver);
        formSecondPage
                .uploadIncomingStatement("test.pdf")
                .uploadConsentToTheProcessingOfPersonalData("test.pdf")
                .uploadPersonalList("test.pdf")
                .uploadPasport("test.pdf")
                .uploadDiplom("test.pdf")
                .uploadDocumentAboutChangingFIO("test.pdf")
                .uploadSnils("test.pdf")
                .clickSendButton();

        return this;
    }

    public PreparedActions fillFullForm() throws InterruptedException
    {
        formFirstPage = new FormFirstPage(driver);
        formSecondPage = new FormSecondPage(driver);

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
                .setEducationProgram(0)
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
                .setPeriodOfWork("с 2024")
                .clickNextButton();

        formSecondPage
                .uploadIncomingStatement("test.pdf")
                .uploadConsentToTheProcessingOfPersonalData("test.pdf")
                .uploadPersonalList("test.pdf")
                .uploadPasport("test.pdf")
                .uploadDiplom("test.pdf")
                .uploadDocumentAboutChangingFIO("test.pdf")
                .uploadSnils("test.pdf")
                .clickSendButton();
        return this;
    }

    public PreparedActions logInOnModerationAndOpenFormByEmail() throws InterruptedException
    {
        logIn();
        fillFullForm();
        new FormSecondPage(driver).clickExitButton();
        logInOnModerationPage();
        new ModerationPage(driver).clickFormByEmail(getEmail());
        return this;
    }

    public FormFirstPage getFormFirstPage()
    {
        return formFirstPage;
    }
    public WebDriver getDriver()
    {
        return driver;
    }
    public FormSecondPage getFormSecondPage()
    {
        return formSecondPage;
    }

    public String getEmail()
    {
        return email;
    }
}
