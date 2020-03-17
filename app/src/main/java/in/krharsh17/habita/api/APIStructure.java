package in.krharsh17.habita.api;

import java.util.List;

import in.krharsh17.habita.models.dishesop;
import in.krharsh17.habita.models.model;
import in.krharsh17.habita.models.outputs;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface APIStructure {
    @POST("models/bd367be194cf45149e75f01d59f77ba7/outputs")
    Call<outputs> verifyOtp(
            @Header("Authorization") String key,
            @Body model model
    );

    @GET("findByIngredients")
    Call<List<dishesop>> dishes(
            @Query("ingredients") String ingredients,
            @Query("apiKey") String apikey
    );
}