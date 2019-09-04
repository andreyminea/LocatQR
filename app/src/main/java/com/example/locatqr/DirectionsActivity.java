package com.example.locatqr;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;

public class DirectionsActivity extends AppCompatActivity
{
    public static String location;
    LinearLayout cb;
    ArrayList<CheckBox> checkBoxList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_directions);

        Bundle bundle = getIntent().getExtras();
        String aux;
        String s = bundle.getString("directions");
        String[] strings = s.split("->");
        location = strings[0];
        String[] directions = strings[1].split("&");
        Integer n,i;

        cb = findViewById(R.id.directions);
        LayoutInflater inflater = LayoutInflater.from(this);

        n=directions.length;
        for (i=0; i<n; i++)
        {
            aux=directions[i];
            View view = inflater.inflate(R.layout.checkbox, cb, false);
            CheckBox checkBox = view.findViewById(R.id.checkbox);

            checkBox.setText(aux);
            checkBox.setChecked(false);

            checkBoxList.add(checkBox);

            cb.addView(view);

        }
    }


    @Override
    protected void onResume()
    {
        super.onResume();

        MaterialButton done = findViewById(R.id.done_btn);

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Integer i;
                Integer total = checkBoxList.size();
                for(i=0; i<total; i++)
                {
                    if(checkBoxList.get(i).isChecked()==false)
                    {
                        break;
                    }
                    if((checkBoxList.get(i).isChecked())&&(i==total-1))
                    {
                        ScanCodeActivity.decision = "fasfas" ;
                        Intent intent = new Intent(getApplicationContext(),ScanCodeActivity.class);
                        startActivity(intent);
                        break;
                    }
                }


            }
        });



    }
}