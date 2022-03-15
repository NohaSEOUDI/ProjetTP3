package fr.nohas.lescapteur;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import android.os.VibrationAttributes;
import android.os.Vibrator;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class SecouerActivity extends AppCompatActivity implements SensorEventListener {
    private SensorManager sm = null;
    private Sensor mAcc;
    private CameraManager cameraManager;
    private CameraCharacteristics cameraCharacteristics;
    CameraDevice cameraDevice;
    String cameraId;
    ImageView imageView;

    private static final float SHAKE_THRESHOLD = 3.25f;
    private static final int MIN_TIME_BETWEEN_SHAKES_MILLISECS = 1000;
    private long mLastShakeTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secouer);
        sm=(SensorManager) getSystemService(SENSOR_SERVICE);
        mAcc=sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        cameraManager = (CameraManager) getSystemService(CAMERA_SERVICE);
        imageView=(ImageView)findViewById(R.id.imageVTorch);

        //pour allumé la torsh
        try {
            cameraId = cameraManager.getCameraIdList()[0];
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }
    protected void onResume(){
        super.onResume();
        sm.registerListener((SensorEventListener)this,mAcc,SensorManager.SENSOR_DELAY_NORMAL);
    }
    protected void onPause(){
        super.onPause();
        sm.unregisterListener((SensorEventListener)this);
    }
    @Override
    public void onSensorChanged(SensorEvent event) {

        if(event.sensor.getType() == Sensor.TYPE_ACCELEROMETER){
            Long currTime =System.currentTimeMillis();
            if((currTime - mLastShakeTime) > MIN_TIME_BETWEEN_SHAKES_MILLISECS){

                float x = event.values[0];
                float y = event.values[1];
                float z = event.values[2];

                double acceleration = Math.sqrt(x*x + y*y + z*z) - SensorManager.GRAVITY_EARTH;

                if(acceleration > SHAKE_THRESHOLD){
                    mLastShakeTime = currTime;
                    try {
                            cameraManager.setTorchMode(cameraId,true);
                            imageView.setBackgroundResource(R.drawable.torch);
                            Toast.makeText(this, "Flashlight ON", Toast.LENGTH_SHORT).show();
                    }catch (CameraAccessException e){
                            e.printStackTrace();
                    }

                }if(acceleration < SHAKE_THRESHOLD){
                    try {
                        cameraManager.setTorchMode(cameraId,false);
                        imageView.setBackgroundResource(R.drawable.torch_non);
                        Toast.makeText(this, "Flashlight OFF", Toast.LENGTH_SHORT).show();
                    }catch (CameraAccessException e){
                        e.printStackTrace();
                    }
                }
            }
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

}
