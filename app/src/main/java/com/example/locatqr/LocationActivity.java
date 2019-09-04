package com.example.locatqr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import java.util.ArrayList;

public class LocationActivity extends AppCompatActivity
{
    TextView test;
    LinearLayout cb;
    ArrayList<CheckBox> checkBoxList = new ArrayList<>();
    MaterialButton guide;
    String[] locations;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);


        String temp;
        String[] aux;
        Integer n,i;

        Bundle b = getIntent().getExtras();
        temp = b.getString("locations");
        locations = temp.split(":");
        n = locations.length;

        cb = findViewById(R.id.locations);
        LayoutInflater inflater = LayoutInflater.from(this);
        for(i=0; i<n; i++)
        {
            aux = locations[i].split("->");

            View view = inflater.inflate(R.layout.checkbox, cb, false);
            CheckBox checkBox = view.findViewById(R.id.checkbox);

            checkBox.setText(aux[0]);
            checkBox.setChecked(false);

            checkBoxList.add(checkBox);

            cb.addView(view);

        }
    }


    @Override
    protected void onResume()
    {
        super.onResume();
        Integer i;
        Integer total = checkBoxList.size();
        for (i=0; i<total; i++)
        {
            checkBoxList.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    Integer j;
                    Integer t = checkBoxList.size();
                    for(j=0; j<t; j++)
                    {
                        if(checkBoxList.get(j)!=v)
                        {
                            checkBoxList.get(j).setChecked(false);
                        }
                    }

                }
            });
        }
        guide = findViewById(R.id.next_btn);
        guide.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Integer i;
                String string = new String();
                Integer total = checkBoxList.size();
                Integer theone=-1;
                for (i=0; i<total; i++)
                {
                    if(checkBoxList.get(i).isChecked())
                    {
                        theone = i;
                    }
                }

                for (i=0; i<locations.length; i++)
                {
                    if(theone==i)
                    {
                        string = locations[i];
                    }
                }

                if(theone!=-1)
                {
                    // daca nu exista casuta bifata nu se va trece la activitatea urmatoare
                    Bundle bundle = new Bundle();
                    bundle.putString("directions",string);
                    Intent intent = new Intent(getApplicationContext(), DirectionsActivity.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            }
        });




    }
}