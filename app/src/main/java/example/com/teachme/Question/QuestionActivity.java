package example.com.teachme.Question;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import example.com.teachme.R;

public class QuestionActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        String gameId = getIntent().getStringExtra("gameId");
        Toast.makeText(getBaseContext(),""+gameId,Toast.LENGTH_SHORT).show();
        QuestionFragment questionFragment = new QuestionFragment(gameId);

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.flquestion,questionFragment,"")
                .commit();
    }
}