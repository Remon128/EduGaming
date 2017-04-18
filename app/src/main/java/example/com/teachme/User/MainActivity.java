package example.com.teachme.User;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.Toast;

import java.io.Serializable;
import java.util.List;

import example.com.teachme.HomeActivity;
import example.com.teachme.R;
import example.com.teachme.Tasks.HTTPTasks.OnPostExecute;
import example.com.teachme.api.UserAPIInterface;
import example.com.teachme.model.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    Intent i = null;
    private final String baseUrl = "http://10.0.2.2:8080";
    List<User> users;
    public String json = "";
    public static String Tag = "TEST_DEBUG";
    String email_str;
    String password_str;
    EditText email, password;
    RadioButton student, teacher;
    SharedPreferences settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        settings = getSharedPreferences("mySharedPref", 0);
        if (settings.getBoolean("connected", false)) {
        /* The user has already login, so start the dashboard */
            if (settings.getBoolean("isTeacher", true)) {
                startActivity(new Intent(getApplicationContext(), TeacherActivity.class));
            } else {
                startActivity(new Intent(getApplicationContext(), StudentActivity.class));
            }
        }
        try {

            progressBar = (ProgressBar) findViewById(R.id.progressbar);
            email = (EditText) findViewById(R.id.email1);
            password = (EditText) findViewById(R.id.password1);
            Button signin = (Button) findViewById(R.id.signin1);
            Button signup = (Button) findViewById(R.id.signup);
            Button forget = (Button) findViewById(R.id.forget);
            student = (RadioButton) findViewById(R.id.student);
            teacher = (RadioButton) findViewById(R.id.teacher);


            signup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(MainActivity.this, SignupActivity.class));
                }
            });


            forget.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getBaseContext(), "Check your mail", Toast.LENGTH_SHORT).show();
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void login(View view) {

        email_str = email.getText().toString();
        password_str = password.getText().toString();

        if (student.isChecked()) {
            i = new Intent(MainActivity.this, StudentActivity.class);
        } else if (teacher.isChecked()) {
            i = new Intent(MainActivity.this, TeacherActivity.class);
        }

        if (student.isChecked() || teacher.isChecked()) {
            if (email_str.length() > 0 && email_str.contains("@")) {
                if (password_str.length() >= 4) {
                    this.validateData(email_str, password_str);
                } else {
                    Toast.makeText(getBaseContext(), "Password at least 4 chars", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(getBaseContext(), "Please enter a valid email", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getBaseContext(), "Please select user type", Toast.LENGTH_SHORT).show();

        }
    }


    void validateData(String email, String password) {

        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        UserAPIInterface userAPIInterface = retrofit.create(UserAPIInterface.class);

        Call<List<User>> connection = userAPIInterface.getUsers();

        connection.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                users = response.body();
                User u = null;
                boolean flag = false;
                for (int i = 0; i < users.size(); i++) {
                    u = users.get(i);
                    if (u.getEmail().equals(email_str)) {
                        if (u.getPassword().equals(password_str)) {
                            flag = true;
                            break;
                        } else {
                            Toast.makeText(getBaseContext(), "Please enter right password", Toast.LENGTH_SHORT).show();

                        }
                    }
                }
                if (flag) {
                    i.putExtra("email", email_str);
                    i.putExtra("password", password_str);
                    SharedPreferences.Editor editor = settings.edit();
                    editor.putBoolean("connected", true);
                    editor.putBoolean("isTeacher", teacher.isChecked());
                    editor.apply();
                    startActivity(i);
                }

            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Toast.makeText(getBaseContext(), "No Internet conncection", Toast.LENGTH_SHORT).show();
            }
        });

    }


}
