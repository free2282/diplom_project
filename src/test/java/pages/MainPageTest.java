package pages;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import ru.miigaik.api.AuthApi;
import ru.miigaik.api.model.auth.EmailRequestModel;
import ru.miigaik.api.model.auth.EmailResponseModel;
import ru.miigaik.browser.Browsers;
import ru.miigaik.browser.WebDrivermanagment;
import ru.miigaik.pages.MainPage;

import static org.junit.Assert.assertTrue;
import static ru.miigaik.browser.Browsers.*;
import static ru.miigaik.cfg.ConfigurationProject.MAIN_PAGE;
import static ru.miigaik.action.Generator.setEmailToAuthRequest2Var;

@RunWith(Parameterized.class)
public class MainPageTest
{
    private MainPage mainPage;
    private AuthApi authApi;
    private EmailRequestModel emailRequestModel;
    private EmailResponseModel emailResponseModel;
    private String email;
    private String token;
    private WebDriver driver;
    private boolean result;
    private Browsers browsers;

    public MainPageTest(Browsers browsers)
    {
        this.browsers = browsers;
    }

    @Parameterized.Parameters
    public static Object[][] getEnterAccount()
    {
        return new Object[][]
                {
                {CHROME},
//                {YANDEX},
//                {FIREFOX},
//                {EDGE}
        };
    }

    @Before
    public void setUp()
    {
        authApi = new AuthApi();

        WebDrivermanagment webDrivermanagment = new WebDrivermanagment();
        Allure.parameter("Проверка на браузере", browsers);
        driver = webDrivermanagment.setDriver(browsers);
        driver.get(MAIN_PAGE);

        mainPage = new MainPage(driver);
        emailRequestModel = setEmailToAuthRequest2Var();
        email = emailRequestModel.getEmail();
    }
    @Test
    @DisplayName("Проверка базовой аунтификации browsers")
    @Description("Проверка базовой аунтификации, ожидание оказаться на странцие заполнения данных, будет доступна кнопка выйти из аккаунта")
    public void checkBaseAuthAfterClickingLogInButtonTest() throws InterruptedException
    {
        Allure.addAttachment("Логин в систему с почтой", email+"");

        mainPage.setEmailToField(email).waitAfterEvent(5)
                .clickGetTokenButton().waitAfterEvent(5);
        Response response = authApi.authEmail(emailRequestModel);
        emailResponseModel = response.body().as(EmailResponseModel.class);
        token = emailResponseModel.getDetail();

        result = mainPage.waitAfterEvent(10).setTokenToField(token).waitAfterEvent(10)
                .clickLogInButton().waitAfterEvent(10)
                .isExitButtonDisplayed();


        assertTrue("Кнопка выйти не была видна, процесс входа не был произведен", result);
    }

    @Test
    @DisplayName("Проверка действия кнопки назад на этапе получения кода")
    @Description("Проверка действия кнопки назад на этапе получения кода, ожидание появление на начальной странцие")
    public void checkBackButtonExpectGetTokenButtonVisTest()
    {
         result = mainPage.setEmailToField(email)
                .clickGetTokenButton()
                 .clickBackButton()
                 .isGetTokenDisplayed();

        assertTrue("Кнопка назад не сработала, кнопка получить код не была видна", result);
    }

    @Test
    @DisplayName("Проверка действия кнопки назад с ожиданием отсутствия кнопки 'Войти'")
    public void checkBackButtonExpectLogInButtonIsNotVisTest()
    {
        result = mainPage.setEmailToField(email)
                .clickGetTokenButton()
                .isLogInIsNotDisplay();

        mainPage.clickBackButton();
        assertTrue("Кнопка назад не сработала, кнопка войти была видна", result );
    }


    @Test
    @DisplayName("Проверка всплывающего уведомления после успешного запроса отправки на почту токена авторизации")
    public void checkSuccessfulEmailInputTest()
    {
        result = mainPage.setEmailToField(email)
                .clickGetTokenButton()
                .isSuccessfulInputEmailNotificationVisible();
        assertTrue("Уведомление об успешном ввооде email не было показано", result);
    }

    @Test
    @DisplayName("Проверка не перехода на следюущий этап после ошибочного ввода email и нажатия войти")
    public void checkGetCodeButtonAfterFailureEmailInputTest()
    {
        email = "error";
        result = mainPage.setEmailToField(email)
                .clickGetTokenButton()
                .isGetTokenDisplayed();

        assertTrue("Кнопку 'Получить код' не было видно, произошел переход на след.шаг", result);
    }

    @Test
    @DisplayName("Проверка уведомления об ошибке при ошибке написания email")
    public void checkNotificationInvalidEmailTest()
    {
        email = "error";
        result = mainPage.setEmailToField(email)
                .clickGetTokenButton()
                .isEmailIsNotValidNotificationDisplayed();
        assertTrue( "Уведомление об не валидном вводе email не было показано",result);
    }

