package com.furb.camera;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.content.ContentProvider;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CAMERA = 100;
    private static final int MY_PERMISSION_REQUEST_CAMERA = 101;
    private static final int MY_PERMISSION_READ_STORAGE = 1;
    private static final int MY_PERMISSION_WRITE_STORAGE = 2;

    Button btnCapture;
    ImageView ivShowImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCapture = findViewById(R.id.btnCapture);
        ivShowImage = findViewById(R.id.ivShowImage);

        btnCapture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPermissionCameraGranted()) {
                    captureImage();
                }
            }
        });
    }

    private boolean isPermissionCameraGranted() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            Log.v("TAG", "Camera permission is granted");
            return true;
        } else if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            Log.v("TAG", "Read storage permission is granted");
            return true;
        } else if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            Log.v("TAG", "Write storage Permission is granted");
            return true;
        } else {
            Log.v("TAG", "Permission is revoked");
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, MY_PERMISSION_REQUEST_CAMERA);
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSION_READ_STORAGE);
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, MY_PERMISSION_WRITE_STORAGE);
            return false;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSION_REQUEST_CAMERA: {
                if (grantResults.length > 0 && grantResults[0] ==   PackageManager.PERMISSION_GRANTED) {
                    captureImage();
                } else {
                    Log.d("TAG", "REQUEST_CAMERA :: " + "not granted");
                }
                break;
            }
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CAMERA && resultCode == RESULT_OK) {

            File out = new File(getFilesDir(), "newImage.jpg");

            if (!out.exists()) {
                Toast.makeText(getBaseContext(), "Error while capturing image", Toast.LENGTH_LONG).show();

                return;
            }

            Bitmap mBitmap = BitmapFactory.decodeFile(out.getAbsolutePath());
            MediaStore.Images.Media.insertImage(getContentResolver(), mBitmap, "yourTitle" , "yourDescription");

            ivShowImage.setImageBitmap(mBitmap);
        }
    }

    private void captureImage() {
        PackageManager pm = getPackageManager();

        if (pm.hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY)) {

            Intent i = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
            i.putExtra(MediaStore.EXTRA_OUTPUT, MyFileContentProvider.CONTENT_URI);

            startActivityForResult(i, REQUEST_CAMERA);
        } else {
            Toast.makeText(getBaseContext(), "Camera is not available", Toast.LENGTH_LONG).show();
        }
    }
}
