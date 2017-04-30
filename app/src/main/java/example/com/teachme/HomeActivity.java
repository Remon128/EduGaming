package example.com.teachme;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Intent i = getIntent();
        String email = i.getStringExtra("email");
        String pass = i.getStringExtra("password");
        String json = i.getStringExtra("json");
    }
}
