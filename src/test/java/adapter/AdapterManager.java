package adapter;

public class AdapterManager {

    public final AuthAdapter auth = new AuthAdapter();
    public final BookingAdapter booking = new BookingAdapter();
    public final BookingIdAdapter bookingId = new BookingIdAdapter();

}
