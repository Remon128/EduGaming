package example.com.teachme.Course;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        teacher = new Teacher();
        teacher.setMail(DbUtils.mail);
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
                        Toast.makeText(getBaseContext(), getString(R.string.Course_has_been_created), Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(CreateCourseActivity.this, TeacherActivity.class);
                        startActivity(i);
                        finish();
                    }
                }

                @Override
                public void onFailure(Call<Course> call, Throwable t) {
                    Toast.makeText(getBaseContext(), getString(R.string.no_internet), Toast.LENGTH_SHORT).show();
                    finish();
                }
            });
        } else {
            Toast.makeText(getBaseContext(), getString(R.string.Please_enter_course_info), Toast.LENGTH_SHORT).show();
        }
    }
}
