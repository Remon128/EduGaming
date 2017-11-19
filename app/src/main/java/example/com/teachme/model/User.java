package example.com.teachme.model;

import com.google.gson.annotations.SerializedName;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.Expose;


import example.com.teachme.api.UserAPIInterface;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class  User implements Serializable {

    @SerializedName("mail")
    @Expose
    private String mail;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("password")
    @Expose
    private String password;


    public User() {
        super();
        // TODO Auto-generated constructor stub
    }

    public User(String mail, String name, String password) {
        super();
        this.mail = mail;
        this.name = name;
        this.password = password;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "mail='" + mail + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
