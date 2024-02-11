package pages;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import ru.miigaik.action.QuickEvent;
import ru.miigaik.browser.Browsers;
import ru.miigaik.pages.FormFirstPage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static ru.miigaik.browser.Browsers.*;

@RunWith(Parameterized.class)
public class FirstPageFormTest
{
    private QuickEvent quickEvent;
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
        quickEvent = new QuickEvent();
        quickEvent.logIn(browsers);
        driver = quickEvent.getDriver();
    }

    @Test
    @DisplayName("Проверка заполнения анкеты и перехода на вторую страницу заполнения данных")
    public void fillFirstRuFormTest() throws InterruptedException
    {
        quickEvent.fillTheForm();
        FormFirstPage firstPage = quickEvent.getFormFirstPage();
        boolean result = firstPage.clickNextButton().isTextSecondPageVisible();

        assertTrue("Текст со второй страницы не был виден",result);
    }

    @Test
    @DisplayName("Проверка показа ошибки после попытки загрзить ошибочный тип файла")
    @Description("Загрузка файлов только разрешена jpg, jpeg, png. В случае ошибочного типа файла ожидается всплывающее окно с просьбой загрузить нужный файл")
    public void checkErrorFileTypeTest() throws InterruptedException
    {
        quickEvent.fillTheForm();
        FormFirstPage firstPage = quickEvent.getFormFirstPage();
        firstPage.uploadPhoto("notPick.mp3");

        boolean result = firstPage.isErrorFileTypeTextVisible();
        assertTrue("После загрузки файла надпись об ошибке не появилась",result);
    }

    @Test
    @DisplayName("Проверка текста ошибки после попытки загрузить ошибочный тип файла")
    @Description("Загрузка файлов только разрешена jpg, jpeg, png. В случае ошибочного типа файла ожидается всплывающее окно с просьбой загрузить нужный файл")
    public void checkErrorFileTypeTextTest() throws InterruptedException
    {
        quickEvent.fillTheForm();
        FormFirstPage firstPage = quickEvent.getFormFirstPage();
        firstPage.uploadPhoto("notPick.mp3");

        String result = firstPage.getTextOfErrorFileType();
        String expected = "Тип файла может быть одним из следующих: image/jpeg image/jpg image/png";
        System.out.println(result);
        assertEquals("Текст ошибки при загрузке ошибочного файла не совпал", expected, result);
    }

    @Test
    @DisplayName("Проверка отображения ошибки неправильного формата телефона")
    public void checkErrorFormatPhoneNumberIsDisplayedTest() throws InterruptedException
    {
        quickEvent.fillTheFormWithoutAutoPhone();
        FormFirstPage firstPage = quickEvent.getFormFirstPage();
        firstPage.setPhoneNumber("(111)111-11-1").clickNextButton();
        //уточнить у Бехи
        boolean result = firstPage.isErrorFormatPhoneNumberNotificationVisible();
        assertTrue(result);
    }

    @Test
    @DisplayName("Проверка текста ошибки неправильного формата телефона")
    public void checkErrorFormatPhoneNumberTextTest() throws InterruptedException
    {
        quickEvent.fillTheFormWithoutAutoPhone();
        FormFirstPage firstPage = quickEvent.getFormFirstPage();
        firstPage.setPhoneNumber("(111)111-11-1").clickNextButton();
        //уточнить у Бехи
        String result = firstPage.getTextOfErrorFormatPhoneNumber();
        assertEquals("Ошибка неверного формата телефона не была показана", "Неверный формат номера телефона", result);
    }

    @Test
    @DisplayName("Проверка отображения ошибки некоректного номера телефона")
    public void checkErrorPhoneNumberIsDisplayedTest() throws InterruptedException
    {
        quickEvent.fillTheFormWithoutAutoPhone();
        FormFirstPage firstPage = quickEvent.getFormFirstPage();
        firstPage.setPhoneNumber("(111)111-11-11").clickNextButton();
        //уточнить у Бехи
        boolean result = firstPage.isIncorrectPhoneNumberNotificationVisible();
        assertTrue(result);
    }

    @Test
    @DisplayName("Проверка текста ошибки некоректного номера телефона")
    public void checkErrorPhoneNumberTextTest() throws InterruptedException
    {
        quickEvent.fillTheFormWithoutAutoPhone();
        FormFirstPage firstPage = quickEvent.getFormFirstPage();
        firstPage.setPhoneNumber("(111)111-11-11").clickNextButton();
        //уточнить у Бехи
        String result = firstPage.getTextOfIncorrectPhoneNumber();
        assertEquals("Ошибка некоректного номера телефона не была показана", "Введен некорректный номер телефона.", result);
    }

    @Test
    @DisplayName("Проверка отсутствия ДУЛ чужой страны при гражданстве РФ")
    @Description("При гражданстве РФ не должно быть возможности постаивть паспорт другой страны")
    public void checkIfСitizenshipIsRussianDulOnlyPasportRfTest() throws InterruptedException
    {
        quickEvent.fillTheForm();
        FormFirstPage firstPage = quickEvent.getFormFirstPage();
        boolean result;
        try
        {
            firstPage.setCitizenship("РОССИЯ").setOtherCountryPasport();
            result = false;
        }
        catch (Exception e)
        {
            result = true;
        }
        assertTrue("Ошибка логики, при гражданстве РФ можно выбрать тип дул другой страны",result);
    }
    //доделать проверки обязательных полей
    @After
    public void setDown()
    {
        driver.quit();
    }
}