    @Test
    @DisplayName("Проверка текста с просьбой ввести email после попытки отправки пустого поля email")
    public void checkNullNotificationEmailTest()
    {
        email = "";
        result = mainPage.setEmailToField(email)
                .clickGetTokenButton()
                .isEmailNullNotificationDisplayed();
        assertTrue("Уведомление о пустом email не было получено", result);
    }

    @Test
    @DisplayName("Проверка не перехода на следюущий этап после пустого значения в email и нажатия войти")
    public void checkGetCodeButtonAfterNullEmailInputTest() {
        email = "";
        result = mainPage.setEmailToField(email)
                .clickGetTokenButton()
                .isGetTokenDisplayed();

        assertTrue("Кнопку 'Получить код' не было видно, произошел переход на след.шаг", result);
    }

    @Test
    @DisplayName("Проверка уведомления о невалидной длине токена, большей 6 цифр, авторизации")
    public void checkTokenNotValidLengthMore6Test() throws InterruptedException {
        token = "1234567";
        result = mainPage.setEmailToField(email)
                .clickGetTokenButton()
                .setTokenToField(token)
                .clickLogInButton()
                .waitAfterEvent(5)
                .isTokenErrorLengthDisplayed();
        assertTrue("Текст о невалидной длине токена авторизации не был показан", result);
    }

    @Test
    @DisplayName("Проверка уведомления о невалидной длине токена, меньшей 6 цифр, авторизации")
    public void checkTokenNotValidLengthLess6Test() throws InterruptedException {
        token = "12345";
        result = mainPage.setEmailToField(email)
                .clickGetTokenButton()
                .setTokenToField(token)
                .clickLogInButton()
                .waitAfterEvent(5)
                .isTokenErrorLengthDisplayed();
        assertTrue( "Текст о невалидной длине токена авторизации не был показан", result);
    }

    @Test
    @DisplayName("Проверка уведомления о передаче пустой строки для токена авторизации")
    public void checkNotValidNullTest() throws InterruptedException {
        token = "";
        result = mainPage.setEmailToField(email)
                .clickGetTokenButton()
                .setTokenToField(token)
                .clickLogInButton()
                .waitAfterEvent(5)
                .isTokenErrorLengthDisplayed();
        assertTrue("Текст о невалидной длине токена авторизации не был показан", result);
    }

    @Test
    @DisplayName("Проверка уведомления об ошибочном токене авторизации")
    public void checkInvalidTokenTest()
    {
        token = "12345q";
        result = mainPage.setEmailToField(email)
                .clickGetTokenButton()
                .setTokenToField(token)
                .clickLogInButton()
                .isInvalidTokenNotificationDisplayed();
        assertTrue("Уведомление о неуспешном вводе токена не было показано", result);
    }

    @Test
    @DisplayName("Проверка видимости кнопки войти после ввода ошибочного 5ти значения токена и не перехода на следюущий этап")
    public void checkVisibilityLoginButtonAfterInvalidShortTokenTest()
    {
        token = "12345";
        result = mainPage.setEmailToField(email)
                .clickGetTokenButton()
                .setTokenToField(token)
                .clickLogInButton()
                .isLogInButtonDisplayed();
        assertTrue("Кнопка 'Войти' не была видна, был переход на следующий шаг", result);
    }

    @Test
    @DisplayName("Проверка видимости кнопки войти после ввода ошибочного 7ти значения токена и не перехода на следюущий этап")
    public void checkVisibilityLoginButtonAfterInvalidLengthTokenTest()
    {
        token = "1234567";
        result = mainPage.setEmailToField(email)
                .clickGetTokenButton()
                .setTokenToField(token)
                .clickLogInButton()
                .isLogInButtonDisplayed();;
        assertTrue("Кнопка 'Войти' не была видна, был переход на следующий шаг", result);
    }

    @Test
    @DisplayName("Проверка видимости кнопки войти после нажатия кнопки 'Войти' с пустым токеном авторизации не перехода на следюущий этап")
    public void checkVisibilityLoginButtonAfterNullTokenTest()
    {
        token = "";
        result = mainPage.setEmailToField(email)
                .clickGetTokenButton()
                .setTokenToField(token)
                .clickLogInButton()
                .isLogInButtonDisplayed();
        assertTrue("Кнопка 'Войти' не была видна, был переход на следующий шаг", result);
    }

    @Test
    @DisplayName("Проверка видимости кнопки войти после ввода ошибочного значения токена правильной длины и не перехода на следюущий этап")
    public void checkVisibilityLoginButtonAfterInvalidTokenTest()
    {
        token = "";
        result = mainPage.setEmailToField(email)
                .clickGetTokenButton()
                .setTokenToField(token)
                .clickLogInButton()
                .isLogInButtonDisplayed();
        assertTrue("Кнопка 'Войти' не была видна, был переход на следующий шаг", result);
    }

    @After
    public void setDown() throws InterruptedException
    {
        mainPage.waitAfterEvent(1);
        driver.quit();
    }
}
