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

import java.util.List;

import example.com.teachme.HomeActivity;
import example.com.teachme.R;
import example.com.teachme.api.UserAPIInterface;
import example.com.teachme.model.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignupActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    Intent i = null;
    List<User> users;
    private final String baseUrl = "http://10.0.2.2:8080";
    public static String Tag = "TEST_DEBUG";
    String email_str;
    String password_str, user_str;
    EditText email, password, username;
    RadioButton student, teacher;
    Button signup, login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        username = (EditText) findViewById(R.id.username);
        login = (Button) findViewById(R.id.login);
        student = (RadioButton) findViewById(R.id.student);
        teacher = (RadioButton) findViewById(R.id.teacher);

    }

    public void signup(View view) {

        email_str = email.getText().toString();
        password_str = password.getText().toString();
        user_str = username.getText().toString();
        if (student.isChecked()) {
            i = new Intent(SignupActivity.this, StudentActivity.class);
        } else if (teacher.isChecked()) {
            i = new Intent(SignupActivity.this, TeacherActivity.class);
        }

        if (student.isChecked() || teacher.isChecked()) {
            if (user_str.length() > 0) {
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
                Toast.makeText(getBaseContext(), "Please enter username", Toast.LENGTH_SHORT).show();
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
                boolean flag = true;
                for (int i = 0; i < users.size(); i++) {
                    u = users.get(i);
                    if (u.getEmail().equals(email_str)) {
                        flag = false;
                        Toast.makeText(getBaseContext(), "Email already exist", Toast.LENGTH_SHORT).show();
                    }
                }

                if (flag) {
                    i.putExtra("email", email_str);
                    i.putExtra("password", password_str);
                    startActivity(i);
                    finish();
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Toast.makeText(getBaseContext(), "No Internet conncection", Toast.LENGTH_SHORT).show();
            }
        });

    }


}
