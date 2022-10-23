import dto.*;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Test;

public class MakeTheUser {
    private final BaseApiClient apiClient = new BaseApiClient();

    @Test
    @DisplayName("Make the uniq user")
    public void makeTheUniqueUser(){
        RandomUser randomUser = RandomUser.getRandomUniqUser();
        Response message = apiClient.post("/api/auth/register",randomUser );
        message.then().assertThat().body("success", Matchers.equalTo(true))
                .and()
                .statusCode(200);
    }

    @Test
    @DisplayName("Make the user second time")
    public void makeTheUserSecondTime(){
        RandomUser randomUser = RandomUser.getRandomUniqUser();
        apiClient.post("/api/auth/register",randomUser);
        Response message = apiClient.post("/api/auth/register",randomUser );
        message.then().assertThat().body("message", Matchers.equalTo("User already exists"))
                .and()
                .statusCode(403);
    }
    @Test
    @DisplayName("Make the user without email")
    public void makeTheUserWithoutEmail(){
        UserWithoutEmail userWithoutEmail = new UserWithoutEmail("123", "Pip");
        Response message = apiClient.post("/api/auth/register",userWithoutEmail );
        message.then().assertThat().body("message", Matchers.equalTo("Email, password and name are required fields"))
                .and()
                .body("success",Matchers.equalTo(false ))
                        .and()
                .statusCode(403);
    }

    @Test
    @DisplayName("Make the user without password")
    public void makeTheUserWithoutPassword(){
        UserWithoutPassword userWithoutPassword = new UserWithoutPassword("jssjsj@mail.ru", "Alan");
        Response message = apiClient.post("/api/auth/register",userWithoutPassword );
        message.then().assertThat().body("message", Matchers.equalTo("Email, password and name are required fields"))
                .and()
                .statusCode(403);
    }
    @Test
    @DisplayName("Make the user without name")
    public void makeTheUserWithoutName(){
        UserWithoutName userWithoutName = new UserWithoutName("ssksks@mail.ru", "12345");
        Response message = apiClient.post("/api/auth/register",userWithoutName );
        message.then().assertThat().body("message", Matchers.equalTo("Email, password and name are required fields"))
                .and()
                .statusCode(403);
    }

}
