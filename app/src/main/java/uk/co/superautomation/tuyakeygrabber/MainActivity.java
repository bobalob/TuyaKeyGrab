package uk.co.superautomation.tuyakeygrabber;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private static final String E_FAMILY_CLOUD_FILE = "/Android/data/com.efamily.cloud/cache/1.abj";

    // Storage Permissions
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    /**
     * Checks if the app has permission to write to device storage
     *
     * If the app does not has permission then the user will be prompted to grant permissions
     *
     * @param activity
     */
    public static void verifyStoragePermissions(Activity activity) {
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        verifyStoragePermissions(this);

        showResults();
    }

    private void showResults() {
        File storage = Environment.getExternalStorageDirectory();

        File family = new File(storage,E_FAMILY_CLOUD_FILE);
        StringBuilder text = new StringBuilder();
        Log.d("TuyaKeyGrab", "file path:" + family.getAbsolutePath());
        Log.d("TuyaKeyGrab", "file exists:" + family.exists());

        try {
            BufferedReader br = new BufferedReader(new FileReader(family));

            String line;

            while ((line = br.readLine()) != null) {
                if (line.contains("localKey")) {
                    String[] parts = line.split("localKey");
                    String key = parts[1].split("\"")[2];

                    text.append(key);
                    text.append('\n');
                    break;
                } else {

                }
            }
            br.close();
        }
        catch (IOException e) {
            Log.d("TuyaKeyGrab", "file open error");
            e.printStackTrace();
        }

        //Find the view by its id
        TextView tv = findViewById(R.id.textView);

        //Set the text
        tv.setText(text.toString());
    }

    @Override
    public void onRequestPermissionsResult(
            int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_EXTERNAL_STORAGE:
                showResults();
        }

    }

}
