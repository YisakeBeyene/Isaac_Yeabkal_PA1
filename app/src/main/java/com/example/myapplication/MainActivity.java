package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //Create variables for items in our view
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private Button launchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //call the onClick Method for our button
        addListenerOnButton();
    }

    //onClickListener for our  button
    public void addListenerOnButton(){

        //Get the items from our view using their id
        launchButton = findViewById(R.id.launchButton);
        radioGroup = findViewById(R.id.conversionRadioGroup);

        launchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Get which radio button was selected
                int selectedId = radioGroup.getCheckedRadioButtonId();
                radioButton = (RadioButton) findViewById(selectedId);

                //Send the value of the radio button clicked to the second page
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("CONVERSIONSTRING", radioButton.getText());
                intent.putExtra("CONVERSOINID", (Integer) radioGroup.indexOfChild(radioButton));
                startActivity(intent);

            }
        });
    }
}