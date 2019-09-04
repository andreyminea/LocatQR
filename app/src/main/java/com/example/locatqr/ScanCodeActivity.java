package com.example.locatqr;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class ScanCodeActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler
{
    public static String decision = "";
    ZXingScannerView ScannerView;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        ScannerView = new ZXingScannerView(this);
        setContentView(ScannerView);

    }

    @Override
    public void handleResult(Result result)
    {

        if(decision!="")
        {
            // Log.d("DEBUG",decision);
            Intent intent = new Intent(getApplicationContext(),BuildingActivity.class);
            intent.putExtra("Result",result.toString());
            startActivity(intent);
            finish();
        }
        else {
            MainActivity.result = result.getText();
            startActivity(new Intent(getApplicationContext(), MapShowActivity.class));
            finish();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        ScannerView.stopCamera();
    }

    @Override
    protected void onResume() {
        super.onResume();
        ScannerView.setResultHandler(this);
        ScannerView.startCamera();
    }
}