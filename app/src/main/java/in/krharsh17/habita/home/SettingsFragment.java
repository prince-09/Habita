package in.krharsh17.habita.home;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;

import androidx.fragment.app.Fragment;

import in.krharsh17.habita.R;
import in.krharsh17.habita.SharedPrefManager;


/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends Fragment {
    Switch Notif;


    public SettingsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        Notif = getView().findViewById(R.id.switchbattery);

        if (SharedPrefManager.getBatteryEnabled(getContext()))
            Notif.setChecked(true);

        Notif.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SharedPrefManager.setBatteryEnabled(getContext(), isChecked);
            }
        });
    }
}
