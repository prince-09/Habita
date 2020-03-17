package in.krharsh17.habita.food;

import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;

import java.util.ArrayList;
import java.util.List;

import in.krharsh17.habita.CameraPreview;
import in.krharsh17.habita.R;
import in.krharsh17.habita.RecyclerAdapter.RecyclerAdapterFoodScan;
import in.krharsh17.habita.api.IngredientToRecipeClient;
import in.krharsh17.habita.api.RecipeToIngredientsClient;
import in.krharsh17.habita.models.data;
import in.krharsh17.habita.models.dishesop;
import in.krharsh17.habita.models.images;
import in.krharsh17.habita.models.inputs;
import in.krharsh17.habita.models.model;
import in.krharsh17.habita.models.outputs;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FoodScan extends AppCompatActivity {
    ConstraintLayout foodscan;
    public final int CODE_IMG = 1;
    boolean safeToCapture = false;
    StorageTask uploadTask;
    RecyclerAdapterFoodScan recyclerAdapterFoodScan;
    List<dishesop> dishesops;
    RecyclerView recyclerView;
    String ingredients = "";
    StorageReference storageRef, storageReference;
    private Camera mCamera;
    Uri imageri;
    private CameraPreview mPreview;
    private static final int CAMERA_REQUEST = 1888;

    private static final int MY_CAMERA_PERMISSION_CODE = 100;

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

    private String getFileExtension(Uri uri) {
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(contentResolver.getType(uri));
    }
    TextView viewDetail ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foodscan);
        foodscan = findViewById(R.id.foodresult);
        dishesops = new ArrayList<>();
        mCamera = getCameraInstance();
        viewDetail = findViewById(R.id.viewallingredient);
        storageRef= FirebaseStorage.getInstance().getReference("posts");
        storageReference= FirebaseStorage.getInstance().getReference("posts");
        recyclerView=findViewById(R.id.resultrecycler);
        storageRef = FirebaseStorage.getInstance().getReference("posts");
        storageReference = FirebaseStorage.getInstance().getReference("posts");
        recyclerView = findViewById(R.id.resultrecycler);
        // Create our Preview view and set it as the content of our activity.
        mPreview = new CameraPreview(this, mCamera);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        FrameLayout preview = findViewById(R.id.foodsurfaceview);
        preview.addView(mPreview);
        mCamera.startPreview();
        mCamera.setPreviewCallback(new Camera.PreviewCallback() {
            @Override
            public void onPreviewFrame(byte[] data, Camera camera) {
                safeToCapture = true;
            }
        });

        viewDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog detail = new Dialog(FoodScan.this);
                detail.setContentView(R.layout.foundingredientpopup);

                detail.show();
            }
        });


        final Handler handler = new Handler();
        // handler.postDelayed(new Runnable() {
        //     @Override
        //     public void run() {
//
        //         //int height = foodscan.getLayoutParams().height;
        //         Display display = getWindowManager().getDefaultDisplay();
        //         Point size = new Point();
        //         display.getSize(size);
        //         int height = size.y;
        //         // Log.e("Width", "" + width);
        //         Log.e("height", "" + height);
        //         //hideFoodResult(height);
