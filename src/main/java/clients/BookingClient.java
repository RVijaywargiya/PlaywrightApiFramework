package clients;

import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.options.RequestOptions;
import config.ConfigManager;
import models.BookingRequest;
import utils.ApiContextManager;

public class BookingClient {

    private final APIRequestContext apiContext = ApiContextManager.context();

    public APIResponse createBooking(BookingRequest request) {
        return apiContext.post(ConfigManager.baseUrl() + "/booking", RequestOptions.create().setData(request));
    }

    public APIResponse getBooking(int bookingId) {
        return apiContext.get(ConfigManager.baseUrl() + "/booking/" + bookingId);
    }
}