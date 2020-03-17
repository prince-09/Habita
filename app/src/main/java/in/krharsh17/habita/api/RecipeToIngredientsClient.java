package in.krharsh17.habita.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RecipeToIngredientsClient {
    public static final String BASE_URL = "https://api.clarifai.com/v2/";
    public static RecipeToIngredientsClient instance;
    public static Retrofit retrofit;

    private RecipeToIngredientsClient() {

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }

    public static synchronized RecipeToIngredientsClient getInstance() {

        if (instance == null) {
            instance = new RecipeToIngredientsClient();
        }
        return instance;
    }

    public APIStructure getApi() {
        return retrofit.create(APIStructure.class);
    }
}