package pages;

import io.qameta.allure.Allure;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import ru.miigaik.action.QuickEvent;
import ru.miigaik.browser.Browsers;
import ru.miigaik.pages.BasePage;
import ru.miigaik.pages.FormFirstPage;
import ru.miigaik.pages.FormSecondPage;

import java.io.File;

import static org.junit.Assert.assertEquals;
import static ru.miigaik.browser.Browsers.*;

@RunWith(Parameterized.class)
public class SecondPageFormUploadParametrizedTest
{
    private QuickEvent quickEvent;
    private FormSecondPage formSecondPage;
    private Browsers browsers;
    private String fileName;
    private boolean expect;
    private WebDriver driver;

    public SecondPageFormUploadParametrizedTest(Browsers browsers, String fileName, boolean expect)
    {
        this.browsers = browsers;
        this.fileName = fileName;
        this.expect = expect;
    }

    @Parameterized.Parameters
    public static Object[][] getEnterAccount()
    {
        return new Object[][]
                {
                        {CHROME,"test.jpeg", true},
                        {CHROME,"test.jpg", true},
                        {CHROME,"test.pdf", true},
                        {CHROME,"test.png", true},
                        {CHROME,"text.txt", false},
                        {CHROME,"more5mb.jpeg", false},

//                        {YANDEX,"test.jpeg", true},
//                        {YANDEX,"test.jpg", true},
//                        {YANDEX,"test.pdf", true},
//                        {YANDEX,"test.png", true},
//                        {YANDEX,"text.txt", false},
//                        {YANDEX,"more5mb.jpeg", false},
//
//                        {FIREFOX,"test.jpeg", true},
//                        {FIREFOX,"test.jpg", true},
//                        {FIREFOX,"test.pdf", true},
//                        {FIREFOX,"test.png", true},
//                        {FIREFOX,"text.txt", false},
//                        {FIREFOX,"more5mb.jpeg", false},
//
//                        {EDGE,"test.jpeg", true},
//                        {EDGE,"test.jpg", true},
//                        {EDGE,"test.pdf", true},
//                        {EDGE,"test.png", true},
//                        {EDGE,"text.txt", false},
//                        {EDGE,"more5mb.jpeg", false},
                };
    }

    @Before
    public void setUp() throws InterruptedException
    {
        quickEvent = new QuickEvent();
        quickEvent.logIn(browsers).fillTheFirstForm();
        driver = quickEvent.getDriver();
        FormFirstPage formFirstPage = new FormFirstPage(driver);
        formFirstPage.clickNextButton();

        formSecondPage = new FormSecondPage(driver);

    }

    @Test
    public void checkPersonalListFileUploadingTest() throws InterruptedException
    {
        boolean result;
            result = formSecondPage
                    .uploadPersonalList(fileName)
                    .uploadIncomingStatement("test.jpeg")
                    .uploadConsentToTheProcessingOfPersonalData("test.jpeg")
                    .uploadPasport("test.jpeg")
                    .uploadDiplom("test.jpeg")
                    .uploadDocumentAboutChangingFIO("test.jpeg")
                    .uploadSnils("test.jpeg")
                    .clickSendButton()
                    .isFormSendingSuccessful();
        Allure.addAttachment("Загруженный файл", fileName);
        assertEquals("Во время загрузки файла (см.вложения) прооизошла ошибка логики",expect, result);
    }

    @Test
    public void checkIncomingStatementFileUploadingTest() throws InterruptedException {
        boolean result;

            result = formSecondPage
                    .uploadPersonalList("test.jpeg")
                    .uploadIncomingStatement(fileName)
                    .uploadConsentToTheProcessingOfPersonalData("test.jpeg")
                    .uploadPasport("test.jpeg")
                    .uploadDiplom("test.jpeg")
                    .uploadDocumentAboutChangingFIO("test.jpeg")
                    .uploadSnils("test.jpeg")
                    .clickSendButton()
                    .isFormSendingSuccessful();

        Allure.addAttachment("Загруженный файл", fileName);
        assertEquals("Во время загрузки файла (см.вложения) прооизошла ошибка логики",expect, result);
    }

