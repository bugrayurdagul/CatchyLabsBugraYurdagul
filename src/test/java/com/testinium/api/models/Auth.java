package com.testinium.api.models;



import io.restassured.http.Header;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Auth {
    private String username;
    private String password;
    private String access_token;
    private String refresh_token;
    private Header header;

    public static Auth fromConfig(String uname, String pass) {
        Auth auth = new Auth();
        auth.setUsername(uname);
        auth.setPassword(pass);
        return auth;
    }

    public Header headerMaker(){
        return new Header("Authorization", "Bearer " + access_token);
    }


}
