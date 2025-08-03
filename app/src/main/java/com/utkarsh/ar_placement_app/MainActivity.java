package com.utkarsh.ar_placement_app;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private Spinner drillSpinner;
    private Button selectButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drillSpinner = findViewById(R.id.drill_spinner);
        selectButton = findViewById(R.id.select_button);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.drill_options, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        drillSpinner.setAdapter(adapter);

        selectButton.setOnClickListener(v -> {
            String selectedDrill = drillSpinner.getSelectedItem().toString();
            Intent intent = new Intent(MainActivity.this, DrillDetailActivity.class);
            intent.putExtra("selected_drill", selectedDrill);
            startActivity(intent);
        });
    }
}