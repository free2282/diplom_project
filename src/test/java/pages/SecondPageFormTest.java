package pages;

import io.qameta.allure.Allure;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.miigaik.action.PreparedActions;
import ru.miigaik.browser.Browsers;
import ru.miigaik.pages.FormFirstPage;
import ru.miigaik.pages.FormSecondPage;

import static org.junit.Assert.*;
import static ru.miigaik.browser.Browsers.*;

@RunWith(Parameterized.class)
public class SecondPageFormTest
{
    private PreparedActions preparedActions;
    private FormSecondPage formSecondPage;
    private Browsers browsers;
    private FormFirstPage formFirstPage;

    public SecondPageFormTest(Browsers browsers)
    {
        this.browsers = browsers;
    }


    @Parameterized.Parameters
    public static Object[][] getEnterAccount()
    {
        return new Object[][]
                {
                        {CHROME},
                        {YANDEX},
                        {FIREFOX},
                        {EDGE}
                };
    }

    @Before
    public void setUp() throws InterruptedException
    {
        preparedActions = new PreparedActions(browsers);
        preparedActions.logIn();
    }

    @Test
    @DisplayName("Проверка перехода на яндекс карты после клика по геолокации университета и видимость МИИГАиК на карте")
    public void checkClickYandexMapReferenceTest() throws InterruptedException
    {
        boolean actual;

            preparedActions.fillTheFirstForm();
            formFirstPage = new FormFirstPage(preparedActions.getDriver());
            formFirstPage.clickNextButton();

            formSecondPage = new FormSecondPage(preparedActions.getDriver());
            actual = formSecondPage.clickMapRef().isMiigaikOnMapVisible();


        Allure.addAttachment("Был ли переход по ссылке и виден ли был МИИГАиК", actual+"");
        assertTrue("Переход по ссылке не был осуществлен",actual);
    }

    @Test
    @DisplayName("Проверка отправки формы при иностранном гражданстве при отсутствии документа оо смене фамилии и снилс на модерацию")
    public void checkSendingFormWithForeignPasportWithoutSnilsAndChangingFIOScanTest() throws InterruptedException
    {
        boolean actual;
        preparedActions.fillTheFirstForm();
        formFirstPage = new FormFirstPage(preparedActions.getDriver());

        formFirstPage
                .setCitizenship("КАЗАХСТАН")
                .clickNextButton();

        formSecondPage = new FormSecondPage(preparedActions.getDriver());
        actual = formSecondPage
                .uploadIncomingStatement("test.pdf")
                .uploadConsentToTheProcessingOfPersonalData("test.pdf")
                .uploadPersonalList("test.pdf")
                .uploadPasport("test.pdf")
                .uploadDiplom("test.pdf")
                .clickSendButton()
                .isFormSendingSuccessful();

        Allure.addAttachment("Была ли отправлена форма на модерацию", actual+"");
        assertTrue("Форма с иностранным паспортом не была отправлена",actual);
    }

    @Test
    @DisplayName("Проверка отправки формы при русском гражданстве при отсутствии документа о смене фамилии на модерацию")
    public void checkSendingFormWithRuPasportWithoutDocumentAboutChangingFIOScanTest() throws InterruptedException
    {
        boolean actual;

            preparedActions.fillTheFirstForm();
            formFirstPage = new FormFirstPage(preparedActions.getDriver());

            formFirstPage
                    .clickNextButton();


            formSecondPage = new FormSecondPage(preparedActions.getDriver());
            actual = formSecondPage
                    .uploadIncomingStatement("test.pdf")
                    .uploadConsentToTheProcessingOfPersonalData("test.pdf")
                    .uploadPersonalList("test.pdf")
                    .uploadPasport("test.pdf")
                    .uploadDiplom("test.pdf")
                    .uploadSnils("test.pdf")
                    .clickSendButton()
                    .isFormSendingSuccessful();


        Allure.addAttachment("Была ли отправлена форма на модерацию", actual+"");
        assertTrue("Форма без документа о осмене фамилии не была отправлена",actual);
    }



