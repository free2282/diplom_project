package pages;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import ru.miigaik.action.QuickEvent;
import ru.miigaik.browser.Browsers;
import ru.miigaik.pages.ConsumerModel;
import ru.miigaik.pages.FormFirstPage;

import static ru.miigaik.browser.Browsers.*;
import static ru.miigaik.generator.Generator.setConsumersData;

@RunWith(Parameterized.class)
public class FirstPageForm
{
    private QuickEvent quickEvent;
    private Browsers browsers;
    private WebDriver driver;

    public FirstPageForm(Browsers browsers)
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
        quickEvent = new QuickEvent();
        quickEvent.logIn(browsers);
        driver = quickEvent.getDriver();
    }

    @Test
    public void fillFirstRuFormTest()
    {
        quickEvent.fillTheForm();
        FormFirstPage firstPage = quickEvent.getFormFirstPage();
        firstPage.chooseFemaleSex();

    }
//    @Test
//    public void checkFemaleSex()
//    {
//        quickEvent.fillTheForm();
//        FormFirstPage formFirstPage = quickEvent.getFormFirstPage();
//        formFirstPage.chooseFemaleSex();
//    }

    @After
    public void setDown()
    {
        driver.quit();
    }
}
