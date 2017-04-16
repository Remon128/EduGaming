package example.com.teachme.User;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import example.com.teachme.HomeActivity;
import example.com.teachme.R;

public class MainActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    String url = "http://10.0.2.2:8080/";
    ArrayList<User> users;
    String json = "";
    public static String Tag = "TEST_DEBUG";
    String email_str;
    String password_str;
    EditText email, password;
    RadioButton student , teacher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        users = new ArrayList<>();

        try {

            progressBar = (ProgressBar) findViewById(R.id.progressbar);
            email = (EditText) findViewById(R.id.email1);
            password = (EditText) findViewById(R.id.password1);
            Button signin = (Button) findViewById(R.id.signin1);
            Button signup = (Button) findViewById(R.id.signup);
            Button forget = (Button) findViewById(R.id.forget);
            student = (RadioButton)findViewById(R.id.student);
            teacher = (RadioButton)findViewById(R.id.teacher);

            signin.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    email_str = email.getText().toString();
                    password_str = password.getText().toString();
                    Intent i = null ;

                    if (validateData(email_str, password_str)) {


                        if(student.isChecked())
                        {
                            i = new Intent(MainActivity.this, StudentActivity.class);
                        }
                        else if(teacher.isChecked())
                        {
                            i = new Intent(MainActivity.this, TeacherActivity.class);
                        }
                        else {
                            i = new Intent(MainActivity.this, HomeActivity.class);
                        }

                        i.putExtra("email", email_str);
                        i.putExtra("password", password_str);

                        final Context context = getApplication();

                        /*
                        Task fetchUsers = new Task(context, new OnPostExecute() {
                            @Override
                            public void onPostExecute(String input) {
                                json = input;
                            }
                        });

                        Toast.makeText(context, "Value - " + json, Toast.LENGTH_SHORT).show();

                        fetchUsers.setProgressBar(progressBar);
                        fetchUsers.execute(url);

                        setDataFromJson(json);
                        //                   i.putExtra("json", json);
*/
                        startActivity(i);
//                    finish();
                    } else {
                        Toast.makeText(getBaseContext(), "You entered wrong email or password", Toast.LENGTH_SHORT).show();
                    }
                }
            });

            signup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(MainActivity.this, SignupActivity.class));
                }
            });


            forget.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getBaseContext(), "Check your mail", Toast.LENGTH_SHORT).show();
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setDataFromJson(String json) {
        try {
            final String name = "name";
            final String email = "email";
            final String password = "password";
            JSONArray jsonArray = new JSONArray(json);
            if (jsonArray != null)
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject user = jsonArray.getJSONObject(i);
                    String n = user.getString(name);
                    Toast.makeText(getBaseContext(), n, Toast.LENGTH_SHORT).show();
                }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    boolean validateData(String email, String password) {

        return true;
    }

    class FetchUser extends AsyncTask<String, Integer, String> {
        Context context;

        FetchUser(Context context) {
            this.context = context;
        }

        @Override
        protected String doInBackground(String... params) {
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;
            String jsonStr = null;
            try {
                String baseUrl = params[0];
                URL url = new URL(baseUrl);
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                InputStream inputStream = urlConnection.getInputStream();
                if (inputStream == null) return null;

                reader = new BufferedReader(new InputStreamReader(inputStream));

                StringBuffer buffer = new StringBuffer();
                String line;

                Toast.makeText(context, baseUrl, Toast.LENGTH_SHORT).show();
                while ((line = reader.readLine()) != null)
                    buffer.append(line + "\n");


                if (buffer.length() == 0) return null;

                jsonStr = buffer.toString();

                Toast.makeText(context, jsonStr, Toast.LENGTH_SHORT).show();
            } catch (IOException e) {

                jsonStr = null;
                Log.e("TASK EXCEPTION", e.toString());

            } finally {

                if (urlConnection != null)
                    urlConnection.disconnect();

                if (reader != null)
                    try {
                        reader.close();
                    } catch (final IOException e) {

                    }
            }
            return jsonStr;
        }

        @Override
        protected void onPostExecute(String s) {
            //super.onPostExecute(s);
            Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
        }

    }
}
