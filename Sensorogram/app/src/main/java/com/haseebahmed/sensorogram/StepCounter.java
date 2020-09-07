package com.haseebahmed.sensorogram;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class StepCounter extends AppCompatActivity implements SensorEventListener {

    TextView tv;
    SensorManager sensorManager;
    boolean running= false;
    ProgressBar progress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_counter);
        tv= findViewById(R.id.textView2);
        progress= findViewById(R.id.stepsprogress);
        progress.setMax(200);

        sensorManager= (SensorManager) getSystemService(SENSOR_SERVICE);
    }

    @Override
    protected  void onResume() {

        super.onResume();
        running=true;
        Sensor countSensor= sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        if(countSensor!=null)
        {
            sensorManager.registerListener(this,countSensor,SensorManager.SENSOR_DELAY_FASTEST);
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

    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        if(running)
        {

            tv.setText(String.valueOf(sensorEvent.values[0]));
           progress.setProgress((int) sensorEvent.values[0]);

        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}







