package in.krharsh17.habita.garden;

import android.hardware.Camera;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;

import in.krharsh17.habita.CameraPreview;
import in.krharsh17.habita.R;

public class PlantScannerActivity extends AppCompatActivity {

    boolean safeToCapture = false;
    private Camera mCamera;
    private CameraPreview mPreview;

    public static Camera getCameraInstance() {
        Camera c = null;
        try {
            c = Camera.open(); // attempt to get a Camera instance
        } catch (Exception e) {
            Log.i("cameraloading", "not opened");
            // Camera is not available (in use or does not exist)
        }
        return c; // returns null if camera is unavailable
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_plant_scanner);
        mCamera = getCameraInstance();
        // Create our Preview view and set it as the content of our activity.
        mPreview = new CameraPreview(this, mCamera);
        FrameLayout preview = findViewById(R.id.plantScannerPreview);
        preview.addView(mPreview);
        mCamera.startPreview();
        mCamera.setPreviewCallback(new Camera.PreviewCallback() {
            @Override
            public void onPreviewFrame(byte[] data, Camera camera) {
                safeToCapture = true;
            }
        });
    }
}