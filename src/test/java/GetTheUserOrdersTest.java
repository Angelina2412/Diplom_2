import dto.ForOrder;
import dto.ForRegister;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.apache.http.protocol.HTTP;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Test;
import java.util.HashMap;
import java.util.Map;

public class GetTheUserOrdersTest extends BaseTest {
    private String accessToken;

    public void registerAndGetAccessToken() {
        ForRegister forRegister = new ForRegister("pizdec10@mail.ru", "12345");
        Response response = apiClient.post("/api/auth/login", forRegister);
        accessToken = response.then()
                .extract()
                .jsonPath()
                .getString("accessToken");
    }
    public void makeTheOrder(){
        ForOrder forOrderWithAuth = new ForOrder(new String[]{"61c0c5a71d1f82001bdaaa79", "61c0c5a71d1f82001bdaaa78"});
        Response res = apiClient.post("/api/orders", getAuthHeader(), forOrderWithAuth);
        res.then()
                .and()
                .statusCode(HttpStatus.SC_OK);
    }
    public Map<String, Object> getAuthHeader(){
        Map<String, Object> auth = new HashMap<>();
        auth.put("Authorization", accessToken);
        return auth;
    }

    @After
    public void setUp(){
        Response message = apiClient.delete("/api/auth/user", getAuthHeader());
        message.then().statusCode(HttpStatus.SC_ACCEPTED)
                .assertThat().body("message", Matchers.equalTo("User successfully removed"));
    }
   @Test
   @DisplayName("Check status code then get the user orders with auth")
   @Description("Test helps to see all user's orders")
    public void getTheUserOrdersWithAuth(){
       registerAndGetAccessToken();
       makeTheOrder();
       Response message = apiClient.get("/api/orders", getAuthHeader());
       message.then().statusCode(HttpStatus.SC_OK)
               .assertThat().body("success", Matchers.equalTo(true));

   }
    @Test
    @DisplayName("Check status code then get the user orders without auth")
    @Description("Test helps to see all user's orders o=in unauth zone")
    public void getTheUserOrdersWithoutAuth(){
        Response message = apiClient.get("/api/orders");
        message.then().assertThat().body("message", Matchers.equalTo("You should be authorised"))
                .and()
                .statusCode(HttpStatus.SC_UNAUTHORIZED);
        registerAndGetAccessToken();
        makeTheOrder();

    }
}
