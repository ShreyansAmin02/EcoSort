package com.example.ecosort;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.example.ecosort.Utils.CaptureActivityPortrait;
import com.example.ecosort.databinding.CameraScanBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

public class CameraScanActivity extends AppCompatActivity {

    @NonNull
    CameraScanBinding binding;


    private final ActivityResultLauncher<ScanOptions> barcodeLauncher = registerForActivityResult(new ScanContract(), result -> {
        if (result.getContents() == null) {
            Toast.makeText(this, "Invalid Barcode", Toast.LENGTH_SHORT).show();
        } else {
            MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(CameraScanActivity.this);
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding = CameraScanBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Block code for bottom navigation
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.bottom_home);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.bottom_home) {
                startActivity(new Intent(getApplicationContext(), BinListActivity.class));
                overridePendingTransition(R.anim.slide_right, R.anim.slider_left);
                finish();
                return true;
            } else if (item.getItemId() == R.id.bottom_scan) {
                return true;
            } else if (item.getItemId() == R.id.bottom_maps) {
                startActivity(new Intent(getApplicationContext(), NavigationActivity.class));
                overridePendingTransition(R.anim.slide_right, R.anim.slider_left);
                finish();
                return true;
            }
            return false;
        });

        binding.btnLeerCodigo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scan();
            }
        });

    }

    public void scan() {
        ScanOptions options = new ScanOptions();
        options.setDesiredBarcodeFormats(ScanOptions.ALL_CODE_TYPES);
        options.setPrompt("Scan code");
        options.setCameraId(0);
        options.setOrientationLocked(false);
        options.setBeepEnabled(false);
        options.setCaptureActivity(CaptureActivityPortrait.class);
        options.setBarcodeImageEnabled(false);

        barcodeLauncher.launch(options);
    }
}
