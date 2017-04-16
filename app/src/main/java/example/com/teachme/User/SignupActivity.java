package example.com.teachme.User;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import example.com.teachme.HomeActivity;
import example.com.teachme.R;

public class SignupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        final EditText email = (EditText) findViewById(R.id.email2);
        final EditText password = (EditText) findViewById(R.id.password2);
        Button signup = (Button) findViewById(R.id.signup2);

        final String uname = email.getText().toString(), pass = password.getText().toString();
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (email.length() != 0 && pass.length() != 0 && !checkMailExist(uname, pass)) {
                    Intent i = new Intent(SignupActivity.this, HomeActivity.class);
                } else {
                    if (pass.length() == 0) {
                        Toast.makeText(getBaseContext(), "Please enter your password", Toast.LENGTH_SHORT).show();
                    } else if (email.length() == 0) {
                        Toast.makeText(getBaseContext(), "Please enter your email", Toast.LENGTH_SHORT).show();
                    }
                    Toast.makeText(getBaseContext(), "Email is already exist", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    boolean checkMailExist(String email, String password) {

        return true;
    }
}
