package example.com.teachme.User;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.Toast;

import example.com.teachme.Connection.ApiUtils;
import example.com.teachme.Connection.DbUtils;
import example.com.teachme.R;
import example.com.teachme.api.UserAPIInterface;
import example.com.teachme.model.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
    //SharedPreferences settings;


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (resultCode) {
            case ApiUtils.logoutTag:
                setResult(ApiUtils.logoutTag);
                finish();            // to close this activity
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

        setProgressBarVisible();

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
                    setProgressBarGone();
                    Toast.makeText(getBaseContext(), getString(R.string.Password_at_least_4_chars), Toast.LENGTH_SHORT).show();
                }
            } else {
                setProgressBarGone();
                Toast.makeText(getBaseContext(), getString(R.string.Please_enter_a_valid_email), Toast.LENGTH_SHORT).show();
            }
        } else {
            setProgressBarGone();
            Toast.makeText(getBaseContext(), getString(R.string.Please_select_user_type), Toast.LENGTH_SHORT).show();
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
                                    setProgressBarGone();
                                    Toast.makeText(getBaseContext(), getString(R.string.Please_enter_right_password), Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                setProgressBarGone();
                                Toast.makeText(getBaseContext(), getString(R.string.Please_enter_right_email), Toast.LENGTH_SHORT).show();
                            }
                            if (flag) {
                                setProgressBarGone();
                                DbUtils.createDBUtils(getBaseContext());
                                DbUtils.addUser(user.getMail(), user.getPassword(), user.getName());
                                DbUtils.name = user.getName();
                                startActivity(i);
                                finish();
                            } else {
                                Toast.makeText(getBaseContext(), getString(R.string.enter_valid_data), Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(getBaseContext(), getString(R.string.enter_valid_data), Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {

                    } finally {
                        setProgressBarGone();
                    }
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    Toast.makeText(getBaseContext(), getString(R.string.no_internet), Toast.LENGTH_SHORT).show();
                }
            });
    }

    void setProgressBarGone() {
        progressBar.setVisibility(View.GONE);
    }

    void setProgressBarVisible() {
        progressBar.setVisibility(View.VISIBLE);
    }
}
