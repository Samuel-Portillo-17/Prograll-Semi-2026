package com.example.miprimerapp;

import android.annotation.SuppressLint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.graphics.Color;
import android.hardware.Sensor;
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
          sensorluz();

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
    private void sensorluz(){
        tempVal = findViewById(R.id.lblSensorLuz);
        sensorManager = (SensorManager)  getSystemService(SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        if(sensor==null){
            tempVal.setText("No dispones de sensor de luz");
            finish();

        }
        sensorEventListener = new SensorEventListener() {
            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }

            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                double valor = sensorEvent.values[0];
                tempVal.setText("luz:  "+ valor);
                int color = Color.BLACK;
                if(valor<=20){
                   color = Color.GRAY;
                }
                if(valor<=75){
                    color = Color.YELLOW;
                }
                if(valor<=150){
                    color = Color.BLUE;
                }
                getWindow().getDecorView().setBackgroundColor(Color.BLACK);
            }
        };
    }
}
