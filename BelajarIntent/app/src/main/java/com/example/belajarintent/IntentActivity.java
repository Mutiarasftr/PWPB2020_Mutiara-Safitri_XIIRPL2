package com.example.belajarintent;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class IntentActivity extends AppCompatActivity implements View.OnClickListener{
    Button intent3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent);

        intent3 = (Button)findViewById(R.id.intent3);
        intent3.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent explicit = new Intent(IntentActivity.this, Intent2Activity.class);
        startActivity(explicit);

    }
}
