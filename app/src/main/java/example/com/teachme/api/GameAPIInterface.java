package example.com.teachme.api;

import java.util.List;

import example.com.teachme.model.Game;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by MrHacker on 4/18/2017.
 */

public interface GameAPIInterface {

    @GET("/api/games")
    Call<List<Game>> getGames();

    @GET("/api/games")
    Call<Game> getGame(@Query("name") String name);

    @POST("api/games")
    Call<Game> createGame(@Body Game game);

}
