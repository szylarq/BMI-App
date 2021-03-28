package com.szylarq.bmi;

import android.content.Intent;
import android.os.Bundle;

import com.szylarq.bmi.model.BMIValue;
import com.szylarq.bmi.utils.BMIStatus;
import com.szylarq.bmi.utils.Utils;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import androidx.core.content.ContextCompat;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView bmiValueTextView;

    private boolean isMetricUnits = true;

    public boolean getIsMetricUnits() {
        return isMetricUnits;
    }

    public void setIsMetricUnits(boolean isMetricUnits) {
        this.isMetricUnits = isMetricUnits;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);

        bmiValueTextView = findViewById(R.id.bmiValueTextView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        TextView massTextView = findViewById(R.id.massTextView);
        TextView heightTextView = findViewById(R.id.heightTextView);

        //noinspection SimplifiableIfStatement
        if (id == R.id.metric_unit_item) {

            massTextView.setText(getString(R.string.mass_label_metric));
            heightTextView.setText(getString(R.string.height_label_metric));

            setIsMetricUnits(true);

            return true;
        }
        if (id == R.id.imperial_unit_item) {

            massTextView.setText(getString(R.string.mass_label_imperial));
            heightTextView.setText(getString(R.string.height_label_imperial));

            setIsMetricUnits(false);

            return true;
        }
        if (id == R.id.about_author){

            startActivity(new Intent(this, AuthorActivity.class));

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onCountBMIButtonClicked(View view) {

        String emptyInputError = getString(R.string.empty_value_error);
        String incorrectInputError = getString(R.string.incorrect_value_error);

        EditText massEditText = findViewById(R.id.massEditText);
        String massString = massEditText.getText().toString();

        EditText heightEditText = findViewById(R.id.heightEditText);
        String heightString = heightEditText.getText().toString();

        double mass = 0.0;
        double height = 0.0;

        boolean correctInputs = true;

        if(!massString.isEmpty()) {

            mass = Double.parseDouble(massString);

            if(!Utils.isIGreaterThanZero(mass)){

                massEditText.setError(incorrectInputError);
                correctInputs = false;
            }
        }
        else{

            massEditText.setError(emptyInputError);
            correctInputs = false;
        }

        if(!heightString.isEmpty()) {

            height = Double.parseDouble(heightString);

            if(!Utils.isIGreaterThanZero(height)){

                heightEditText.setError(incorrectInputError);
                correctInputs = false;
            }
        }
        else{

            heightEditText.setError(emptyInputError);
            correctInputs = false;
        }

        if(correctInputs) {

            BMIValue bmiValue = new BMIValue(mass, height);

            double calculatedBmiValue = Utils.roundDoubleValue(bmiValue.calculateBmi(getIsMetricUnits()));

            setBmiTextViewColor(BMIValue.getBmiStatus(calculatedBmiValue));

            bmiValueTextView.setText(String.valueOf(calculatedBmiValue));
        }
        else{
            bmiValueTextView.setText("");
        }
    }

    public void onBmiTextViewClicked(View view) {

        Intent bmiDetailsIntent = new Intent(this, BmiDetailsActivity.class);

        bmiDetailsIntent.putExtra("bmiValueString", bmiValueTextView.getText().toString());
        bmiDetailsIntent.putExtra("bmiValueTextViewColor", bmiValueTextView.getCurrentTextColor());

        startActivity(bmiDetailsIntent);
    }

    private void setBmiTextViewColor(BMIStatus bmiStatus){

        switch(bmiStatus){

            case UNDERWEIGHT:
                bmiValueTextView.setTextColor(ContextCompat.getColor(this, R.color.purple_200));
                break;

            case NORMAL:
                bmiValueTextView.setTextColor(ContextCompat.getColor(this, R.color.green));
                break;

            case OVERWEIGHT:
                bmiValueTextView.setTextColor(ContextCompat.getColor(this, R.color.orange));
                break;

            case OBESE:
                bmiValueTextView.setTextColor(ContextCompat.getColor(this, R.color.red));
                break;
        }
    }
}