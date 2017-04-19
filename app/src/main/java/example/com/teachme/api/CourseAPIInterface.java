package example.com.teachme.api;

import java.util.List;

import example.com.teachme.Course.Course;
import example.com.teachme.Game.Game;
import example.com.teachme.model.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by MrHacker on 4/18/2017.
 */

public interface CourseAPIInterface {

    @GET("/api/course")
    Call<List<Course>> getCourses();

    @GET("/api/course")
    Call<Course> getCourse(@Query("name") String name);

    @POST("api/course")
    Call<Course> createCourse(@Body Game game);

}
