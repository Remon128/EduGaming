package example.com.teachme.api;

import java.util.List;

import example.com.teachme.Question.MCQ;
import example.com.teachme.Question.Question;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
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

    @PUT("/api/update/question/{questionId}")
    Call<MCQ> updateQuestion(@Body MCQ question , @Path("questionId") Integer questionId);

    @POST("/api/create/Question/{gameId}")
    Call<MCQ> createQuestion(@Path("gameId")String id, @Body MCQ question);

}
