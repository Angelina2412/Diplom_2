import dto.UniqUser;
import org.junit.Before;

public class BaseTest {
     final BaseApiClient apiClient = new BaseApiClient();

    @Before
    public void makeTheUser(){
        UniqUser uniqueUser = new UniqUser("pizdec10@mail.ru", "12345", "pizdec8");
        apiClient.post("/api/auth/register",uniqueUser );
    }
}
