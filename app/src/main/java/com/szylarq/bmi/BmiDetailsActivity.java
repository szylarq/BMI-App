package com.szylarq.bmi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

public class BmiDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi_details);

        Toolbar toolbar = findViewById(R.id.bmiDetailsToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TextView bmiValueTextView = findViewById(R.id.bmiValueDetailsTextView);

        bmiValueTextView.setText(getIntent().getStringExtra("bmiValueString"));

        bmiValueTextView.setTextColor(getIntent().getIntExtra("bmiValueTextViewColor",
                ContextCompat.getColor(this, android.R.color.primary_text_light)));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        this.finish();
        return true;
    }
}