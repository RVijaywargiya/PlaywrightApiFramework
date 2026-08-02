package tests;

import static org.assertj.core.api.Assertions.assertThat;
import org.testng.annotations.Test;

import models.BookingRequest;
import models.BookingResponse;
import services.BookingService;
import testData.BookingDataProvider;

public class BookingTest {

    private final BookingService bookingService = new BookingService();

    private BookingResponse createNewBooking() {
        BookingRequest request = BookingDataProvider.validBooking();
        return bookingService.createBooking(request);
    }

    @Test
    public void shouldCreateBooking() {
        BookingRequest request = BookingDataProvider.validBooking();
        System.out.println(request);

        BookingResponse response = bookingService.createBooking(request);

        assertThat(response).isNotNull();
        assertThat(response.getBookingid()).isGreaterThan(0);
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