package example.com.teachme.api;

import java.util.List;

import example.com.teachme.model.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;


public interface UserAPIInterface {
    @GET("/api/teacher/all")
    Call<List<User>> getUsers();

    @POST("/api/get/teacher")
    Call<User> getTeacher(@Body User user);

    @POST("/api/get/student")
    Call<User> getStudent(@Body User user);

    @POST("/api/create/teacher")
    Call<User> createTeacher(@Body User users);

    @POST("/api/create/student")
    Call<User> createStudent(@Body User users);

}
