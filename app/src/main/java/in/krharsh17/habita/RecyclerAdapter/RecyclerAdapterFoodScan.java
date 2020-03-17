package in.krharsh17.habita.RecyclerAdapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import in.krharsh17.habita.R;
import in.krharsh17.habita.models.dishesop;
import in.krharsh17.habita.models.missedIngredients;

public class RecyclerAdapterFoodScan extends RecyclerView.Adapter<RecyclerAdapterFoodScan.ViewHolderFoodScan> {
    List<dishesop> dishesops;
    Context context;

    public RecyclerAdapterFoodScan(List<dishesop> dishesops, Context context) {
        this.dishesops = dishesops;
        this.context = context;
    }

    public class ViewHolderFoodScan extends RecyclerView.ViewHolder{

        ImageView  foodImage;
        TextView foodName;
        TextView availableIngredients;
        ListView ingredientsList;
        LinearLayout missingIngredients;
        ImageView downarrow;

        public ViewHolderFoodScan(@NonNull View itemView) {
            super(itemView);
            foodImage = itemView.findViewById(R.id.foodimage);
            foodName = itemView.findViewById(R.id.foodname);
            availableIngredients = itemView.findViewById(R.id.availableingredients);
            ingredientsList = itemView.findViewById(R.id.ingredientslist);
            missingIngredients = itemView.findViewById(R.id.missingingredients);
            downarrow = itemView.findViewById(R.id.downarrow);
        }
    }
    @NonNull
    @Override
    public ViewHolderFoodScan onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.foodrecycleritem,parent,false);

        return new ViewHolderFoodScan(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderFoodScan holder, int position) {
        dishesop dishesop = dishesops.get(position);
        holder.foodName.setText(dishesop.getTitle());
        Log.i("adap","adap");
        holder.availableIngredients.setText("Available Ingredients: " + "");
        ArrayList<String> arrayList = new ArrayList<>();
        for(missedIngredients name : dishesop.getMissedIngredientsList()){
            arrayList.add(name.getName());
        }
        Glide.with(context).load(dishesop.getImage()).centerCrop().into(holder.foodImage);
        //ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(context, R.layout.fooditemlisttextview, arrayList);
        //holder.ingredientsList.setAdapter(arrayAdapter);

    }

    @Override
    public int getItemCount() {
        return dishesops.size();
    }


}