    @Test
    @DisplayName("Проверка отправки формы при русском гражданстве при заполнении всех документов на модерацию")
    public void checkSendingFormWithRuPasportTest() throws InterruptedException
    {
        boolean actual;
        preparedActions.fillTheFirstForm();
        formFirstPage = new FormFirstPage(preparedActions.getDriver());

        formFirstPage.clickNextButton();

        formSecondPage = new FormSecondPage(preparedActions.getDriver());
        actual = formSecondPage
                .uploadIncomingStatement("test.pdf")
                .uploadConsentToTheProcessingOfPersonalData("test.pdf")
                .uploadPersonalList("test.pdf")
                .uploadPasport("test.pdf")
                .uploadDiplom("test.pdf")
                .uploadDocumentAboutChangingFIO("test.pdf")
                .uploadSnils("test.pdf")
                .clickSendButton()
                .isFormOnModeration();


        Allure.addAttachment("Была ли отправлена форма на модерацию", actual+"");
        assertTrue("Форма не была отправлена на модерацию",actual);

    }


    @Test
    @DisplayName("Проверка отсутствия отправки формы на модерацию при отсутствии заявления поступающего")
    public void checkAbsentSendingToModerationWithoutIncomingStatementRuPasportTest() throws InterruptedException
    {
        boolean actual;
        try
        {
            preparedActions.fillTheFirstForm();
            formFirstPage = new FormFirstPage(preparedActions.getDriver());

            formFirstPage
                    .clickNextButton();


            formSecondPage = new FormSecondPage(preparedActions.getDriver());
            actual = formSecondPage
                    .uploadConsentToTheProcessingOfPersonalData("test.pdf")
                    .uploadPersonalList("test.pdf")
                    .uploadPasport("test.pdf")
                    .uploadDiplom("test.pdf")
                    .uploadDocumentAboutChangingFIO("test.pdf")
                    .uploadSnils("test.pdf")
                    .clickSendButton()
                    .isFormOnModeration();
        }
        catch (Exception e)
        {
            actual = false;
        }


        Allure.addAttachment("Видно ли уведомление об отправке анкеты на модерацию при не загрузке заявления поступающего", actual+"");
        assertFalse("Форма была отправлена на модерацию", actual);
    }

    @Test
    @DisplayName("Проверка ошибки при отсутствии заявления поступающего")
    public void checkErrorSendingWithoutIncomingStatementRuPasportTest() throws InterruptedException
    {
        boolean actual;
        preparedActions.fillTheFirstForm();
        formFirstPage = new FormFirstPage(preparedActions.getDriver());

        formFirstPage.clickNextButton();

        formSecondPage = new FormSecondPage(preparedActions.getDriver());
        actual = formSecondPage
                .uploadConsentToTheProcessingOfPersonalData("test.pdf")
                .uploadPersonalList("test.pdf")
                .uploadPasport("test.pdf")
                .uploadDiplom("test.pdf")
                .uploadDocumentAboutChangingFIO("test.pdf")
                .uploadSnils("test.pdf")
                .clickSendButton()
                .isNotificationToUploadFileVisible();


        Allure.addAttachment("Видно ли уведомление об ошибке не загрузки обязательноого документа заявления поступающего поступающего", actual+"");
        assertTrue("Ошибка о не загрузке заявления поступающего не была показана", actual);
    }

    @Test
    @DisplayName("Проверка отсутствия отправки формы на модерацию при отсутствии личного листка поступающего")
    public void checkAbsentSendingFormToModerationWithoutPersonalDataRuTest() throws InterruptedException
    {
        boolean actual;
        try
        {
            preparedActions.fillTheFirstForm();
            formFirstPage = new FormFirstPage(preparedActions.getDriver());

            formFirstPage
                    .clickNextButton();


            formSecondPage = new FormSecondPage(preparedActions.getDriver());
            actual = formSecondPage
                    .uploadConsentToTheProcessingOfPersonalData("test.pdf")
                    .uploadIncomingStatement("test.pdf")
                    .uploadPasport("test.pdf")
                    .uploadDiplom("test.pdf")
                    .uploadDocumentAboutChangingFIO("test.pdf")
                    .uploadSnils("test.pdf")
                    .clickSendButton()
                    .isFormOnModeration();
        }
        catch (Exception e)
        {
            actual = false;
        }


        Allure.addAttachment("Видно ли уведомление об отправке анкеты на модерацию при не загрузке паспорта", actual+"");
        assertFalse("Форма была отправлена на модерацию", actual);
    }

