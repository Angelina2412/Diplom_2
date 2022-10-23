package dto;

public class UserWithoutPassword {
    public String getEmail() {
        return email;
    }
    public String getUsername() {
        return name;
    }
    private String email;

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String name) {
        this.name = name;
    }

    private String  name;

    public UserWithoutPassword(String email, String name){
        this.email = email;
        this.name = name;
    }
}
