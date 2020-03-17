package in.krharsh17.habita.home;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.gauravk.bubblenavigation.BubbleNavigationConstraintView;
import com.gauravk.bubblenavigation.listener.BubbleNavigationChangeListener;

import in.krharsh17.habita.R;
import in.krharsh17.habita.energy.LightMonitor;
import in.krharsh17.habita.energy.PlugInControlReceiver;

public class HomeActivity extends AppCompatActivity {

    BubbleNavigationConstraintView bottomNav;

    @Override
    public void onBackPressed() {
        if(viewPager.getCurrentItem()!=0){
            viewPager.setCurrentItem(0);
        }else {
            super.onBackPressed();
        }
    }
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_home);

        viewPager = findViewById(R.id.home_pager);

        viewPager.setAdapter(new HomePagerAdapter(getSupportFragmentManager()));

        activateSensors();

        bottomNav = findViewById(R.id.bottom_navigation_constraint);
        bottomNav.setNavigationChangeListener(new BubbleNavigationChangeListener() {
            @Override
            public void onNavigationChanged(View view, int position) {
                viewPager.setCurrentItem(position, true);
            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                bottomNav.setCurrentActiveItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }

    void activateSensors() {

        LightMonitor.start(this);

        IntentFilter ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);

        PlugInControlReceiver plugInControlReceiver = new PlugInControlReceiver();
        registerReceiver(plugInControlReceiver, ifilter);
        Log.i("TAG", "activateSensors: ");
    }

    class HomePagerAdapter extends FragmentPagerAdapter {
        HomeFragment homeFragment;
        ProfileFragment profileFragment;
        SettingsFragment settingsFragment;

        HomePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    if (homeFragment == null)
                        homeFragment = new HomeFragment();
                    return homeFragment;
                case 1:
                    if (profileFragment == null)
                        profileFragment = new ProfileFragment();
                    return profileFragment;
                case 2:
                    if (settingsFragment == null)
                        settingsFragment = new SettingsFragment();
                    return settingsFragment;
            }
            return null;
        }

        @Override
        public int getCount() {
            return 3;
        }
    }
}