    @Test
    @DisplayName("Проверка ошибки при отсутствии персонального листа поступающего")
    public void checkErrorSendingWithoutPersonalDataRuPasportTest() throws InterruptedException
    {
        boolean actual;
        preparedActions.fillTheFirstForm();
        formFirstPage = new FormFirstPage(preparedActions.getDriver());

        formFirstPage.clickNextButton();

        formSecondPage = new FormSecondPage(preparedActions.getDriver());
        actual = formSecondPage
                .uploadConsentToTheProcessingOfPersonalData("test.pdf")
                .uploadIncomingStatement("test.pdf")
                .uploadPasport("test.pdf")
                .uploadDiplom("test.pdf")
                .uploadDocumentAboutChangingFIO("test.pdf")
                .uploadSnils("test.pdf")
                .clickSendButton()
                .isNotificationToUploadFileVisible();


        Allure.addAttachment("Видно ли уведомление об ошибке не загрузки обязательноого документа персонального листа поступающего", actual+"");
        assertTrue("Ошибка о не загрузке персонального листа поступающего не была показана", actual);
    }

    @Test
    @DisplayName("Проверка ошибки при отсутствии согласия на обработку ПД")
    public void checkErrorSendingWithoutConsentToTheProcessingOfPersonalDataRuPasportTest() throws InterruptedException
    {
        boolean actual;
        preparedActions.fillTheFirstForm();
        formFirstPage = new FormFirstPage(preparedActions.getDriver());
        formFirstPage.clickNextButton();

        formSecondPage = new FormSecondPage(preparedActions.getDriver());
        actual = formSecondPage
                .uploadIncomingStatement("test.pdf")
                .uploadPersonalList("test.pdf")
                .uploadPasport("test.pdf")
                .uploadDiplom("test.pdf")
                .uploadDocumentAboutChangingFIO("test.pdf")
                .uploadSnils("test.pdf")
                .clickSendButton()
                .isNotificationToUploadFileVisible();



        Allure.addAttachment("Видно ли уведомление об ошибке не загрузки обязательноого документа согласия на ообработку ПД", actual+"");
        assertTrue("Ошибка о не загрузке согласия на ПД не была показана", actual);
    }

    @Test
    @DisplayName("Проверка отсутствия отправки формы на модерацию при отсутствии согласия на обработку ПД")
    public void checkAbsentSendingFormToModerationWithoutConsentToTheProcessingOfPersonalDataRuPasportTest() throws InterruptedException
    {
        boolean actual;
        try
        {
            preparedActions.fillTheFirstForm();
            formFirstPage = new FormFirstPage(preparedActions.getDriver());

            formFirstPage
                    .clickNextButton();


            formSecondPage = new FormSecondPage(preparedActions.getDriver());
            actual = formSecondPage
                    .uploadPersonalList("test.pdf")
                    .uploadIncomingStatement("test.pdf")
                    .uploadPasport("test.pdf")
                    .uploadDiplom("test.pdf")
                    .uploadDocumentAboutChangingFIO("test.pdf")
                    .uploadSnils("test.pdf")
                    .clickSendButton()
                    .isFormOnModeration();
        }
        catch (Exception e)
        {
            actual = false;
        }


        Allure.addAttachment("Видно ли уведомление об отправке анкеты на модерацию при не загрузке согласия на ПД", actual+"");
        assertFalse("Форма была отправлена на модерацию", actual);
    }

    @Test
    @DisplayName("Проверка отсутствия отправки формы на модерацию при отсутствии паспорта")
    public void checkAbsentSendingFormToModerationWithoutRuPasportTest() throws InterruptedException
    {
        boolean actual;
        try
        {
            preparedActions.fillTheFirstForm();
            formFirstPage = new FormFirstPage(preparedActions.getDriver());

            formFirstPage.clickNextButton();


            formSecondPage = new FormSecondPage(preparedActions.getDriver());
            actual = formSecondPage
                    .uploadPersonalList("test.pdf")
                    .uploadConsentToTheProcessingOfPersonalData("test.pdf")
                    .uploadIncomingStatement("test.pdf")
                    .uploadDiplom("test.pdf")
                    .uploadDocumentAboutChangingFIO("test.pdf")
                    .uploadSnils("test.pdf")
                    .clickSendButton()
                    .isFormOnModeration();
        }
        catch (Exception e)
        {
            actual = false;
        }


        Allure.addAttachment("Видно ли уведомление об отправке анкеты на модерацию при не загрузке паспорта", actual+"");
        assertFalse("Форма была отправлена на модерацию", actual);
    }

