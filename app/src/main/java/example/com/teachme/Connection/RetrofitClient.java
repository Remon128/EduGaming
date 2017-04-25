package example.com.teachme.Connection;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


// singleton design pattern for creation objects
public class RetrofitClient {

    private static Retrofit retrofit = null;

    public static Retrofit getClient(String baseUrl) {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }


}