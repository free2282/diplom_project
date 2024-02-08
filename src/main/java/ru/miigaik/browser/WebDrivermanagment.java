package ru.miigaik.browser;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class WebDrivermanagment
{
    private WebDriver driver;

    public WebDriver setDriver(Browsers browser)
    {


        switch (browser) {
            case CHROME:
                driver = getChromeDriver();
                break;
            case YANDEX:
                driver = getYandexDriver();
                break;
            case FIREFOX:
                driver = getFireFoxDriver();
            case EDGE:
                driver = getEdgeBrowser();
        }
        return driver;
    }

    @Step("Проверка теста на chrome браузере")
    private WebDriver getChromeDriver()
    {
        System.setProperty("webdriver.chrome.driver", "browsers/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");
        options.addArguments("--remote-allow-origins=*");
        options.setHeadless(false);
        return driver = new ChromeDriver(options);
    }

    @Step("Проверка теста на яндекс браузере")
    private WebDriver getYandexDriver()
    {
        System.setProperty("webdriver.chrome.driver", "browsers/yandexdriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.setHeadless(false);
        return driver = new ChromeDriver(options);
    }

    @Step("Проверка теста на firefox браузере")
    private WebDriver getFireFoxDriver()
    {
        System.setProperty("webdriver.gecko.driver", "browsers/geckodriver.exe");
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--remote-allow-origins=*");
        options.setHeadless(false);
        return driver = new FirefoxDriver();
    }

    @Step("Проверка теста на edge браузере")
    private WebDriver getEdgeBrowser()
    {
        System.setProperty("webdriver.edge.driver", "browsers/msedgedriver.exe");
        return driver = new EdgeDriver();
    }
}
