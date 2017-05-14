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
import retrofit2.http.Streaming;


public interface CommentAPIInterface {

    @Streaming
    @GET("/api/comments/getByGameId/{gameId}")
    Call<List<Comment>> getComments(@Path("gameId") String id);

    @POST("/api/comments/addComment")
    Call<Comment> createComment(@Body Comment comment);

}
