package pages;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import ru.miigaik.api.AuthApi;
import ru.miigaik.api.model.auth.EmailRequestModel;
import ru.miigaik.api.model.auth.EmailResponseModel;
import ru.miigaik.browser.WebDrivermanaagment;
import ru.miigaik.pages.MainPage;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;
import static ru.miigaik.action.Generator.setEmailToAuthRequest1Var;
import static ru.miigaik.browser.Browsers.CHROME;
import static ru.miigaik.browser.Browsers.YANDEX;
import static ru.miigaik.cfg.ConfigurationProject.MAIN_PAGE;
import static ru.miigaik.action.Generator.setEmailToAuthRequest2Var;

public class TestMainPage
{
    private MainPage mainPage;
    private AuthApi authApi;
    private EmailRequestModel emailRequestModel;
    private EmailResponseModel emailResponseModel;
    private String email;
    private String token;
    private WebDriver driver;
    private boolean result;

    @BeforeEach
    public void setUp()
    {
        authApi = new AuthApi();

        WebDrivermanaagment webDrivermanaagment = new WebDrivermanaagment();
        driver = webDrivermanaagment.setDriver(CHROME);

        driver.get(MAIN_PAGE);

        mainPage = new MainPage(driver);
        emailRequestModel = setEmailToAuthRequest2Var();
        email = emailRequestModel.getEmail();
    }


    @Test
    public void checkBaseAuthAfterClickingLogInButtonTest() throws InterruptedException
    {
        TimeUnit.SECONDS.sleep(10);

        mainPage.setEmailToField(email)
                .clickGetTokenButton();
        TimeUnit.SECONDS.sleep(10);
        Response response = authApi.authEmail(emailRequestModel);
        TimeUnit.SECONDS.sleep(10);
        emailResponseModel = response.body().as(EmailResponseModel.class);
        TimeUnit.SECONDS.sleep(10);
        token = emailResponseModel.getDetail();
        TimeUnit.SECONDS.sleep(10);
        result = mainPage.setTokenToField(token)
                .clickLogInButton()
                .isExitButtonDisplayed();


        assertTrue("Кнопка выйти не была видна, процесс входа не был произведен", result);
    }

    @Test
    public void checkBackButtonExpectGetTokenButtonVisTest()
    {
        System.out.println(2);
         result = mainPage.setEmailToField(email)
                .clickGetTokenButton()
                 .clickBackButton()
                 .isGetTokenDisplayed();

        assertTrue("Кнопка назад не сработала, кнопка получить код не была видна", result);
    }

    @Test
    public void checkBackButtonExpectLogInButtonIsNotVisTest()
    {
        System.out.println(3);
        result = mainPage.setEmailToField(email)
                .clickGetTokenButton()
                .isLogInIsNotDisplayAfterClickBackButton();

        mainPage.clickBackButton();
        assertTrue("Кнопка назад не сработала, кнопка войти была видна", result );
    }

    @Test
    public void checkSuccessfulEmailInputTest()
    {
        System.out.println(4);
        result = mainPage.setEmailToField(email)
                .clickGetTokenButton()
                .isSuccessfulInputEmailNotificationVisible();
        assertTrue("Уведомление об успешном ввооде email не было показано", result);
    }

    @Test
    public void checkInvalidEmailTest()
    {
        System.out.println(5);
        email = "error";
        result = mainPage.setEmailToField(email)
                .clickGetTokenButton()
                .isEmailIsNotValidNotificationDisplayed();
        assertTrue( "Уведомление об не валидном ввооде email не было показано",result);
    }

    @Test
    public void checkNullEmailTest()
    {
        System.out.println(6);
        email = "";
        result = mainPage.setEmailToField(email)
                .clickGetTokenButton()
                .isEmailNullNotificationDisplayed();
        assertTrue("Уведомление о пустом email не было получено", result);
    }

    @Test
    public void checkNotValidLengthMore6Test()
    {
        System.out.println(7);
        token = "1234567";
        result = mainPage.setEmailToField(email)
                .clickGetTokenButton()
                .setTokenToField(token)
                .clickLogInButton()
                .isTokenErrorLengthDisplayed();
        assertTrue("Текст о невалидной длине токена авторизации не был показан", result);
    }

    @Test
    public void checkNotValidLengthLess6Test()
    {
        System.out.println(8);
        token = "12345";
        result = mainPage.setEmailToField(email)
                .clickGetTokenButton()
                .setTokenToField(token)
                .clickLogInButton()
                .isTokenErrorLengthDisplayed();
        assertTrue( "Текст о невалидной длине токена авторизации не был показан", result);
    }

    @Test
    public void checkNotValidNullTest()
    {
        System.out.println(9);
        token = "";
        result = mainPage.setEmailToField(email)
                .clickGetTokenButton()
                .setTokenToField(token)
                .clickLogInButton()
                .isTokenErrorLengthDisplayed();
        assertTrue("Текст о невалидной длине токена авторизации не был показан", result);
    }

    @Test
    public void checkInvalidTokenTest()
    {
        System.out.println(10);
        token = "12345q";
        result = mainPage.setEmailToField(email)
                .clickGetTokenButton()
                .setTokenToField(token)
                .clickLogInButton()
                .isInvalidTokenNotificationDisplayed();
        assertTrue("Уведомление о неуспешном вводе токена не было показано", result);
    }


    @AfterEach
    public void setDown() throws InterruptedException
    {
        TimeUnit.SECONDS.sleep(1);
        driver.quit();
    }
}
