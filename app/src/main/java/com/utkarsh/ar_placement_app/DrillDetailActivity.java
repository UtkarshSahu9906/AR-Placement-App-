package com.utkarsh.ar_placement_app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class DrillDetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drill_detail);

        String drillName = getIntent().getStringExtra("selected_drill");
        Button startARButton = findViewById(R.id.start_ar_button);

        // Set drill details based on selection
        // Add your implementation here

        startARButton.setOnClickListener(v -> {
            Intent intent = new Intent(DrillDetailActivity.this, ARActivity.class);
            intent.putExtra("selected_drill", drillName);
            startActivity(intent);
        });
    }
}