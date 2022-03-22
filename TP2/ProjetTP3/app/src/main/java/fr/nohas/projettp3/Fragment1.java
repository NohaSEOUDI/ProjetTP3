package fr.nohas.projettp3;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;


import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.URLUtil;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.Buffer;
import java.util.HashMap;

//fragement 1 saisie
public class Fragment1 extends Fragment {

    private EditText editText_nom;
    private EditText editText_prenom;
    private EditText editText_adresseMail;
    private EditText editText_numTel;
    private EditText editText_dateNaissance;

    //les checks box
    private CheckBox checkBox_Voyage;
    private CheckBox checkBox_Lecture;
    private CheckBox checkBox_Sport;
    private CheckBox checkBox_Musique;
    private Switch switchButton;
    private Fragment fragment2;

    private String strNom,strPrenom,strMail,strNumTel,strDateN;
    public static final String link="https://whispyy.github.io/TP-Services/[PDF]TP-Services.pdf";

    //cette fonction ce charge pour injecter les ressources de layout(XML) au fragment1.java
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //1) Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_1, container,false);

        //2)
        editText_nom=(EditText)rootView.findViewById(R.id.et_nom);
        editText_prenom=(EditText)rootView.findViewById(R.id.et_prenom);
        editText_adresseMail=(EditText)rootView.findViewById(R.id.et_mail);
        editText_numTel=(EditText)rootView.findViewById(R.id.et_numTel);
        editText_dateNaissance = (EditText)rootView.findViewById(R.id.et_dnaissance);

        checkBox_Voyage=(CheckBox)rootView.findViewById(R.id.cb_voyage);
        checkBox_Lecture=(CheckBox)rootView.findViewById(R.id.cb_lecture);
        checkBox_Sport=(CheckBox)rootView.findViewById(R.id.cb_sport);
        checkBox_Musique=(CheckBox)rootView.findViewById(R.id.cb_musique);
        switchButton=(Switch)rootView.findViewById(R.id.switch1); // fait rien pour l'insatnt
        Button button_soumettre = (Button) rootView.findViewById(R.id.btt_soumettre);
        Button button_telechargement = (Button) rootView.findViewById(R.id.btt_telecharger);

        switchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
                if(checked) {
                    //do anything
                    switchButton.setText("Données synchronisée");
                }else {
                    ///do another thing
                    switchButton.setText("Données non synchronisées");

                }
            }
        });

        button_telechargement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                save();
              /* String st = "https://www.metaweather.com/api/location/44";
               AsyncHTTP task= new AsyncHTTP(getActivity());
               task.execute(st);//"https://newsapi.org/v2/top-headlines?country=fr&category=health&apiKey=4abe9d26b7f64ca3adcca4d8a0935f22");
                System.out.println("Affichage"+task.doInBackground(st));*/
            }
        });

        button_soumettre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //on doit passer à la 2eme fragement
                Bundle bundle = new Bundle();

                bundle.putString("LNameKey",editText_nom.getText().toString());
                bundle.putString("FameKey",editText_prenom.getText().toString());
                bundle.putString("MailKey",editText_adresseMail.getText().toString());
                bundle.putString("NumTelKey",editText_numTel.getText().toString());
                bundle.putString("DateKey",editText_dateNaissance.getText().toString());

                //get the box cheked

                if(checkBox_Musique.isChecked()){
                    bundle.putString("Musique",checkBox_Musique.getText().toString());
                }
                if(checkBox_Voyage.isChecked()){
                    bundle.putString("Voyage",checkBox_Voyage.getText().toString());
                }
                if(checkBox_Lecture.isChecked()){
                    bundle.putString("Lecture",checkBox_Lecture.getText().toString());
                }
                if(checkBox_Sport.isChecked()){
                    bundle.putString("Sport",checkBox_Sport.getText().toString());
                }
                if(switchButton.isChecked()){
                    bundle.putString("SwitchB",switchButton.getText().toString());
                }

                fragment2 = new Fragment2();
                fragment2.setArguments(bundle);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,fragment2).addToBackStack(null).commit(); // pour retourner au fragement précédent :o

            }
        });
        if(savedInstanceState != null){
            strNom=savedInstanceState.getString("LNameKey");
            fragment2=getParentFragmentManager().getFragment(savedInstanceState, "mySecondFragment");
        }
        return rootView;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    public void save(){
        if ( ContextCompat.checkSelfPermission(getActivity(),android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            //Log.e("Permission error","You have permission");
            DownloadManager.Request request = new DownloadManager.Request(Uri.parse(link));
            String title = URLUtil.guessFileName(link, null, null);
            request.setTitle(title);
            request.setDescription("En cours de téléchargement...");
            String cookie = android.webkit.CookieManager.getInstance().getCookie(link);
            request.addRequestHeader("cookie", cookie);
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
            request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, title);

            DownloadManager downloadManager = (DownloadManager) getContext().getSystemService(Context.DOWNLOAD_SERVICE);
            downloadManager.enqueue(request);
            Toast.makeText(getActivity(), "Téléchargement terminé !", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(getActivity(), "Vous n'avez pas la permission pour télécharger le fichier", Toast.LENGTH_SHORT).show();
            Log.e("Permission error", "You dont have permission");
        }
    }


}

