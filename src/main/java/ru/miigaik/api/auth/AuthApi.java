package ru.miigaik.api.auth;
import io.qameta.allure.Allure;
import io.restassured.response.Response;
import io.qameta.allure.Step;
import ru.miigaik.api.BaseApiRequest;
import ru.miigaik.api.auth.model.EmailRequestModel;

import static ru.miigaik.cfg.ConfigurationProject.EMAIL_API;

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
}
