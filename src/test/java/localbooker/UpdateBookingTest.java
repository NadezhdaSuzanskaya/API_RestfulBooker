package localbooker;

import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import static constants.Endpoints.BOOKING_ID_URN_PATTERN;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class UpdateBookingTest extends BaseTest {
    public static final String LIST_OF_BOOKING = "[{bookingid=15}, {bookingid=14}, {bookingid=13}, {bookingid=12}, {bookingid=11}]";

    @Test
    public void checkUpdateValueWithoutCookieTest() {

        String t = given()
                .spec(requestSpec)
                .body(new model.Booking())
                .when()
                .log().all()
                .put(BOOKING_ID_URN_PATTERN+"12")
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .body().jsonPath().get().toString();
        assertThat(t, equalTo(LIST_OF_BOOKING));

    }
}