    @Test
    @DisplayName("Проверка ошибки при отправке при отсутствии паспорта")
    public void checkErrorSendingWithoutRuPasportTest() throws InterruptedException
    {
        boolean actual;

        preparedActions.fillTheFirstForm();
        formFirstPage = new FormFirstPage(preparedActions.getDriver());

        formFirstPage.clickNextButton();

        formSecondPage = new FormSecondPage(preparedActions.getDriver());
        actual = formSecondPage
                .uploadPersonalList("test.pdf")
                .uploadConsentToTheProcessingOfPersonalData("test.pdf")
                .uploadIncomingStatement("test.pdf")
                .uploadDiplom("test.pdf")
                .uploadDocumentAboutChangingFIO("test.pdf")
                .uploadSnils("test.pdf")
                .clickSendButton()
                .isNotificationToUploadFileVisible();


        Allure.addAttachment("Видно ли уведомление об ошибке не загрузки обязательноого документа паспорт", actual+"");
        assertTrue("Ошибка о не загрузке паспорта не была показана", actual);
    }

    @Test
    @DisplayName("Проверка отсутствия отправки формы на модерацию при отсутствии диплома")
    public void checkAbsentNotificationAboutSendingFormToModerationWithoutDiplomTest() throws InterruptedException
    {
        boolean actual;
        try
        {
            preparedActions.fillTheFirstForm();
            formFirstPage = new FormFirstPage(preparedActions.getDriver());

            formFirstPage
                    .clickNextButton();


            formSecondPage = new FormSecondPage(preparedActions.getDriver());
            actual = formSecondPage
                    .uploadConsentToTheProcessingOfPersonalData("test.pdf")
                    .uploadPersonalList("test.pdf")
                    .uploadIncomingStatement("test.pdf")
                    .uploadPasport("test.pdf")
                    .uploadDocumentAboutChangingFIO("test.pdf")
                    .uploadSnils("test.pdf")
                    .clickSendButton()
                    .isFormOnModeration();
        }
        catch (Exception e)
        {
            actual = false;
        }

        Allure.addAttachment("Видно ли уведомление об отправке анкеты на модерацию при не загрузке диплома", actual+"");
        assertFalse("Форма была отправлена на модерацию", actual);
    }


    @Test
    @DisplayName("Проверка ошибки при отправке при отсутствии диплома")
    public void checkErrorSendingWithoutDiplomTest() throws InterruptedException
    {
        boolean actual;

        preparedActions.fillTheFirstForm();
        formFirstPage = new FormFirstPage(preparedActions.getDriver());

        formFirstPage
                .clickNextButton();


        formSecondPage = new FormSecondPage(preparedActions.getDriver());
        actual = formSecondPage
                .uploadConsentToTheProcessingOfPersonalData("test.pdf")
                .uploadPersonalList("test.pdf")
                .uploadIncomingStatement("test.pdf")
                .uploadPasport("test.pdf")
                .uploadDocumentAboutChangingFIO("test.pdf")
                .uploadSnils("test.pdf")
                .clickSendButton()
                .isNotificationToUploadFileVisible();

        Allure.addAttachment("Видно ли уведомление об ошибке не загрузки обязательноого документа диплом", actual+"");
        assertTrue("Ошибка о не загрузке диплома не была показана", actual);
    }

