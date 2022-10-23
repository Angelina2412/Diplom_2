package dto;

import org.apache.commons.lang3.RandomStringUtils;

public class RandomUser {
    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return name;
    }
    private String email;
    private String password;

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String name) {
        this.name = name;
    }

    private String  name;

    public RandomUser(String email, String password, String name){
        this.email = email;
        this.password = password;
        this.name = name;
    }
    public static RandomUser getRandomUniqUser(){
        return new RandomUser(
                RandomStringUtils.randomAlphanumeric(10) + "@mail.ru",
                "12345",
                RandomStringUtils.randomAlphanumeric(5)
        );
    }
}
