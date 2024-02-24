package pages;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import ru.miigaik.action.PreparedActions;
import ru.miigaik.browser.Browsers;
import ru.miigaik.pages.FormFirstPage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static ru.miigaik.browser.Browsers.*;

@RunWith(Parameterized.class)
public class FirstPageFormTest
{
    private PreparedActions preparedActions;
    private Browsers browsers;
    private WebDriver driver;

    public FirstPageFormTest(Browsers browsers)
    {
        this.browsers = browsers;
    }

    @Parameterized.Parameters
    public static Object[][] getEnterAccount()
    {
        return new Object[][]
                {
                        {CHROME},
//                        {YANDEX},
//                        {FIREFOX},
//                        {EDGE}
                };
    }

    @Before
    public void setUp() throws InterruptedException
    {
        preparedActions = new PreparedActions(browsers);
        preparedActions.logIn();
        driver = preparedActions.getDriver();
    }

    @Test
    @DisplayName("Проверка заполнения всех полей с гражданством РФ анкеты и перехода на вторую страницу заполнения данных")
    public void fillFullFieldRuPasportAndCitizenshipFirstFormTest() throws InterruptedException {
        boolean result;

            preparedActions.fillTheFirstForm();
            FormFirstPage firstPage = preparedActions.getFormFirstPage();
            result = firstPage.setCitizenship("КАЗАХСТАН").clickNextButton()
                    .isTextSecondPageVisible();


        assertTrue("Текст со второй страницы не был виден",result);
    }

    @Test
    @DisplayName("Проверка заполнения всех полей с гражданством другой страны анкеты и перехода на вторую страницу заполнения данных")
    public void fillFullFieldOtherCountryPasportAndCitizenshipFirstFormTest() throws InterruptedException {
        boolean result;

            preparedActions.fillTheFirstForm();
            FormFirstPage firstPage = preparedActions.getFormFirstPage();
            result = firstPage.clickNextButton().isTextSecondPageVisible();



        assertTrue("Текст со второй страницы не был виден",result);
    }

    @Test
    @DisplayName("Проверка заполнения только обязательных полей анкеты иностранного гражданина и перехода на вторую страницу заполнения данных")
    @Description("В модели есть много полей, которые не обязательны. Также добаювляются необязательные поля, если гражданство не РФ. Ожидается, что будет возможно заполнить только обязательные поля и после нажатия кнопки далее, валидация на фронте пропустит данные")
    public void fillOnlyRequiredFieldForeignerFirstFormTest() throws InterruptedException {
        boolean result;

            preparedActions.fillMinFormForeignPasport();
            FormFirstPage form = preparedActions.getFormFirstPage();

            result = form
                    .clickNextButton()
                    .isTextSecondPageVisible();


        assertTrue("Переход на вторую страницу не был осуществлен, какие-то обязтельные поля не были заполнены, произошло расхождение расхождение обязательных полей с докой",result);
    }

    @Test
    @DisplayName("Проверка заполнения полной анкеты иностранного гражданина")
    public void fillFullFieldForeignerFirstFormTest() throws InterruptedException {
        boolean result;
        String otherCountry = "КАЗАХСТАН";

            preparedActions.fillTheFirstForm();
            FormFirstPage form = preparedActions.getFormFirstPage();
            result = form
                    .setCitizenship(otherCountry)
                    .setOtherCountryPasport()
                    .clickNextButton()
                    .isTextSecondPageVisible();



        assertTrue("Переход на вторую страницу не был осуществлен, какие-то обязтельные поля не были заполнены, расхождение обязательных полей с докой",result);
    }

