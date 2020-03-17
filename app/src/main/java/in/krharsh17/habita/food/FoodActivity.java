package in.krharsh17.habita.food;

import android.content.Intent;
import android.hardware.Camera;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import in.krharsh17.habita.R;
import in.krharsh17.habita.api.IngredientToRecipeClient;
import in.krharsh17.habita.api.RecipeToIngredientsClient;
import in.krharsh17.habita.models.data;
import in.krharsh17.habita.models.dishesop;
import in.krharsh17.habita.models.images;
import in.krharsh17.habita.models.inputs;
import in.krharsh17.habita.models.model;
import in.krharsh17.habita.models.outputs;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class FoodActivity extends AppCompatActivity {
String ingredients="";

Button foodscan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_food);

        final String API="407eb22d13af48da89dbe77f0d2932d6";
        data data1=new data("https://clarifai.com/cms-assets/20180320212159/food-003.jpg");
        images images=new images(data1);
        inputs inputs=new inputs(images);
        List<in.krharsh17.habita.models.inputs> inputsList=new ArrayList<>();
        inputsList.add(inputs);
        foodscan=findViewById(R.id.foodscan);
        Log.i("hey","sucess");
        foodscan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FoodActivity.this,FoodScan.class));
                finish();
            }
        });
        final model model=new model(inputsList);
        Call<outputs> call= RecipeToIngredientsClient.getInstance().getApi().verifyOtp("Key "+API,model);
        call.enqueue(new Callback<outputs>() {
            @Override
            public void onResponse(Call<outputs> call, Response<outputs> response) {
                Log.i("res",response.isSuccessful()+" "+response.raw()+" ");
                if(response.isSuccessful()&&response.body()!=null){
                    for(int i=0;i<response.body().getResultsList().get(0).getConcepts().getItems().size();i++) {
                        Log.i("response", response.body().getResultsList().get(0).getConcepts().getItems().get(i).getName());
                        ingredients+=response.body().getResultsList().get(0).getConcepts().getItems().get(i).getName();
                        if(i!=response.body().getResultsList().get(0).getConcepts().getItems().size()-1){
                            ingredients+=",+";
                        }
                    }
                }
            }
            @Override
            public void onFailure(Call<outputs> call, Throwable t) {
                Log.i("error",t.toString());
            }
        });
        //ingredients to recipe
        Call<List<dishesop>> call1= IngredientToRecipeClient.getInstance().getApi().dishes(ingredients,API);
        call1.enqueue(new Callback<List<dishesop>>() {
            @Override
            public void onResponse(Call<List<dishesop>> call, Response<List<dishesop>> response) {
                Log.i("hey",response.isSuccessful()+" "+response.raw());
                if(response.isSuccessful()&&response.body()!=null){
                    for(int  i=0;i<response.body().size();i++)
                        Log.i("hey",response.body().get(i).getTitle());
                }
            }

            @Override
            public void onFailure(Call<List<dishesop>> call, Throwable t) {

            }
        });
    }

}