//
//
        //     }
        // }, 1000);
        final String API = "4e90ae05c1ac461d918998fda3fa238a";
        data data1 = new data("https://clarifai.com/cms-assets/20180320212159/food-003.jpg");
        images images = new images(data1);
        inputs inputs = new inputs(images);
        List<in.krharsh17.habita.models.inputs> inputsList = new ArrayList<>();
        inputsList.add(inputs);

        Log.i("hey", "sucess");

        final model model = new model(inputsList);
        Call<outputs> call = RecipeToIngredientsClient.getInstance().getApi().verifyOtp("Key " + API, model);
        call.enqueue(new Callback<outputs>() {
            @Override
            public void onResponse(Call<outputs> call, Response<outputs> response) {
                Log.i("res", response.isSuccessful() + " " + response.raw() + " ");
                if (response.isSuccessful() && response.body() != null) {
                    for (int i = 0; i < response.body().getResultsList().get(0).getConcepts().getItems().size(); i++) {
                        Log.i("response", response.body().getResultsList().get(0).getConcepts().getItems().get(i).getName());
                        ingredients += response.body().getResultsList().get(0).getConcepts().getItems().get(i).getName();
                        if (i != response.body().getResultsList().get(0).getConcepts().getItems().size() - 1) {
                            ingredients += ",+";
                        }
                    }
                    populate();
                }
            }

            @Override
            public void onFailure(Call<outputs> call, Throwable t) {
                Log.i("error", t.toString());
            }
        });
        //ingredients to recipe

        //startActivityForResult(new Intent()
        //        .setAction(Intent.ACTION_GET_CONTENT)
        //        .setType("image/*"),CODE_IMG);
    }

    private void populate() {
        String api = "407eb22d13af48da89dbe77f0d2932d6";
        Call<List<dishesop>> call1 = IngredientToRecipeClient.getInstance().getApi().dishes(ingredients, api);
        call1.enqueue(new Callback<List<dishesop>>() {
            @Override
            public void onResponse(Call<List<dishesop>> call, Response<List<dishesop>> response) {
                Log.i("hey", response.isSuccessful() + " " + response.raw());
                if (response.isSuccessful() && response.body() != null) {
                    dishesops = response.body();
                    recyclerAdapterFoodScan = new RecyclerAdapterFoodScan(dishesops, getApplicationContext());
                    recyclerView.setAdapter(recyclerAdapterFoodScan);

                    for (int i = 0; i < response.body().size(); i++)
                        Log.i("hey", response.body().get(i).getTitle());
                }
            }

            @Override
            public void onFailure(Call<List<dishesop>> call, Throwable t) {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.i("TAG", "onActivityResult: ");
        if (requestCode == CODE_IMG && resultCode == RESULT_OK) {
            Log.i("TAG", "onActivityResult: ");
            Log.i("hey", data.getData() + " ");
            imageri = data.getData();
            storageRef.child("users/me/profile.png").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {

                    // Uri downloadUri = taskSnapshot.getMetadata().getDownloadUrl();
                    // String generatedFilePath = downloadUri.toString(); /// The string(file link) that you need
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
// Handle any errors
                }
            });
        }
    }
    //public void startMatching(){
    //    Runnable r = new Runnable() {
    //        public void run() {
    //            while (true) {
    //                runOnUiThread(new Runnable() {
    //                    @Override
    //                    public void run() {
    //                        captureAndMatch();
    //                    }
    //                });
    //                try {
    //                    Thread.sleep(80);
    //                } catch (InterruptedException e) {
    //                    e.printStackTrace();
    //                }
    //            }
    //        }
    //    };
    //    new Thread(r).start();
    //}
    //public void hideFoodResult(int height) {
    //    Log.i("height", String.valueOf(height));
    //    foodscan.animate().translationYBy(height).setDuration(20);
    //}

    //public void showFoodResult() {
    //    foodscan.animate().translationY(0).setDuration(300);
    //}


    //public void captureAndMatch() {
    //    if (safeToCapture){
    //        mCamera.takePicture(null, null, new Camera.PictureCallback() {
    //            @Override
    //            public void onPictureTaken(byte[] bytes, Camera camera) {
    //                Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    //                Matrix matrix = new Matrix();
    //                matrix.postRotate(90);
    //                showFoodResult();
    //                Bitmap bmprotated = Bitmap.createBitmap(bmp, 0, 0, bmp.getWidth(), bmp.getHeight(), matrix, true);
    //                String root = Environment.getExternalStorageDirectory().toString();
    //                File myDir = new File(root);
    //                myDir.mkdirs();
    //                String fname = "Image-test" + ".jpg";
    //                File file = new File(myDir, fname);
    //                if (file.exists()) file.delete();
    //                Log.i("LOAD", "" + bmp.getByteCount());
    //                try {
    //                    FileOutputStream out = new FileOutputStream(file);
    //                    bmprotated.compress(Bitmap.CompressFormat.JPEG, 90, out);
    //                    out.flush();
    //                    out.close();
    //                } catch (Exception e) {
    //                    e.printStackTrace();
    //                }

    //            }
    //        });
    //}
    //}
}
