package in.krharsh17.habita;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefManager {

    public static boolean getBatteryEnabled(Context context) {
        return context.getSharedPreferences("HABITA", 0).getBoolean("BATTERY", false);
    }

    public static void setBatteryEnabled(Context context, boolean isEnabled) {
        SharedPreferences.Editor editor = context.getSharedPreferences("HABITA", 0).edit();
        editor.putBoolean("BATTERY", isEnabled);
        editor.apply();
    }


    public static boolean getLightingEnabled(Context context) {
        return context.getSharedPreferences("HABITA", 0).getBoolean("LIGHT", false);
    }

    public static void setLightingEnabled(Context context, boolean isEnabled) {
        SharedPreferences.Editor editor = context.getSharedPreferences("HABITA", 0).edit();
        editor.putBoolean("LIGHT", isEnabled);
        editor.apply();
    }
}
