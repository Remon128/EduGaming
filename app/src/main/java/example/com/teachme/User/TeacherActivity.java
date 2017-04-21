package example.com.teachme.User;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import example.com.teachme.Course.CreateCourseActivity;
import example.com.teachme.R;
import example.com.teachme.model.User;

public class TeacherActivity extends AppCompatActivity {
    SharedPreferences settings;
    Button createCourse ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);

        Intent i = getIntent();
        User user = (User) i.getSerializableExtra("user");

        Toast.makeText(getBaseContext(),user.toString(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        startActivity(intent);
    }

    public void createCourse(View view)
    {
        Intent i = new Intent(TeacherActivity.this, CreateCourseActivity.class);
        startActivity(i);
    }

    public void logout(View view) {
        settings = getSharedPreferences("mySharedPref", 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean("connected", false);
        editor.apply();
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }

}
