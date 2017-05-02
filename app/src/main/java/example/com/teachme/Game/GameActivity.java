package example.com.teachme.Game;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import example.com.teachme.Connection.DbUtils;
import example.com.teachme.Question.CreateQuestionFragment;
import example.com.teachme.R;

public class GameActivity extends AppCompatActivity {

    int courseId ;
    Button courseBtn ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        courseBtn = (Button)findViewById(R.id.createCourse);
        GameFragment gameFragment = new GameFragment(DbUtils.courseId,getBaseContext());

        if(DbUtils.isTeacher)
            courseBtn.setVisibility(View.VISIBLE);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.gamelist,gameFragment,"")
                .commit();
    }

    public void createGamebtn(View view)
    {
        startActivity(new Intent(GameActivity.this,CreateGameActivity.class));
    }
    /*
    public void showQuestion(View view)
    {
        GameFragment gameFragment = new GameFragment(DbUtils.courseId,getBaseContext());
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.gamelist,gameFragment,"")
                .commit();
    }*/
}
