package fr.nohas.tp4;

import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleOwner;

public class Utilisation implements DefaultLifecycleObserver {
    public static int numberUsers=0;
    TextView text_view;

    //constructeur
    public Utilisation(TextView tv){
        this.text_view=tv;
    }

    public void nombreUtilisation() {
        numberUsers++;
        this.text_view.setText("OnResume() appelée "+numberUsers+" fois");
    }
    @Override
    public void onResume(@NonNull LifecycleOwner owner) {
        nombreUtilisation();
    }

}
