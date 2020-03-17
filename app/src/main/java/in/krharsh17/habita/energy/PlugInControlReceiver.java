package in.krharsh17.habita.energy;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import in.krharsh17.habita.R;
import in.krharsh17.habita.SharedPrefManager;

public class PlugInControlReceiver extends BroadcastReceiver {

    float batteryPct = 0;

    public void onReceive(Context context, Intent intent) {
        if (SharedPrefManager.getBatteryEnabled(context)) {
            IntentFilter ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
            Intent batteryStatus = context.registerReceiver(null, ifilter);
            int level = batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
            int scale = batteryStatus.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
            batteryPct = level * 100 / (float) scale;

            NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "HABITA")
                    .setSmallIcon(R.drawable.ic_launcher_foreground)
                    .setContentTitle("Smart Charging Recommendation")
                    .setContentText("")
                    .setPriority(NotificationCompat.PRIORITY_HIGH)
                    .setAutoCancel(true);

            Log.i("BAT", "onReceive: ");

            if (batteryPct > 20) {
                builder.setContentTitle("Please remove the charger")
                        .setStyle(new NotificationCompat.BigTextStyle()
                                .bigText("It is not advisable to charge your phone when it is more than 20% charged"));

                NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);

                notificationManager.notify(999, builder.build());
            }


        }
    }
}