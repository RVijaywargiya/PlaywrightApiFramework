package testData;

import models.BookingDates;
import models.BookingRequest;

public final class BookingData {

    private BookingData() {
    }

    public static BookingRequest validBooking() {

        BookingDates dates =
                new BookingDates(
                        "2018-01-01",
                        "2019-01-01"
                );

        return new BookingRequest(
                "Jim",
                "Brown",
                111,
                true,
                dates,
                "Breakfast"
        );
    }
}