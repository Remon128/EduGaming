package example.com.teachme.api;

import java.util.List;

import example.com.teachme.model.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by MrHacker on 4/18/2017.
 */


public interface UserAPIInterface {
    @GET("/")
    Call<List<User>> getUsers();

    @POST("/user")
    Call<User> createUser(@Body User user);
}