    @Test
    @DisplayName("Проверка отсутствия уведомления об отправке формы на модерацию при отсутствие скана паспорта")
    public void checkAbsentModerationFormWithoutPasportTest() throws InterruptedException
    {
        boolean actual;
        preparedActions.fillTheFirstForm();
        formFirstPage = new FormFirstPage(preparedActions.getDriver());

        formFirstPage.clickNextButton();


        formSecondPage = new FormSecondPage(preparedActions.getDriver());
        try
        {
            actual = formSecondPage
                    .uploadConsentToTheProcessingOfPersonalData("test.pdf")
                    .uploadPersonalList("test.pdf")
                    .uploadIncomingStatement("test.pdf")
                    .uploadDiplom("test.pdf")
                    .uploadDocumentAboutChangingFIO("test.pdf")
                    .uploadSnils("test.pdf")
                    .clickSendButton()
                    .isFormOnModeration();
        }
        catch (Exception e)
        {
            actual = false;
        }



        Allure.addAttachment("Видно ли уведомление об отправке анкеты на модерацию при не загрузке паспорта", actual+"");
        assertFalse("Форма была отправлена на модерацию", actual);
    }

    @Test
    @DisplayName("Проверка видимости уведомления об ошибке загрузке обязательного паспорта")
    public void checkErrorSendingWithoutPasportTest() throws InterruptedException
    {
        boolean actual;
        preparedActions.fillTheFirstForm();
        formFirstPage = new FormFirstPage(preparedActions.getDriver());

        formFirstPage.clickNextButton();


        formSecondPage = new FormSecondPage(preparedActions.getDriver());
        actual = formSecondPage
                .uploadConsentToTheProcessingOfPersonalData("test.pdf")
                .uploadPersonalList("test.pdf")
                .uploadIncomingStatement("test.pdf")
                .uploadDiplom("test.pdf")
                .uploadDocumentAboutChangingFIO("test.pdf")
                .uploadSnils("test.pdf")
                .clickSendButton()
                .isNotificationToUploadFileVisible();


        Allure.addAttachment("Видно ли уведомление об ошибке не загрузки обязательноого паспорта", actual+"");
        assertTrue("Ошибка о не загрузке паспорта не была показана", actual);
    }

    @Test
    @DisplayName("Проверка уведомления об ошибке не загрузке СНИЛС с граждантсвом РФ")
    public void checkErrorSendingWithoutSnilsTest() throws InterruptedException
    {
        boolean actual;

        preparedActions.fillTheFirstForm();
        formFirstPage = new FormFirstPage(preparedActions.getDriver());

        formFirstPage
                .clickNextButton();


        formSecondPage = new FormSecondPage(preparedActions.getDriver());
        actual = formSecondPage
                    .uploadConsentToTheProcessingOfPersonalData("test.pdf")
                    .uploadPersonalList("test.pdf")
                    .uploadIncomingStatement("test.pdf")
                    .uploadDiplom("test.pdf")
                    .uploadDocumentAboutChangingFIO("test.pdf")
                    .uploadPasport("test.pdf")
                    .clickSendButton()
                    .isNotificationToUploadFileVisible();

        System.out.println(actual);

        Allure.addAttachment("Видно ли уведомление об ошибке не загрузки обязательноого документа СНИЛС при гражданстве РФ", actual+"");
        assertTrue("Ошибка о не загрузке СНИЛС не была показана", actual);

        }

    @Test
    @DisplayName("Проверка отсутствия видимости уведомления об отправке анкеты на модерацию при отсутствующем СНИЛС с граждантством РФ")
    public void checkAbsentModerationFormRuCitizenshipWithoutSnilsTest() throws InterruptedException
    {
        boolean actual;

        preparedActions.fillTheFirstForm();
        formFirstPage = new FormFirstPage(preparedActions.getDriver());

        formFirstPage.clickNextButton();
        formSecondPage = new FormSecondPage(preparedActions.getDriver());
        try
        {
            actual = formSecondPage
                    .uploadConsentToTheProcessingOfPersonalData("test.pdf")
                    .uploadPersonalList("test.pdf")
                    .uploadIncomingStatement("test.pdf")
                    .uploadDiplom("test.pdf")
                    .uploadDocumentAboutChangingFIO("test.pdf")
                    .uploadPasport("test.pdf")
                    .clickSendButton()
                    .isFormOnModeration();
        }
        catch (Exception e)
        {
            actual = false;
        }
        Allure.addAttachment("Видно ли уведомление об отправке анкеты на модерацию при не загрузке СНИЛС", actual+"");
        assertFalse("Форма была отправлена на модерацию", actual);
    }



    @After
    public void tearDown()
    {
        preparedActions.getDriver().quit();
    }


}
