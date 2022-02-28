package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.TouchDelegate;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    //Create variables for items in our view
    TextView conversionTextView;
    TextView resultTextView;
    EditText measurementEditText;
    Button startOverButton;
    Button convertButton;

    Float measurement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        //Get the items from our view using their id
        conversionTextView = findViewById(R.id.conversionTextView);
        measurementEditText =  findViewById(R.id.measurementEditText);
        convertButton = findViewById(R.id.convertButton);
        resultTextView = findViewById(R.id.resultTextView);
        startOverButton = findViewById(R.id.startOverButton);

        //Receive the values sent from the first page
        Intent intent = getIntent();
        String conversionString = intent.getStringExtra("CONVERSIONSTRING");
        int selectedId = intent.getIntExtra("CONVERSOINID", 0);

        //Update the textView in the second page with the value from page one
        conversionTextView.setText("Converting measurements from " + conversionString);

        //call the onClick Method for our convert button
        addListenerOnConvertButton(selectedId);

        //call the onClick Method for our retrun back button
        addListenerOnReturnButton();
    }

    //onClickListener for our convert button
    public void addListenerOnConvertButton(int selectedId){

        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Get value from users
                String measurementString = measurementEditText.getText().toString();

                //Notify users to input a value if empty or convert the value
                if (measurementString.isEmpty()){
                    Toast.makeText(SecondActivity.this, "Please input a value to convert", Toast.LENGTH_LONG).show();
                }else{
                    measurement = Float.parseFloat(measurementString);


                    switch(selectedId){
                        case 0:
                            Float answer = measurement*2.20462f;
                            resultTextView.setText("Output: " + measurement*2.20462f + " Pounds (lb)");
                            break;
                        case 1:
                            resultTextView.setText("Output: " + measurement/2.20462f + " Kilogram (lb)");
                            break;
                        case 2:
                            resultTextView.setText("Output: " + measurement/1.60934f + " Miles (Ml)");
                            break;
                        case 3:
                            resultTextView.setText("Output: " + measurement*1.60934f + " Kilometers (Km)");
                            break;
                    }

                    resultTextView.setVisibility(View.VISIBLE);

                    //Hide the soft Keyboard if it is visible
                    hideKeyboard(view);
                }


            }
        });

    }

    //onClickListener for our return button
    public void addListenerOnReturnButton(){

        startOverButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SecondActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public void hideKeyboard(View view){

            InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            if (inputManager.isActive()) {
                inputManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
    }

}