package example.com.teachme.Course;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import example.com.teachme.R;
import example.com.teachme.User.TeacherActivity;

public class CreateCourseActivity extends AppCompatActivity {

    Button btn;
    EditText courseName;
    EditText courseDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_course);
        //this.btn = (Button)findViewById()
        courseName = (EditText) findViewById(R.id.courseName);
        courseDesc = (EditText) findViewById(R.id.courseDesc);

    }

    void createCourse()
    {

        Toast.makeText(this, "Course has been created", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(CreateCourseActivity.this, TeacherActivity.class));

    }
}
