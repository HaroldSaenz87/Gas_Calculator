package com.example.project_1;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    int checked = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // for the checkbox/radio buttons and the spinner and seekbar
        RadioGroup rg = findViewById(R.id.radio_Group);
        CheckBox AggressiveDriver = findViewById(R.id.checkBox);
        Spinner type_roads = findViewById(R.id.spinner);
        SeekBar seek = findViewById(R.id.seekBar);
        TextView averageSpeed = findViewById(R.id.Avg_speed);
        Button theButton = findViewById(R.id.button_calc);
        TextView result = findViewById(R.id.resultCost);


        // user input for cost of gallon, highway mpg and distance
        EditText user_distance = findViewById(R.id.Distance_editTextNumber);
        EditText CostGal = findViewById(R.id.Cost_edit);
        EditText Highway_mpg = findViewById(R.id.MPGeditTextNumber);




/*          // testing how to check the radio buttons
        // radio buttons
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(@NonNull RadioGroup group, int checkedId) {

                // which one of the radio buttons was selected
                if(checkedId == R.id.radioButtonYes)
                {
                    modifier = modifier + 15;
                }

                //RadioButton clickedButton = findViewById(checkedId);
                //checking if it works when selecting radio
                //Log.i("debug", "" + clickedButton.getText());
            }
        });

*/

        // seek bar
        seek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                // increment by 5 starting from 35 up to 70 which is 7 ticks in total
                int incrementedProg = 35 + (progress * 5);
                averageSpeed.setText(String.valueOf(incrementedProg));

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });




        // checkbox

        AggressiveDriver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(AggressiveDriver.isChecked())
                {
                    checked = 1;
                }
                else
                {
                    checked = 0;
                }
            }
        });



/*          i was testing a way to work the spinner

        // spinner
        type_roads.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();

                // not aggressive driver
                if(item.equals("City") && checked == 0)
                {
                    modifier = modifier + 15;
                }
                else if(item.equals("Mixed") && checked == 0)
                {
                    modifier = modifier + 10;
                }
                // aggressive driver
                else if(item.equals("Highway") && checked == 1)
                {
                    modifier = modifier + 15;
                }
                else if(item.equals("City") && checked == 1)
                {
                    modifier = modifier + 25;
                }
                else if(item.equals("Mixed") && checked == 1)
                {
                    modifier = modifier + 20;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }


        });

*/
        // NOTE TO SELF: modifier must be reset so every input doesnt stack up or act wierd.
        //              move everything into button and have it calc

        // button to calculate
        theButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // will be used to reset everytime we need to run new input
                int modifier = 0;

                // check if radiobutton yes is selected
                int selection = rg.getCheckedRadioButtonId();
                if(selection == R.id.radioButtonYes)
                {
                    modifier = modifier + 15;
                }

                // convert the text of the avg speed used in the seekbar into a number to be able to use it
                int incrementedProg = Integer.parseInt(averageSpeed.getText().toString());
                if(incrementedProg > 50)
                {
                    // only for every 5mph increase points by 5
                    int ByFive = (incrementedProg - 50) % 5;
                    int increasePoint = incrementedProg - 50;

                    // goes in conditional for every 5 mph
                    if(ByFive == 0)
                    {
                        modifier = modifier + increasePoint;
                    }
                }

                // convert the selected item in the spinner to a string to be able to check up
                // on what was selected and which will help adding to modifier
                String roads = type_roads.getSelectedItem().toString();
                if(checked == 0 && roads.equals("City"))
                {
                    modifier = modifier + 15;
                }
                else if(checked == 0 && roads.equals("Mixed"))
                {
                    modifier = modifier + 10;
                }
                else if(checked ==  1 && roads.equals("Highway"))
                {
                    modifier = modifier + 15;
                }
                else if(checked ==  1 && roads.equals("City"))
                {
                    modifier = modifier + 25;
                }
                else if(checked ==  1 && roads.equals("Mixed"))
                {
                    modifier = modifier + 20;
                }

                // convert the user input of distance, cost per gal, and the initial mpg
                // then later calculate final mpg * 2 for the round trip
                String distanceString = user_distance.getText().toString();
                int distance = Integer.parseInt(distanceString);

                String CostGallon = CostGal.getText().toString();
                float cost_of_gas = Float.parseFloat(CostGallon);

                String initial_MPG = Highway_mpg.getText().toString();
                int initial = Integer.parseInt(initial_MPG);

                float finalMPG =  initial * ((float) (100 - modifier) /100);
                float fuel = distance / finalMPG;
                float cost = fuel * cost_of_gas;

                float Total_Cost_Trip = cost * 2;

                // display the result after calculating
                result.setText(String.format("$%.2f", Total_Cost_Trip));

            }
        });
    }
}