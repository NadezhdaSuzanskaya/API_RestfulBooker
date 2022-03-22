package adaptertest;

import adapter.AuthAdapter;
import adapter.BaseAdapter;
import adapter.BookingAdapter;
import model.Booking;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class AdapterTests extends BaseTest {

    @Test
    public void getTokenTest() {
        assertThat(http.auth.getToken(), allOf(notNullValue(), hasLength(15)));
    }

    @Test
    public void createBookingTest() {
        int bookingId = http.booking.createBooking(new Booking());
        assertThat(bookingId, greaterThan(10));
        Booking actualBooking = http.bookingId.getBookingById(bookingId);
        assertThat(actualBooking, equalTo(new Booking()));
    }

}
