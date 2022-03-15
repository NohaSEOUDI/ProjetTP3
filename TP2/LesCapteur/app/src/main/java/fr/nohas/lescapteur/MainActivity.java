package fr.nohas.lescapteur;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

//Page d'acceuil (pour l'affichage de tous les buton)
public class MainActivity extends AppCompatActivity {

    Button butt_affichage_tous_les_capteurs=null;
    Button butt_estDispo=null;
    Button butt_accelerometre=null;
    Button butt_direction=null;
    Button butt_torsh=null;
    Button butt_proximity=null;
    Button butt_geolo=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        butt_affichage_tous_les_capteurs= (Button)findViewById(R.id.button1);
        butt_estDispo= (Button)findViewById(R.id.button2);
        butt_accelerometre = (Button)findViewById(R.id.button3);
        butt_direction = (Button)findViewById(R.id.button4);
        butt_torsh = (Button)findViewById(R.id.button5);
        butt_proximity = (Button)findViewById(R.id.button6);
        butt_geolo = (Button)findViewById(R.id.button7);


        //button 1 Affichage Liste de capteurs
        butt_affichage_tous_les_capteurs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intention1 = new Intent(MainActivity.this, ListeCapteurActivity.class);
                startActivity(intention1);
            }
        });

        //button 2 Détection de présence/absence de capteurs
        butt_estDispo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intention2 = new Intent(MainActivity.this, DetectionCapteurActivity.class);
                startActivity(intention2);
            }
        });

        //button 3 Accéléromètre
        butt_accelerometre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intention3 = new Intent(MainActivity.this, AccelerometreActivity.class);
                startActivity(intention3);
            }
        });

        //button 4 direction
        butt_direction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intention3 = new Intent(MainActivity.this, DirectionActivity.class);
                startActivity(intention3);
            }
        });



        //button 5 secouer pour allumer le torsh
        butt_torsh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intention4 = new Intent(MainActivity.this, SecouerActivity.class);
                startActivity(intention4);
            }
        });

        //button 6 Proximity
        butt_proximity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intention5 = new Intent(MainActivity.this, ProximiteActivity.class);
                startActivity(intention5);
            }
        });

        //button 7 Géolocalisation
        butt_geolo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intention6 = new Intent(MainActivity.this,GeolocalisationActivity.class);
                startActivity(intention6);
            }
        });
    }
}