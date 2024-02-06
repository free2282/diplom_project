package ru.miigaik.api;
import io.qameta.allure.Allure;
import io.restassured.response.Response;
import io.qameta.allure.Step;
import ru.miigaik.api.BaseApiRequest;
import ru.miigaik.api.auth.model.EmailRequestModel;
import ru.miigaik.api.token.model.TokenRequestModel;

import static ru.miigaik.cfg.ConfigurationProject.EMAIL_API;
import static ru.miigaik.cfg.ConfigurationProject.TOKEN_API;

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
}
