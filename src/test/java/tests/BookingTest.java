package tests;

import com.microsoft.playwright.APIResponse;
import config.ConfigManager;
import models.BookingRequest;
import org.testng.annotations.Test;
import services.BookingService;
import testData.BookingDataProvider;
import utils.JsonUtils;

import static org.assertj.core.api.Assertions.assertThat;

public class BookingTest {

    private final BookingService bookingService = new BookingService();

    private APIResponse createNewBooking() {
        BookingRequest request = BookingDataProvider.validBooking();
        APIResponse response = bookingService.createBooking(request);
        String id = JsonUtils.getValueFromJson(response, "id");
        request.setId(id);
        return bookingService.createBooking(request);
    }


    @Test
    public void shouldCreateBooking() {

        BookingRequest request = BookingDataProvider.validBooking();
        System.out.println(request);

        APIResponse response = bookingService.createBooking(request);
        System.out.println("Request URL: " + ConfigManager.baseUrl() + "/booking");

        assertThat(response).isNotNull();
        assertThat(JsonUtils.getValueFromJson(response, "id")).isNotNull();
    }

//    @Test
//    public void getBooking() {
//        APIResponse response = createNewBooking();
//
//        // ensure creation succeeded
//        assertThat(response).isNotNull();
//        int id = response.getBookingid();
//
//        System.out.println("Booking id : " + id);
//
//        APIResponse getBookingByIdResponse = bookingService.getBooking(id);
//
//        assertThat(getBookingByIdResponse.status()).isEqualTo(200);
//    }
}