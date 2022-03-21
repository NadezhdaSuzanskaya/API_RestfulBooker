package adapter;

import com.google.gson.Gson;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import lombok.AllArgsConstructor;
import lombok.Setter;
import model.Booking;
import org.apache.http.HttpStatus;

import static io.restassured.RestAssured.given;

@Setter
public abstract class BaseAdapter {

    RequestSpecification requestSpec = new RequestSpecBuilder()
            .setContentType(ContentType.JSON)
            .setBaseUri("http://localhost:3001/")
            .build();

    private String endpoint;
    protected Gson gson = new Gson();

    public BaseAdapter(String endpoint) {
        this.endpoint = endpoint;
    }

    public ValidatableResponse post(String payload) {
        return given()
                .spec(requestSpec)
                .body(payload)
                .when()
                .post(endpoint)
                .then()
                .statusCode(HttpStatus.SC_OK);
    }

    public ValidatableResponse post(Booking model) {
        return given()
                .spec(requestSpec)
                .body(model)
                .when()
                .post(endpoint)
                .then()
                .statusCode(HttpStatus.SC_OK);
    }

    public ValidatableResponse get() {
        return given()
                .spec(requestSpec)
                .when()
                .get(endpoint)
                .then()
                .statusCode(HttpStatus.SC_OK);
    }

}
