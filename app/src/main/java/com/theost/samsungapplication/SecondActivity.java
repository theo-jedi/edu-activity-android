package com.theost.samsungapplication;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        // Получаем строку, переданную в Intent
        String intentText = getIntent().getStringExtra("key");

        // Устанавливаем данные
        ((TextView) findViewById(R.id.nameTextView)).setText(intentText);
    }

}
