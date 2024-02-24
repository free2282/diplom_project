package pages;

import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import ru.miigaik.action.PreparedActions;
import ru.miigaik.browser.Browsers;
import ru.miigaik.pages.FormOnModerationSide;
import ru.miigaik.pages.ModerationPage;

import static org.junit.Assert.assertTrue;
import static ru.miigaik.browser.Browsers.*;

@RunWith(Parameterized.class)
public class FormOnModerationSideTest
{
    private PreparedActions preparedActions;
    private ModerationPage moderationPage;
    private WebDriver driver;
    private Browsers browsers;


    public FormOnModerationSideTest(Browsers browsers)
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
        preparedActions = new PreparedActions(browsers).logInOnModerationAndOpenFormByEmail();
        moderationPage = new ModerationPage(preparedActions.getDriver());
        driver = preparedActions.getDriver();
    }

    @Test
    @DisplayName("Проверка присвоения статуса анкете 'Принято'")
    public void checkAcceptFormTest() throws InterruptedException
    {
        boolean result;
        String expectingFormStatus = "Принято";
        String email = preparedActions.getEmail();

        new FormOnModerationSide(driver).clickAccept();

        result = moderationPage.isValueStatusCurrentForm(email, expectingFormStatus);
        assertTrue("Анкета не изменила статус на 'Принято'",result);
    }

    @Test
    @DisplayName("Проверка присвоения статуса анкете 'Отклонено'")
    public void checkDeclineFormTest() throws InterruptedException
    {
        boolean result;
        String expectingFormStatus = "Отклонено";
        String email = preparedActions.getEmail();

        new FormOnModerationSide(driver)
                .clickDeclin()
                .setValueToReasonField()
                .clickSendButtot();

        result = moderationPage
                .isValueStatusCurrentForm(email, expectingFormStatus);
        assertTrue("Анкета не изменила статус на 'Отклонено'",result);
    }

    @Test
    @DisplayName("Проверка правильности заполнения поля 'Имя' после загрузки на сервер")
    public void checkCorrectFillingNameTest()
    {
        boolean result;
        String name = preparedActions.getFormFirstPage().getNameInForm();
        FormOnModerationSide formOnModerationSide = new FormOnModerationSide(driver);

        result = formOnModerationSide.isNameValueOnPageMatchedWithCreatedData(name);

        assertTrue("Вводимые данные в поле 'имя' не совпали с данными из бд",result);
    }

    @Test
    @DisplayName("Проверка правильности заполнения поля 'Фамилия' после загрузки на сервер")
    public void checkCorrectFillingSurnameTest()
    {
        boolean result;
        String name = preparedActions.getFormFirstPage().getNameInForm();
        FormOnModerationSide formOnModerationSide = new FormOnModerationSide(driver);

        result = formOnModerationSide.isSurnameValueOnPageMatchedWithCreatedData(name);
        assertTrue("Вводимые данные в поле 'Фамилия' не совпали с данными из бд",result);
    }

    @Test
    @DisplayName("Проверка правильности заполнения поля 'Отчество' после загрузки на сервер")
    public void checkCorrectFillingPatronymicTest()
    {
        boolean result;
        String patronymic = preparedActions.getFormFirstPage().getPatronymicInForm();
        FormOnModerationSide formOnModerationSide = new FormOnModerationSide(driver);

        result = formOnModerationSide.isPatronymicValueOnPageMatchedWithCreatedData(patronymic);
        assertTrue("Вводимые данные в поле 'Отчество' не совпали с данными из бд",result);
    }

    @Test
    @DisplayName("Проверка правильности заполнения поля 'Пол' после загрузки на сервер")
    public void checkCorrectFillingSexTest()
    {
        boolean result;
        String sex = preparedActions.getFormFirstPage().getSexInForm();
        FormOnModerationSide formOnModerationSide = new FormOnModerationSide(driver);

        result = formOnModerationSide.isSexValueOnPageMatchedWithCreatedData(sex);
        assertTrue("Вводимые данные в поле 'Пол' не совпали с данными из бд",result);
    }

    @Test
    @DisplayName("Проверка правильности заполнения поля 'Дата рождения' после загрузки на сервер")
    public void checkCorrectFillingBirthDateTest()
    {
        boolean result;
        String birthDate = preparedActions.getFormFirstPage().getBirthInForm();
        FormOnModerationSide formOnModerationSide = new FormOnModerationSide(driver);

        result = formOnModerationSide.isBirthOnPageMatchedWithCreatedBirthDate(birthDate);
        assertTrue("Вводимые данные в поле 'Дата рождения' не совпали с данными из бд",result);
    }

    @Test
    @DisplayName("Проверка правильности заполнения поля 'Место рождения' после загрузки на сервер")
    public void checkCorrectFillingBirthPlaceTest()
    {
        boolean result;
        String birthPlace = preparedActions.getFormFirstPage().getBirthPlaceInForm();
        FormOnModerationSide formOnModerationSide = new FormOnModerationSide(driver);

        result = formOnModerationSide.isPlaceOfBirthONPageMatchedWithCreatedPlaceOfBirth(birthPlace);
        assertTrue("Вводимые данные в поле 'Место рождения' не совпали с данными из бд",result);
    }

    @Test
    @DisplayName("Проверка правильности заполнения поля 'Гражданство' после загрузки на сервер")
    public void checkCorrectFillingCinizenshipTest()
    {
        boolean result;
        String cinizenship = preparedActions.getFormFirstPage().getCinizenshipInForm();
        FormOnModerationSide formOnModerationSide = new FormOnModerationSide(driver);

        result = formOnModerationSide.isCinizenShipOnPageMatchedWithCreatedCinizenship(cinizenship);
        assertTrue("Вводимые данные в поле 'Гражданство' не совпали с данными из бд",result);
    }

    @Test
    @DisplayName("Проверка правильности заполнения поля 'Номер телефона' после загрузки на сервер")
    public void checkCorrectFillingPhoneNumberTest()
    {
        boolean result;
        String phoneNuber = preparedActions.getFormFirstPage().getPhoneNumberInForm();
        FormOnModerationSide formOnModerationSide = new FormOnModerationSide(driver);

        result = formOnModerationSide.isPhoneNumberOnPageMatchedWithCreatedPhoneNumber(phoneNuber);
        assertTrue("Вводимые данные в поле 'Номер телефона' не совпали с данными из бд",result);
    }

    @Test
    @DisplayName("Проверка правильности заполнения поля 'Уровень образования' после загрузки на сервер")
    public void checkCorrectFillingEducationLevelTest()
    {
        boolean result;
        String educationLevel = preparedActions.getFormFirstPage().getEducationGradeInForm();
        FormOnModerationSide formOnModerationSide = new FormOnModerationSide(driver);

        result = formOnModerationSide.isEducationGradeOnPageMatchedWithCreatedEducationGrade(educationLevel);
        assertTrue("Вводимые данные в поле 'Уровень образования' не совпали с данными из бд",result);
    }

    @Test
    @DisplayName("Проверка правильности заполнения поля 'Номер снилс' после загрузки на сервер")
    public void checkCorrectFillingNumberSnilsTest()
    {
        boolean result;
        String snils = preparedActions.getFormFirstPage().getSnilsInForm();
        FormOnModerationSide formOnModerationSide = new FormOnModerationSide(driver);

        result = formOnModerationSide.isSnilsOnPageMatchedWithSnils(snils);
        assertTrue("Вводимые данные в поле 'СНИЛС' не совпали с данными из бд",result);
    }

    @Test
    @DisplayName("Проверка правильности заполнения поля 'Даты регистрации СНИЛС' после загрузки на сервер")
    public void checkCorrectFillingSnilsDateTest()
    {
        boolean result;
        String snilsDate = preparedActions.getFormFirstPage().getSnilsDateInForm();
        FormOnModerationSide formOnModerationSide = new FormOnModerationSide(driver);

        result = formOnModerationSide.isSnilsDateOnPageMatchedWithSnilsDate(snilsDate);
        assertTrue("Вводимые данные в поле 'Даты регистрации СНИЛС' не совпали с данными из бд",result);
    }

    @Test
    @DisplayName("Проверка правильности заполнения поля 'Инвалидность' после загрузки на сервер")
    public void checkCorrectFillingDisabilityTest()
    {
        boolean result;
        String disability = preparedActions.getFormFirstPage().getDisabilityInForm();
        FormOnModerationSide formOnModerationSide = new FormOnModerationSide(driver);

        result = formOnModerationSide.isDisabilityOnPageMatchedWithDisability(disability);
        assertTrue("Вводимые данные в поле 'Инвалидность' не совпали с данными из бд",result);
    }

    @Test
    @DisplayName("Проверка правильности заполнения поля 'Программа ДПО' после загрузки на сервер")
    public void checkCorrectFillingEducationProgramTest()
    {
        boolean result;
        String educationProgram = preparedActions.getFormFirstPage().getEducationProgramInForm();
        FormOnModerationSide formOnModerationSide = new FormOnModerationSide(driver);

        result = formOnModerationSide.isEducationProgramOnPageMatchedWithEducationProgram(educationProgram);
        assertTrue("Вводимые данные в поле 'Программа ДПО' не совпали с данными из бд",result);
    }

    @Test
    @DisplayName("Проверка правильности заполнения поля 'Тип документа удостоверяющего личность' после загрузки на сервер")
    public void checkCorrectFillingPasportTypeTest()
    {
        boolean result;
        String pasportType = preparedActions.getFormFirstPage().getPasportTypeInForm();
        FormOnModerationSide formOnModerationSide = new FormOnModerationSide(driver);

        result = formOnModerationSide.isPasportTypeOnPageMatchedWithPasportType(pasportType);
        assertTrue("Вводимые данные в поле 'Тип документа удостоверяющего личность' не совпали с данными из бд",result);
    }

    @Test
    @DisplayName("Проверка правильности заполнения поля 'Серия документа' после загрузки на сервер")
    public void checkCorrectFillingSeriesOfPasportTest()
    {
        boolean result;
        String pasportSeries = preparedActions.getFormFirstPage().getSerisOfPasportInForm();
        FormOnModerationSide formOnModerationSide = new FormOnModerationSide(driver);

        result = formOnModerationSide.isSeriesOfPasportOnPageMatchedWithSeriesOfPasport(pasportSeries);
        assertTrue("Вводимые данные в поле 'Серия документа' не совпали с данными из бд",result);
    }

    @Test
    @DisplayName("Проверка правильности заполнения поля 'Номер документа' после загрузки на сервер")
    public void checkCorrectFillingNumberOfPasportTest()
    {
        boolean result;
        String pasportNumber = preparedActions.getFormFirstPage().getNumberOfPasportInForm();
        FormOnModerationSide formOnModerationSide = new FormOnModerationSide(driver);

        result = formOnModerationSide.iSNumberOfPasportOnPageMatchedWithNumberOfPasport(pasportNumber);
        assertTrue("Вводимые данные в поле 'Номер документа' не совпали с данными из бд",result);
    }

    @Test
    @DisplayName("Проверка правильности заполнения поля 'Орган, выдавший документ удостоверяющий личность' после загрузки на сервер")
    public void checkCorrectFillingAgencyThatGavePasportTest()
    {
        boolean result;
        String agencyThatGavePasport = preparedActions.getFormFirstPage().getGovermentAgencyGavePasportInForm();
        FormOnModerationSide formOnModerationSide = new FormOnModerationSide(driver);

        result = formOnModerationSide.iSGovernmentAgencyGavePasportOnPageMatchedGovAgencyGavePasport(agencyThatGavePasport);
        assertTrue("Вводимые данные в поле 'Орган, выдавший документ удостоверяющий личность' не совпали с данными из бд",result);
    }

    @Test
    @DisplayName("Проверка правильности заполнения поля 'Код подразделения' после загрузки на сервер")
    public void checkCorrectFillingCodeOfAgencyTest()
    {
        boolean result;
        String codeOfAgencyThatGavePasport = preparedActions.getFormFirstPage().getCodeOfAgencyGavePasportInForm();
        FormOnModerationSide formOnModerationSide = new FormOnModerationSide(driver);

        result = formOnModerationSide.isGovernmentCodeOfPasportOnPageMatchedWithGovernmentCode(codeOfAgencyThatGavePasport);
        assertTrue("Вводимые данные в поле 'Код подразделения' не совпали с данными из бд",result);
    }

    @Test
    @DisplayName("Проверка правильности заполнения поля 'Дата выдачи паспорта' после загрузки на сервер")
    public void checkCorrectFillingDateOfIssueGivingTest()
    {
        boolean result;
        String dateOfIssueGivingPasport = preparedActions.getFormFirstPage().getDateOfIssuePasportInForm();
        FormOnModerationSide formOnModerationSide = new FormOnModerationSide(driver);

        result = formOnModerationSide.isIssuDateOfPasportGivingOnPageMatchedWithIssueDate(dateOfIssueGivingPasport);
        assertTrue("Вводимые данные в поле 'Дата выдачи паспорта' не совпали с данными из бд",result);
    }

    @Test
    @DisplayName("Проверка правильности заполнения поля 'Индекс' после загрузки на сервер")
    public void checkCorrectIndexTest()
    {
        boolean result;
        String indexOfRegistration = preparedActions.getFormFirstPage().getIndexOfRegistrationInForm();
        FormOnModerationSide formOnModerationSide = new FormOnModerationSide(driver);

        result = formOnModerationSide.isIndexOfRegistrationOnPageMatchedWithIndex(indexOfRegistration);
        assertTrue("Вводимые данные в поле 'Индекс' не совпали с данными из бд",result);
    }

    @Test
    @DisplayName("Проверка правильности заполнения поля 'Страна регистрации' после загрузки на сервер")
    public void checkCorrectCountryTest()
    {
        boolean result;
        String countryOfRegistration = preparedActions.getFormFirstPage().getCountryOfRegistrationInForm();
        FormOnModerationSide formOnModerationSide = new FormOnModerationSide(driver);

        result = formOnModerationSide.isContyOfRegistrationOnPageMatchedWithCountry(countryOfRegistration);
        assertTrue("Вводимые данные в поле 'Страна регистрации' не совпали с данными из бд",result);
    }

    @Test
    @DisplayName("Проверка правильности заполнения поля 'Регион' после загрузки на сервер")
    public void checkCorrectFillingStateTest()
    {
        boolean result;
        String stateOfRegistration = preparedActions.getFormFirstPage().getStateOfRegistrationInForm();
        FormOnModerationSide formOnModerationSide = new FormOnModerationSide(driver);

        result = formOnModerationSide.isStateOfRegistrationOnPageMatchedWithState(stateOfRegistration);
        assertTrue("Вводимые данные в поле 'Регион' не совпали с данными из бд",result);
    }

    @Test
    @DisplayName("Проверка правильности заполнения поля 'населенный пункт' после загрузки на сервер")
    public void checkCorrectFillingCityTest()
    {
        boolean result;
        String cityOfRegistration = preparedActions.getFormFirstPage().getCityOfRegistrationInForm();
        FormOnModerationSide formOnModerationSide = new FormOnModerationSide(driver);

        result = formOnModerationSide.isCityOfRegistrationOnPageMatchedWithCity(cityOfRegistration);
        assertTrue("Вводимые данные в поле '' не совпали с данными из бд",result);
    }

    @Test
    @DisplayName("Проверка правильности заполнения поля 'Улица' после загрузки на сервер")
    public void checkCorrectFillingStreetTest()
    {
        boolean result;
        String streetOfRegistration = preparedActions.getFormFirstPage().getStreetOfRegistratinInForm();
        FormOnModerationSide formOnModerationSide = new FormOnModerationSide(driver);

        result = formOnModerationSide.isStreetOfRegistrationOnPageMatchedWithStreet(streetOfRegistration);
        assertTrue("Вводимые данные в поле 'Улица' не совпали с данными из бд",result);
    }

    @Test
    @DisplayName("Проверка правильности заполнения поля 'Дом' после загрузки на сервер")
    public void checkCorrectFillingHouseTest()
    {
        boolean result;
        String houseOfRegistration= preparedActions.getFormFirstPage().getHouseOfRegistrationInForm();
        FormOnModerationSide formOnModerationSide = new FormOnModerationSide(driver);

        result = formOnModerationSide.isHouseOfRegistrationOnPageMatchedWithHouse(houseOfRegistration);
        assertTrue("Вводимые данные в поле 'Дом' не совпали с данными из бд",result);
    }

    @Test
    @DisplayName("Проверка правильности заполнения поля 'Наименование образовательной организации' после загрузки на сервер")
    public void checkCorrectFillingEducationOrganizationNameTest()
    {
        boolean result;
        String educationOrganizationName = preparedActions.getFormFirstPage().getNameOfEducationOrganizationInForm();
        FormOnModerationSide formOnModerationSide = new FormOnModerationSide(driver);

        result = formOnModerationSide.isNameOfEducationFormOnPageMatchedWithEducationName(educationOrganizationName);
        assertTrue("Вводимые данные в поле 'Наименование образовательной организации' не совпали с данными из бд",result);
    }

    @Test
    @DisplayName("Проверка правильности заполнения поля 'Специальность / направление подготовки' после загрузки на сервер")
    public void checkCorrectFillingTest()
    {
        boolean result;
        String universitySpeciality = preparedActions.getFormFirstPage().getUniversitySpecialtyInForm();
        FormOnModerationSide formOnModerationSide = new FormOnModerationSide(driver);

        result = formOnModerationSide.isUniversitySpecialtyInFormOnPageMatchedWithUniversitySpecialty(universitySpeciality);
        assertTrue("Вводимые данные в поле 'Специальность / направление подготовки' не совпали с данными из бд",result);
    }

    @Test
    @DisplayName("Проверка правильности заполнения поля 'Серия и номер диплома' после загрузки на сервер")
    public void checkCorrectFillingDiplomSeriesAndNumberHouseTest()
    {
        boolean result;
        String seriesAndNumberOfDiplom = preparedActions.getFormFirstPage().getSeriesAndNumberOfDiplomInForm();
        FormOnModerationSide formOnModerationSide = new FormOnModerationSide(driver);

        result = formOnModerationSide.isDiplomSeriesAndNumberOnPageMatchedWithSerieswAndNumber(seriesAndNumberOfDiplom);
        assertTrue("Вводимые данные в поле 'Серия и номер диплома' не совпали с данными из бд",result);
    }

    @Test
    @DisplayName("Проверка правильности заполнения поля 'Дата выдачи' после загрузки на сервер")
    public void checkCorrectFillingDateOfReceiptDiplomTest()
    {
        boolean result;
        String dateOfReceiptDiplom = preparedActions.getFormFirstPage().getDateOfReceiptDiplomInForm();
        FormOnModerationSide formOnModerationSide = new FormOnModerationSide(driver);

        result = formOnModerationSide.isDiplomDateOfReceiptOnPageMatchedWithDateOfReceipt(dateOfReceiptDiplom);
        assertTrue("Вводимые данные в поле 'Дата выдачи' не совпали с данными из бд",result);
    }

    @Test
    @DisplayName("Проверка правильности заполнения поля 'Год окончания' после загрузки на сервер")
    public void checkCorrectFillingYearOfEndingTest()
    {
        boolean result;
        String yearOfEnding = preparedActions.getFormFirstPage().getYearOfEndingEducationInForm();
        FormOnModerationSide formOnModerationSide = new FormOnModerationSide(driver);

        result = formOnModerationSide.isDiplomYearOfEndingOnPageMatchedWithYearOfEnding(yearOfEnding);
        assertTrue("Вводимые данные в поле 'Год окончания' не совпали с данными из бд",result);
    }

    @Test
    @DisplayName("Проверка правильности заполнения поля 'Место работы' после загрузки на сервер")
    public void checkCorrectFillingPlaceOfWorkTest()
    {
        boolean result;
        String placeOfWork = preparedActions.getFormFirstPage().getPlaceOfWorkingInForm();
        FormOnModerationSide formOnModerationSide = new FormOnModerationSide(driver);

        result = formOnModerationSide.isPlaceOfWorkOnPageMatchedWithPlaceOfWork(placeOfWork);
        assertTrue("Вводимые данные в поле 'Место работы' не совпали с данными из бд",result);
    }

    @Test
    @DisplayName("Проверка правильности заполнения поля 'Занимаемая должность' после загрузки на сервер")
    public void checkCorrectFillingWorkingPositionTest()
    {
        boolean result;
        String workingPosition = preparedActions.getFormFirstPage().getWorkingPositionInForm();
        FormOnModerationSide formOnModerationSide = new FormOnModerationSide(driver);

        result = formOnModerationSide.isWorkingPositionOnPageMatchedWithWorkingPosition(workingPosition);
        assertTrue("Вводимые данные в поле 'Занимаемая должность' не совпали с данными из бд",result);
    }

    @Test
    @DisplayName("Проверка правильности заполнения поля 'Период работы' после загрузки на сервер")
    public void checkCorrectFillingPeriodOfWorkingTest()
    {
        boolean result;
        String periodOfWorking = preparedActions.getFormFirstPage().getPeriodOfWorkInForm();
        FormOnModerationSide formOnModerationSide = new FormOnModerationSide(driver);

        result = formOnModerationSide.isPeriodOfWorkingEndingOnPageMatchedWithYearOfEnding(periodOfWorking);
        assertTrue("Вводимые данные в поле 'Период работы' не совпали с данными из бд",result);
    }
}
