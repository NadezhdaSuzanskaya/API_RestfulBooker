package localbooker;

import io.restassured.http.ContentType;
import model.Booking;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import static constants.Endpoints.BOOKING_URN;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class CreateBookingTest extends BaseTest {

    public static final String BOOKING_PATH = "booking";

    @Test
    public void createBookingTest() {
        model.Booking createdBooking = given()
                .spec(requestSpec)
                .body(new model.Booking())
                .when()
                .log().body()
                .post(BOOKING_URN)
                .then()
                .log().body()
                .statusCode(200)
                .extract()
                .jsonPath()
                .getObject(BOOKING_PATH, model.Booking.class);
        System.out.println(createdBooking);
        System.out.println(new model.Booking());
        assertThat(createdBooking, equalTo(new model.Booking()));
    }
    @Test
    public void checkRequiredFirstnameTest() {
        model.Booking createdBooking = new model.Booking();
        createdBooking.setFirstname("");
        given()
                .spec(requestSpec)
                .body(createdBooking)
                .when()
                .log().body()
                .post(BOOKING_URN)
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    public void checkRequiredLastnameTest() {
        model.Booking createdBooking = new model.Booking();
        createdBooking.setLastname("");
        given()
                .spec(requestSpec)
                .body(createdBooking)
                .when()
                .log().body()
                .post(BOOKING_URN)
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }
}
