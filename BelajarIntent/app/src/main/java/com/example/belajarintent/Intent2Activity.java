package com.example.belajarintent;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Intent2Activity extends AppCompatActivity implements View.OnClickListener{
    Button intent2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent2);

        intent2 = (Button)findViewById(R.id.intent2);
        intent2.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent explicit = new Intent(Intent2Activity.this, IntentActivity.class);
        startActivity(explicit);
    }
}
