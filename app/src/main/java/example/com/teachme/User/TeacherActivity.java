package example.com.teachme.User;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import example.com.teachme.Connection.ApiUtils;
import example.com.teachme.Connection.DbUtils;
import example.com.teachme.Course.CourseFragment;
import example.com.teachme.Course.CreateCourseActivity;
import example.com.teachme.R;

public class TeacherActivity extends AppCompatActivity {

    Button createCourse;
    String email;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        try {
            if (id == R.id.logout) {
                DbUtils.delete();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
                setResult(ApiUtils.logoutTag);
            } else if (id == R.id.main) {
                if (DbUtils.isTeacher)
                    startActivity(new Intent(getApplicationContext(), TeacherActivity.class));
                else
                    startActivity(new Intent(getApplicationContext(), StudentActivity.class));
            }
            return super.onOptionsItemSelected(item);
        } catch (Exception e) {
            Toast.makeText(getBaseContext(), getString(R.string.no_internet), Toast.LENGTH_SHORT).show();
            return super.onOptionsItemSelected(item);
        }
    }

    /*
    @Override
    public void onBackPressed() {
        finish();
    }
*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);

        DbUtils.createDBUtils(getBaseContext());
        email = DbUtils.mail;

        //Toast.makeText(getBaseContext(),email,Toast.LENGTH_SHORT).show();

        createCourse = (Button) findViewById(R.id.createCourse);

        CourseFragment courseFragment = CourseFragment.newInstance(email, 1);

        getSupportFragmentManager().
                beginTransaction().
                replace(R.id.flcourse, courseFragment, "").
                commit();
    }

    /*
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        startActivity(intent);
    }*/

    public void createCourse(View view) {
        Intent i = new Intent(TeacherActivity.this, CreateCourseActivity.class);
        startActivity(i);
        //finish();
    }

    public void logout(View view) {
        DbUtils.delete();
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finish();
    }

}
