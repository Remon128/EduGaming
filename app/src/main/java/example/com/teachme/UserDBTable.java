package example.com.teachme;

import java.io.Serializable;

/**
 * Created by MrHacker on 4/25/2017.
 */

public class UserDBTable implements Serializable {
    private String Id ;
    private String Password ;
    private String Username ;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }
}