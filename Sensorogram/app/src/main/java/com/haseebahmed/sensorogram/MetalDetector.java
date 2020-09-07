package com.haseebahmed.sensorogram;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;


public class MetalDetector extends AppCompatActivity implements SensorEventListener {

    private TextView value;
    private SensorManager sensorManager;
    public static DecimalFormat DECIMAL_FORMATTER;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metal_detector);
        value= (TextView) findViewById(R.id.textView1);
        progressBar= findViewById(R.id.progressBar3);
        progressBar.setMax(200);
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
        symbols.setDecimalSeparator('.');
        DECIMAL_FORMATTER= new DecimalFormat("#.000",symbols);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);






    }

    @Override
    protected void onResume()
    {
        super.onResume();
        Sensor metalSensor= sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);

        if(metalSensor!=null) {
            sensorManager.registerListener(this, metalSensor, SensorManager.SENSOR_DELAY_FASTEST);
        }
        else
        {
            Toast.makeText(this,"Sensor not found",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        sensorManager.unregisterListener(this);

    }




    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        if(sensorEvent.sensor.getType()==Sensor.TYPE_MAGNETIC_FIELD){
            float magX= sensorEvent.values[0];
            float magY= sensorEvent.values[1];
            float magZ= sensorEvent.values[2];
            double magnitude= Math.sqrt((magX*magX) + (magY*magY) + (magZ*magZ));

            value.setText(DECIMAL_FORMATTER.format(magnitude) + " \u00B5 Tesla");
            progressBar.setProgress( (int) magnitude);




        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}