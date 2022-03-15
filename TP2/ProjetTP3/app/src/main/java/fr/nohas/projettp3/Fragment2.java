package fr.nohas.projettp3;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


//pour juste afficher toutes les données siasies
public class Fragment2 extends Fragment {
    //pour l'exo2 on déclare 2 fichier jon et txt
    private static final String FILE_NAME= "example.txt";
    private static final String FILE_NAMEJSON= "example.json";
    String s1,s2,s3,s4,s5,s6;
    String cb1,cb2,cb3,cb4;

    //déclare tous nos éléments
    TextView textViewNom,textViewPrenom,textViewDateN,textViewNumTel,textViewMail,textSwitchB;
    ListView listeV;
    List<String> listeS= new ArrayList<>(); //liste pour le listeView sert à l'affichage des items
    Button buttonValidation,readButton;
    List<String> listeCheck_box=new ArrayList<>();//sert à stocker tout les valeurs de mes checkbox

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =inflater.inflate(R.layout.fragment_2, container, false);

        textViewNom=(TextView) rootView.findViewById(R.id.Nom);
        textViewPrenom=(TextView) rootView.findViewById(R.id.Prenom);
        textViewDateN=(TextView) rootView.findViewById(R.id.DateN);
        textViewNumTel=(TextView) rootView.findViewById(R.id.NumTel);
        textViewMail=(TextView) rootView.findViewById(R.id.mail);
        textSwitchB=(TextView) rootView.findViewById(R.id.switchB);
        listeV=(ListView) rootView.findViewById(R.id.listView);
        buttonValidation=(Button) rootView.findViewById(R.id.btt_validation);
        readButton=(Button) rootView.findViewById(R.id.btt_lecture);


        //on recupére les données saisie du fragmenet1 à partir du bundle
        Bundle bundle = this.getArguments();
        s1=bundle.getString("LNameKey");
        s2=bundle.getString("FameKey");
        s3=bundle.getString("DateKey");
        s4=bundle.getString("NumTelKey");
        s5=bundle.getString("MailKey");
        s6=bundle.getString("SwitchB");

        textViewNom.setText(s1);
        textViewPrenom.setText(s2);
        textViewDateN.setText(s3);
        textViewNumTel.setText(s4);
        textViewMail.setText(s5);
        textSwitchB.setText(s6);

        cb1=bundle.getString("Musique");
        cb2=bundle.getString("Voyage");
        cb3=bundle.getString("Lecture");
        cb4=bundle.getString("Sport");

        //on ajoute les checkbox dans la liste
        listeCheck_box.add(cb1);listeCheck_box.add(cb2);listeCheck_box.add(cb3);listeCheck_box.add(cb4);

        if(cb1!=null){
            listeS.add(bundle.getString("Musique"));
        }
        if(cb2!=null){
            listeS.add(bundle.getString("Voyage"));
        }
        if(cb3!=null){
            listeS.add(bundle.getString("Lecture"));
        }
        if(cb4!=null){
            listeS.add(bundle.getString("Sport"));
        }
        listeV.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,listeS));

       //enregistrer les données dans une fichier .txt quand on click sur  le button valider
        buttonValidation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FileOutputStream fos1,fos2;

                try {
                    fos1 = getActivity().openFileOutput(FILE_NAME, MODE_PRIVATE);
                    fos1.write(("Nom: " + s1 + "\n").getBytes());
                    fos1.write(("Prénom: " + s2 + "\n").getBytes());
                    fos1.write(("Date de Naissance: " + s3 + "\n").getBytes());
                    fos1.write(("Numéro de Tél: " + s4 + "\n").getBytes());
                    fos1.write(("Adresse mail: " + s5 + "\n").getBytes());
                    fos1.write(("Centre d'intérêt: \n").getBytes());
                    for (String s : listeCheck_box) {
                        fos1.write((s + "\n").getBytes());
                    }
                    fos1.write(("SwitchButton: " + s6 + "\n").getBytes());
                    fos1.close();

                    writeJsonFile();
                    Toast.makeText(getActivity(), "Vous avez bien enregister 2 fichier, Json et txt", Toast.LENGTH_SHORT).show();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        readButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                readFile();
               // Intent intent= new Intent(this,MyIntentService.class);
               // startService(intent);
            }
        });

        if(savedInstanceState != null){
            //esk on utilise saveInstanceState ??
        }
        return  rootView;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

   private void writeJsonFile(){
        JSONObject obj = new JSONObject();
        try {
            obj.put("LNameKey",s1);
            obj.put("FameKey",s2);
            obj.put("DateKey",s3);
            obj.put("NumTelKey",s4);
            obj.put("MailKey",s5);
            obj.put("SwitchB",s6);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        String userString = obj.toString();
       File file = new File(getActivity().getFilesDir(), FILE_NAME);
       FileWriter fileWriter = null;
       try {
           fileWriter = new FileWriter(file);
       } catch (IOException e) {
           e.printStackTrace();
       }
       BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
       try {
           bufferedWriter.write(userString);
       } catch (IOException e) {
           e.printStackTrace();
       }
       try {
           bufferedWriter.close();
       } catch (IOException e) {
           e.printStackTrace();
       }
    }

    private void readFile(){
        try {
            FileInputStream fileInputStream = getActivity().openFileInput(FILE_NAME);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);

            BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
            StringBuffer stringBuffer=new StringBuffer();
            String lines;

            while((lines=bufferedReader.readLine())!=null){
                stringBuffer.append(lines+"\n");

            }
            Toast.makeText(getActivity(), stringBuffer, Toast.LENGTH_LONG).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}