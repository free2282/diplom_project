package pages;

import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.miigaik.action.PreparedActions;
import ru.miigaik.browser.Browsers;
import ru.miigaik.pages.FormSecondPage;
import ru.miigaik.pages.ModerationPage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static ru.miigaik.browser.Browsers.*;

@RunWith(Parameterized.class)
public class ModerationPageTest
{
    private PreparedActions preparedActions;
    private Browsers browsers;

    public ModerationPageTest(Browsers browsers)
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
        FormSecondPage formSecondPage = preparedActions.logIn().fillFullForm().getFormSecondPage();
        formSecondPage.clickExitButton();
        preparedActions.logInOnModerationPage();
    }

    @Test
    @DisplayName("Проверка видимости только что созданной анкеты а модераторской части клиента")
    public void visibleHasCreatedFormTest()
    {
        String emailInForm = preparedActions.getEmail();

        ModerationPage moderationPage = new ModerationPage(preparedActions.getDriver());
        boolean result = moderationPage.isFormEmailVisible(emailInForm);
        assertTrue("Только что созданная анкета не была отображена на модераторской части ",result);
    }


    @Test
    @DisplayName("Проверка правильности присвоения статуса 'Не проверено' только что созданной анкет на модераторской частие")
    public void checkHasCreatedFormHasStatusNotCheckedTest()
    {
        String emailInForm = preparedActions.getEmail();

        ModerationPage moderationPage = new ModerationPage(preparedActions.getDriver());
        boolean result = moderationPage.setDataToSearch(emailInForm).isValueStatusCurrentForm(emailInForm, "Не проверено");
        assertTrue("Только что созданной анкете был присвоен не тот статус", result);
    }



    @Test
    @DisplayName("Проверка видимости анкет с фильтром 'Принято'")
    public void checkFilterFormsAcceptedFormsTest() throws InterruptedException
    {
        ModerationPage moderationPage = new ModerationPage(preparedActions.getDriver());

        boolean result = moderationPage
                .clickFilterAccept()
                .isVisibleOnlyAcceptedForms();

        assertTrue("Фильтр по статусу 'Принято' не сработал",result);
    }

    @Test
    @DisplayName("Проверка видимости анкет с фильтром 'Отклонено'")
    public void checkFilterFormsDeclinedFormsTest() throws InterruptedException
    {
        ModerationPage moderationPage = new ModerationPage(preparedActions.getDriver());

        boolean result = moderationPage
                .clickFilterDeclined()
                .isVisibleOnlyDeclinedForms();

        assertTrue("Фильтр по статусу 'Отклонено' не сработал",result);
    }

    @Test
    @DisplayName("Проверка видимости анкет с фильтром 'Не проверено'")
    public void checkFilterFormsNotCheckedFormsTest() throws InterruptedException
    {
        ModerationPage moderationPage = new ModerationPage(preparedActions.getDriver());

        boolean result = moderationPage
                .clickFilterNotChecked()
                .isVisibleOnlyNotCheckedForms();

        assertTrue("Фильтр по статусу 'Не проверено' не сработал",result);
    }

//    @Test
//    @DisplayName()
//    public void clickFormTest()
//    {
//        String emailInForm = preparedActions.getEmail();
//        new ModerationPage(preparedActions.getDriver())
//                .clickFormByEmail(emailInForm);
//    }

    @Test
    @DisplayName("Проверка видимости анкет с фильтром по программе обучения")
    public void clickEducationProgramFilterTest()
    {
        ModerationPage moderationPage = new ModerationPage(preparedActions.getDriver());

        boolean result = moderationPage
                .clickHardCodeEducationProgramFilter()
                .isVisibleOnlyHardCodeEducationProgramForms();

        assertTrue("Фильтр п опрограммамм обучения не сработал",result);
    }

    @Test
    @DisplayName("Проверка совпадения реального времени создания анкеты со временеем, указанным в анкете.")
    public void checkTimeWhenCreatedFormTest()
    {
        String currentTime = preparedActions.getFormSecondPage().getCurrentTimeOfCreatingForm();
        String email = preparedActions.getEmail();
        String currentDay = preparedActions.getFormSecondPage().getCurrentDate();
        ModerationPage moderationPage = new ModerationPage(preparedActions.getDriver());

        System.out.println(currentTime + "текущее время");

        System.out.println(currentDay + "текущий день");

        boolean result = moderationPage.isTimeOfCreatingFormCorrect(currentDay, currentTime, email);
        assertTrue("Время создания анкеты не совпало с реальным временем",result);
    }
}
