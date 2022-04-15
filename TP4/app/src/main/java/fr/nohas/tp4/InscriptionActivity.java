package fr.nohas.tp4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
//life cycle owener we need to create a life cycle observer
public class InscriptionActivity extends AppCompatActivity {
    private Button button_validation,button_plannig;
    private EditText edt_nom,edt_prenom,edt_tel,edt_age,edt_mdp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);

        edt_nom=findViewById(R.id.edit_text_Nom);
        edt_prenom=findViewById(R.id.edit_text_prenom);
        edt_tel=findViewById(R.id.edit_text_numTel);
        edt_age=findViewById(R.id.edit_text_age);
        edt_mdp=findViewById(R.id.edit_text_mdp);
        button_validation=findViewById(R.id.btt_valier);
        button_plannig=findViewById(R.id.btt_plannig);


        button_validation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(InscriptionActivity.this,RecapitulatifActivity.class);
                    intent.putExtra("Nom",edt_nom.getText().toString());
                    intent.putExtra("Prenom",edt_prenom.getText().toString());
                    intent.putExtra("Age",edt_age.getText().toString());
                    intent.putExtra("NumTel",edt_tel.getText().toString());
                    intent.putExtra("MotDePasse",edt_mdp.getText().toString());
                startActivity(intent);
            }
        });

        button_plannig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(InscriptionActivity.this, PlanningActivity.class);
                startActivity(intent);
            }
        });


    }
}