package in.krharsh17.habita.energy;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;
import android.widget.TextView;

import in.krharsh17.habita.SharedPrefManager;

public class LightMonitor {
    private static TextView target;
    private static boolean isStarted = false;

    public static void start(final Context context) {
        if (isStarted)
            return;
        Log.i("TAG", "start: ");
        SensorManager sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        Sensor lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        if (lightSensor != null) {

            SensorEventListener lightSensorEventListener = new SensorEventListener() {

                @Override
                public void onAccuracyChanged(Sensor sensor, int accuracy) {
                    // TODO Auto-generated method stub

                }

                // get sensor update and reading
                @Override
                public void onSensorChanged(SensorEvent event) {
                    Log.i("TAG", "onSensorChanged: ");
                    if (SharedPrefManager.getLightingEnabled(context))
                        // TODO Auto-generated method stub
                        Log.i("TAG", "onSensorChanged: 2");
                    if (event.sensor.getType() == Sensor.TYPE_LIGHT && target != null) {
                        float currentReading = event.values[0];
                        if (currentReading <= 5) {
                            target.setText("Good to sleep");
                        } else if (currentReading <= 150) {
                            target.setText("Good for normal activity");
                        } else if (currentReading <= 300) {
                            target.setText("Good for reading");
                        } else {
                            float remove = (currentReading - 300) / 1200;
                            target.setText("Wasting eletricity! Remove " + Math.round(Math.ceil(remove)) + " bulbs");
                        }
                    }
                }

            };

            Log.i("TAG", "start: ");
            sensorManager.registerListener(lightSensorEventListener,
                    lightSensor, SensorManager.SENSOR_DELAY_NORMAL);

        } else {
            SharedPrefManager.setLightingEnabled(context, false);
        }
        isStarted = true;
    }

    public static void setTarget(TextView t) {
        Log.i("TAG", "setTarget: ");
        target = t;
    }
}
