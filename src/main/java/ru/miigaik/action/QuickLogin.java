package ru.miigaik.action;

import io.restassured.response.Response;
import org.openqa.selenium.WebDriver;
import ru.miigaik.api.AuthApi;
import ru.miigaik.api.model.auth.EmailRequestModel;
import ru.miigaik.api.model.auth.EmailResponseModel;
import ru.miigaik.browser.WebDrivermanaagment;
import ru.miigaik.pages.MainPage;

import static ru.miigaik.action.Generator.setEmailToAuthRequest2Var;
import static ru.miigaik.browser.Browsers.YANDEX;
import static ru.miigaik.cfg.ConfigurationProject.MAIN_PAGE;

public class QuickLogin
{
    private WebDriver driver;
    private AuthApi authApi;
    private WebDrivermanaagment webDrivermanaagment;
    private EmailResponseModel emailResponseModel;
    private EmailRequestModel emailRequestModel;
    private String email;
    private String token;
    private MainPage mainPage;


    public QuickLogin()
    {
        authApi = new AuthApi();
        webDrivermanaagment = new WebDrivermanaagment();
        driver = webDrivermanaagment.setDriver(YANDEX);
        mainPage = new MainPage(driver);
    }


    public void quickLogIn()
    {
        driver.get(MAIN_PAGE);
        emailRequestModel = setEmailToAuthRequest2Var();
        email = emailRequestModel.getEmail();

        mainPage.setEmailToField(email).clickGetTokenButton();

        Response response = authApi.authEmail(emailRequestModel);
        emailResponseModel = response.body().as(EmailResponseModel.class);
        token = emailResponseModel.getDetail();
        mainPage.setTokenToField(token).clickLogInButton();
    }

    public WebDriver getDriver()
    {
        return driver;
    }
}
