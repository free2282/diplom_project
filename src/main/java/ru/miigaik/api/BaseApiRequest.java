package ru.miigaik.api;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.given;
import static java.lang.String.format;
import static ru.miigaik.cfg.ConfigurationProject.MAIN_URL;


public class BaseApiRequest
{
    @Step()
    protected RequestSpecification baseRequest()
    {
        Allure.addAttachment(
                "Базовый запрос, который отправляется на ",
                MAIN_URL);
        return given()
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .contentType(ContentType.JSON).baseUri(MAIN_URL);
    }
}