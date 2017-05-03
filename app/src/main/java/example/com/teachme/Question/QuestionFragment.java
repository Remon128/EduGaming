package example.com.teachme.Question;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import example.com.teachme.Connection.ApiUtils;
import example.com.teachme.R;
import example.com.teachme.api.QuestionAPIInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class QuestionFragment extends Fragment implements TextToSpeech.OnInitListener{


    private OnListFragmentInteractionListener mListener;
    QuestionRecyclerViewAdapter adapter;
    List<MCQ> mcqList = null;
    Call<List<MCQ>> connection;

    public QuestionFragment(String gameId) {
        mcqList = new ArrayList<>();
        QuestionAPIInterface questionAPIInterface = ApiUtils.getAPIQuestion();
        connection = questionAPIInterface.getQuestions(gameId);
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_question_list, container, false);

        // Set the adapter
        Context context = view.getContext();
        RecyclerView recyclerView = (RecyclerView) view;
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        adapter = new QuestionRecyclerViewAdapter(mcqList, context);
        recyclerView.setAdapter(adapter);


        connection.enqueue(new Callback<List<MCQ>>() {
            @Override
            public void onResponse(Call<List<MCQ>> call, Response<List<MCQ>> response) {

                if (response.isSuccessful()) {
                    if (response.body() != null)
                        mcqList.addAll(response.body());
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<MCQ>> call, Throwable t) {

            }
        });


        return view;
    }


    private static final String TAG = "MagicWord";
    private TextView result;
    private TextToSpeech tts;
    private Button speak;

    private int SPEECH_REQUEST_CODE = 1234;


    public void sendRecognizeIntent() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Say Car");
        intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 100);
        startActivityForResult(intent, SPEECH_REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == SPEECH_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                ArrayList<String> matches = data
                        .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

                if (matches.size() == 0) {
                    tts.speak("Heard nothing", TextToSpeech.QUEUE_FLUSH, null);
                } else {
                    String mostLikelyThingHeard = matches.get(0);
                    String magicWord = this.getResources().getString(R.string.magicword);
                    if (mostLikelyThingHeard.equals(magicWord)) {
                        tts.speak("You said the magic word!", TextToSpeech.QUEUE_FLUSH, null);
                    } else {
                        tts.speak("The magic word is not " + mostLikelyThingHeard + " try again", TextToSpeech.QUEUE_FLUSH, null);
                    }
                }
                result.setText("heard: " + matches);
            } else {
                Log.d(TAG, "result NOT ok");
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
            speak.setEnabled(true);
        } else {
            //failed to init
        }
    }


    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(Question item);
    }
}
