package com.example.locatqr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class    MapShowActivity extends AppCompatActivity
{
    ImageView map;
    MaterialButton next;
    private String[] instr;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_show);

        map = findViewById(R.id.image_map);
        choosemap(MainActivity.result);
        next = findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getApplicationContext(), LocationActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("locations",instr[1]);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    private void choosemap(String result)
    {
        this.instr = result.split(" ; ");
        String zone = instr[0];
        switch(zone) {
            case "Camin A":
                map.setImageResource(R.drawable.camina);
                break;
            case "Camin C":
                map.setImageResource(R.drawable.caminc);
                break;
            case "Leu A":
                map.setImageResource(R.drawable.corpa);
                break;
            case "Leu B":
                map.setImageResource(R.drawable.corpb);
                break;
            case "Magazin":
                map.setImageResource(R.drawable.magazin);
                break;
            case "Car Entry":
                map.setImageResource(R.drawable.car);
                break;

            default:
                Toast.makeText(this, "This is not a valid QR code", Toast.LENGTH_LONG).show();
                onBackPressed();
        }
    }

}
