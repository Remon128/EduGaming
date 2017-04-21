package example.com.teachme.User;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import example.com.teachme.R;

public class EditUserActivity extends AppCompatActivity {
    EditText username , password , confirm ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);
        username = (EditText)findViewById(R.id.username);
        password = (EditText)findViewById(R.id.password);
        confirm = (EditText)findViewById(R.id.confirm);
    }
}
