package com.example.acelerometro;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    TextView txtEixoX, txtEixoY, txtEixoZ, txtPosicao;
    SensorManager sensorManager;
    Sensor sensor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //iniciando os componentes

        txtEixoX = (TextView) findViewById(R.id.txtEixoX);
        txtEixoY = (TextView) findViewById(R.id.txtEixoY);
        txtEixoZ = (TextView) findViewById(R.id.txtEixoZ);
        txtPosicao = (TextView) findViewById(R.id.txtPosicao);

        //instaciando as class

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        //definir o tipo de acelerometro

        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);


    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        //pegando os valores
        Float x = event.values[0];
        Float y = event.values[1];
        Float z = event.values[2];


        //plotando os valores de x,y,z
        txtEixoX.setText("Valor eixo x: " + x.intValue());
        txtEixoY.setText("Valor eixo y: " + y.intValue());
        txtEixoZ.setText("Valor eixo z: " + z.intValue());

        if (y > 0) {
            if (x > 0) txtPosicao.setText("Esquerda \n Invertida");
            if (x < 0) txtPosicao.setText("Direita \n Invertida");

        } else {
            if (x > 0) txtPosicao.setText("Esquerda");
            if (x < 0) txtPosicao.setText("Direita");
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
