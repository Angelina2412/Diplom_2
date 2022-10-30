import dto.ForRegister;
import dto.UserDataChangingWithAuth;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.apache.http.protocol.HTTP;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class UserDataChangingTest extends BaseTest {

    public String getAccessToken() {
        ForRegister forRegister = new ForRegister("pizdec10@mail.ru", "12345");
        Response response = apiClient.post("/api/auth/login", forRegister);
        return response.then()
                .extract()
                .jsonPath()
                .getString("accessToken");
    }

    @Test
    @Description("Check the possibility to change the user's field after auth")
    @Issue("BUG-985-it is just interesting") // ссылка на баг-репорт
    public void userDataChangingAllFieldsWithAuth() {
        String accessToken = getAccessToken();
        Map<String, Object> auth = new HashMap<>();
        auth.put("Authorization", accessToken);
        UserDataChangingWithAuth userDataChangingWithAuth = new UserDataChangingWithAuth("kakbit9@mail.ru", "1234", "Lrak9");
        Response message = apiClient.patch("/api/auth/user", auth, userDataChangingWithAuth);
        message.then().statusCode(HttpStatus.SC_OK)
                .assertThat().body("success", Matchers.equalTo(true));

        Response response = apiClient.delete("/api/auth/user", auth);
        response.then().statusCode(HttpStatus.SC_ACCEPTED)
                .assertThat().body("message", Matchers.equalTo("User successfully removed"));

    }

    @Test
    @Description("Check the possibility to change the email field after auth")
    @Issue("BUG-988-it is just interesting")
    public void userDataChangingEmailFieldWithAuth() {
        String accessToken = getAccessToken();
        Map<String, Object> auth = new HashMap<>();
        auth.put("Authorization", accessToken);
        UserDataChangingWithAuth userDataChangingWithAuth = new UserDataChangingWithAuth("kakbit9@mail.ru", "12345", "pizdec9");
        Response message = apiClient.patch("/api/auth/user", auth, userDataChangingWithAuth);
        message.then().statusCode(HttpStatus.SC_OK)
                 .assertThat().body("success", Matchers.equalTo(true));
        Response response = apiClient.delete("/api/auth/user", auth);
        response.then().statusCode(HttpStatus.SC_ACCEPTED)
                .assertThat().body("message", Matchers.equalTo("User successfully removed"));
    }
    @Test
    @Description("Check the possibility to change the password field after auth")
    @Issue("BUG-981-it is just interesting")
    public void userDataChangingPasswordFieldWithAuth() {
        String accessToken = getAccessToken();
        Map<String, Object> auth = new HashMap<>();
        auth.put("Authorization", accessToken);
        UserDataChangingWithAuth userDataChangingWithAuth = new UserDataChangingWithAuth("kakbit9@mail.ru", "123456", "pizdec9");
        Response message = apiClient.patch("/api/auth/user", auth, userDataChangingWithAuth);
        message.then().statusCode(HttpStatus.SC_OK)
                .assertThat().body("success", Matchers.equalTo(true));
        Response response = apiClient.delete("/api/auth/user", auth);
        response.then().statusCode(HttpStatus.SC_ACCEPTED)
                .assertThat().body("message", Matchers.equalTo("User successfully removed"));
    }
    @Test
    @Description("Check the possibility to change the name field after auth")
    @Issue("BUG-95-it is just interesting")
    public void userDataChangingNameFieldWithAuth() {
        String accessToken = getAccessToken();
        Map<String, Object> auth = new HashMap<>();
        auth.put("Authorization", accessToken);
        UserDataChangingWithAuth userDataChangingWithAuth = new UserDataChangingWithAuth("kakbit9@mail.ru", "12345", "pizdec1");
        Response message = apiClient.patch("/api/auth/user", auth, userDataChangingWithAuth);
        message.then().statusCode(HttpStatus.SC_OK)
                .assertThat().body("success", Matchers.equalTo(true));
        Response response = apiClient.delete("/api/auth/user", auth);
        response.then().statusCode(HttpStatus.SC_ACCEPTED)
                .assertThat().body("message", Matchers.equalTo("User successfully removed"));
    }

    @Test
    @Description("Check the possibility to change the user's field without auth")
    @Issue("BUG-99-it is just interesting")
    public void userDataChangingWithoutAuth() {
        String accessToken = getAccessToken();
        Map<String, Object> auth = new HashMap<>();
        auth.put("Authorization", accessToken);
        UserDataChangingWithAuth userDataChangingWithAuth = new UserDataChangingWithAuth("kakbit9@mail.ru", "12345", "pizdec1");
        Response message = apiClient.patch("/api/auth/user", userDataChangingWithAuth);
        message.then().statusCode(HttpStatus.SC_NOT_FOUND)
                .assertThat().body("message", Matchers.equalTo("You should be authorised"));
        Response response = apiClient.delete("/api/auth/user", auth);
        response.then().statusCode(202)
                .assertThat().body("message", Matchers.equalTo("User successfully removed"));
    }
    }

