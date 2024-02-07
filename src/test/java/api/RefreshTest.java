package api;

import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.miigaik.api.AuthApi;
import ru.miigaik.api.model.auth.EmailRequestModel;
import ru.miigaik.api.model.auth.EmailResponseModel;
import ru.miigaik.api.model.refresh.RefreshErrorResponseModel;
import ru.miigaik.api.model.refresh.RefreshRequestModel;
import ru.miigaik.api.model.refresh.RefreshResponseModel;
import ru.miigaik.api.model.token.TokenRequestModel;
import ru.miigaik.api.model.token.TokenResponseModel;

import java.util.concurrent.TimeUnit;

import static org.apache.http.HttpStatus.SC_OK;
import static org.apache.http.HttpStatus.SC_UNAUTHORIZED;
import static org.junit.Assert.assertEquals;

import static ru.miigaik.generator.Generator.setEmailToAuthRequest1Var;
import static ru.miigaik.generator.Generator.setEmailToAuthRequest2Var;

public class RefreshTest
{
    private AuthApi authApi;
    private EmailRequestModel emailRequestModel;
    private Response response;
    private EmailResponseModel emailResponseModel;
    private RefreshRequestModel refreshRequestModel;
    private TokenRequestModel tokenRequestModel;
    private TokenResponseModel tokenResponseModel;
    @BeforeEach
    public void setUp()
    {
        authApi = new AuthApi();

        emailRequestModel = setEmailToAuthRequest1Var();
        System.out.println(emailRequestModel.getEmail());
        response = authApi.authEmail(emailRequestModel);
        emailResponseModel = response.body().as(EmailResponseModel.class);

        tokenRequestModel = new TokenRequestModel(
                emailRequestModel.getEmail(), emailResponseModel.getDetail()
        );
        response = authApi.authToken(tokenRequestModel);

        tokenResponseModel = response.body().as(TokenResponseModel.class);
    }

    @Test
    public void check200StatusCodeRefreshTokenTest()
    {
        refreshRequestModel = new RefreshRequestModel(tokenResponseModel.getRefresh());
        response = authApi.refreshToken(refreshRequestModel);

        assertEquals("Статус код не 200", SC_OK, response.statusCode());
    }

    @Test
    public void checkNegative401StatusCodeRefreshToken()
    {
        refreshRequestModel = new RefreshRequestModel("error refresh");
        response = authApi.refreshToken(refreshRequestModel);

//        RefreshErrorResponseModel refreshErrorResponseModel = response.body().as(RefreshErrorResponseModel.class);
        assertEquals("Статус код не 401", SC_UNAUTHORIZED, response.statusCode());
    }

    @Test
    public void checkNegativeResponseBodyDetailTest()
    {
        refreshRequestModel = new RefreshRequestModel("error refresh");
        response = authApi.refreshToken(refreshRequestModel);

        RefreshErrorResponseModel refreshErrorResponseModel = response.body().as(RefreshErrorResponseModel.class);
        assertEquals(
                "Текст detail ответа на ошибку не совпал",
                "Токен недействителен или просрочен",
                refreshErrorResponseModel.getDetail());
    }

    @Test
    public void checkNegativeResponseBodyCodeTest()
    {
        refreshRequestModel = new RefreshRequestModel("error refresh");
        response = authApi.refreshToken(refreshRequestModel);

        RefreshErrorResponseModel refreshErrorResponseModel = response.body().as(RefreshErrorResponseModel.class);
        assertEquals(
                "Текст code ответа на ошибку не совпал",
                "token_not_valid",
                refreshErrorResponseModel.getCode());
    }

    @Test
    public void checkNegativeResponseBodyNullRefreshTest()
    {
        refreshRequestModel = new RefreshRequestModel(null);
        response = authApi.refreshToken(refreshRequestModel);

        RefreshErrorResponseModel refreshErrorResponseModel = response.body().as(RefreshErrorResponseModel.class);
        assertEquals(
                "Текст ответа на пустой refresh не совпал",
                "Это поле не может быть пустым.",
                refreshErrorResponseModel.getRefresh());
    }


}
