package com.example.locatqr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class BuildingActivity extends AppCompatActivity
{
    String location = DirectionsActivity.location;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_building);

        final EditText input = findViewById(R.id.user_input);
        final TextView instructions = findViewById(R.id.instructions);
        MaterialButton home = findViewById(R.id.home_btn);

        String result, building_name;
        result = getIntent().getStringExtra("Result");
        Log.d("DEBUG!", result);
        building_name = (result.split(" ; "))[0];
        Log.d("DEBUG!",building_name+" "+location);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        if(building_name.equals(location)==false)
        {
            Toast.makeText(this, "This is not the right building", Toast.LENGTH_LONG).show();
            onBackPressed();
        }
        else
        {
            Toast.makeText(this, "Congratulations!", Toast.LENGTH_SHORT).show();
        }

        String rooms = (result.split(" ; "))[1];
        final String[] room =rooms.split(":");

        input.addTextChangedListener(new TextWatcher()
        {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {
                // not needed
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                Integer n,i;
                n = room.length;
                String userInput;
                userInput = input.getText().toString();

                for(i=0; i<n; i++)
                {
                    if(userInput.equals((room[i].split("->"))[0]))
                    {
                        instructions.setText((room[i].split("->"))[1]);
                        Log.d("DEBUG!",room[i]);
                        break;
                    }
                    else
                    {
                        instructions.setText(" ");
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s)
            {
                // not needed
            }
        });

    }
}
