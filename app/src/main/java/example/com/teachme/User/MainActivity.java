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

import example.com.teachme.Connection.ApiUtils;
import example.com.teachme.Connection.DbUtils;
import example.com.teachme.DBHandler;
import example.com.teachme.HomeActivity;
import example.com.teachme.R;
import example.com.teachme.UserDBTable;
import example.com.teachme.api.UserAPIInterface;
import example.com.teachme.model.User;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Path;

public class MainActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    Intent i = null;
    User user;
    public static String Tag = "TEST_DEBUG";
    String email_str;
    String password_str;
    EditText email, password;
    RadioButton student, teacher;
    String isChecked = "student";
    SharedPreferences settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
        settings = getSharedPreferences("mySharedPref", 0);

        if (settings.getBoolean("connected", false)) {
        // The user has already login, so start the dashboard
            if (settings.getBoolean("isTeacher", true)) {
               Intent intent = new Intent(MainActivity.this, TeacherActivity.class);
                startActivity(intent);
            } else {
                Intent intent = new Intent(MainActivity.this, StudentActivity.class);
                startActivity(intent);
            }
        }
*/

        try {

            progressBar = (ProgressBar) findViewById(R.id.progressbar);
            email = (EditText) findViewById(R.id.email1);
            password = (EditText) findViewById(R.id.password1);
            Button signup = (Button) findViewById(R.id.signup);
            Button forget = (Button) findViewById(R.id.forget);
            student = (RadioButton) findViewById(R.id.student);
            teacher = (RadioButton) findViewById(R.id.teacher);

            signup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(MainActivity.this, SignupActivity.class));
                    finish();
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
            isChecked = "teacher";
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


    void validateData(String email, final String password) {


        UserAPIInterface userAPIInterface = ApiUtils.getAPIUser();

        User u = new User();
        u.setMail(email_str);


        Call<User> connection = null;
        if (student.isChecked()) {
            connection = userAPIInterface.getStudent(u);
            DbUtils.isTeacher = false;
        } else if (teacher.isChecked()) {
            connection = userAPIInterface.getTeacher(u);
            DbUtils.isTeacher = true;
        }

        if (connection != null)
            connection.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    boolean flag = false;
                    try {
                        if (response.isSuccessful()) {
                            user = response.body();
                            if (user.getMail().equals(email_str)) {
                                if (user.getPassword().equals(password_str)) {
                                    flag = true;
                                } else {
                                    Toast.makeText(getBaseContext(), "Please enter right password", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(getBaseContext(), "Please enter right email", Toast.LENGTH_SHORT).show();
                            }
                            if (flag) {
                                DbUtils.createDBUtils(getBaseContext());
                                DbUtils.addUser(user.getMail(), user.getPassword(), user.getName());
                                DbUtils.name = user.getName();
                                startActivity(i);
                                finish();
                            } else {
                                Toast.makeText(getBaseContext(), "Please enter a valid data", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(getBaseContext(), "Please enter a valid data", Toast.LENGTH_SHORT).show();

                        }
                    } catch (Exception e) {

                    } finally {

                    }
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    Toast.makeText(getBaseContext(), "No Internet conncection", Toast.LENGTH_SHORT).show();
                }
            });
    }
}
