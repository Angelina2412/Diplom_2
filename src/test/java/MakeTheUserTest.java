import clients.BaseApiClient;
import dto.*;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.Test;

public class MakeTheUserTest {
    private final BaseApiClient apiClient = new BaseApiClient();

    @Test
    @DisplayName("Make the uniq user")
    public void makeTheUniqueUser(){
        RandomUser randomUser = RandomUser.getRandomUniqUser();
        Response message = apiClient.post("/api/auth/register",randomUser );
        message.then().assertThat().statusCode(HttpStatus.SC_OK)
                .and()
                .body("success", Matchers.equalTo(true));
    }

    @Test
    @DisplayName("Make the user second time")
    public void makeTheUserSecondTime(){
        RandomUser randomUser = RandomUser.getRandomUniqUser();
        apiClient.post("/api/auth/register",randomUser);
        Response message = apiClient.post("/api/auth/register",randomUser );
        message.then().statusCode(HttpStatus.SC_FORBIDDEN)
                .and()
                .assertThat().body("message", Matchers.equalTo("User already exists"));

    }
    @Test
    @DisplayName("Make the user without email")
    public void makeTheUserWithoutEmail(){
        UniqUser userWithoutEmail = new UniqUser("", "123","Pip");
        Response message = apiClient.post("/api/auth/register",userWithoutEmail );
        message.then().assertThat().statusCode(HttpStatus.SC_FORBIDDEN)
                .body("message", Matchers.equalTo("Email, password and name are required fields"))
                .and()
                .body("success",Matchers.equalTo(false ));
    }

    @Test
    @DisplayName("Make the user without password")
    public void makeTheUserWithoutPassword(){
        UniqUser userWithoutPassword = new UniqUser("jssjsj@mail.ru", "","Alan");
        Response message = apiClient.post("/api/auth/register",userWithoutPassword );
        message.then().statusCode(HttpStatus.SC_FORBIDDEN)
                .and()
                .assertThat().body("message", Matchers.equalTo("Email, password and name are required fields"));

    }
    @Test
    @DisplayName("Make the user without name")
    public void makeTheUserWithoutName(){
        UniqUser userWithoutName = new UniqUser("ssksks@mail.ru", "12345","");
        Response message = apiClient.post("/api/auth/register",userWithoutName );
        message.then().statusCode(HttpStatus.SC_FORBIDDEN)
                .assertThat().body("message", Matchers.equalTo("Email, password and name are required fields"));

    }

}
