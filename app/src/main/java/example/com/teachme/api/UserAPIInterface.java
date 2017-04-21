package example.com.teachme.api;

import java.util.List;

import example.com.teachme.Course.Course;
import example.com.teachme.model.User;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by MrHacker on 4/18/2017.
 */


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
