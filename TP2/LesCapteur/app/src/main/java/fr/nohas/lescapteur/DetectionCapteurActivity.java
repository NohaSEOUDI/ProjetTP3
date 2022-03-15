package fr.nohas.lescapteur;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DetectionCapteurActivity extends AppCompatActivity {
    SensorManager sm;
    ListView listView1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detection_capteur);

        listView1= (ListView)findViewById(R.id.lvUnspported);

        sm=(SensorManager)getSystemService(SENSOR_SERVICE);
        List<Sensor> sensorList = sm.getSensorList(Sensor.TYPE_ALL);
        List<String> sensorListDisplay= new ArrayList<String>(){}; //la liste qui sera affiché

        //tous les capteurs indisponibles
        for(Sensor s :sensorList){
            if (sm.getDefaultSensor(s.getType())==null) {
                sensorListDisplay.add(s.getName()+" de version "+s.getVersion());
            }
        }
        //détection s'il y a un Thermomètre ou un Baromètre
        if(sm.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE) == null && sm.getDefaultSensor(Sensor.TYPE_PRESSURE) == null){
            sensorListDisplay.add("Thermomètre TYPE_AMBIENT_TEMPERATURE");
            sensorListDisplay.add("Baromètre TYPE_PRESSURE ");
        }
        listView1.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,sensorListDisplay));

    }
}