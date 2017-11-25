package example.com.teachme.Question;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import example.com.teachme.Connection.ApiUtils;
import example.com.teachme.Connection.DbUtils;
import example.com.teachme.R;
import example.com.teachme.Score.ScoreActivity;
import example.com.teachme.User.MainActivity;
import example.com.teachme.User.StudentActivity;
import example.com.teachme.User.TeacherActivity;
import example.com.teachme.api.QuestionAPIInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuestionActivity extends AppCompatActivity implements CreateQuestionFragment.OnFragmentInteractionListener, QuestionRecyclerViewAdapter.OnRVInteractionListener, TextToSpeech.OnInitListener {
    String gameId = null;
    Button addbtn;
    Button showbtn;
    Button okbtn;
    boolean state = false;
    String check = null;
    private static final String TAG = "MagicWord";

    private TextView result;
    private TextToSpeech tts;

    private int SPEECH_REQUEST_CODE = 1234;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        try {
            if (id == R.id.logout) {
                Toast.makeText(getBaseContext(), id, Toast.LENGTH_SHORT).show();
                DbUtils.delete();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
                setResult(ApiUtils.logoutTag);
            } else if (id == R.id.main) {
                if (DbUtils.isTeacher)
                    startActivity(new Intent(getApplicationContext(), TeacherActivity.class));
                else
                    startActivity(new Intent(getApplicationContext(), StudentActivity.class));
            }
            return super.onOptionsItemSelected(item);
        } catch (Exception e) {
            Toast.makeText(getBaseContext(), getString(R.string.no_internet), Toast.LENGTH_SHORT).show();
            return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        tts = new TextToSpeech(this, this);

        gameId = DbUtils.gameId;

        addbtn = (Button) findViewById(R.id.addbtn);
        showbtn = (Button) findViewById(R.id.showbtn);
        okbtn = (Button) findViewById(R.id.submit);
        
        if (DbUtils.isTeacher) {
            showbtn.setVisibility(View.VISIBLE);
            addbtn.setVisibility(View.VISIBLE);
        }


        QuestionFragment questionFragment = QuestionFragment.newInstance(gameId);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.flquestion, questionFragment, "")
                .commit();
    }

    public void addQuestion(View view) {

        startActivity(new Intent(this, CreateQuestionActivity.class));

        /*
        okbtn.setVisibility(View.GONE);
        CreateQuestionFragment questionFragment = new CreateQuestionFragment(getBaseContext(), this);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.flquestion, questionFragment, "")
                .commit();
*/
    }

    @Override
    public void onBackPressed() {
        finish();
    }


    public void submit(View view) {
        Integer score = 0;

        try {
            int[] answers = QuestionRecyclerViewAdapter.getUserAnswers();
            List<MCQ> mcqs = QuestionRecyclerViewAdapter.getQuestions();

            for (int i = 0; i < answers.length; i++) {
                if (mcqs.get(i).getChoices().length == 1) {
                    if (answers[i] == 1)
                        score += 10;
                } else if (answers[i] == mcqs.get(i).getAnswer()) {
                    score += 10;
                }
            }


            String scoreMsg = getString(R.string.your_score_is) + "\n" + score;
            Intent i = new Intent(QuestionActivity.this, ScoreActivity.class);
            i.putExtra("score", scoreMsg);
            startActivity(i);
            finish();
        } catch (Exception e) {
            //
        }
    }

    public void showQuestion(View view) {

        okbtn.setVisibility(View.VISIBLE);

        QuestionFragment questionFragment = QuestionFragment.newInstance(gameId);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.flquestion, questionFragment, "")
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
    }


    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {

        } else {
            //failed to init
            finish();
        }

    }

    private void sendRecognizeIntent() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, getString(R.string.say) + check);
        intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 100);

        startActivityForResult(intent, SPEECH_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == SPEECH_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                ArrayList<String> matches = data
                        .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

                if (matches.size() == 0) {
                    tts.speak(getString(R.string.heard_nothing), TextToSpeech.QUEUE_FLUSH, null);
                } else {
                    String matched = matches.get(0);
                    String magicWord = check.toLowerCase();
                    if (matched.equals(magicWord)) {
                        state = true;
                        tts.speak(getString(R.string.exc_you_said_word_right), TextToSpeech.QUEUE_FLUSH, null);
                    } else {
                        tts.speak(getString(R.string.try_again), TextToSpeech.QUEUE_FLUSH, null);
                    }
                }
                Toast.makeText(getBaseContext(), matches.get(0), Toast.LENGTH_SHORT).show();

            } else {
                Log.d(TAG, "result NOT ok");
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    protected void onDestroy() {
        if (tts != null) {
            tts.shutdown();
        }
        super.onDestroy();
    }

    @Override
    public boolean onClickInteraction(String word) {

        check = word;

        sendRecognizeIntent();

        Toast.makeText(getApplication(), state + " 2", Toast.LENGTH_SHORT).show();

        return state;

    }
}