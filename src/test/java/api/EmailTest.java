package api;
import static org.junit.Assert.*;
import io.qameta.allure.Allure;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import ru.miigaik.api.auth.AuthApi;
import ru.miigaik.api.auth.model.EmailErrorResponseModel;
import ru.miigaik.api.auth.model.EmailRequestModel;
import ru.miigaik.api.auth.model.EmailResponseModel;
import static org.apache.http.HttpStatus.*;
import java.util.ArrayList;
import java.util.List;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static ru.miigaik.generator.Generator.setEmailToAuthRequest;

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
        emailRequestModel = setEmailToAuthRequest();
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
