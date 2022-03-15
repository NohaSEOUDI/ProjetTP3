package fr.nohas.horairetrain

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
//1er activité
class MainActivity : AppCompatActivity() {
    private lateinit var editText1: EditText
    private lateinit var editText2: EditText
    private lateinit var btt_validation: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editText1 = findViewById(R.id.et_vd)
        editText2 = findViewById(R.id.et_vA)

        btt_validation = findViewById(R.id.btt_submition)

        btt_validation.setOnClickListener {
            val eTet1 = editText1.text.toString().trim()
            val eTet2 = editText2.text.toString().trim()
            if (eTet1.isEmpty()) {
                editText1.error = "Aucun train n'est dispo pour le moment"
                return@setOnClickListener
            } else if (eTet2.isEmpty()) {
                editText2.error = "Aucun train n'est dispo pour le moment"
                return@setOnClickListener
            } else {
                val intent = Intent(this, DispalySchedulesActivity::class.java)
                intent.putExtra("Ville Départ",editText1.getText().toString())
                intent.putExtra("Ville Arrivée",editText2.getText().toString())
                startActivity(intent)
            }

        }
    }
}
