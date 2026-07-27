package services;

import clients.BookingClient;
import com.microsoft.playwright.APIResponse;
import models.BookingRequest;
import utils.JsonUtils;

public class BookingService {

    private final BookingClient bookingClient = new BookingClient();

    public APIResponse createBooking(BookingRequest request) {

        APIResponse response = bookingClient.createBooking(request);

        if (response.status() != 200) {
            throw new AssertionError("Booking creation failed");
        }

        JsonUtils.printResponseBody(response);
        return response;
    }

    public APIResponse getBooking(int bookingId) {
        return bookingClient.getBooking(bookingId);
    }
}