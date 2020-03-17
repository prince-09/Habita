package in.krharsh17.habita.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class IngredientToRecipeClient {
    public static final String BASE_URL = "https://api.spoonacular.com/recipes/";
    public static IngredientToRecipeClient instance;
    public static Retrofit retrofit;

    private IngredientToRecipeClient() {

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }

    public static synchronized IngredientToRecipeClient getInstance() {

        if (instance == null) {
            instance = new IngredientToRecipeClient();
        }
        return instance;
    }

    public APIStructure getApi() {
        return retrofit.create(APIStructure.class);
    }
}