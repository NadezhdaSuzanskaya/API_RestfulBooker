package localbooker;

import io.restassured.specification.RequestSpecification;
import model.BookingDates;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import static constants.Endpoints.BOOKING_ID_URN_PATTERN;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class UpdateBookingTest extends BaseTest {
    public static final String LIST_OF_BOOKING = "[{bookingid=15}, {bookingid=14}, {bookingid=13}, {bookingid=12}, {bookingid=11}]";
    model.Booking createdBooking = new model.Booking();
    public static final String TOTALPRICE_PATH = "totalprice";
    public static final String price = "123454323344444567";

    @Test
    public void checkUpdateValueWithoutCookieTest() {

        given()
                .spec(requestSpec)
                .body(new model.Booking())
                .when()
                .log().all()
                .put(BOOKING_ID_URN_PATTERN + "12")
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void checkUpdateValueWithCookieTest() {

        given()
                .spec(requestSpecWithCookie)
                .body(new model.Booking())
                .when()
                .log().all()
                .put(BOOKING_ID_URN_PATTERN + "12")
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void checkUpdateTotalPriceValueWithMoreThan17Test() {
        createdBooking.setTotalPrice(price);
        String str = given()
                .spec(requestSpecWithCookie)
                .body(createdBooking)
                .when()
                .log().all()
                .put(BOOKING_ID_URN_PATTERN + "12")
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .jsonPath().get("totalprice").toString();
        assertThat(str, equalTo(price));

    }

    @Test
    public void checkUpdateOnlyOneValueWithCookieTest() {

        given()
                .spec(requestSpecWithCookie)
                .body("""
                        {
                            "firstname" : "admin111",
                                "bookingdates": {
                                                "checkin": "2021-03-21",
                                                "checkout": "2022-05-18"
                                            }
                        }
                        """)
                .when()
                .log().all()
                .patch(BOOKING_ID_URN_PATTERN + "12")
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK);
    }
}
