package guru.qa.helpers;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import io.restassured.RestAssured;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class Config1 {
    static ConfigForAuth configForAuth = ConfigFactory.create(ConfigForAuth.class);
    public static String login = configForAuth.login();
    public static String password = configForAuth.password();
    public static String authCookieName = "NOPCOMMERCE.AUTH";

    @BeforeAll
    static void configBeforeAll() {
        ConfigProps configProps = ConfigFactory.create(ConfigProps.class);
        Configuration.remote = ConfigForTests.config.selenideUrl();
        Configuration.baseUrl = ConfigForTests.config.url();
        RestAssured.baseURI = ConfigForTests.config.url();
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);
        Configuration.browserCapabilities = capabilities;
    }

    @AfterEach
    void afterEach() {

        ALlureAttachments.screenshotAs("Final screenshot");;
        ALlureAttachments.pageSource();
        ALlureAttachments.browserConsoleLogs();
        ALlureAttachments.addVideo();

        closeWebDriver();
    }
}
