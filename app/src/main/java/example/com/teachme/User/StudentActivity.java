package example.com.teachme.User;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import example.com.teachme.Connection.DbUtils;
import example.com.teachme.model.Course;
import example.com.teachme.Course.CourseFragment;
import example.com.teachme.R;
import example.com.teachme.model.User;

public class StudentActivity extends AppCompatActivity implements CourseFragment.OnListFragmentInteractionListener {
    @Override
    public void onListFragmentInteraction(Course item) {

    }

   TextView test;
    SharedPreferences settings;
    Button logout;
    String email ;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        email = DbUtils.mail;

   }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        startActivity(intent);
    }

    public void listCourses(View view)
    {
        CourseFragment courseFragment = new CourseFragment(email,3);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.flcourse, courseFragment, "")
                .commit();

    }

    public void Enroll(View view)
    {
        CourseFragment courseFragment = new CourseFragment(email,2);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.flcourse, courseFragment, "")
                .commit();
    }



    public void logout(View view) {
        settings = getSharedPreferences("mySharedPref", 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean("connected", false);
        editor.apply();
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }


}
