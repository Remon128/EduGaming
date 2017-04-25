package example.com.teachme.User;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

import example.com.teachme.Connection.DbUtils;
import example.com.teachme.Course.CourseFragment;
import example.com.teachme.Course.CreateCourseActivity;
import example.com.teachme.DBHandler;
import example.com.teachme.R;
import example.com.teachme.UserDBTable;
import example.com.teachme.model.User;

public class TeacherActivity extends AppCompatActivity {

    SharedPreferences settings;
    Button createCourse;
    Bundle bundle;
    String email ;
    DBHandler dbHandler = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);
        dbHandler = new DBHandler(getBaseContext());

        email = DbUtils.email;

        createCourse = (Button) findViewById(R.id.createCourse);
        CourseFragment courseFragment = new CourseFragment(email);

        getSupportFragmentManager().
                beginTransaction().
                add(R.id.flcourse, courseFragment, "").
                commit();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        startActivity(intent);
    }

    public void createCourse(View view) {
        Intent i = new Intent(TeacherActivity.this, CreateCourseActivity.class);
        startActivity(i);
    }

    public void logout(View view) {
        dbHandler.deleteUser(email);
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }

}
