package dto;

public class UserWithoutName {
    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    private String email;
    private String password;

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }





    public UserWithoutName(String email, String password){
        this.email = email;
        this.password = password;
    }
}
