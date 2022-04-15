package fr.nohas.tp4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class RecapitulatifActivity extends AppCompatActivity {
   private TextView tv_onResume,tv_nom,tv_prenom,tv_age,tv_numTel,tv_mdp;
   private String nom,prenom,age,numTel,motDepasse;
   Intent inscription_intent;

   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recapitulatif);

        tv_nom=findViewById(R.id.tv_nom);
        tv_prenom=findViewById(R.id.tv_prenom);
        tv_age=findViewById(R.id.tv_age);
        tv_numTel=findViewById(R.id.tv_numTel);
        tv_mdp=findViewById(R.id.tv_mdp);
        tv_onResume=findViewById(R.id.tv_nbOnResume);

        //on récupére les valeurs passer à l'intent
        inscription_intent=getIntent();
        if(inscription_intent != null){
            nom=inscription_intent.getStringExtra("Nom");
            prenom=inscription_intent.getStringExtra("Prenom");
            age=inscription_intent.getStringExtra("Age");
            numTel=inscription_intent.getStringExtra("NumTel");
            motDepasse=inscription_intent.getStringExtra("MotDePasse");
        }

        //on injecte les valeurs
        tv_nom.setText("Nom: "+nom);
        tv_prenom.setText("Prenom: "+prenom);
        tv_age.setText("Age: "+age);
        tv_numTel.setText("Numéro du téléphone: "+numTel);
        tv_mdp.setText("Votre mot de passe: "+motDepasse);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getLifecycle().addObserver(new Utilisation(tv_onResume));
    }
}