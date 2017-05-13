package example.com.teachme.api;

import java.util.List;

import example.com.teachme.Comment.Comment;
import example.com.teachme.model.Game;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by MrHacker on 5/13/2017.
 */

public interface CommentAPIInterface {


    @GET("/api/comment/getByGameId/{gameId}")
    Call<List<Comment>> getComments(@Path("gameId") Integer id);

    @POST("/api/comment/addComment")
    Call<Comment> createComment(@Body Comment comment);

}
