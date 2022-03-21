package adapter;

import constants.Endpoints;
import model.Booking;

public class BookingAdapter extends BaseAdapter {

    public static final String BOOKING_PATH = "booking";
    public static final String BOOKING_ID_PATH = "bookingid";

    public BookingAdapter() {
        super(Endpoints.BOOKING_URN);
    }

    public int createBooking(Booking booking) {
        return post(booking)
                .extract()
                .path(BOOKING_ID_PATH);
    }

    public int selectBooking(Booking booking) {
        return get()
                .extract()
                .path(BOOKING_ID_PATH);
    }

}
