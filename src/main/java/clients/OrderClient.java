package clients;

import dto.ForOrder;
import io.restassured.response.Response;

import java.util.Map;

public class OrderClient {
    public static final String API_ORDERS = "/api/orders";
    private final BaseApiClient apiClient = new BaseApiClient();

    public Response createOrder(Map<String, Object> auth, ForOrder forOrderWithAuth) {
        return apiClient.post(API_ORDERS, auth, forOrderWithAuth);
    }
}
