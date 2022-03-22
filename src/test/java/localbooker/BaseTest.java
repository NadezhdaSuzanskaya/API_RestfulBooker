package localbooker;

import com.google.gson.internal.bind.util.ISO8601Utils;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import model.User;
import org.apache.http.HttpStatus;

import static constants.Endpoints.AUTH_URN;
import static io.restassured.RestAssured.given;


public class BaseTest {

    RequestSpecification requestSpec = new RequestSpecBuilder()
            .setContentType(ContentType.JSON)
            .setBaseUri("http://localhost:3001/")
            .build();
    public static final User admin = new User("admin", "password123");

    public String  GetToken3Test() {
        String token=    given()
                .spec(requestSpec)
                .body(admin)
                .when()
                .log().body()
                .post(AUTH_URN)
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .body().jsonPath().get().toString();
        return token;
    }
    String token1 = GetToken3Test().substring(7,22);




    RequestSpecification requestSpecWithCookie = new RequestSpecBuilder()
            .setContentType(ContentType.JSON)

            .addCookie("token",token1)
            .setBaseUri("http://localhost:3001/")
            .build();
}
