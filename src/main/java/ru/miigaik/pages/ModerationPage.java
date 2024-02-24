package ru.miigaik.pages;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import ru.miigaik.action.PreparedActions;

public class ModerationPage extends BasePage
{
    private PreparedActions preparedActions;
    private By sortByTime = By.xpath("//*[@id='root']/main/div/div[2]/div/ul[1]/li[4]/svg");
    private By searchBar = By.xpath(".//input[@placeholder='Поиск']");
    private By labelFormNotChecked = By.xpath(".//label[text()='Не проверено']");
    private By labelFormAccepted = By.xpath(".//label[text()='Принято']");
    private By labelFormDeclined = By.xpath(".//label[text()='Отклонено']");
    private By currentRowRorm = By.xpath("//*[@id=\"root\"]/main/div/div[2]/div/ul[2]");

    private By filterByStatusButton = By.xpath("//*[@id=\"root\"]/main/div/div[2]/div/ul[1]/li[6]/div");
    private By filterByEducationProgram = By.xpath("//*[@id=\"root\"]/main/div/div[2]/div/ul[1]/li[3]/div");
    private By filterButtonAccepted = By.xpath("//*[@id=\"root\"]/main/div/div[2]/div/ul[1]/li[6]/div/ul/div[1]/p[text()='Принято']");
    private By filterButtonDeclined = By.xpath("//*[@id=\"root\"]/main/div/div[2]/div/ul[1]/li[6]/div/ul/div[2]/p[text()='Отклонено']");
    private By filterButtonNotChecked = By.xpath("//*[@id=\"root\"]/main/div/div[2]/div/ul[1]/li[6]/div/ul/div[3]/p[text()='Не проверено']");
    private By educationProgramHardCode = By.xpath("//*[@id=\"root\"]/main/div/div[2]/div/ul[1]/li[3]/div/ul/div[p[text()='Геодезические и аэрокосмические технологии в инфраструктуре пространственных данных (программа профессиональной переподготовки)']]");
    // private By educationProgramWontSeeInFilter = By.xpath(".//p[text()='Геосервисы как средство цифровой трансформации экономики (программа повышения квалификации)']");
    private By formWithBanFilteredEducationProgramWontSee = By.xpath(".//ul/li/p[text()='Геосервисы как средство цифровой трансформации экономики (программа повышения квалификации)']");
    private By formWithFilteredEducationProgramWillSee = By.xpath(".//ul/li/p[text()='Геодезические и аэрокосмические технологии в инфраструктуре пространственных данных (программа профессиональной переподготовки)']");

    public ModerationPage(WebDriver driver)
    {
        super(driver);
    }


    @Step("Проверка видимости по ее почте")
    public boolean isFormEmailVisible(String email)
    {
        return findElementOnPage(By.xpath(".//label[text()='" + email + "']")).isDisplayed();
    }

    @Step("Устанвока занчения в поля поиска")
    public ModerationPage setDataToSearch(String data)
    {
        setDataToInputElement(searchBar, data);
        return this;
    }

    @Step("Проверка соответствия анкеты и ее статуса")
    public boolean isValueStatusCurrentForm(String email, String status)
    {
        Allure.addAttachment("Почта анкеты", email);
        Allure.addAttachment("Статус анкеты", status);

        return findElementOnPage(By.xpath(
                "//ul[li/p/div/label[text()='"+email+"']]/li/p/div/label[text()='"+status+"']"
        )).isDisplayed();
    }

    @Step("Использование фильтра отображения анкет со статусом 'Принято'")
    public ModerationPage clickFilterAccept()
    {
        WebElement filterWebElement = findElementOnPage(filterByStatusButton);
        new Actions(driver).moveToElement(filterWebElement).perform();

        clickElementOnPage(filterButtonAccepted);
        return this;
    }

    @Step("Использование фильтра отображения анкет со статусом 'Отклонено'")
    public ModerationPage clickFilterDeclined()
    {
        WebElement filterWebElement = findElementOnPage(filterByStatusButton);
        new Actions(driver).moveToElement(filterWebElement).perform();

        clickElementOnPage(filterButtonDeclined);
        return this;
    }

    @Step("Использование фильтра отображения анкет со статусом'Не проверено'")
    public ModerationPage clickFilterNotChecked()
    {
        WebElement filterWebElement = findElementOnPage(filterByStatusButton);
        new Actions(driver).moveToElement(filterWebElement).perform();

        clickElementOnPage(filterButtonNotChecked);
        return this;
    }

    @Step("Использование фильтра отображения анкет с определенной программой ДПО ")
    public ModerationPage clickHardCodeEducationProgramFilter()
    {
        WebElement filterWebElement = findElementOnPage(filterByEducationProgram);
        new Actions(driver).moveToElement(filterWebElement).perform();

        clickElementOnPage(educationProgramHardCode);
        return this;
    }

    @Step("Проверка видимости анкет послее использования фильтра по программам ДПО")
    public boolean isVisibleOnlyHardCodeEducationProgramForms()
    {
        boolean result;
        try
        {
            result = (findElementOnPage(formWithBanFilteredEducationProgramWontSee).isDisplayed());

        }
        catch (Exception e)
        {
            result = findElementOnPage(formWithFilteredEducationProgramWillSee).isDisplayed();
        }

        return result;
    }

    @Step("Проверка видимости только принятых форм")
    public boolean isVisibleOnlyAcceptedForms()
    {
        boolean result;
        try
        {
            result = (findElementOnPage(labelFormNotChecked).isDisplayed())
                    |
                    (findElementOnPage(labelFormDeclined).isDisplayed());
            result = false;
        }
        catch (Exception e)
        {
            result = findElementOnPage(labelFormAccepted).isDisplayed();
        }

        return result;
    }

    @Step("Проверка видимости только отклоненных форм")
    public boolean isVisibleOnlyDeclinedForms()
    {
        boolean result;
        try
        {
            result = (findElementOnPage(labelFormNotChecked).isDisplayed())
                    |
                    (findElementOnPage(labelFormAccepted).isDisplayed());
            result = false;
        }
        catch (Exception e)
        {
            result = findElementOnPage(labelFormDeclined).isDisplayed();
        }

        return result;
    }

    @Step("Проверка видимости только не проверенных форм")
    public boolean isVisibleOnlyNotCheckedForms()
    {
        boolean result;
        try
        {
            result = (findElementOnPage(labelFormDeclined).isDisplayed())
                    |
                    (findElementOnPage(labelFormAccepted).isDisplayed());
            result = false;
        }
        catch (Exception e)
        {
            result = findElementOnPage(labelFormNotChecked).isDisplayed();
        }

        return result;
    }

    @Step("Выбор анкеты по ее почте")
    public ModerationPage clickFormByEmail(String email)
    {
        clickElementOnPage(
                By.xpath(".//ul[li/p/div/label[text()='" + email+ "']]")
        );
        return this;
    }

    @Step("Проверка правильного отображения времени создания анкеты")
    public boolean isTimeOfCreatingFormCorrect(String currtntDayPlusSpace, String time, String email)
    {
        return findElementOnPage(By.xpath(
                ".//ul[li/p/div/label[text()='"+email+"']]/li/p[contains(text(),'"+currtntDayPlusSpace+"') and contains(text(),'"+time+"')]"

        )).isDisplayed();
    }
}

