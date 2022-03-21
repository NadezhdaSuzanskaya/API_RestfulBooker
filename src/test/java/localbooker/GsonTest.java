package localbooker;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.Booking;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class GsonTest {

    public static final String EXPECTED_JSON = """
            {
              "firstname": "test-r",
              "lastname": "test-s",
              "totalprice": 101,
              "depositpaid": true,
              "bookingdates": {
                "checkin": "2022-03-12",
                "checkout": "2022-03-20"
              },
              "additionalneeds": "Massage"
            }""";

    @Test
    public void serialiseTest() {
        Booking actualModel = new Booking();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String actualJson = gson.toJson(actualModel);
        assertThat(actualJson, equalTo(EXPECTED_JSON));
    }

    @Test
    public void deserializeTest() {
        Booking expectedModel = new Booking();
        Booking actualModel = new Gson().fromJson(EXPECTED_JSON, Booking.class);
        assertThat(actualModel, equalTo(expectedModel));
    }

}
