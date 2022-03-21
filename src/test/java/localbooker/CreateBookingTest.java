package localbooker;

import io.restassured.http.ContentType;
import model.Booking;
import model.BookingDates;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import static constants.Endpoints.BOOKING_URN;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class CreateBookingTest extends BaseTest {

    public static final String BOOKING_PATH = "booking";
    model.Booking createdBooking = new model.Booking();

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

        createdBooking.setFirstname("");
        requestExecution();
    }

    @Test
    public void checkRequiredLastnameTest() {

        createdBooking.setLastname("");
        requestExecution();
    }

    @Test
    public void checkDepositpaidIsBooleanTest() {

        createdBooking.setDepositPaid("test");
        requestExecution();
    }

    @Test
    public void checkInvalidCheckInValueTest() {

        createdBooking.setBookingdates(BookingDates.builder().checkin("2020-13-13").build());
        requestExecution();
    }

    @Test
    public void checkInvalidCheckOutValueTest() {

        createdBooking.setBookingdates(BookingDates.builder().checkout("2020-15-18").build());
        requestExecution();
    }

    @Test
    public void checkValueCheckinIsLessValueCheckoutTest() {

        createdBooking.setBookingdates(BookingDates.builder().checkin("2022-01-10").build());
        createdBooking.setBookingdates(BookingDates.builder().checkout("2020-11-11").build());
        requestExecution();
    }

    @Test
    public void checkRequiredAdditionalneedsTest() {

        createdBooking.setAdditionalNeeds(null);
        requestExecution();
    }

    private void requestExecution() {
        given()
                .spec(requestSpec)
                .body(createdBooking)
                .when()
                .log().body()
                .post(BOOKING_URN)
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }

}
