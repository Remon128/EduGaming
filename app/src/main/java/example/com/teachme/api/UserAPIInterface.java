package example.com.teachme.api;

import java.util.List;

import example.com.teachme.model.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by MrHacker on 4/18/2017.
 */


public interface UserAPIInterface {
    @GET("/api/users")
    Call<List<User>> getUsers();

    @GET("api/users/email/{email}")
    Call<User> getUser(@Path("email") String email);

    @POST("api/users")
    Call<User> createUser(@Body User user);
}
