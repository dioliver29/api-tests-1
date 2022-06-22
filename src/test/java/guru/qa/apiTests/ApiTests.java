package guru.qa.apiTests;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ApiTests {

    @Tag("first api tests")
    @Test
    void checkTotalPages() {
        given()
                .when()
                .get("https://reqres.in/api/users")
                .then()
                .log().body()
                .body("total_pages", is(2));
    }

    @Test
    void createUser() {

        String body = "{ \"name\": \"morpheus\", \"job\": \"leader\" }";
        String name = "morpheus";

        given()
                .body(body)
                .contentType(ContentType.JSON)
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .statusCode(201)
                .log().status()
                .log().body()
                .body("name", is(name));
    }

    @Test
    void notFoundTest() {
        given()
                .when()
                .get("https://reqres.in/api/unknown/23")
                .then()
                .log().status()
                .statusCode(404)
                .log().body();
    }

    @Test
    void updateUserJob() {
        String body = "{ \"name\": \"morpheus\", \"job\": \"QaGuru\" }";
        String job = "QaGuru";

        given()
                .body(body)
                .contentType(ContentType.JSON)
                .when()
                .patch("https://reqres.in/api/user/2")
                .then()
                .statusCode(200)
                .log().status()
                .log().body()
                .body("job", is(job));
    }

    @Test
    void foundPantoneValueWithId1() {
        given()
                .when()
                .get("https://reqres.in/api/unknown")
                .then()
                .log().body()
                .body("data.findAll { it.id = 1}.pantone_value", hasItem("15-4020"));
    }
}
