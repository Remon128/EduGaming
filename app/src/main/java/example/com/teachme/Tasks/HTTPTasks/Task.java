package example.com.teachme.Tasks.HTTPTasks;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by MrHacker on 4/15/2017.
 */

public class Task extends AsyncTask<String, Integer, String> {
    private ProgressBar bar;
    public static String Tag = "TEST_DEBUG";
    public OnPostExecute onPostExecute;
    private final Context context;
    private String json;

    public String getJson() {
        return json;
    }

    public Task(Context context, OnPostExecute postExecute) {
        this.context = context;
        this.onPostExecute = postExecute;
    }

    public void setProgressBar(ProgressBar bar) {
        this.bar = bar;
    }

    @Override
    public void onPreExecute() {
        json = "";
        super.onPreExecute();
        if (bar != null)
            bar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        if (this.bar != null)
            bar.setProgress(values[0]);
    }

    // params[0] is url
    @Override
    public String doInBackground(String... params) {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        json = null;
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

            while ((line = reader.readLine()) != null)
                buffer.append(line + "\n");


            if (buffer.length() == 0)
                return null;

            json = buffer.toString();


//                Log.d(Tag,"OnBackground="+jsonSt+r);
        } catch (IOException e) {

            json = null;
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
        return json;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        onPostExecute.onPostExecute(s);
        if (bar != null)
            bar.setVisibility(View.INVISIBLE);
    }
}