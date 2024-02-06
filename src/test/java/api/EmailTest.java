package api;
import io.qameta.allure.Allure;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import ru.miigaik.api.AuthApi;
import ru.miigaik.api.model.auth.EmailErrorResponseModel;
import ru.miigaik.api.model.auth.EmailRequestModel;
import ru.miigaik.api.model.auth.EmailResponseModel;

import java.util.concurrent.TimeUnit;

import static org.apache.http.HttpStatus.*;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static ru.miigaik.generator.Generator.setEmailToAuthRequest1Var;
import static ru.miigaik.generator.Generator.setEmailToAuthRequest2Var;

public class EmailTest
{
    private AuthApi authApi;
    private String email;
    private Response response;
    private EmailRequestModel emailRequestModel;

    @BeforeEach
    public void setUp() 
    {

        authApi = new AuthApi();
        emailRequestModel = setEmailToAuthRequest1Var();
    }


    @Test
    public void checkEmailAuthStatusCodeTest()
    {
        response = authApi.authEmail(emailRequestModel);

        Allure.addAttachment("Проверка статус кода ответа по отправке кода на почту", emailRequestModel.getEmail());
        assertEquals("Статус код ответа не 200",SC_OK, response.statusCode());
    }

    @Test
    public void checkEmailAuthTokenSizeTest()
    {
        response = authApi.authEmail(emailRequestModel);

        Allure.addAttachment("Проверка запроса по отправке кода на почту", emailRequestModel.getEmail());
        EmailResponseModel emailResponseModel = response.body().as(EmailResponseModel.class);


        assertEquals("Длина токена не равна 6",6, emailResponseModel.getDetail().length());
    }

    @ParameterizedTest
    @CsvSource({
            "testData","@ya.ru", "йцу@ya.ru"
    })
    public void checkValidationOfApiStringValueTest(String errorEmails)
    {

        emailRequestModel.setEmail(errorEmails);
        response = authApi.authEmail(emailRequestModel);

        assertEquals("Статус код не 400", SC_BAD_REQUEST,response.statusCode());
    }

    @Test
    public void checkValidationNullValueOfEmailTest()
    {

        emailRequestModel.setEmail(null);
        response = authApi.authEmail(emailRequestModel);

        assertEquals("Статус код не 400", SC_BAD_REQUEST,response.statusCode());
    }

    @Test
    public void checkBadRequestResponseTextTest()
    {
        emailRequestModel.setEmail(null);
        response = authApi.authEmail(emailRequestModel);

        EmailErrorResponseModel emailErrorResponseModel = response.body().as(EmailErrorResponseModel.class);

        assertEquals("Тело ответа на ошибочынй запрос не совпало","Это поле не может быть пустым.", emailErrorResponseModel.getEmail());
    }
}
