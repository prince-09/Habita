package in.krharsh17.habita.home;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import in.krharsh17.habita.R;
import in.krharsh17.habita.energy.EnergyActivity;
import in.krharsh17.habita.food.FoodActivity;
import in.krharsh17.habita.garden.GardenActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();


        getView().findViewById(R.id.gardenimage).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), GardenActivity.class));
            }
        });

        getView().findViewById(R.id.foodimage).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), FoodActivity.class));
            }
        });

        getView().findViewById(R.id.energyimage).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), EnergyActivity.class));
            }
        });

        getView().findViewById(R.id.user_image).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((HomeActivity) getActivity()).bottomNav.setCurrentActiveItem(1);
            }
        });
    }
}
