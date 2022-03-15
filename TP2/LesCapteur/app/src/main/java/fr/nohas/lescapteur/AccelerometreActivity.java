package fr.nohas.lescapteur;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

public class AccelerometreActivity extends AppCompatActivity {
    //define the value
    private SensorManager sm;
    private Sensor mAcc;

    private ProgressBar prog_shakeMeter,prog_shakeMeterX,prog_shakeMeterY,prog_shakeMeterZ;
    private TextView txt_acceleration; // acc
    private TextView txt_prevAccel; // prev
    private TextView txt_currectAccel; // current
    private double accelerationCurrentValue;
    private double accelarationPreviousValue;

    private SensorEventListener sensorEventListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];

            accelerationCurrentValue = Math.sqrt(x*x + y*y + z*z);

            double changeInAcceleration = Math.abs(accelerationCurrentValue - accelarationPreviousValue);
            accelarationPreviousValue = accelerationCurrentValue;

            //update text Views
            txt_currectAccel.setText("current ="+(int)accelerationCurrentValue);
            txt_prevAccel.setText("Prev ="+(int) accelarationPreviousValue);
            txt_acceleration.setText("Acceleration change ="+ (int)changeInAcceleration);

            prog_shakeMeter.setProgress((int)changeInAcceleration);
            prog_shakeMeterX.setProgress((int)x);
            prog_shakeMeterY.setProgress((int)y);
            prog_shakeMeterZ.setProgress((int)z);



            //pour les couleurs de background
            if(changeInAcceleration > 14){
                txt_acceleration.setBackgroundColor(Color.RED);
            }else if(changeInAcceleration > 5){
                txt_acceleration.setBackgroundColor(Color.parseColor("#fcad03"));
            }else if( changeInAcceleration > 2){
                txt_acceleration.setBackgroundColor(Color.YELLOW);
            }else {
                txt_acceleration.setBackgroundColor(getResources().getColor(com.google.android.material.R.color.design_default_color_background)); //if no acc at all
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accelerometre);

        //les 4 bare de progresssions
        prog_shakeMeter= (ProgressBar) findViewById(R.id.progressBarAcc);
        prog_shakeMeterX= (ProgressBar) findViewById(R.id.progressBarX);
        prog_shakeMeterY= (ProgressBar) findViewById(R.id.progressBarY);
        prog_shakeMeterZ= (ProgressBar) findViewById(R.id.progressBarZ);

        txt_acceleration=(TextView) findViewById(R.id.tv1);
        txt_prevAccel=(TextView) findViewById(R.id.tv2);
        txt_currectAccel=(TextView) findViewById(R.id.tv3);



        sm=(SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mAcc=sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }

    protected void onResume(){
        super.onResume();
        sm.registerListener(sensorEventListener,mAcc,SensorManager.SENSOR_DELAY_NORMAL);
    }
    protected void onPause(){
        super.onPause();
        sm.unregisterListener(sensorEventListener);
    }

}