    @Test
    @DisplayName("Проверка видимости ошибки заполнения анкеты возраст <14")
    @Description("В случае, если заполняющему меньше 14 лет, то запрещено прохождление заполнения формы на второй этап")
    public void checkRequiredAgeLess14Test() throws InterruptedException
    {
        boolean result;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        LocalDate dateForError = LocalDate.now().minusYears(14).minusDays(1);
        String dateForErrorAfterFormatting = dateForError.format(formatter);
        String expected = "Регистрация возможна только для лиц от 14 лет. Дата рождения должна быть не позже " + dateForErrorAfterFormatting;

        LocalDate errorDateLess14 = LocalDate.now().minusYears(13).plusDays(1);
        String formattedDateLess14 = errorDateLess14.format(formatter);


        preparedActions.fillTheFirstFormWithoutAutoBirthDate();

        FormFirstPage form = preparedActions.getFormFirstPage();
        result = form.setBirth(formattedDateLess14)
                .clickNextButton()
                .isErrorVisibleSpanElement(expected);


        assertTrue("Ограничение о том, что регистрация доступна с 14 лет не было высвечено",result);
    }

    @Test
    @DisplayName("Проверка текста ошибки заполнения анкеты возраст <14")
    @Description("В случае, если заполняющему меньше 14 лет, то запрещено прохождление заполнения формы на второй этап")
    public void checkRequiredAgeMore14TextErrorTest() throws InterruptedException {
        String result;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        LocalDate dateForError = LocalDate.now().minusYears(14).minusDays(1);
        String dateForErrorAfterFormatting = dateForError.format(formatter);
        String expected = "Регистрация возможна только для лиц от 14 лет. Дата рождения должна быть не позже " + dateForErrorAfterFormatting;

        LocalDate errorDateLess14 = LocalDate.now().minusYears(13).plusDays(1);
        String formattedDateLess14 = errorDateLess14.format(formatter);


        preparedActions.fillTheFirstFormWithoutAutoBirthDate();

            FormFirstPage form = preparedActions.getFormFirstPage();
            result = form.setBirth(formattedDateLess14)
                    .clickNextButton()
                    .getErrorTextSpanElement(expected);

        assertEquals("Ограничение о том, что регистрация доступна с 14 лет не было высвечено",expected, result);
    }


    @Test
    @DisplayName("Проверка валидации серии паспорта по стандартам РФ")
    public void checkRequiredValidationDulSeriesIfCitizenShipIsRfTest() throws InterruptedException {
        boolean result;
        String dulSeries = "289711";

            preparedActions.fillTheFirstFormRuWithoutSeriesDul();
            FormFirstPage form = preparedActions.getFormFirstPage();
            result = form.setDulSeries(dulSeries)
                    .checkDulSeriesValidation(dulSeries);


        assertTrue("Валидация серии паспорта рф при гражданстве РФ не сработала", result);
    }

    @Test
    @DisplayName("Проверка обязательности заполнения серии паспорта при гражданстве РФ")
    public void checkRequiredFieldDulSeriesCinizenshipIsRfTest() throws InterruptedException {
        boolean result;
        String expected = "Поле обязательно к заполнению";

            preparedActions.fillTheFirstFormRuWithoutSeriesDul();
            FormFirstPage form = preparedActions.getFormFirstPage();
            result = form.clickNextButton()
                    .isErrorVisibleSpanElement(expected);

        assertTrue("Информация об ошибке незаполненности обязательного поля серии паспорта не высветилась", result);
    }

    @Test
    @DisplayName("Проверка текста ошибки обязательности заполнения серии паспорта при гражданстве РФ")
    public void checkTextErrorRequiredFieldDulSeriesCinizenshipIsRfTest() throws InterruptedException {
        String result;
        String expected = "Поле обязательно к заполнению";

            preparedActions.fillTheFirstFormRuWithoutSeriesDul();
            FormFirstPage form = preparedActions.getFormFirstPage();
            result = form
                    .clickNextButton()
                    .getErrorTextSpanElement(expected);

        assertEquals("Текст ошибки обязательности серии паспорта не совпал", expected, result);
    }

    @Test
    @DisplayName("Проверка текста видимости ошибки при гражданстве РФ и не заполненном номера снилс")
    public void checkTextErrorRequiredFieldSnilsNumberReceiptCinizenshipIsRfTest() throws InterruptedException {
        String result;
        String expected = "Поле обязательно к заполнению";
        String snilsRegistrationDate = "10.10.2015";

            preparedActions.fillTheFirstFormRuWithoutNumberSnilsAndDate();
            FormFirstPage form = preparedActions.getFormFirstPage();
            result = form
                    .setSnilsRegistrationDate(snilsRegistrationDate)
                    .clickNextButton()
                    .getErrorTextSpanElement(expected);

        assertEquals("Текст ошибки обязательности номера снилс не совпал", expected, result);
    }

