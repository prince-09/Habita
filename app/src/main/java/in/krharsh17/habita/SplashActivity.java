package in.krharsh17.habita;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.ncorti.slidetoact.SlideToActView;

import in.krharsh17.habita.home.HomeActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        createNotificationChannel();

        SlideToActView slideToActView = findViewById(R.id.proceed);

        slideToActView.setOnSlideToActAnimationEventListener(new SlideToActView.OnSlideToActAnimationEventListener() {
            @Override
            public void onSlideCompleteAnimationStarted(SlideToActView slideToActView, float v) {
            }

            @Override
            public void onSlideCompleteAnimationEnded(SlideToActView slideToActView) {
                startActivity(new Intent(SplashActivity.this, HomeActivity.class));
                SplashActivity.this.finish();
            }

            @Override
            public void onSlideResetAnimationStarted(SlideToActView slideToActView) {

            }

            @Override
            public void onSlideResetAnimationEnded(SlideToActView slideToActView) {

            }
        });


    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Habita";
            String description = "Habita";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel("HABITA", name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }


}
