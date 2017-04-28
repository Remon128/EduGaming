package example.com.teachme.api;

import java.util.List;

import example.com.teachme.Question.MCQ;
import example.com.teachme.Question.Question;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by MrHacker on 4/19/2017.
 */

public interface QuestionAPIInterface {
    @GET("/api/get/questions/{gameId}")
    Call<List<MCQ>> getQuestions(@Path("gameId")String gameId);

    @GET("/api/games/{gameId}")
    Call<List<MCQ>> getQuestion(@Path("gameId") String id);

    @POST("api/create/question/{gameId}")
    Call<MCQ> createQuestion(@Path("gameId")Integer id, @Body MCQ question);

}
