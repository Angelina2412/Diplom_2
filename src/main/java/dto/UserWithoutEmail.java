package dto;

public class UserWithoutEmail {

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return name;
    }
    private String password;


    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String name) {
        this.name = name;
    }

    private String  name;

    public UserWithoutEmail( String password, String name){
        this.password = password;
        this.name = name;
    }
}
