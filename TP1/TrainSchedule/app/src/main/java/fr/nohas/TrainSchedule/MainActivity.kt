package fr.nohas.TrainSchedule

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    private lateinit var  editText1 :EditText
    private lateinit var editText2: EditText
    private lateinit var btt_validation : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editText1 = findViewById(R.id.et_vd)
        editText2 = findViewById(R.id.et_vA)

        btt_validation= findViewById(R.id.btt_submition)

        btt_validation.setOnClickListener{
            val eTet1= editText1.text.toString().trim()
            val eTet2= editText2.text.toString().trim()
            if (eTet1.isEmpty() || eTet1!="Montpellier" ){
                editText1.error="Erreur"
                return@setOnClickListener
            }else if(eTet2.isEmpty() || eTet2!="Paris") {
                editText2.error="Erreur"
                return@setOnClickListener
            }else{
                val intent =Intent(this,DispalySchedules::class.java)
                startActivity(intent)
            }

        }
    }
}