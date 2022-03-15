package fr.nohas.lescapteur;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;


//EXO 1
public class ListeCapteurActivity extends AppCompatActivity {
    ListView listView1=null;
    List<Sensor> sensorList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_capteur);

        //pour avoir une instance du service de gestion des capteurs
        SensorManager sm=(SensorManager)getSystemService(SENSOR_SERVICE);

        //Pour lister les capteurs présents sur l'appareil
        sensorList = sm.getSensorList(Sensor.TYPE_ALL);

        listView1 = (ListView)findViewById(R.id.lv);

        //pour afficher la liste de capteurs dans ma listeView
        listView1.setAdapter(new ArrayAdapter<Sensor>(this, android.R.layout.simple_list_item_1,sensorList));
    }
}