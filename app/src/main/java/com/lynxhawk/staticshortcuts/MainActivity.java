package com.lynxhawk.staticshortcuts;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button notebook;
    Button camera;
    Button baidu;
    Button map;
    Button exchange;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        notebook = (Button) findViewById(R.id.notebook);
        camera = (Button) findViewById(R.id.camera);
        baidu = (Button)findViewById(R.id.baidu);
        map = (Button)findViewById(R.id.map);
        exchange = (Button)findViewById(R.id.exchange);

        notebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NoteActivity.class);
                startActivity(intent);
                //    MainActivity.this.finish();
            }
        });

        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MapActivity.class);
                startActivity(intent);
                //    MainActivity.this.finish();
            }
        });

        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CameraActivity.class);
                startActivity(intent);
                //    MainActivity.this.finish();
            }
        });

        exchange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ExchangeActivity.class);
                startActivity(intent);
                //    MainActivity.this.finish();
            }
        });

        baidu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Uri uri = Uri.parse("https://www.baidu.com");
//                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
//                startActivity(intent);
                Intent intent = new Intent(MainActivity.this, WebActivity.class);
                startActivity(intent);
                //    MainActivity.this.finish();
            }
        });

    }
}
