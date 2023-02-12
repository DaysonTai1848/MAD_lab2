package com.example.lab2;

import androidx.appcompat.app.AppCompatActivity;

import android.appwidget.AppWidgetProviderInfo;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class ThreadedActivity extends AppCompatActivity {

    ImageView imgVwPic;
    TextView tvGreet;
    Bitmap bp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_threaded);

        imgVwPic = findViewById(R.id.imgVwPic);
        Intent intent = getIntent();

        String strMsg = intent.getStringExtra("varStr1");
        tvGreet = findViewById(R.id.tvGreet);
        tvGreet.setText("Welcome to second activity " + strMsg);

    }

    public void fnTakePic(View view){
        Runnable run = new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 0);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tvGreet.setText(tvGreet.getText().toString() + ".. This is your picture heheh..");
                    }
                });
            }
        };

        Thread thread = new Thread(run);
        thread.start();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        bp = (Bitmap) data.getExtras().get("data");
        imgVwPic.setImageBitmap(bp);
    }

    public void fnFirstActivity(View view){
        Intent intent = new Intent(ThreadedActivity.this, FirstActivity.class);
        intent.putExtra("image", bp);
        startActivity(intent);
    }
}