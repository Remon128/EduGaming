package example.com.teachme.api;

import java.util.List;

import example.com.teachme.Question.Question;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by MrHacker on 4/19/2017.
 */

public interface QuestionAPIInterface {
    @GET("/api/question")
    Call<List<Question>> getQuestions();

    @GET("/api/games")
    Call<Question> getQuestion(@Query("name") String name);

    @POST("api/games")
    Call<Question> createQuestion(@Body Question question);

}
