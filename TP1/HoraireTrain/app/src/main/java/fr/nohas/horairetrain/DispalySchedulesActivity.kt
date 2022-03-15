package fr.nohas.horairetrain

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ListView
import androidx.recyclerview.widget.RecyclerView
//2eme activité
class DispalySchedulesActivity : AppCompatActivity() {
    private lateinit var listViewHoraire: ListView // création de variable de type déclarer dans le xml

    override fun onCreate(savedInstanceState: Bundle?) {
 	super.onCreate(savedInstanceState)
 	setContentView(R.layout.activity_dispaly_schedules)
 	
 	    val vd:String = intent.getStringExtra("Ville Départ").toString()
        val va:String = intent.getStringExtra("Ville Arrivée").toString()


        listViewHoraire = findViewById(R.id.lvHoraire) // on le récupére via l'id


    }
}
