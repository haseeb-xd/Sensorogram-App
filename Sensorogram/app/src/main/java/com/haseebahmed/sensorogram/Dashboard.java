package com.haseebahmed.sensorogram;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

public class Dashboard extends AppCompatActivity implements View.OnClickListener{

    private CardView stepsCard,magnetCard;
    private int RECOGNITION_PERMISSION_CODE=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_dashboard);
        stepsCard = (CardView) findViewById(R.id.cardViewSteps);
        magnetCard= (CardView) findViewById(R.id.cardViewMagnet);
        stepsCard.setOnClickListener(this);
        magnetCard.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {

        Intent i;
        if(view.getId()==R.id.cardViewSteps)
        {
            i= new Intent(this,StepCounter.class);
            startActivity(i);

        }

        else if (view.getId()==R.id.cardViewMagnet)
        {
            i= new Intent(this,MetalDetector.class);
            startActivity(i);

        }

    }
}