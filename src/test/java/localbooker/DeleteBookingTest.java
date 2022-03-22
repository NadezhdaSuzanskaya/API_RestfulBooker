package localbooker;


import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import static constants.Endpoints.BOOKING_ID_URN_PATTERN;
import static constants.Endpoints.BOOKING_URN;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class DeleteBookingTest extends  BaseTest {

    public static final String BOOKING_PATH = "booking";
    model.Booking createdBooking = new model.Booking();

    @Test
    public void deleteBookingTest() {
        given()
                .spec(requestSpecWithCookie)
                .when()
                .log().all()
                .delete(BOOKING_ID_URN_PATTERN+"11")
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK);

    }

}
