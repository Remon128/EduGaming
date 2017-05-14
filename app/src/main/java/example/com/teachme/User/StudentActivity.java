package example.com.teachme.User;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import example.com.teachme.Connection.DbUtils;
import example.com.teachme.model.Course;
import example.com.teachme.Course.CourseFragment;
import example.com.teachme.R;
import example.com.teachme.model.User;

public class StudentActivity extends AppCompatActivity implements CourseFragment.OnListFragmentInteractionListener , TextToSpeech.OnInitListener {
    @Override
    public void onListFragmentInteraction(Course item) {

    }

    TextView test;
    SharedPreferences settings;
    TextToSpeech tts;
    Button logout;
    Button speak ;
    String email;
    String text ;
//    private static final int SPEECH_REQUEST_CODE = 0;

    private static final String TAG = "MagicWord";

    private int SPEECH_REQUEST_CODE = 1234;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        speak = (Button)findViewById(R.id.sound);

        email = DbUtils.mail;

        text = "Welcome "+DbUtils.name;
/*
        Toast.makeText(getApplicationContext(), text ,Toast.LENGTH_SHORT).show();

*/


    }

    // Create an intent that can start the Speech Recognizer activity
    private void displaySpeechRecognizer() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
// Start the activity, the intent will be populated with the speech text
        startActivityForResult(intent, SPEECH_REQUEST_CODE);
    }

    // This callback is invoked when the Speech Recognizer returns.
// This is where you process the intent and extract the speech text from the intent.
    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        if (requestCode == SPEECH_REQUEST_CODE && resultCode == RESULT_OK) {
            List<String> results = data.getStringArrayListExtra(
                    RecognizerIntent.EXTRA_RESULTS);
            String spokenText = results.get(0);
            Toast.makeText(getBaseContext(),spokenText,Toast.LENGTH_SHORT).show();
            // Do something with spokenText
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void sound(View view) {
        displaySpeechRecognizer();

        tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                // change required.Initialization has to finish first.
                if (status != TextToSpeech.ERROR) {
                    tts.setLanguage(Locale.UK);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        tts.speak(text,TextToSpeech.QUEUE_FLUSH,null,null);
                    } else {
                        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
                    }
                }
            }
        });

    }





    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        startActivity(intent);
    }

    public void listCourses(View view) {
        CourseFragment courseFragment = new CourseFragment(email, 3);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.flcourse, courseFragment, "")
                .commit();

    }

    public void Enroll(View view) {
        CourseFragment courseFragment = new CourseFragment(email, 2);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.flcourse, courseFragment, "")
                .commit();
    }


    public void logout(View view) {
        settings = getSharedPreferences("mySharedPref", 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean("connected", false);
        editor.apply();
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finish();

    }


    /**
     * Called to signal the completion of the TextToSpeech engine initialization.
     *
     * @param status {@link TextToSpeech#SUCCESS} or {@link TextToSpeech#ERROR}.
     */
    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS)
        {
            speak.setEnabled(true);
        }
        else
        {
            //failed to init
            finish();
        }

    }
}
