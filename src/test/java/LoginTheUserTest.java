import dto.*;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Test;
import java.util.HashMap;
import java.util.Map;

public class LoginTheUserTest extends BaseTest{

    @After
    public void setUp(){
        LoginUser loginUser = new LoginUser("laguna10@mail.ru", "12345");
        Response response = apiClient.post("/api/auth/login",loginUser);
        String token = response.then()
                .extract()
                .jsonPath()
                .getString("accessToken");
        Map<String, Object> auth = new HashMap<>();
        auth.put("Authorization", token);
        Response message = apiClient.delete("/api/auth/user", auth);
        message.then().statusCode(HttpStatus.SC_ACCEPTED)
                 .assertThat().body("message", Matchers.equalTo("User successfully removed"));
    }

    @DisplayName("Check status code after login")
    @Test
    public void loginWithSuccess() {
        LoginUser loginUser = new LoginUser("laguna10@mail.ru", "12345");
        Response response = apiClient.post("/api/auth/login", loginUser);
        response.then().statusCode(HttpStatus.SC_OK)
                .assertThat().body("success", Matchers.equalTo(true));
    }
    @DisplayName("Check status code if login without email")
    @Test
    public void loginWithoutEmailWithUnsuccess(){
        LoginUser loginUserWithoutEmail = new LoginUser("","12345");
        Response response = apiClient.post("/api/auth/login",loginUserWithoutEmail);
        response.then().statusCode(HttpStatus.SC_UNAUTHORIZED)
                .assertThat().body("message", Matchers.equalTo("email or password are incorrect"));

    }
    @DisplayName("Check status code if login without password")
    @Test
    public void loginWithoutPasswordWithUnsuccess(){
        LoginUser loginUserWithoutPassword = new LoginUser("laguna10@mail.ru","");
        Response response = apiClient.post("/api/auth/login",loginUserWithoutPassword);
        response.then().statusCode(HttpStatus.SC_UNAUTHORIZED)
                .assertThat().body("message", Matchers.equalTo("email or password are incorrect"));

    }
}
