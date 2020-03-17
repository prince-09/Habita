package in.krharsh17.habita.RecyclerAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import in.krharsh17.habita.R;

public class RecyclerAdapterGarden extends RecyclerView.Adapter<RecyclerAdapterGarden.ViewHolderGarden> {
    public class ViewHolderGarden extends RecyclerView.ViewHolder {
        ImageView plantImage;
        TextView plantName, waterDetail, removePlant ,waterPlant;

        public ViewHolderGarden(@NonNull View itemView) {
            super(itemView);
            plantImage = itemView.findViewById(R.id.plantimage);
            plantName = itemView.findViewById(R.id.plantname);
            waterDetail = itemView.findViewById(R.id.waterdetail);
            removePlant = itemView.findViewById(R.id.removeplant);
            waterPlant = itemView.findViewById(R.id.waterplant);
        }
    }
    @NonNull
    @Override
    public ViewHolderGarden onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.plantrecycleritem,parent,false);
        return new ViewHolderGarden(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderGarden holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}