    @Test
    public void checkConsentToTheProcessingOfPersonalDataFileUploadingTest() throws InterruptedException {
        boolean result;

            result = formSecondPage
                    .uploadPersonalList("test.jpeg")
                    .uploadIncomingStatement("test.jpeg")
                    .uploadConsentToTheProcessingOfPersonalData(fileName)
                    .uploadPasport("test.jpeg")
                    .uploadDiplom("test.jpeg")
                    .uploadDocumentAboutChangingFIO("test.jpeg")
                    .uploadSnils("test.jpeg")
                    .clickSendButton()
                    .isFormSendingSuccessful();

        Allure.addAttachment("Загруженный файл", fileName);
        assertEquals("Во время загрузки файла (см.вложения) прооизошла ошибка логики",expect, result);
    }

    @Test
    public void checkPasportFileUploadingTest() throws InterruptedException {
        boolean result;
            result = formSecondPage
                    .uploadPersonalList("test.jpeg")
                    .uploadIncomingStatement("test.jpeg")
                    .uploadConsentToTheProcessingOfPersonalData("test.jpeg")
                    .uploadPasport(fileName)
                    .uploadDiplom("test.jpeg")
                    .uploadDocumentAboutChangingFIO("test.jpeg")
                    .uploadSnils("test.jpeg")
                    .clickSendButton()
                    .isFormSendingSuccessful();

        Allure.addAttachment("Загруженный файл", fileName);
        assertEquals("Во время загрузки файла (см.вложения) прооизошла ошибка логики",expect, result);
    }

    @Test
    public void checkDiplomFileUploadingTest() throws InterruptedException {
        boolean result;
            result = formSecondPage
                    .uploadPersonalList("test.jpeg")
                    .uploadIncomingStatement("test.jpeg")
                    .uploadConsentToTheProcessingOfPersonalData("test.jpeg")
                    .uploadPasport("test.jpeg")
                    .uploadDiplom(fileName)
                    .uploadDocumentAboutChangingFIO("test.jpeg")
                    .uploadSnils("test.jpeg")
                    .clickSendButton()
                    .isFormSendingSuccessful();

        Allure.addAttachment("Загруженный файл", fileName);
        assertEquals("Во время загрузки файла (см.вложения) прооизошла ошибка логики",expect, result);
    }

    @Test
    public void checkDocumentAboutChangingFIOUploadingTest() throws InterruptedException {
        boolean result;

            result = formSecondPage
                    .uploadPersonalList("test.jpeg")
                    .uploadIncomingStatement("test.jpeg")
                    .uploadConsentToTheProcessingOfPersonalData("test.jpeg")
                    .uploadPasport("test.jpeg")
                    .uploadDiplom("test.jpeg")
                    .uploadDocumentAboutChangingFIO(fileName)
                    .uploadSnils("test.jpeg")
                    .clickSendButton()
                    .isFormSendingSuccessful();

        Allure.addAttachment("Загруженный файл", fileName);
        assertEquals("Во время загрузки файла (см.вложения) прооизошла ошибка логики",expect, result);
    }

    @Test
    public void checkSnilsUploadingTest() throws InterruptedException {
        boolean result;

            result = formSecondPage
                    .uploadPersonalList("test.jpeg")
                    .uploadIncomingStatement("test.jpeg")
                    .uploadConsentToTheProcessingOfPersonalData("test.jpeg")
                    .uploadPasport("test.jpeg")
                    .uploadDiplom("test.jpeg")
                    .uploadDocumentAboutChangingFIO("test.jpeg")
                    .uploadSnils(fileName)
                    .clickSendButton()
                    .isFormSendingSuccessful();

        Allure.addAttachment("Загруженный файл", fileName);
        assertEquals("Во время загрузки файла (см.вложения) прооизошла ошибка логики",expect, result);
    }

    @After
    public void tearDown()
    {
        driver.quit();
        //дописапть, если true, то перейти на форму админа и закрыть
    }
}
