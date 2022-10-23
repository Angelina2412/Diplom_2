//import dto.ForOrder;
//import dto.ForRegister;
//import dto.OrderInfo;
//import io.qameta.allure.junit4.DisplayName;
//import io.restassured.response.Response;
//import org.apache.http.HttpStatus;
//import org.hamcrest.Matchers;
//import org.junit.After;
//import org.junit.Test;
//import java.util.HashMap;
//import java.util.Map;
//import static org.junit.Assert.assertEquals;
//
//public class MakeTheOrderTest extends BaseTest {
//    public String getAccessToken() {
//        ForRegister forRegister = new ForRegister("pizdec10@mail.ru", "12345");
//        Response response = apiClient.post("/api/auth/login", forRegister);
//        return response.then()
//                .extract()
//                .jsonPath()
//                .getString("accessToken");
//    }
//
//    @After
//    public void setUp(){
//        String token = getAccessToken();
//        Map<String, Object> auth = new HashMap<>();
//        auth.put("Authorization", token);
//        Response message = apiClient.delete("/api/auth/user", auth);
//        message.then().assertThat().body("message", Matchers.equalTo("User successfully removed"))
//                .and()
//                .statusCode(202);
//    }
//
//    @Test
//    @DisplayName("Make the order after auth with ingredients")
//    public void makeTheOrderWithAuthAndIngredients() {
//        String accessToken = getAccessToken();
//        Map<String, Object> auth = new HashMap<>();
//        auth.put("Authorization", accessToken);
//        ForOrder forOrderWithAuth = new ForOrder(new String[]{"61c0c5a71d1f82001bdaaa79", "61c0c5a71d1f82001bdaaa78"});
//        Response response = apiClient.post("/api/orders", auth, forOrderWithAuth);
//        OrderInfo res = response.then().assertThat().statusCode(HttpStatus.SC_OK)
//                .and()
//                .extract()
//                .as(OrderInfo.class);
//        assertEquals("pizdec8", res.getOrder().getOwner().getName());
//    }
//    @Test
//    @DisplayName("Make the order  without auth")
//    public void makeTheOrderWithoutAuth() {
//        ForOrder forOrderWithAuth = new ForOrder(new String[]{"61c0c5a71d1f82001bdaaa79", "61c0c5a71d1f82001bdaaa78"});
//        Response response = apiClient.post("/api/orders",forOrderWithAuth);
//        response.then().assertThat().body("success", Matchers.equalTo(true))
//                .and()
//                .statusCode(200);
//
//    }
//    @Test
//    @DisplayName("Make the order after auth without ingredients")
//    public void makeTheOrderWithoutIngredients() {
//        String accessToken = getAccessToken();
//        Map<String, Object> auth = new HashMap<>();
//        auth.put("Authorization", accessToken);
//        Response response = apiClient.post("/api/orders", auth);
//        response.then().assertThat().body("message", Matchers.equalTo("Ingredient ids must be provided"))
//                .and()
//                .statusCode(HttpStatus.SC_BAD_REQUEST);
//    }
//    @Test
//    @DisplayName("Make the order after auth with wrong ingredients")
//    public void makeTheOrderWithIncorrectIngredients() {
//        String accessToken = getAccessToken();
//        Map<String, Object> auth = new HashMap<>();
//        auth.put("Authorization", accessToken);
//        ForOrder forOrderWithAuth = new ForOrder(new String[]{"61c0c5a71d1f82001", "61c0c5a7"});
//        Response response = apiClient.post("/api/orders", auth, forOrderWithAuth);
//        response.then()
//                .and()
//                .statusCode(HttpStatus.SC_INTERNAL_SERVER_ERROR);
//    }
//}
