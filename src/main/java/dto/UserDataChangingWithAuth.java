package dto;

public class UserDataChangingWithAuth {
    private String authorization;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String email;

    public UserDataChangingWithAuth(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    private String name;

}
