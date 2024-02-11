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

    public void fillTheForm() throws InterruptedException
    {
        formFirstPage = new FormFirstPage(driver);
        ConsumerModel consumerModel = setConsumersData();
        formFirstPage
                .uploadPhoto("main_icons.png").waitAfterEvent(1)
                .quickSetName(consumerModel.getName()).waitAfterEvent(1)
                .chooseMaleSex().waitAfterEvent(1)
                .setBirth("01.01.2000").waitAfterEvent(1)
                .setCountryAndCity("Россия", "Москва").waitAfterEvent(1)
                .setCitizenship("РОССИЯ").waitAfterEvent(1)
                .setPhoneNumber(consumerModel.getPhoneNumbers()).waitAfterEvent(1)
                .setHighGradeLevel().waitAfterEvent(1)
                .setSnils("178-747-544 36").waitAfterEvent(1)
                .setSnilsRegistrationDate("01.01.2013").waitAfterEvent(1)
                .setDisabilityYes().waitAfterEvent(1)
                .setEducationProgram(1).waitAfterEvent(1)
                .setRussianPasport().waitAfterEvent(1)
                .setDulSeries("46 16").waitAfterEvent(1)
                .setDulNumber("466378").waitAfterEvent(1)
                .setGovernmentAgency("Умвд №3").waitAfterEvent(1)
                .setCodeAgency("915-781").waitAfterEvent(1)
                .setDateOfReceiptDul("01.01.2022").waitAfterEvent(1)
                .setZipCode(consumerModel.getZipCode()).waitAfterEvent(1)
                .setCountryOfRegistration("РОССИЯ").waitAfterEvent(1)
                .setStateOfRegistration(consumerModel.getState()).waitAfterEvent(1)
                .setCityOfregistration(consumerModel.getCity()).waitAfterEvent(1)
                .setStreetOfRegistration(consumerModel.getStreet()).waitAfterEvent(1)
                .setHouseOfRegistration(consumerModel.getHouse()).waitAfterEvent(1)
                .setNamingOfEducationOrganization("МИИГАиК").waitAfterEvent(1)
                .setUniversitySpecialty("Геодез").waitAfterEvent(1)
                .setDiplomNumberAndSeries("46871267").waitAfterEvent(1)
                .setDateOfReceiptDiplom("01.01.2020").waitAfterEvent(1)
                .setYearOfEnding("2020").waitAfterEvent(1)
                .setPlaceOfWork("Завод").waitAfterEvent(1)
                .setWorkingPosition("Геодезист").waitAfterEvent(1)
                .setPeriodOfWork("с 2024").waitAfterEvent(1);
    }

    public void fillTheFormWithoutAutoPhone() throws InterruptedException
    {
        formFirstPage = new FormFirstPage(driver);
        ConsumerModel consumerModel = setConsumersData();
        formFirstPage
                .uploadPhoto("main_icons.png").waitAfterEvent(1)
                .quickSetName(consumerModel.getName()).waitAfterEvent(1)
                .chooseMaleSex().waitAfterEvent(1)
                .setBirth("01.01.2000").waitAfterEvent(1)
                .setCountryAndCity("Россия", "Москва").waitAfterEvent(1)
                .setCitizenship("РОССИЯ").waitAfterEvent(1)
                .setHighGradeLevel().waitAfterEvent(1)
                .setSnils("178-747-544 36").waitAfterEvent(1)
                .setSnilsRegistrationDate("01.01.2013").waitAfterEvent(1)
                .setDisabilityYes().waitAfterEvent(1)
                .setEducationProgram(1).waitAfterEvent(1)
                .setRussianPasport().waitAfterEvent(1)
                .setDulSeries("46 16").waitAfterEvent(1)
                .setDulNumber("466378").waitAfterEvent(1)
                .setGovernmentAgency("Умвд №3").waitAfterEvent(1)
                .setCodeAgency("915-781").waitAfterEvent(1)
                .setDateOfReceiptDul("01.01.2022").waitAfterEvent(1)
                .setZipCode(consumerModel.getZipCode()).waitAfterEvent(1)
                .setCountryOfRegistration("РОССИЯ").waitAfterEvent(1)
                .setStateOfRegistration(consumerModel.getState()).waitAfterEvent(1)
                .setCityOfregistration(consumerModel.getCity()).waitAfterEvent(1)
                .setStreetOfRegistration(consumerModel.getStreet()).waitAfterEvent(1)
                .setHouseOfRegistration(consumerModel.getHouse()).waitAfterEvent(1)
                .setNamingOfEducationOrganization("МИИГАиК").waitAfterEvent(1)
                .setUniversitySpecialty("Геодез").waitAfterEvent(1)
                .setDiplomNumberAndSeries("46871267").waitAfterEvent(1)
                .setDateOfReceiptDiplom("01.01.2020").waitAfterEvent(1)
                .setYearOfEnding("2020").waitAfterEvent(1)
                .setPlaceOfWork("Завод").waitAfterEvent(1)
                .setWorkingPosition("Геодезист").waitAfterEvent(1)
                .setPeriodOfWork("с 2024").waitAfterEvent(1);
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
