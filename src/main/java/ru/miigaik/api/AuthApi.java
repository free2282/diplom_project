package ru.miigaik.api;
import io.qameta.allure.Allure;
import io.restassured.response.Response;
import io.qameta.allure.Step;
import ru.miigaik.api.model.auth.EmailRequestModel;
import ru.miigaik.api.model.refresh.RefreshRequestModel;
import ru.miigaik.api.model.token.TokenRequestModel;

import static ru.miigaik.cfg.ConfigurationProject.*;

public class AuthApi extends BaseApiRequest
{
    @Step()
    public Response authEmail(EmailRequestModel emailRequestModel)
    {
        Allure.addAttachment("Запрос по созданию токена авторизации, отправляющийся на ", EMAIL_API);
        return baseRequest()
                .body(emailRequestModel)
                .post(EMAIL_API);

    }

    @Step()
    public Response authToken(TokenRequestModel tokenRequestModel)
    {
        Allure.addAttachment("Запрос по созданию токена доступа и токена для обновления доступа", TOKEN_API);
        return baseRequest()
                .body(tokenRequestModel)
                .post(TOKEN_API);
    }

    @Step()
    public Response refreshToken(RefreshRequestModel refreshRequestModel)
    {
        Allure.addAttachment("Запрос по обновлению токена доступа", REFRESH_TOKEN_API);
        return baseRequest()
                .body(refreshRequestModel)
                .post(REFRESH_TOKEN_API);
    }
}