    @Test
    @DisplayName("Проверка видимости ошибки обязательности заполнения снилс при гражданстве РФ")
    public void checkErrorRequiredFieldSnilsNumberReceiptCinizenshipIsRfTest() throws InterruptedException {
        boolean result;
        String expected = "Поле обязательно к заполнению";
        String snilsRegistration = "10.10.2015";

            preparedActions.fillTheFirstFormRuWithoutNumberSnilsAndDate();
            FormFirstPage form = preparedActions.getFormFirstPage();
            result = form
                    .setSnilsRegistrationDate(snilsRegistration)
                    .clickNextButton()
                    .isErrorVisibleSpanElement(expected);

        assertTrue("Текст ошибки обязательности номера снилс не совпал", result);
    }

//    @Test
//    @DisplayName("Проверка текста видимости ошибки при гражданстве РФ и не заполненном даты получения снилс")
//    public void checkTextErrorRequiredFieldSnilsDateReceiptCinizenshipIsRfTest()
//    //Должен успасть
//    {
//        String result;
//        String expected = "Поле обязательно к заполнению";
//        String snilsNumber = "178-747-544 36";
//        try
//        {
//            quickEvent.fillTheFirstFormRuWithoutNumberSnilsAndDate();
//            FormFirstPage form = quickEvent.getFormFirstPage();
//            result = form
//                    .setSnils(snilsNumber)
//                    .clickNextButton()
//                    .getErrorTextSpanElement(expected);
//        }
//        catch (Exception e)
//        {
//            result = "Ошибка не была показана";
//        }
//        assertEquals("Текст ошибки обязательности даты снилс не совпал", expected, result);
//    }

//    @Test
//    @DisplayName("Проверка видимости ошибки обязательности заполнения даты получения снилс при гражданстве РФ")
//    public void checkErrorRequiredFieldSnilsDateReceiptCinizenshipIsRfTest()
//    //Должен успасть
//    {
//        boolean result;
//        String snilsNumber = "178-747-544 36";
//        String expected = "Поле обязательно к заполнению";
//        try
//        {
//            quickEvent.fillTheFirstFormRuWithoutNumberSnilsAndDate();
//            FormFirstPage form = quickEvent.getFormFirstPage();
//            result = form
//
//                    .setSnils(snilsNumber)
//                    .clickNextButton()
//                    .isErrorVisibleSpanElement(expected);
//        }
//        catch (Exception e)
//        {
//            result = false;
//        }
//        assertTrue("Текст ошибки обязательности даты снилс не появился", result);
//    }

    @Test
    @DisplayName("Проверка видимости ошибки ошибочной контрольной суммы снилса")
    public void checkValidationSnilsNumberTest() throws InterruptedException {
        boolean result;
        String errorSnilsNumber = "111-111-111 11";
        String correctDate = "01.01.2015";
        String expected = "Неверное контрольное число.";

            preparedActions.fillTheFirstFormRuWithoutNumberSnilsAndDate();
            FormFirstPage form = preparedActions.getFormFirstPage();
            result = form

                    .setSnils(errorSnilsNumber)
                    .setSnilsRegistrationDate(correctDate)
                    .clickNextButton()
                    .isErrorVisibleSpanElement(expected);

        assertTrue("Текст ошибки контрольной суммы снилса не был показан", result);
    }

