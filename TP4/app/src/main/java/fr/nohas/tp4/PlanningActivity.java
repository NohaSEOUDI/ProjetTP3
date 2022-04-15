package fr.nohas.tp4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.TextView;
//l'activité du planning
public class PlanningActivity extends AppCompatActivity {
    private TextView tv1,tv2,tv3,tv4;
    private PlanningViewModel creneaux_8h,creneaux_10h,creneaux_14h,creneaux_16h;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planning);

        tv1=findViewById(R.id.tv_c1);
        tv2=findViewById(R.id.tv_c2);
        tv3=findViewById(R.id.tv_c3);
        tv4=findViewById(R.id.tv_c4);

        //pour avoir le viewModel
        creneaux_8h = new ViewModelProvider(this).get(PlanningViewModel.class);
        creneaux_10h = new ViewModelProvider(this).get(PlanningViewModel.class);
        creneaux_14h = new ViewModelProvider(this).get(PlanningViewModel.class);
        creneaux_16h = new ViewModelProvider(this).get(PlanningViewModel.class);

        //on injecte les valeurs aux TextView
        tv1.append((CharSequence)creneaux_8h.getLiveData_8h().getValue());
        tv2.append((CharSequence)creneaux_10h.getLiveData_10h().getValue());
        tv3.append((CharSequence)creneaux_14h.getLiveData_14h().getValue());
        tv4.append((CharSequence)creneaux_16h.getLiveData_16h().getValue());

    }

}