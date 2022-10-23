package dto;

public class LoginWithoutPassword {
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LoginWithoutPassword(String email) {
        this.email = email;
    }

    private String email;

}
