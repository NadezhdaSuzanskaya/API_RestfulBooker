package model;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Booking {

    @Builder.Default
    public String firstname = "test-r";
    @Builder.Default
    public String lastname = "test-s";

    @SerializedName("totalprice")
    @Builder.Default
    public String totalPrice = "101";

    @SerializedName("depositpaid")
    @Builder.Default
    public String depositPaid = "true";

    @Builder.Default
    public BookingDates bookingdates = new BookingDates();
    @SerializedName("additionalneeds")
    @Builder.Default
    public String additionalNeeds = "Massage";

}