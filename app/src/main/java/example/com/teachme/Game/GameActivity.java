package example.com.teachme.Game;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import example.com.teachme.Connection.DbUtils;
import example.com.teachme.Question.CreateQuestionFragment;
import example.com.teachme.R;

public class GameActivity extends AppCompatActivity {

    int courseId ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        GameFragment gameFragment = new GameFragment(DbUtils.courseId,getBaseContext());

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.gamelist,gameFragment,"")
                .commit();
    }

    public void addQuestion(View view)
    {
/*
        CreateQuestionFragment createQuestionFragment = new CreateQuestionFragment(getBaseContext(),);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.gamelist,createQuestionFragment,"")
                .commit();
  */
    }
    public void showQuestion(View view)
    {
        GameFragment gameFragment = new GameFragment(DbUtils.courseId,getBaseContext());
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.gamelist,gameFragment,"")
                .commit();
    }
}
