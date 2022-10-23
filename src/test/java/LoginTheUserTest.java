import dto.*;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class LoginTheUserTest extends BaseTest{

    @After
    public void setUp(){
        LoginUser loginUser = new LoginUser("pizdec10@mail.ru", "12345");
        Response response = apiClient.post("/api/auth/login",loginUser);
        String token = response.then()
                .extract()
                .jsonPath()
                .getString("accessToken");
        Map<String, Object> auth = new HashMap<>();
        auth.put("Authorization", token);
        Response message = apiClient.delete("/api/auth/user", auth);
        message.then().assertThat().body("message", Matchers.equalTo("User successfully removed"))
                .and()
                .statusCode(202);
    }

    @DisplayName("Check status code after login")
    @Test
    public void loginWithSuccess() {
        LoginUser loginUser = new LoginUser("pizdec10@mail.ru", "12345");
        Response response = apiClient.post("/api/auth/login", loginUser);
        response.then().assertThat().body("success", Matchers.equalTo(true))
                .and()
                .statusCode(200);
    }
    @DisplayName("Check status code if login without email")
    @Test
    public void loginWithoutEmailWithUnsuccess(){
        LoginWithoutEmail loginWithoutEmail = new LoginWithoutEmail("12345");
        Response response = apiClient.post("/api/auth/login",loginWithoutEmail);
        response.then().assertThat().body("message", Matchers.equalTo("email or password are incorrect"))
                .and()
                .statusCode(401);

    }
    @DisplayName("Check status code if login without password")
    @Test
    public void loginWithoutPasswordWithUnsuccess(){
        LoginWithoutPassword loginWithoutPassword = new LoginWithoutPassword("qwqwqwq@mail.ru");
        Response response = apiClient.post("/api/auth/login",loginWithoutPassword);
        response.then().assertThat().body("message", Matchers.equalTo("email or password are incorrect"))
                .and()
                .statusCode(401);

    }
}
