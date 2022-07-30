package guru.qa.demoShopAuth;

import com.codeborne.selenide.WebDriverRunner;
import guru.qa.helpers.TestBase;
import io.qameta.allure.restassured.AllureRestAssured;
import org.junit.jupiter.api.*;
import org.openqa.selenium.Cookie;
import org.junit.jupiter.api.Test;


import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static guru.qa.helpers.CustomApiListener.withCustomTemplates;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class AuthorizationTest extends TestBase {

 //       @Tag("demowebshop")
        @Test
        @DisplayName("Successful authorization to some demowebshop (UI)")
        void loginTest() {
            step("Open login page", () ->
                    open("/login"));

            step("Fill login form", () -> {
                $("#Email").setValue(login);
                $("#Password").setValue(password)
                        .pressEnter();
            });

            step("Verify successful authorization", () ->
                    $(".account").shouldHave(text(login)));
        }
        @Test
        @DisplayName("Successful authorization to some demowebshop (API + UI)")
        void loginWithApiTest() {
            step("Get cookie by api and set it to browser", () -> {
                String authCookieValue = given()
                        .filter(withCustomTemplates())
                        .contentType("application/x-www-form-urlencoded")
                        .formParam("Email", login)
                        .formParam("Password", password)
                        .log().all()
                        .when()
                        .post("/login")
                        .then()
                        .log().all()
                        .statusCode(302)
                        .extract().cookie(authCookieName);

                step("Open minimal content, because cookie can be set when site is opened", () ->
                        open("/Themes/DefaultClean/Content/images/logo.png"));
                step("Set cookie to to browser", () -> {
                    Cookie authCookie = new Cookie(authCookieName, authCookieValue);
                    WebDriverRunner.getWebDriver().manage().addCookie(authCookie);
                });
            });

            step("Open main page", () ->
                    open(""));
            step("Verify successful authorization", () ->
                    $(".account").shouldHave(text(login)));
        }

        @Test
        @DisplayName("Successful authorization to some demowebshop (API + UI)")
        void loginWithApiAndAllureListenerTest() {
            step("Get cookie by api and set it to browser", () -> {
                String authCookieValue = given()
                        .filter(new AllureRestAssured())
                        .contentType("application/x-www-form-urlencoded")
                        .formParam("Email", login)
                        .formParam("Password", password)
                        .log().all()
                        .when()
                        .post("/login")
                        .then()
                        .log().all()
                        .statusCode(302)
                        .extract().cookie(authCookieName);

                step("Open minimal content, because cookie can be set when site is opened", () ->
                        open("/Themes/DefaultClean/Content/images/logo.png"));
                step("Set cookie to to browser", () -> {
                    Cookie authCookie = new Cookie(authCookieName, authCookieValue);
                    WebDriverRunner.getWebDriver().manage().addCookie(authCookie);
                });
            });

            step("Open main page", () ->
                    open(""));
            step("Verify successful authorization", () ->
                    $(".account").shouldHave(text(login)));
        }

        @Test
        @DisplayName("add Product To Cart")
        void addProductToCartWithDynamicCookieTest() {
            String authCookieValue = given()
                    .filter(withCustomTemplates())
                    .contentType("application/x-www-form-urlencoded")
                    .formParam("Email", login)
                    .formParam("Password", password)
                    .log().all()
                    .when()
                    .post("/login")
                    .then()
                    .log().all()
                    .statusCode(302)
                    .extract().cookie(authCookieName);

            String body = "product_attribute_72_5_18=53" +
                    "&product_attribute_72_6_19=54" +
                    "&product_attribute_72_3_20=57" +
                    "&addtocart_72.EnteredQuantity=1";

            String cartSize = given()
                    .filter(withCustomTemplates())
                    .contentType("application/x-www-form-urlencoded")
                    .body(body)
                    .cookie(authCookieName, authCookieValue)
                    .log().all()
                    .when()
                    .post("/addproducttocart/details/72/1")
                    .then()
                    .log().all()
                    .statusCode(200)
                    .body("success", is(true))
                    .body("message", is("The product has been added to your <a href=\"/cart\">shopping cart</a>"))
                    .body("updateflyoutcartsectionhtml", notNullValue())
                    .body("updatetopcartsectionhtml", notNullValue())
                    .extract()
                    .path("updatetopcartsectionhtml");

            step("Open minimal content, because cookie can be set when site is opened", () ->
                    open("/Themes/DefaultClean/Content/images/logo.png"));
            step("Set cookie to to browser", () -> {
                Cookie authCookie = new Cookie(authCookieName, authCookieValue);
                WebDriverRunner.getWebDriver().manage().addCookie(authCookie);
            });

            step("Open main page", () ->
                    open(""));
            step("Verify cart size", () ->
                    $("#topcartlink .cart-qty").shouldHave(text(cartSize)));
        }

    }
