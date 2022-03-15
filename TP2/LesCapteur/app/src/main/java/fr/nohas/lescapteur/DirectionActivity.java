package fr.nohas.lescapteur;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;


// on veux affiché haut bas gauche droite
public class DirectionActivity extends AppCompatActivity implements SensorEventListener {
    private SensorManager sm;
    private Sensor sAcc;
    String[] direction ={"None","None"};
    float[] history = new float[2];
    ImageView imageView;
    TextView tv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_direction);


        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        sm=(SensorManager)getSystemService(SENSOR_SERVICE);
        sAcc = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        tv = (TextView)findViewById(R.id.tv_direction);
        imageView=(ImageView)findViewById(R.id.imageView2);

        if(sAcc != null){
            sm.registerListener((SensorEventListener) DirectionActivity.this, sAcc, SensorManager.SENSOR_DELAY_NORMAL);
        }

    }

    protected void onResume(){
        super.onResume();
        sm.registerListener((SensorEventListener) DirectionActivity.this, sAcc, SensorManager.SENSOR_DELAY_NORMAL);
    }
    protected void onPause(){
        super.onPause();
        sm.unregisterListener((SensorEventListener) DirectionActivity.this);
    }


    @Override
    public void onSensorChanged(SensorEvent event) {
       float xChange = history[0] - event.values[0];
       float yChange = history[1] - event.values[1];

       history[0] = event.values[0];
       history[1] = event.values[1];

       if(xChange > 2){
           direction[0] = "GAUCHE";
           imageView.setBackgroundResource(R.drawable.direction);

       }else if(xChange < -2){
           direction[0] = "DROIT";
           imageView.setBackgroundResource(R.drawable.direction_droite);
       }


       if(yChange > 2){
           direction[1] = "BAS";
           imageView.setBackgroundResource(R.drawable.direction_bas);
       }else if(yChange < -2){
            direction[1] = "HAUT";
           imageView.setBackgroundResource(R.drawable.direction_haut);
       }


       String res="Direction :"+direction[0] +","+direction[1];
       tv.setText(res);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}