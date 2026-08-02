package services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import clients.PlaywrightBookingClient;
import exceptions.BookingException;
import models.BookingRequest;
import models.BookingResponse;
import utils.JsonUtils;

public class BookingService {

    private static final Logger LOGGER = LogManager.getLogger(BookingService.class);

    private final PlaywrightBookingClient bookingClient;

    public BookingService() {
        this(new PlaywrightBookingClient());
    }

    public BookingService(PlaywrightBookingClient bookingClient) {
        this.bookingClient = bookingClient;
    }

    public BookingResponse createBooking(BookingRequest request) {
        LOGGER.info("Creating booking for customer: {} {}", request.getFirstname(), request.getLastname());
        BookingResponse response = bookingClient.createBooking(request);
        if (response == null || response.getBookingid() <= 0) {
            throw new BookingException("Booking creation failed");
        }

        LOGGER.info("Booking created successfully with id: {}", response.getBookingid());
        JsonUtils.printResponseBody(response);
        return response;
    }

    public BookingResponse getBooking(int bookingId) {
        LOGGER.info("Retrieving booking with id: {}", bookingId);
        BookingResponse response = bookingClient.getBooking(bookingId);
        if (response == null) {
            throw new BookingException("Booking retrieval failed for id: " + bookingId);
        }
        return response;
    }
}