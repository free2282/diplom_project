package api;
import static org.junit.Assert.*;
import io.qameta.allure.Allure;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.miigaik.api.auth.AuthApi;
import ru.miigaik.api.auth.model.EmailErrorResponseModel;
import ru.miigaik.api.auth.model.EmailRequestModel;
import ru.miigaik.api.auth.model.EmailResponseModel;
import static org.apache.http.HttpStatus.*;
import java.util.ArrayList;
import java.util.List;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

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
        email = "qwe@ya.ru"; // добавить генерацию email
        emailRequestModel = new EmailRequestModel(email);
    }


    @Test
    public void checkEmailAuthStatusCodeTest()
    {
        response = authApi.authEmail(emailRequestModel);

        Allure.addAttachment("Проверка запросооа по отправке кода на почту", email);
        EmailResponseModel emailResponseModel = response.body().as(EmailResponseModel.class);
        assertEquals(SC_OK, response.statusCode());
    }

    @Test
    public void checkEmailAuthBodyTest()
    {
        response = authApi.authEmail(emailRequestModel);

        Allure.addAttachment("Проверка запросооа по отправке кода на почту", email);
        EmailResponseModel emailResponseModel = response.body().as(EmailResponseModel.class);
        assertEquals("Код для входа был отправлен вам на email.", emailResponseModel.getDetail());
    }

    @Test
    public void checkNegativeEmailAuthStatusCodeTest()
    {
        email = "hardcode_error_value_for_test";
        emailRequestModel.setEmail(email);
        response = authApi.authEmail(emailRequestModel);

        assertEquals(SC_BAD_REQUEST, response.statusCode()); // 400 или 429?
    }

    @Test
    public void checkNegativeEmailAuthBodyTest()
    {
        email = "hardcode_error_value_for_test";
        emailRequestModel.setEmail(email);
        response = authApi.authEmail(emailRequestModel);

        EmailErrorResponseModel emailErrorResponseModel = response.body().as(EmailErrorResponseModel.class);
        Allure.addAttachment("Проверка запросооа по отправке кода на почту", email);

        List<String> expecting = new ArrayList<>();
        expecting.add("Введите правильный адрес электронной почты.");

        assertEquals(expecting,
                emailErrorResponseModel.getEmail());
    }
}
