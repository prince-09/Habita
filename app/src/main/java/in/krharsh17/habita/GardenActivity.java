package in.krharsh17.habita;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class GardenActivity extends AppCompatActivity {

    View scanplant;
    TextView addPlant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_garden);
        scanplant = findViewById(R.id.scanplant);
        addPlant = findViewById(R.id.add_plant);

        scanplant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scanPlantDisease();
            }
        });

        addPlant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scanNewPlant();
            }
        });
    }

    void scanPlantDisease(){
        Intent intent = new Intent(GardenActivity.this,GardenDiseaseScannerActivity.class);
        startActivity(intent);
    }
    void scanNewPlant(){
        Intent intent = new Intent(GardenActivity.this,GardenPlantScannerActivity.class);
        startActivity(intent);
    }

}
