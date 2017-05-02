package example.com.teachme.Game;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import example.com.teachme.Connection.ApiUtils;
import example.com.teachme.Connection.DbUtils;
import example.com.teachme.Course.CreateCourseActivity;
import example.com.teachme.R;
import example.com.teachme.api.GameAPIInterface;
import example.com.teachme.model.Game;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateGameActivity extends AppCompatActivity {

    EditText gName ;
    EditText gDesc ;
    Call<String> conn ;
    GameAPIInterface gameAPIInterface ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_game);

        gName = (EditText)findViewById(R.id.gameName);
        gDesc = (EditText)findViewById(R.id.gameDesc);

        gameAPIInterface = ApiUtils.getAPIGame();



    }

    public void createGame(View view)
    {
        String name = gName.getText().toString();
        String desc = gDesc.getText().toString();
        if(name.length()>0)
        {
            if(desc.length()>0)
            {
                Game game = new Game();
                game.setName(name);
                game.setDescription(desc);
                conn = gameAPIInterface.createGame(game, DbUtils.courseId);
                conn.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {

                        //String res = response.body();
                        Toast.makeText(getBaseContext()," Game has been created",Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                       // Toast.makeText(getBaseContext(),"No connection",Toast.LENGTH_SHORT).show();

                    }
                });
                startActivity(new Intent(CreateGameActivity.this,GameActivity.class));
            }
            else
                Toast.makeText(getBaseContext(),"Please enter game description",Toast.LENGTH_SHORT).show();
        }
        else
            Toast.makeText(getBaseContext(),"Please enter game name",Toast.LENGTH_SHORT).show();
    }
}