    @Test
    @DisplayName("Проверка текста ошибки ошибочной контрольной суммы снилса")
    public void checkTextValidationSnilsNumberTest() throws InterruptedException {
        String result;
        String errorSnilsNumber = "111-111-111 11";
        String correctDate = "01.01.2015";
        String expected = "Неверное контрольное число.";

            preparedActions.fillTheFirstFormRuWithoutNumberSnilsAndDate();
            FormFirstPage form = preparedActions.getFormFirstPage();
            result = form

                    .setSnils(errorSnilsNumber)
                    .setSnilsRegistrationDate(correctDate)
                    .clickNextButton()
                    .getErrorTextSpanElement(expected);

        assertEquals("Текст ошибки не совпал", expected, result);
    }
    @Test
    @DisplayName("Проверка видимости ошибки при паспорте рф и не заполнению кода подразделения выдачи паспорта")
    public void requiredCodeAgencyIfCinizenShipIsRfTest() throws InterruptedException {
        boolean result;
        String expected = "Поле обязательно к заполнению";

            preparedActions.fillTheFirstFormRuWithoutCodeAgency();
            FormFirstPage form = preparedActions.getFormFirstPage();
            result = form
                    .clickNextButton()
                    .isErrorVisibleSpanElement(expected);

        assertTrue("Текст ошибки обязательности кода подразделени не был показан", result);
    }

    @Test
    @DisplayName("Проверка текста ошибки при паспорте рф и не заполнению кода подразделения выдачи паспорта")
    public void checkTextRequiredCodeAgencyIfCinizenShipIsRfTest() throws InterruptedException {
        String result;
        String expected = "Поле обязательно к заполнению";

            preparedActions.fillTheFirstFormRuWithoutCodeAgency();
            FormFirstPage form = preparedActions.getFormFirstPage();
            result = form
                    .clickNextButton()
                    .getErrorTextSpanElement(expected);

        assertEquals("Текст ошибки обязательности кода подразделени не совпал", expected, result);
    }

    @Test
    @DisplayName("Проверка автозаполнения паспорта РФ при попадении на форму")
    public void checkPreFilledRussianDulTest()
    {
        boolean result;

            FormFirstPage form = new FormFirstPage(driver);
            result = form
                    .isPasportVisibleInField();

        assertTrue("Паспорт не был автозаполнен при попадании на форму", result);
        //должен упасть
    }

    @Test
    @DisplayName("Проверка автозаполнения паспорта в типе дул после выбора гражданства РФ")
    public void checkAutoFillInRuDulAfterChoosingRussianCitizenshipTest() throws InterruptedException {
        boolean result;
        String ruCitizenship = "РОССИЯ";
        String kzCitizenship = "КАЗАХСТАН";

            FormFirstPage form = new FormFirstPage(driver);
            result = form
                    .setCitizenship(kzCitizenship)
                    .setCitizenship(ruCitizenship)
                    .isPasportVisibleInField();

        assertTrue("Паспорт не был автозаполнен после установки гражданства РФ", result);
        //должен упасть
    }

    @Test
    @DisplayName("Проверка показа ошибки после попытки загрзить ошибочный тип файла")
    @Description("Загрузка файлов только разрешена jpg, jpeg, png. В случае ошибочного типа файла ожидается всплывающее окно с просьбой загрузить нужный файл. Ожидается ошибка сразу после попытки загрузки.")
    public void checkErrorFileTypeTest() throws InterruptedException {
        boolean result;
        String expected = "Тип файла может быть одним из следующих: image/jpeg image/jpg image/png";
        String fileErrorTypeNameInAttachFolder = "notPick.mp3";

            preparedActions.fillTheFirstForm();
            FormFirstPage firstPage = preparedActions.getFormFirstPage();
            firstPage.uploadPhoto(fileErrorTypeNameInAttachFolder);

            result = firstPage.isErrorVisibleSpanElement(expected);

        assertTrue("После загрузки файла надпись об ошибке не появилась",result);
        //должен упасть
    }

    @Test
    @DisplayName("Проверка текста ошибки после попытки загрузить ошибочный тип файла")
    @Description("Загрузка файлов только разрешена jpg, jpeg, png. В случае ошибочного типа файла ожидается всплывающее окно с просьбой загрузить нужный файл")
    public void checkErrorFileTypeTextTest() throws InterruptedException {
        String expected = "Тип файла может быть одним из следующих: image/jpeg image/jpg image/png";
        String result = "";
        String fileErrorTypeNameInAttachFolder = "notPick.mp3";

            preparedActions.fillTheFirstForm();
            FormFirstPage firstPage = preparedActions.getFormFirstPage();
            firstPage.uploadPhoto(fileErrorTypeNameInAttachFolder);

            result = firstPage.getErrorTextSpanElement(expected);
            System.out.println(result);

        assertEquals("Текст ошибки при загрузке ошибочного файла не совпал", expected, result);
        //должен упасть
    }

