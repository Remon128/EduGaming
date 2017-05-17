package example.com.teachme.Comment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.nio.channels.Channel;
import java.util.List;

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
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class CommentActivity extends AppCompatActivity {

    ImageButton postComment = null;
    EditText inputComment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        postComment = (ImageButton) findViewById(R.id.addComment);
        inputComment = (EditText) findViewById(R.id.inputComment);


        postComment.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        CommentAPIInterface commentAPIInterface = ApiUtils.getAPICommentObservable();
                        Comment comment = new Comment();
                        comment.setComment(inputComment.getText().toString());
                        inputComment.setText("");
                        Game game = new Game();
                        game.setId(DbUtils.gameId);

                        comment.setGame(game);

                        comment.setMail(DbUtils.mail);


                        Observable<Comment> con = commentAPIInterface.createComment(comment);

                        con.subscribeOn(Schedulers.newThread())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new Subscriber<Comment>() {
                                    @Override
                                    public void onCompleted() {
                                        unsubscribe();
                                    }

                                    @Override
                                    public void onError(Throwable e) {
                                        Toast.makeText(getBaseContext(), "No Connection", Toast.LENGTH_SHORT).show();
                                    }

                                    @Override
                                    public void onNext(Comment comments) {
                                        refreashList();
                                    }
                                });
                    }
                });
            refreashList();

    }

    public void refresh(View view)
    {
        CommentFragment commentFragment = new CommentFragment();

        getSupportFragmentManager().
                beginTransaction().
                replace(R.id.cl, commentFragment, "").
                commit();

    }
    public void refreashList() {
        CommentFragment commentFragment = new CommentFragment();

        getSupportFragmentManager().
                beginTransaction().
                replace(R.id.cl, commentFragment, "").
                commit();

    }
}
