package example.com.teachme.api;

import java.util.List;

import example.com.teachme.model.Game;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by MrHacker on 4/18/2017.
 */

public interface GameAPIInterface {

    @GET("/api/games/{courseId}")
    Call<List<Game>> getGames(@Path("courseId") int id);

    @GET("/api/games")
    Call<Game> getGame(@Query("name") String name);

    @POST("/api/game/{courseId}")
    Call<Game> createGame(@Body Game game,@Path("courseId") Integer id);

}