    @Test
    @DisplayName("Проверка отображения ошибки неправильного формата телефона")
    public void checkErrorFormatPhoneNumberIsDisplayedTest() throws InterruptedException {
        boolean result;
        String expected = "Неверный формат номера телефона";
        String errorPhoneNumber = "(111)111-11-1";

            preparedActions.fillTheFirstFormWithoutAutoPhone();
            FormFirstPage firstPage = preparedActions.getFormFirstPage();
            firstPage.setPhoneNumber(errorPhoneNumber).clickNextButton();
            //уточнить у Бехи
            result = firstPage.isErrorVisibleSpanElement(expected);


        assertTrue("Ошибки неправильно набранного формата телефона не было видно",result);
    }

    @Test
    @DisplayName("Проверка текста ошибки неправильного формата телефона")
    public void checkErrorFormatPhoneNumberTextTest() throws InterruptedException {
        String result;
        String expected = "Неверный формат номера телефона";
        String errorPhoneNumber = "(111)111-11-1";

            preparedActions.fillTheFirstFormWithoutAutoPhone();
            FormFirstPage firstPage = preparedActions.getFormFirstPage();
            firstPage.setPhoneNumber(errorPhoneNumber).clickNextButton();
            //уточнить у Бехи
            result = firstPage.getErrorTextSpanElement(expected);

        assertEquals("Ошибка неверного формата телефона не ссоответствовала ошибке формата телефона", expected, result);
    }

    @Test
    @DisplayName("Проверка отображения ошибки некоректного номера телефона")
    public void checkErrorPhoneNumberIsDisplayedTest() throws InterruptedException {
        boolean result;
        String expected = "Введен некорректный номер телефона.";
        String errorPhoneNumber = "(111)111-11-11";

            preparedActions.fillTheFirstFormWithoutAutoPhone();
            FormFirstPage firstPage = preparedActions.getFormFirstPage();
            firstPage.setPhoneNumber(errorPhoneNumber).clickNextButton();
            //уточнить у Бехи
            result = firstPage.isErrorVisibleSpanElement(expected);

        assertTrue("Ошибка некоректного номера телефона не была показана",result);
    }

    @Test
    @DisplayName("Проверка текста ошибки некоректного номера телефона")
    public void checkErrorPhoneNumberTextTest() throws InterruptedException {
        String result;
        String expected = "Введен некорректный номер телефона.";
        String errorPhoneNumber = "(111)111-11-11";

            preparedActions.fillTheFirstFormWithoutAutoPhone();
            FormFirstPage firstPage = preparedActions.getFormFirstPage();
            firstPage.setPhoneNumber(errorPhoneNumber).clickNextButton();

            //уточнить у Бехи
            result = firstPage.getErrorTextSpanElement(expected);
            //.//span[text()='Введен некорректный номер телефона.']

        assertEquals("Ошибка некоректного номера телефона не была показана", expected, result);
    }

    @Test
    @DisplayName("Проверка отсутствия ДУЛ чужой страны при гражданстве РФ")
    @Description("При гражданстве РФ не должно быть возможности постаивть паспорт другой страны")
    public void checkIfСitizenshipIsRussianDulOnlyPasportRfTest() throws InterruptedException {
        String countryRussia = "РОССИЯ";
        boolean result;

            FormFirstPage form = new FormFirstPage(driver);
            result = form
                    .setCitizenship(countryRussia)
                    .isPasportOtherCountryIsAbsentIfCinizenshipRf();

        assertTrue("Ошибка логики, при гражданстве РФ можно выбрать тип дул другой страны",result);
    }
    //доделать проверки обязательных полей
    @After
    public void setDown()
    {
        driver.quit();
    }
}
/*
25
 */