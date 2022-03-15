package fr.nohas.lescapteur;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

public class ProximiteActivity extends AppCompatActivity {
    private static final float SENSOR_SENSITIVITY = 4;
    private SensorManager sm;
    private Sensor proximitySensor;
    private ImageView myImage;

    private SensorEventListener sensorEventListener= new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            //pk je trouve une valeur à coté de chaque type genre pour TYPE_PROXIMITY il y avait num 8
            if(event.sensor.getType() == Sensor.TYPE_PROXIMITY){
                if(event.values[0] >= -SENSOR_SENSITIVITY  && event.values[0] <= SENSOR_SENSITIVITY){
                    //near
                    myImage.setBackgroundResource(R.drawable.proche);
                }else{
                    myImage.setBackgroundResource(R.drawable.loin);
                }
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proximite);
        myImage = (ImageView) findViewById(R.id.imageView1);
        sm=(SensorManager)getSystemService(Context.SENSOR_SERVICE);
        proximitySensor = sm.getDefaultSensor(Sensor.TYPE_PROXIMITY);
    }
    @Override
    protected void onResume(){
        super.onResume();
        sm.registerListener(sensorEventListener,proximitySensor,SensorManager.SENSOR_DELAY_NORMAL);
    }
    @Override
    protected void onPause(){
        super.onPause();
        sm.unregisterListener(sensorEventListener);
    }

}