package example.com.teachme.Game;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import example.com.teachme.Connection.ApiUtils;
import example.com.teachme.Connection.DbUtils;
import example.com.teachme.R;
import example.com.teachme.User.MainActivity;
import example.com.teachme.User.StudentActivity;
import example.com.teachme.User.TeacherActivity;

public class GameActivity extends AppCompatActivity {

    int courseId ;
    Button courseBtn ;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        try {
            if(id == R.id.logout)
            {
                Toast.makeText(getBaseContext(),id,Toast.LENGTH_SHORT).show();
                DbUtils.delete();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
                setResult(ApiUtils.logoutTag);
            }else if(id == R.id.main)
            {
                if(DbUtils.isTeacher)
                    startActivity(new Intent(getApplicationContext(), TeacherActivity.class));
                else
                    startActivity(new Intent(getApplicationContext(), StudentActivity.class));
            }
            return super.onOptionsItemSelected(item);
        } catch (Exception e) {
            Toast.makeText(getBaseContext(), "No Internet Connection", Toast.LENGTH_SHORT).show();
            return super.onOptionsItemSelected(item);
        }
    }
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

    @Override
    public void onBackPressed() {
        finish();
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
