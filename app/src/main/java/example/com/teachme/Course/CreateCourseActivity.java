package example.com.teachme.Course;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import example.com.teachme.R;

public class CreateCourseActivity extends AppCompatActivity {

    Button btn ;
    EditText courseName ;
    EditText courseDesc ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_course);
        //this.btn = (Button)findViewById()
        courseName = (EditText)findViewById(R.id.courseName);
        courseDesc = (EditText)findViewById(R.id.courseDesc);
        
    }

    void createCourse()
    {
        Toast.makeText(this,"Create course",Toast.LENGTH_SHORT).show();
    }
}
