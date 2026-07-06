package tests;

import static org.assertj.core.api.Assertions.assertThat;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import models.BookingRequest;
import models.BookingResponse;
import services.BookingService;
import testData.BookingData;

public class BookingTest {

    private final BookingService bookingService =
            new BookingService();

    @Test
    public void shouldCreateBooking() {

        BookingRequest request =
                BookingData.validBooking();

        System.out.println(request);

        BookingResponse response =
                bookingService.createBooking(
                        request
                );
        ObjectMapper mapper = new ObjectMapper();
        try {
            System.out.println("Response JSON:\n" + mapper.writerWithDefaultPrettyPrinter().writeValueAsString(response));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        // ensure we received a response before asserting on its contents
        assertThat(response).isNotNull();

        assertThat(response.getBookingid())
                .isNotNull()
                .isGreaterThan(0);
    }
}