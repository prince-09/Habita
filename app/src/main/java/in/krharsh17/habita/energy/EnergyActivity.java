package in.krharsh17.habita.energy;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;

import in.krharsh17.habita.R;

public class EnergyActivity extends AppCompatActivity {

    TextView batteryStatus, lightStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_energy);

        lightStatus = findViewById(R.id.battery_status);
        batteryStatus = findViewById(R.id.light_status);

        ImageView userPic = findViewById(R.id.user_image);

        String root = "/storage/emulated/0/Android/data/in.krharsh17.habita/files/Habita/Images";
        String name = "ad.png";
        File file = new File(root, name);
        Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
        Log.i("bitmap", String.valueOf(bitmap));
        userPic.setImageBitmap(bitmap);

        LightMonitor.setTarget(batteryStatus);

        LightMonitor.start(this);

    }


}

