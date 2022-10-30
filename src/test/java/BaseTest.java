import clients.BaseApiClient;
import dto.UniqUser;
import org.junit.Before;

public class BaseTest {
     final BaseApiClient apiClient = new BaseApiClient();

    @Before
    public void makeTheUser(){
        UniqUser uniqueUser = new UniqUser("laguna11@mail.ru", "12345", "laguna9");
        apiClient.post("/api/auth/register",uniqueUser );
    }
}
