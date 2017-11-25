package example.com.teachme.Question;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import example.com.teachme.Connection.ApiUtils;
import example.com.teachme.R;
import example.com.teachme.api.QuestionAPIInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static example.com.teachme.Connection.DbUtils.gameId;

public class CreateQuestionActivity extends AppCompatActivity implements CreateQuestionFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_question);

        addQuestion();
    }

    public void addQuestion() {

        CreateQuestionFragment questionFragment =  CreateQuestionFragment.newInstance();

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.flquestion1, questionFragment, "")
                .commit();
    }

    @Override
    public void onFragmentInteraction(MCQ question) {
        QuestionAPIInterface questionAPIInterface = ApiUtils.getAPIQuestion();
        Call<MCQ> conn = questionAPIInterface.createQuestion(gameId, question);


        conn.enqueue(new Callback<MCQ>() {
            @Override
            public void onResponse(Call<MCQ> call, Response<MCQ> response) {
            }

            @Override
            public void onFailure(Call<MCQ> call, Throwable t) {
                Toast.makeText(getBaseContext(), getString(R.string.no_internet), Toast.LENGTH_SHORT).show();
            }
        });

        finish();
    }
}
