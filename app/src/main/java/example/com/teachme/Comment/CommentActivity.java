package example.com.teachme.Comment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import example.com.teachme.R;

public class CommentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        CommentFragment commentFragment = new CommentFragment();

        /*
        getSupportFragmentManager().
                beginTransaction().
                replace(R.id.cl, commentFragment ,"").
                commit();
*/
    }
}
