package com.lynxhawk.staticshortcuts;

import android.os.Build;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
        import android.content.Intent;
        import android.graphics.Bitmap;
        import android.graphics.BitmapFactory;
        import android.net.Uri;
        import android.os.Bundle;
        import android.os.Environment;
        import android.provider.MediaStore;
        import android.util.Log;
        import android.view.View;
        import android.view.View.OnClickListener;
        import android.widget.Button;
        import android.widget.ImageView;
        import android.widget.Toast;

public class CameraActivity extends AppCompatActivity {


    public static final int TAKE_PHOTO = 1;

    private Button takePicBtn = null;
    private ImageView imageView = null;

    private Uri fileUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        takePicBtn = (Button) findViewById(R.id.buttonPicture);
        imageView = (ImageView) findViewById(R.id.imageView1);
        takePicBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //File outputImage = new File(getExternalCacheDir(), "output_image.jpg");
                File outputImage = new File("/storage/emulated/0/DCIM/Camera", "output_image.jpg");
                try {
                    if (outputImage.exists()) {
                        outputImage.delete();
                    }
                    outputImage.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (Build.VERSION.SDK_INT >= 24) {
                    fileUri = FileProvider.getUriForFile(CameraActivity.this, "com.lynxhawk.staticshortcuts.fileprovider", outputImage);
                } else {
                    fileUri = Uri.fromFile(outputImage);
                }
                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
                startActivityForResult(intent, TAKE_PHOTO);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case TAKE_PHOTO:
                if (resultCode == RESULT_OK) {
                    try {
                        Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(fileUri));
                        imageView.setImageBitmap(bitmap);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                break;
            default:
                break;
        }
    }
}



