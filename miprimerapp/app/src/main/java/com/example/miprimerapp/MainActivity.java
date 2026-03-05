package com.example.miprimerapp;

import android.annotation.SuppressLint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.graphics.Color;

import androidx.appcompat.app.AppCompatActivity;

import android.widget.TextView;
public class MainActivity extends AppCompatActivity {

    TextView tempVal;
    SensorManager sensorManager;
    Sensor sensor;
    SensorEventListener sensorEventListener;

    @Override
    protected void onResume() {
        iniciar();
        super.onResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
          sensorProximidad();

    }
    @Override
    protected void onPause() {
        detener();
        super.onPause();
    }

    private void iniciar(){

        sensorManager.registerListener(sensorEventListener, sensor, 2000*1000);
    }



    private void detener(){
        sensorManager.unregisterListener(sensorEventListener);
    }
    @SuppressLint("ServiceCast")
    private void sensorProximidad(){
        tempVal = findViewById(R.id.lblSensorProximidad);
        sensorManager = (SensorManager)  getSystemService(SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        if(sensor==null){
            tempVal.setText("No dispones de sensor de proximidad");
            finish();

        }
        sensorEventListener = new SensorEventListener() {
            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }

            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                double valor = sensorEvent.values[0];
                tempVal.setText("Proximidad:  "+ valor);
                int color = Color.BLACK;
                if(valor<=4){
                   color = Color.BLACK;

                }
                getWindow().getDecorView().setBackgroundColor(Color.BLACK);
            }
        };
    }
}
