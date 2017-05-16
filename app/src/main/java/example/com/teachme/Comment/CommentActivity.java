package example.com.teachme.Comment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.nio.channels.Channel;

import example.com.teachme.Connection.ApiUtils;
import example.com.teachme.Connection.DbUtils;
import example.com.teachme.R;
import example.com.teachme.api.CommentAPIInterface;
import example.com.teachme.model.Game;
import example.com.teachme.model.Student;
import example.com.teachme.model.Teacher;
import example.com.teachme.model.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommentActivity extends AppCompatActivity {

    ImageButton postComment = null;
    EditText inputComment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        postComment = (ImageButton) findViewById(R.id.addComment);
        inputComment = (EditText) findViewById(R.id.inputComment);

        postComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CommentAPIInterface commentAPIInterface = ApiUtils.getAPIComment();
                Comment comment = new Comment();
                comment.setComment(inputComment.getText().toString());
                Game game = new Game();
                game.setId(DbUtils.gameId);
                comment.setGame(game);

                User user = null;
                if (DbUtils.isTeacher) {
                    user = new Teacher();
                } else {
                    user = new Student();
                }
                user.setMail(DbUtils.mail);

                if (DbUtils.isTeacher) {
                    comment.setTeacher((Teacher) user);
                } else {
                    comment.setStudent((Student) user);
                }

                Call<Comment> con = commentAPIInterface.createComment(comment);

                con.enqueue(new Callback<Comment>() {
                    @Override
                    public void onResponse(Call<Comment> call, Response<Comment> response) {
                        inputComment.setText("");
                        refreashList();
                    }

                    @Override
                    public void onFailure(Call<Comment> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "No connection", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

//        refreashList();


    }

    public void refreashList()
    {
        CommentFragment commentFragment = new CommentFragment();

        getSupportFragmentManager().
                beginTransaction().
                replace(R.id.cl, commentFragment, "").
                commit();

    }

}
