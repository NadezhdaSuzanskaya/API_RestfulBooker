package localbooker;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ValidatableResponse;
import model.Booking;
import model.Reason;
import model.User;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import static constants.Endpoints.AUTH_URN;
import static constants.Endpoints.BOOKING_URN;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class AuthTest extends BaseTest {

    public static final String CREDS = """
            {
                "username" : "admin",
                "password" : "password1234"
            }
            """;



    public static final User admin = new User("admin", "password123");
    public static final User wrong_cred = new User("user111", "password111");
    public static final String TOKEN_PATH = "token";
    public static final String REASON_PATH = "";

    @Test
    public void GetTokenTest() {
        String actualToken = given()
                .spec(requestSpec)
                .body(CREDS)
        .when()
                .log().all()
                .post(AUTH_URN)
        .then()
                .log().all()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .path(TOKEN_PATH);
        assertThat(actualToken, allOf(notNullValue(), hasLength(15)));
    }

    @Test
    public void GetToken2Test() {
        given()
                .spec(requestSpec)
                .body(CREDS)
        .when()
                .post(AUTH_URN)
        .then()
                .statusCode(HttpStatus.SC_OK)
                .body(TOKEN_PATH, allOf(notNullValue(), hasLength(15)));
    }

    @Test
    public void GetToken3Test() {
        given()
                .spec(requestSpec)
                .body(admin)
        .when()
                .log().body()
                .post(AUTH_URN)
        .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK)
                .body(TOKEN_PATH, allOf(notNullValue(), hasLength(15)));
    }

    @Test
    public void Check400CodeTest() {
        given()
                .spec(requestSpec)
                .body(wrong_cred)
                .when()
                .log().body()
                .post(AUTH_URN)
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    public void CheckCorrectReasonForWrongCred() {

        Reason createdReason = given()
                .spec(requestSpec)
                .body(wrong_cred)
                .when()
                .log().body()
                .post(AUTH_URN)
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .jsonPath()
       .getObject(REASON_PATH, Reason.class);
        assertThat(createdReason, equalTo(new Reason()));
    }

}
