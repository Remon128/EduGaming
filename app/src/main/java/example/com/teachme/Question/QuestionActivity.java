package example.com.teachme.Question;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

import example.com.teachme.Connection.ApiUtils;
import example.com.teachme.Connection.DbUtils;
import example.com.teachme.R;
import example.com.teachme.api.QuestionAPIInterface;
import example.com.teachme.model.Game;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuestionActivity extends AppCompatActivity implements CreateQuestionFragment.OnFragmentInteractionListener {
    String gameId = null;
    Button addbtn;
    Button showbtn;
    Button okbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        gameId = DbUtils.gameId;

        addbtn = (Button) findViewById(R.id.addbtn);
        showbtn = (Button) findViewById(R.id.showbtn);
        okbtn = (Button) findViewById(R.id.submit);


        if (!DbUtils.isTeacher) {
            okbtn.setVisibility(View.VISIBLE);
        } else {
            addbtn.setVisibility(View.VISIBLE);
            showbtn.setVisibility(View.VISIBLE);
            okbtn.setVisibility(View.VISIBLE);
        }


        QuestionFragment questionFragment = new QuestionFragment(gameId);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.flquestion, questionFragment, "")
                .commit();
    }

    public void addQuestion(View view) {

        okbtn.setVisibility(View.GONE);
        CreateQuestionFragment questionFragment = new CreateQuestionFragment(getBaseContext(), this);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.flquestion, questionFragment, "")
                .commit();

    }

    public void submit(View view) {
        Integer score = 0;

        try {

            int[] answers = QuestionRecyclerViewAdapter.getUserAnswers();
            List<MCQ> mcqs = QuestionRecyclerViewAdapter.getQuestions();

            if ((answers.length - 1) == mcqs.size())
                for (int i = 1; i < answers.length; i++) {
                    if (answers[i] == mcqs.get(i-1).getAnswer()) {
                        score += 10;
                    }
                }
            Toast.makeText(getBaseContext(), "Your Score is " + score, Toast.LENGTH_SHORT).show();
        }catch (Exception e)
        {
            //
        }
    }

    public void showQuestion(View view) {

        okbtn.setVisibility(View.VISIBLE);

        QuestionFragment questionFragment = new QuestionFragment(gameId);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.flquestion, questionFragment, "")
                .commit();
    }

    @Override
    public void onFragmentInteraction(MCQ question) {

        Toast.makeText(getBaseContext(), question.getDescription(), Toast.LENGTH_SHORT).show();

        QuestionAPIInterface questionAPIInterface = ApiUtils.getAPIQuestion();
        Call<MCQ> conn = questionAPIInterface.createQuestion(gameId, question);

        conn.enqueue(new Callback<MCQ>() {
            @Override
            public void onResponse(Call<MCQ> call, Response<MCQ> response) {
//                Toast.makeText(getBaseContext(),response.body().getDescription(),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<MCQ> call, Throwable t) {
                Toast.makeText(getBaseContext(), "No connection", Toast.LENGTH_SHORT).show();
            }

        });

    }
}