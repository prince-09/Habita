package in.krharsh17.habita.garden;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.ncorti.slidetoact.SlideToActView;

import java.io.File;
import java.io.FileOutputStream;

import in.krharsh17.habita.R;

public class GardenActivity extends AppCompatActivity {

    TextView addPlant;
    SlideToActView slideToActView;
    ImageView userPic ;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_garden);
        addPlant = findViewById(R.id.add_plant);
        userPic = findViewById(R.id.user_image);

        slideToActView = findViewById(R.id.scanplant);

        slideToActView.setOnSlideToActAnimationEventListener(new SlideToActView.OnSlideToActAnimationEventListener() {
            @Override
            public void onSlideCompleteAnimationStarted(SlideToActView slideToActView, float v) {
                scanPlantDisease();
            }

            @Override
            public void onSlideCompleteAnimationEnded(SlideToActView slideToActView) {
                slideToActView.resetSlider();
            }

            @Override
            public void onSlideResetAnimationStarted(SlideToActView slideToActView) {
            }

            @Override
            public void onSlideResetAnimationEnded(SlideToActView slideToActView) {
            }
        });

        addPlant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scanNewPlant();
            }
        });
        String root = "/storage/emulated/0/Android/data/in.krharsh17.habita/files/Habita/Images";
        String name="ad.png";
        File file = new File(root,name);
        Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
        Log.i("bitmap", String.valueOf(bitmap));
        userPic.setImageBitmap(bitmap);


    }

    private String saveImage(Bitmap finalBitmap, String image_name)
    {
        Log.i("savingimage","started");
        String root =  getApplicationContext().getExternalFilesDir(null) + "/Habita" + "/Images";
        File myDir = new File(root);
        myDir.mkdirs();
        String fname =image_name;
        File file = new File(myDir, fname);
        if (file.exists())
            file.delete();
        Log.i("LOAD", root + fname);
        try {
            FileOutputStream out = new FileOutputStream(file);
            finalBitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();
        } catch (Exception e) {
            Log.i("savingimage","undercatch");
            e.printStackTrace();
        }
        Log.i("savingimage","done");
        return root;
    }

    void scanPlantDisease() {
        Intent intent = new Intent(GardenActivity.this, DiseaseScannerActivity.class);
        startActivity(intent);
    }

    void scanNewPlant() {
        Intent intent = new Intent(GardenActivity.this, PlantScannerActivity.class);
        startActivity(intent);
    }

}
