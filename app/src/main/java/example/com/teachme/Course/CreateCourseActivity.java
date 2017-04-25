package example.com.teachme.Course;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import example.com.teachme.Connection.ApiUtils;
import example.com.teachme.Connection.DbUtils;
import example.com.teachme.R;
import example.com.teachme.User.TeacherActivity;
import example.com.teachme.api.CourseAPIInterface;
import example.com.teachme.model.Course;
import example.com.teachme.model.Teacher;
import example.com.teachme.model.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CreateCourseActivity extends AppCompatActivity {

    Button btn;
    EditText courseName;
    EditText courseDesc;
    Teacher teacher = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_course);
        //this.btn = (Button)findViewById()
        courseName = (EditText) findViewById(R.id.courseName);
        courseDesc = (EditText) findViewById(R.id.courseDesc);
        Toast.makeText(getBaseContext(), DbUtils.email, Toast.LENGTH_SHORT).show();
        teacher = new Teacher();
        teacher.setMail(DbUtils.email);
    }

    public void createCourse(View view) {

        CourseAPIInterface courseAPIInterface = ApiUtils.getAPICourse();
        Course course = new Course();
        String courseN = courseName.getText().toString();
        String courseD = courseDesc.getText().toString();
        if (courseN.length() > 0 && courseD.length() > 0) {
            course.setName(courseN);
            course.setDescription(courseD);
            course.setTeacher(teacher);
            Call<Course> courseCall = courseAPIInterface.createCourse(course);

            courseCall.enqueue(new Callback<Course>() {
                @Override
                public void onResponse(Call<Course> call, Response<Course> response) {
                    if (response.isSuccessful()) {

                        String json = response.body().getName();
                        Toast.makeText(getBaseContext(), "Course has been created "+ json, Toast.LENGTH_SHORT).show();

                        Intent i = new Intent(CreateCourseActivity.this, TeacherActivity.class);
                        startActivity(i);
                    }
                }

                @Override
                public void onFailure(Call<Course> call, Throwable t) {
                    Toast.makeText(getBaseContext(), "no internet connection", Toast.LENGTH_SHORT).show();
                }
            });
        }
        else {
            Toast.makeText(getBaseContext(), "Please enter course info", Toast.LENGTH_SHORT).show();

        }

    }
}
