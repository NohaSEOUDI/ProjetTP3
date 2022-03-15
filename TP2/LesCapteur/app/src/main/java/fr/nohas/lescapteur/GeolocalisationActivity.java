package fr.nohas.lescapteur;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class GeolocalisationActivity extends AppCompatActivity {
    private FusedLocationProviderClient fusedLocationClient;
    private TextView myTextViewLongitude, myTextViewLatitude, myTextViewAddress, myTextViewContryName, myTextViewLocality;
    private Button myButtonValidation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geolocalisation);

        //pour les attributs dans le xml
        myTextViewLongitude = (TextView) findViewById(R.id.textViewLongitude);
        myTextViewLatitude = (TextView) findViewById(R.id.textViewLatitude);
        myTextViewAddress = (TextView) findViewById(R.id.textViewAddress);
        myTextViewContryName = (TextView) findViewById(R.id.textViewContryName);
        myTextViewLocality = (TextView) findViewById(R.id.textViewLocality);
        myButtonValidation = (Button) findViewById(R.id.bttV);


        //initialisation fusedLocationProviderClient
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        myButtonValidation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //check permission
                if (ActivityCompat.checkSelfPermission(GeolocalisationActivity.this
                        , Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    //when permission granted

                    fusedLocationClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
                        @Override
                        public void onComplete(@NonNull Task<Location> task) {
                            //on initialise la  location
                            Location location = task.getResult();
                            if (location != null) {
                                try {
                                    //initialize geoCoder
                                    Geocoder geocoder = new Geocoder(GeolocalisationActivity.this, Locale.getDefault());
                                    Log.d(this.getClass().getName(), String.valueOf(geocoder));
                                    //initialize address list
                                    List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                                    //set latitude on TextView
                                    myTextViewLatitude.setText("Latitude: "+addresses.get(0).getLatitude());
                                    myTextViewLongitude.setText("Longtitude: "+addresses.get(0).getLongitude());
                                    myTextViewContryName.setText("Pays: "+addresses.get(0).getCountryName());
                                    myTextViewAddress.setText("Adresse: "+addresses.get(0).getAddressLine(0));
                                    myTextViewLocality.setText("Ville: "+addresses.get(0).getLocality());
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    });
                    Toast.makeText(GeolocalisationActivity.this, "Check your GPS !", Toast.LENGTH_SHORT).show();
                } else {
                    //when permission denied
                    ActivityCompat.requestPermissions(GeolocalisationActivity.this,
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
                    Toast.makeText(GeolocalisationActivity.this, "!! pb !!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}