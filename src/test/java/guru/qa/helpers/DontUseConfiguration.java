package guru.qa.helpers;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;


import static com.codeborne.selenide.Selenide.closeWebDriver;

public class DontUseConfiguration {

    //public static String login = "some@mail.com";
   // public static String password = "some@password";
   // public static String authCookieName = "NOPCOMMERCE.AUTH";

    @BeforeAll
    static void configure() {
       // com.codeborne.selenide.Configuration.baseUrl = "http://demowebshop.tricentis.com";
        //RestAssured.baseURI = "http://demowebshop.tricentis.com";
        com.codeborne.selenide.Configuration.baseUrl = ConfigForTests.config.url();
        RestAssured.baseURI = ConfigForTests.config.url();
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);
        com.codeborne.selenide.Configuration.browserCapabilities = capabilities;

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
