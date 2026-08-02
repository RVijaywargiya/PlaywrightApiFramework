package clients;

import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.options.RequestOptions;

import config.ConfigManager;
import models.BookingRequest;
import models.BookingResponse;
import utils.ApiContextManager;
import utils.JsonUtils;

public class PlaywrightBookingClient {

    private final APIRequestContext apiContext;

    public PlaywrightBookingClient() {
        this(ApiContextManager.context());
    }

    public PlaywrightBookingClient(APIRequestContext apiContext) {
        this.apiContext = apiContext;
    }

    public BookingResponse createBooking(BookingRequest request) {
        APIResponse response = apiContext.post(
                ConfigManager.baseUrl() + "/booking",
                RequestOptions.create().setData(request)
        );
        return JsonUtils.fromJson(response.text(), BookingResponse.class);
    }

    public BookingResponse getBooking(int bookingId) {
        APIResponse response = apiContext.get(ConfigManager.baseUrl() + "/booking/" + bookingId);
        return JsonUtils.fromJson(response.text(), BookingResponse.class);
    }
}
