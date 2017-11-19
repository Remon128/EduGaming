package example.com.teachme.api;

import java.util.List;

import example.com.teachme.model.Course;
import example.com.teachme.model.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by MrHacker on 4/18/2017.
 */

public interface CourseAPIInterface {

    @POST("/api/course/getAll")
    Call<List<Course>> getAllCourses();

    @POST("/api/course/getByTeacherMail")
    Call<List<Course>> getCourses(@Body User mail);

    @POST("/api/course/getByStudentMail")
    Call<List<Course>> getCoursesStudent(@Body User mail);

    @POST("/api/create/course")
    Call<Course> createCourse(@Body Course course);

    @POST("/api/enroll/course")
    Call<Course> enrollCourse(@Body Course course);

}
