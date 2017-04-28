package example.com.teachme.Game;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import example.com.teachme.R;

public class GameActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        int courseId = getIntent().getIntExtra("courseid",0);
        GameFragment gameFragment = new GameFragment(courseId,getBaseContext());

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.gamelist,gameFragment,"")
                .commit();
    }
}
