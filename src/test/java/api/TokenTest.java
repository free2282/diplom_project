package api;

import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import ru.miigaik.api.AuthApi;
import ru.miigaik.api.model.auth.EmailRequestModel;
import ru.miigaik.api.model.auth.EmailResponseModel;
import ru.miigaik.api.model.token.TokenErrorResponseModel;
import ru.miigaik.api.model.token.TokenRequestModel;

import static org.apache.http.HttpStatus.SC_BAD_REQUEST;
import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.Assert.assertEquals;
import static ru.miigaik.action.Generator.setEmailToAuthRequest2Var;

public class TokenTest
{
    private AuthApi authApi;
    private EmailRequestModel emailRequestModel;
    private EmailResponseModel emailResponseModel;
    private TokenRequestModel tokenRequestModel;
    private Response response;
    private String emailToken;
    private String email;

    @Before
    public void setUp()
    {
        authApi = new AuthApi();

        emailRequestModel = setEmailToAuthRequest2Var();
        System.out.println(emailRequestModel.getEmail());
        response = authApi.authEmail(emailRequestModel);
        emailResponseModel = response.body().as(EmailResponseModel.class);

        email = emailRequestModel.getEmail();
        emailToken = emailResponseModel.getDetail();
    }

    @Test
    public void check200StatusCodeGettingRefreshAndAccessTokenTest()
    {
        tokenRequestModel = new TokenRequestModel(email, emailToken);
        response = authApi.authToken(tokenRequestModel);

        assertEquals("статус код не 200", SC_OK, response.statusCode());
    }

    @Test
    public void checkNegativeErrorEmailStatusCodeTest()
    {
        email = "wqe";
        tokenRequestModel = new TokenRequestModel(email, emailToken);

        response = authApi.authToken(tokenRequestModel);
        assertEquals("статус код не 400", SC_BAD_REQUEST, response.statusCode());
    }

    @Test
    public void checkNegativeNullEmailStatusCodeTest()
    {
        email = null;
        tokenRequestModel = new TokenRequestModel(email, emailToken);

        response = authApi.authToken(tokenRequestModel);
        assertEquals("статус код не 400", SC_BAD_REQUEST, response.statusCode());
    }

    @Test
    public void checkNegativeErrorTokenStatusCodeTest()
    {
        emailToken = "qwe";
        tokenRequestModel = new TokenRequestModel(email, emailToken);

        response = authApi.authToken(tokenRequestModel);
        assertEquals("статус код не 400", SC_BAD_REQUEST, response.statusCode());
    }

    @Test
    public void checkNegativeNullTokenStatusCodeTest()
    {
        emailToken = null;
        tokenRequestModel = new TokenRequestModel(email, emailToken);

        response = authApi.authToken(tokenRequestModel);
        assertEquals("статус код не 400", SC_BAD_REQUEST, response.statusCode());
    }

    @Test
    public void checkNegativeErrorEmailResponseCodeTest()
    {
        email = "wqe";
        tokenRequestModel = new TokenRequestModel(email, emailToken);

        response = authApi.authToken(tokenRequestModel);
        TokenErrorResponseModel tokenErrorResponseModel = response.body().as(TokenErrorResponseModel.class);
        assertEquals("Ответ ошибки не совпал",
                "Введите правильный адрес электронной почты.",
                tokenErrorResponseModel.getEmail());
    }

    @Test
    public void checkNegativeNullEmailResponseTest()
    {
        email = null;
        tokenRequestModel = new TokenRequestModel(email, emailToken);

        response = authApi.authToken(tokenRequestModel);
        TokenErrorResponseModel tokenErrorResponseModel = response.body().as(TokenErrorResponseModel.class);
        assertEquals("Ответ ошибки не совпал",
                "Это поле не может быть пустым.",
                tokenErrorResponseModel.getEmail());
    }

    @Test
    public void checkNegativeErrorTokenResponseTest()
    {
        emailToken = "qqqqqq";
        tokenRequestModel = new TokenRequestModel(email, emailToken);

        response = authApi.authToken(tokenRequestModel);
        TokenErrorResponseModel tokenErrorResponseModel = response.body().as(TokenErrorResponseModel.class);
        assertEquals("Ответ ошибки не совпал",
                "Получен неверный токен.",
                tokenErrorResponseModel.getToken(0));
    }

    @Test
    public void checkNegativeNullTokenResponseTest()
    {
        emailToken = null;
        tokenRequestModel = new TokenRequestModel(email, emailToken);

        response = authApi.authToken(tokenRequestModel);
        TokenErrorResponseModel tokenErrorResponseModel = response.body().as(TokenErrorResponseModel.class);
        assertEquals("Ответ ошибки не совпал",
                "Это поле не может быть пустым.",
                tokenErrorResponseModel.getToken(0));
    }

    @Test
    public void checkNegativeErrorLengthLess6TokenResponseTest()
    {
        emailToken = "12345";
        tokenRequestModel = new TokenRequestModel(email, emailToken);

        response = authApi.authToken(tokenRequestModel);
        TokenErrorResponseModel tokenErrorResponseModel = response.body().as(TokenErrorResponseModel.class);
        assertEquals("Ответ ошибки не совпал",
                "Получен неверный токен.",
                tokenErrorResponseModel.getToken(0));
        assertEquals("Ответ ошибки не совпал",
                "Минимальная длина токена равна 6.",
                tokenErrorResponseModel.getToken(1));
    }

    @Test
    public void checkNegativeErrorLengthMore6TokenResponseTest()
    {
        emailToken = "1234567";
        tokenRequestModel = new TokenRequestModel(email, emailToken);

        response = authApi.authToken(tokenRequestModel);
        TokenErrorResponseModel tokenErrorResponseModel = response.body().as(TokenErrorResponseModel.class);
        assertEquals("Ответ ошибки не совпал",
                "Получен неверный токен.",
                tokenErrorResponseModel.getToken(0));
        assertEquals("Ответ ошибки не совпал",
                "Максимальная длина токена равна 6.",
                tokenErrorResponseModel.getToken(1));
    }
}
