package adapter;

import constants.Endpoints;
import model.Booking;

public class BookingIdAdapter extends BaseAdapter {

    public BookingIdAdapter() {
        super(Endpoints.BOOKING_ID_URN_PATTERN);
    }

    public Booking getBookingById(int id) {
        setEndpoint(String.format(Endpoints.BOOKING_ID_URN_PATTERN, id));
        return get()
                .extract()
                .body()
                .as(Booking.class);
    }

}
