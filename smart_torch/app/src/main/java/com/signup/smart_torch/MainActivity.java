package com.signup.smart_torch;

import android.annotation.SuppressLint;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private TextView textView;
    private String cameraID;
    private CameraManager cameraManager;
    private boolean isTorchOn = false;
    private boolean hasFlash = false;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.text);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);

        try {
            cameraID = cameraManager.getCameraIdList()[0];
            CameraCharacteristics characteristics = cameraManager.getCameraCharacteristics(cameraID);
            Integer level = characteristics.get(CameraCharacteristics.INFO_SUPPORTED_HARDWARE_LEVEL);
            hasFlash = level == CameraCharacteristics.INFO_SUPPORTED_HARDWARE_LEVEL_FULL;
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }

        Sensor lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        if (lightSensor == null) {
            Toast.makeText(this, "Sorry no light sensor in your phone ! ", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_LIGHT) {
            float lightValue = event.values[0];
            textView.setText(String.valueOf(lightValue));

            if (hasFlash) {
                if (lightValue < 50 && !isTorchOn) {
                    try {
                        cameraManager.setTorchMode(cameraID, true);
                        isTorchOn = true;
                    } catch (CameraAccessException e) {
                        e.printStackTrace();
                    }
                } else if (lightValue >= 50 && isTorchOn) {
                    try {
                        cameraManager.setTorchMode(cameraID, false);
                        isTorchOn = false;
                    } catch (CameraAccessException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                // Handle devices without flash
                Toast.makeText(this, "Device doesn't have a flash", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Handle accuracy changes if needed
    }

    @Override
    protected void onResume() {
        super.onResume();
        Sensor lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        if (lightSensor != null) {
            sensorManager.registerListener(this, lightSensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }
}
