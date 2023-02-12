package com.example.lab2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Calendar;

public class FirstActivity extends AppCompatActivity {

    TextView txtvwAge;
    EditText edtName, edtYear;
    Button btnClick;
    Bitmap bp = null;
    ImageView imgVwProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        txtvwAge = (TextView) findViewById(R.id.txtvwAge);
        edtName = (EditText) findViewById(R.id.edtTxtName);
        edtYear = (EditText) findViewById(R.id.edtYear);
        imgVwProfile = (ImageView) findViewById(R.id.imgVwProfile);

        if (getIntent().hasExtra("image")) {
            bp = (Bitmap) getIntent().getParcelableExtra("image");
        } else {
            // set default image or handle the situation
        }
        imgVwProfile.setImageBitmap(bp);
    }

    public void fnGreet(View view){
        String strName = edtName.getText().toString();
        int yearOfBirth = Integer.parseInt(edtYear.getText().toString());
        Calendar calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR);
        String strYear = String.valueOf(currentYear - yearOfBirth);

        txtvwAge.setText("Helloo and welcome " + strName + ". You are " + strYear + " years old.");
    }

    public void fnThreadedActivity(View view){
        Intent intent = new Intent(this, ThreadedActivity.class);
        String strMsg = ((EditText) findViewById(R.id.edtTxtName)).getText().toString();
        intent.putExtra("varStr1", strMsg);
        startActivity(intent);
    }
}