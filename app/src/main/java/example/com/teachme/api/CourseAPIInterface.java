package example.com.teachme.api;

import java.util.List;

import example.com.teachme.model.Course;
import example.com.teachme.model.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by MrHacker on 4/18/2017.
 */

public interface CourseAPIInterface {

    @POST("/api/course/getByTeacherMail")
    Call<List<Course>> getCourses(@Body User mail);

    @POST("/api/create/course")
    Call<Course> createCourse(@Body Course course);

}
